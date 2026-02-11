package com.itgmm.controller;

import com.itgmm.pojo.Competition;
import com.itgmm.pojo.CompetitionRule;
import com.itgmm.pojo.Result;
import com.itgmm.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/competitions")
@RestController
public class CompetitionController {

    @Autowired
    private CompetitionService CompetitionService;

    @GetMapping()
    public Result list(String name, String type, String status, String level){
        List<Competition> competitionList = CompetitionService.list(name, type, status, level);
        return Result.success(competitionList);
    }

    //根据竞赛id查询对应的竞赛的参赛要求和规则说明
    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id){
        List<CompetitionRule> competitionRuleList = CompetitionService.getById(id);
        return Result.success(competitionRuleList);
    }
}
