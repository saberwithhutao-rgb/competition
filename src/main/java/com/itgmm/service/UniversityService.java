package com.itgmm.service;

import com.itgmm.pojo.University;
import com.itgmm.pojo.UserFavoriteUniversity;

import java.util.List;

public interface UniversityService {
    List<University> list();

    void toggleFavorite(Integer userId, Integer universityId);

    void addFavorite(Integer userId, Integer universityId);

    void removeFavorite(Integer userId, Integer universityId);

    boolean isFavorited(Integer userId, Integer universityId);

    List<Integer> getFavoriteUniversityIds(Integer userId);

    List<UserFavoriteUniversity> getFavoriteList(Integer userId);

    int getFavoriteCount(Integer universityId);
}
