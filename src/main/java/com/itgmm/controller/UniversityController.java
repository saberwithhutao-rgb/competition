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

        // 3. 获取院校ID
        Integer universityId = null;
        Object idObj = request.get("universityId");

        if (idObj == null) {
            System.out.println("❌ 院校ID为空");
            return Result.error("院校ID不能为空");
        }

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

        // 4. 执行操作 - 添加try-catch和详细日志
        try {
            System.out.println("🔍 开始调用service.toggleFavorite, userId: " + userId.intValue() + ", universityId: " + universityId);

            // 检查当前状态
            boolean currentStatus = universityService.isFavorited(userId.intValue(), universityId);
            System.out.println("📊 当前收藏状态: " + currentStatus);

            // 执行切换
            universityService.toggleFavorite(userId.intValue(), universityId);
            System.out.println("✅ 操作成功");

            // 检查切换后的状态
            boolean newStatus = universityService.isFavorited(userId.intValue(), universityId);
            System.out.println("📊 切换后状态: " + newStatus);

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

        System.out.println("=================================");
        System.out.println("【/check】接口被调用");
        System.out.println("收到参数 - universityId: " + universityId);
        System.out.println("收到参数 - authHeader: " + (authHeader != null ? "存在" : "不存在"));

        if (authHeader == null) {
            System.out.println("❌ authHeader为空");
            return Result.success(false);
        }

        if (!authHeader.startsWith("Bearer ")) {
            System.out.println("❌ authHeader格式错误: " + authHeader);
            return Result.success(false);
        }

        String token = authHeader.substring(7);
        System.out.println("提取token: " + token.substring(0, Math.min(20, token.length())) + "...");

        try {
            Long userId = jwtUtil.getUserIdFromToken(token);
            System.out.println("✅ token解析成功 - userId: " + userId);

            System.out.println("调用service.isFavorited - userId: " + userId + ", universityId: " + universityId);
            boolean favorited = universityService.isFavorited(userId.intValue(), universityId);
            System.out.println("✅ isFavorited返回: " + favorited);

            return Result.success(favorited);
        } catch (Exception e) {
            System.out.println("❌ 执行失败: " + e.getMessage());
            e.printStackTrace();
            return Result.error("操作失败: " + e.getMessage());
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