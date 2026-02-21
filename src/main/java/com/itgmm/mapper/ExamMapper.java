
package com.itgmm.mapper;

import com.itgmm.pojo.Exam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ExamMapper {
    
    //将 timestamptz 强制转换为 timestamp
    @Select("SELECT id, name, start_time::timestamp as start_time, end_time::timestamp as end_time FROM exams")
    List<Exam> findAll();
}