package com.itgmm.mapper;

import com.itgmm.pojo.Competition;
import com.itgmm.pojo.CompetitionRule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface CompetitionMapper {

    List<Competition> search(String name, String type, String status, String level);

    List<CompetitionRule> getById(Integer id);
}
