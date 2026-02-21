// src/main/java/com/example/countdowndemo/service/ExamService.java
package com.itgmm.service.lmpl;

import com.itgmm.dto.CountdownResponse;
import com.itgmm.mapper.ExamMapper;
import com.itgmm.pojo.Exam;
import com.itgmm.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamMapper examMapper;

    @Override
    public List<CountdownResponse> getExamsCountdown() {
        List<Exam> exams = examMapper.findAll();
        List<CountdownResponse> responses = new ArrayList<>();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Exam exam : exams) {
            LocalDateTime startTime = exam.getStartTime();
            LocalDateTime endTime = exam.getEndTime();

            // 计算剩余天数
            Duration duration = Duration.between(now, startTime);
            boolean expired = duration.isNegative();

            Integer daysRemaining;
            if (expired) {
                daysRemaining = 0;
            } else {
                long totalSeconds = duration.getSeconds();
                daysRemaining = (int) (totalSeconds / 86400);
            }

            // 格式化日期
            String startDateStr = startTime.toLocalDate().format(dateFormatter);
            String endDateStr = endTime.toLocalDate().format(dateFormatter);

            CountdownResponse response = new CountdownResponse(
                exam.getName(),
                startDateStr,
                endDateStr,
                daysRemaining,
                expired
            );

            responses.add(response);
        }

        return responses;
    }
}