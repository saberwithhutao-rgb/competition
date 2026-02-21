package com.itgmm.dto;


/**
 * 考试倒计时响应DTO
 */
public class CountdownResponse {
    private String name;                    // 考试名称
    private String startDate;              // 考试开始日期
    private String endDate;                // 考试结束日期
    private Integer daysRemaining;         // 剩余天数
    private Boolean expired;               // 是否已过期

    public CountdownResponse() {}

    public CountdownResponse(String name, String startDate, String endDate, Integer daysRemaining, Boolean expired) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.daysRemaining = daysRemaining;
        this.expired = expired;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getDaysRemaining() {
        return daysRemaining;
    }

    public void setDaysRemaining(Integer daysRemaining) {
        this.daysRemaining = daysRemaining;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }
}