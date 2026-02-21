package com.itgmm.service;

import com.itgmm.dto.CountdownResponse;

import java.util.List;

public interface ExamService {
    
    /**
     * 获取考试倒计时列表
     * @return 考试倒计时列表
     */
    public List<CountdownResponse> getExamsCountdown();
}
