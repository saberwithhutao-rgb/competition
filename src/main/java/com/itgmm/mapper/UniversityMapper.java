package com.itgmm.mapper;

import com.itgmm.pojo.University;
import com.itgmm.pojo.UserFavoriteUniversity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper

public interface UniversityMapper {
    List<University> list();

    //收藏
    void insert(@Param("userId") Integer userId, @Param("universityId")  Integer universityId);

    //取消收藏
    void deleteByUserIdAndUniversityId(@Param("userId") Integer userId, @Param("universityId") Integer universityId);

    //根据用户id查询收藏的院校id
    List<Integer> selectUniversityIdsByUserId(Integer userId);

    //根据用户id查询
    List<UserFavoriteUniversity> selectAllByUserId(Integer userId);

    //根据用户id和院校id查询用户自己的收藏人数
    Integer countByUserIdAndUniversityId(@Param("userId") Integer userId, @Param("universityId") Integer universityId);

    //根据院校id查询收藏人数
    Integer countByUniversityId(Integer universityId);
}
