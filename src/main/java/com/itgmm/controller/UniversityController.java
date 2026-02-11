package com.itgmm.controller;

import com.itgmm.pojo.Result;
import com.itgmm.pojo.University;
import com.itgmm.pojo.UserFavoriteUniversity;
import com.itgmm.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/universities")
@RestController
/**
 * 控制器类，用于处理与院校相关的HTTP请求。
 */
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    /**
     * 查询所有院校列表。
     *
     * @return 返回包含所有院校信息的结果对象。
     */
    @GetMapping()
    public Result list() {
        List<University> universityList = universityService.list();
        return Result.success(universityList);
    }

    /**
     * 收藏或取消收藏指定院校。
     *
     * @param universityId  院校ID，用于标识要操作的院校。
     * @param currentUserId 当前用户ID，从请求头中获取。
     * @return 返回操作结果。
     */
    @PostMapping("/toggle")
    public Result insert(@RequestParam Integer universityId,
                         @RequestHeader("X-User-Id") Integer currentUserId) {
        universityService.toggleFavorite(currentUserId, universityId);
        return Result.success();
    }

    /**
     * 检查当前用户是否已收藏指定院校。
     *
     * @param universityId  院校ID，用于标识要检查的院校。
     * @param currentUserId 当前用户ID，从请求头中获取。
     * @return 返回布尔值，表示是否已收藏。
     */
    @GetMapping("/check")
    public Result checkIfFavorited(
            @RequestParam Integer universityId,
            @RequestHeader("X-User-Id") Integer currentUserId) {
        boolean favorited = universityService.isFavorited(currentUserId, universityId);
        return Result.success(favorited);
    }

    /**
     * 获取当前用户收藏的所有院校ID列表。
     *
     * @param currentUserId 当前用户ID，从请求头中获取。
     * @return 返回包含收藏院校ID的列表。
     */
    @GetMapping("/university-ids")
    public Result getFavoriteUniversityIds(@RequestHeader("X-User-Id") Integer currentUserId) {
        List<Integer> ids = universityService.getFavoriteUniversityIds(currentUserId);
        return Result.success(ids);
    }

    /**
     * 获取当前用户收藏的院校详细信息列表。
     *
     * @param currentUserId 当前用户ID，从请求头中获取。
     * @return 返回包含收藏院校详细信息的列表。
     */
    @GetMapping("/list")
    public Result getFavoriteList(@RequestHeader("X-User-Id") Integer currentUserId) {
        List<UserFavoriteUniversity> list = universityService.getFavoriteList(currentUserId);
        return Result.success(list);
    }

    /**
     * 获取指定院校被收藏的次数。
     *
     * @param universityId 院校ID，用于标识要查询的院校。
     * @return 返回该院校被收藏的次数。
     */
    @GetMapping("/count")
    public Result getFavoriteCount(@RequestParam Integer universityId) {
        int count = universityService.getFavoriteCount(universityId);
        return Result.success(count);
    }
}


