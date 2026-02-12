package com.itgmm.controller;

import com.itgmm.pojo.Result;
import com.itgmm.pojo.University;
import com.itgmm.pojo.UserFavoriteUniversity;
import com.itgmm.service.UniversityService;
import com.itgmm.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/universities")
@RestController
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 查询所有院校列表（公开接口，不需要登录）
     */
    @GetMapping()
    public Result list() {
        List<University> universityList = universityService.list();
        return Result.success(universityList);
    }

    /**
     * 收藏或取消收藏指定院校
     */
    @PostMapping("/toggle")
    public Result toggleFavorite(
            @RequestBody Map<String, Object> request,
            @RequestHeader("Authorization") String authHeader) {

        System.out.println("=== 收到收藏请求 ===");
        System.out.println("请求体: " + request);

        // 1. 验证token
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("❌ 未提供token");
            return Result.error("未登录");
        }

        // 2. 从token获取用户ID
        String token = authHeader.substring(7);
        Long userId;
        try {
            userId = jwtUtil.getUserIdFromToken(token);
            System.out.println("✅ 解析用户ID: " + userId);
        } catch (Exception e) {
            System.out.println("❌ token解析失败: " + e.getMessage());
            return Result.error("登录已过期，请重新登录");
        }

        // 3. 获取院校ID - 简化版本
        Integer universityId = null;
        Object idObj = request.get("universityId");

        if (idObj == null) {
            System.out.println("❌ 院校ID为空");
            return Result.error("院校ID不能为空");
        }

        // 🔴 关键修复：直接转换为Number，再取intValue
        if (idObj instanceof Number) {
            universityId = ((Number) idObj).intValue();
        } else {
            try {
                universityId = Integer.parseInt(idObj.toString());
            } catch (NumberFormatException e) {
                System.out.println("❌ 院校ID格式错误: " + idObj);
                return Result.error("院校ID格式错误");
            }
        }

        System.out.println("✅ 院校ID: " + universityId);

        // 4. 执行操作
        try {
            universityService.toggleFavorite(userId.intValue(), universityId);
            System.out.println("✅ 操作成功");
            return Result.success();
        } catch (Exception e) {
            System.out.println("❌ 操作失败: " + e.getMessage());
            e.printStackTrace();
            return Result.error("操作失败：" + e.getMessage());
        }
    }

    /**
     * 检查当前用户是否已收藏指定院校
     */
    @GetMapping("/check")
    public Result checkIfFavorited(
            @RequestParam Integer universityId,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        // 未登录用户默认返回false
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Result.success(false);
        }

        // 从token获取用户ID
        String token = authHeader.substring(7);
        try {
            Long userId = jwtUtil.getUserIdFromToken(token);
            boolean favorited = universityService.isFavorited(userId.intValue(), universityId);
            return Result.success(favorited);
        } catch (Exception e) {
            return Result.success(false);
        }
    }

    /**
     * 获取当前用户收藏的所有院校ID列表
     */
    @GetMapping("/university-ids")
    public Result getFavoriteUniversityIds(
            @RequestHeader("Authorization") String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Result.error("未登录");
        }

        String token = authHeader.substring(7);
        try {
            Long userId = jwtUtil.getUserIdFromToken(token);
            List<Integer> ids = universityService.getFavoriteUniversityIds(userId.intValue());
            return Result.success(ids);
        } catch (Exception e) {
            return Result.error("登录已过期，请重新登录");
        }
    }

    /**
     * 获取当前用户收藏的院校详细信息列表
     */
    @GetMapping("/list")
    public Result getFavoriteList(
            @RequestHeader("Authorization") String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Result.error("未登录");
        }

        String token = authHeader.substring(7);
        try {
            Long userId = jwtUtil.getUserIdFromToken(token);
            List<UserFavoriteUniversity> list = universityService.getFavoriteList(userId.intValue());
            return Result.success(list);
        } catch (Exception e) {
            return Result.error("登录已过期，请重新登录");
        }
    }

    /**
     * 获取指定院校被收藏的次数（公开接口，不需要登录）
     */
    @GetMapping("/count")
    public Result getFavoriteCount(@RequestParam Integer universityId) {
        int count = universityService.getFavoriteCount(universityId);
        return Result.success(count);
    }
}