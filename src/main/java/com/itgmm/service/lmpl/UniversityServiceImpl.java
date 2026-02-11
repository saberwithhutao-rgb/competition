package com.itgmm.service.lmpl;

import com.itgmm.mapper.UniversityMapper;
import com.itgmm.pojo.University;
import com.itgmm.pojo.UserFavoriteUniversity;
import com.itgmm.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UniversityServiceImpl implements UniversityService {

    @Autowired
    private UniversityMapper universityMapper;

    /**
     * 获取所有大学列表。
     *
     * @return 包含所有大学信息的列表。
     */
    @Override
    public List<University> list() {
        return universityMapper.list();
    }

    /**
     * 切换用户对指定大学的收藏状态。
     * 如果已收藏则取消收藏，否则添加收藏。
     *
     * @param userId       用户ID，不能为空且必须大于0。
     * @param universityId 大学ID，不能为空且必须大于0。
     */
    @Override
    @Transactional
    public void toggleFavorite(Integer userId, Integer universityId) {
        validate(userId, universityId);
        if (isFavorited(userId, universityId)) {
            removeFavorite(userId, universityId);
        } else {
            addFavorite(userId, universityId);
        }
    }

    /**
     * 添加用户对指定大学的收藏。
     *
     * @param userId       用户ID，不能为空且必须大于0。
     * @param universityId 大学ID，不能为空且必须大于0。
     */
    @Override
    @Transactional
    public void addFavorite(Integer userId, Integer universityId) {
        validate(userId, universityId);
        universityMapper.insert(userId, universityId);
    }

    /**
     * 移除用户对指定大学的收藏。
     *
     * @param userId       用户ID，不能为空且必须大于0。
     * @param universityId 大学ID，不能为空且必须大于0。
     */
    @Override
    @Transactional
    public void removeFavorite(Integer userId, Integer universityId) {
        validate(userId, universityId);
        universityMapper.deleteByUserIdAndUniversityId(userId, universityId);
    }

    /**
     * 检查用户是否已收藏指定大学。
     *
     * @param userId       用户ID，不能为空且必须大于0。
     * @param universityId 大学ID，不能为空且必须大于0。
     * @return 如果已收藏返回true，否则返回false。
     */
    @Override
    public boolean isFavorited(Integer userId, Integer universityId) {
        validate(userId, universityId);
        return universityMapper.countByUserIdAndUniversityId(userId, universityId) > 0;
    }

    /**
     * 获取用户收藏的所有大学ID列表。
     *
     * @param userId 用户ID，不能为空且必须大于0。
     * @return 包含用户收藏大学ID的列表。
     * @throws IllegalArgumentException 当用户ID无效时抛出异常。
     */
    @Override
    public List<Integer> getFavoriteUniversityIds(Integer userId) {
        if (userId == null || userId <= 0) throw new IllegalArgumentException("用户ID无效");
        return universityMapper.selectUniversityIdsByUserId(userId);
    }

    /**
     * 获取用户收藏的所有大学详细信息列表。
     *
     * @param userId 用户ID，不能为空且必须大于0。
     * @return 包含用户收藏大学详细信息的列表。
     * @throws IllegalArgumentException 当用户ID无效时抛出异常。
     */
    @Override
    public List<UserFavoriteUniversity> getFavoriteList(Integer userId) {
        if (userId == null || userId <= 0) throw new IllegalArgumentException("用户ID无效");
        return universityMapper.selectAllByUserId(userId);
    }

    /**
     * 获取指定大学被收藏的次数。
     *
     * @param universityId 大学ID，不能为空且必须大于0。
     * @return 该大学被收藏的次数。
     * @throws IllegalArgumentException 当大学ID无效时抛出异常。
     */
    @Override
    public int getFavoriteCount(Integer universityId) {
        if (universityId == null || universityId <= 0) throw new IllegalArgumentException("高校ID无效");
        return universityMapper.countByUniversityId(universityId);
    }

    /**
     * 验证用户ID和大学ID的有效性。
     *
     * @param userId       用户ID，不能为空且必须大于0。
     * @param universityId 大学ID，不能为空且必须大于0。
     * @throws IllegalArgumentException 当任一参数无效时抛出异常。
     */
    private void validate(Integer userId, Integer universityId) {
        if (userId == null || userId <= 0) throw new IllegalArgumentException("用户ID无效");
        if (universityId == null || universityId <= 0) throw new IllegalArgumentException("高校ID无效");
    }

}
