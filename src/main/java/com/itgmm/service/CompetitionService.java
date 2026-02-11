package com.itgmm.service;

import com.itgmm.pojo.Competition;
import com.itgmm.pojo.CompetitionRule;

import java.time.LocalDate;
import java.util.List;

public interface CompetitionService {

    List<Competition> list(String name, String type, String status, String level);

    List<CompetitionRule> getById(Integer id);
}
