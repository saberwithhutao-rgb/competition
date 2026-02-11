package com.itgmm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Competition {
    private Integer id; // 主键
    private String name; // 比赛名称
    private String description; // 描述
    private LocalDate competitionTime; // 比赛时间
    private String duration; // 持续时间
    private String location; // 地点
    private List<String> tags; // 标签
    private String status; // 状态
    private String statusText; // 状态文本
    private String type; // 类型
    private String level; // 等级
    private LocalDate registrationDeadline; // 报名截止时间
    private String organizer; // 组织者
    private String contact; // 联系方式
    private String requirements; // 要求
    private LocalDate createdAt; // 创建时间
    private LocalDate updatedAt; // 更新时间
    private String officialWebsite; // 官方网站
}
