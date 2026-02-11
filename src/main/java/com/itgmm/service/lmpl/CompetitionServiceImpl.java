package com.itgmm.service.lmpl;

import com.itgmm.mapper.CompetitionMapper;
import com.itgmm.pojo.Competition;
import com.itgmm.pojo.CompetitionRule;
import com.itgmm.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CompetitionServiceImpl implements CompetitionService {

    @Autowired
    private CompetitionMapper competitionMapper;

    @Override
    public List<Competition> list(String name, String type, String status, String level) {
        return competitionMapper.search(name, type, status, level);
    }

    @Override
    public List<CompetitionRule> getById(Integer id) {
        return competitionMapper.getById(id);
    }
}
