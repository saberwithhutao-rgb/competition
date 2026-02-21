
package com.itgmm.controller;
import com.itgmm.dto.CountdownResponse;
import com.itgmm.pojo.Result;
import com.itgmm.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping()
@RestController
public class ExamController {

    @Autowired
    private  ExamService examService;


    /**
     * 获取考试倒计时列表（新格式，符合前端要求）
     * @return 考试倒计时列表
     */
    @GetMapping("/exams")
    public Result getCountdown() {
        List<CountdownResponse> countdownList = examService.getExamsCountdown();
        return Result.success(countdownList);
    }
}