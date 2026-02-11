package com.itgmm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionRule {
    private Integer id;
    private Integer competitionId;          // 对应 competition_id
    private String category;             // 如 "eligibility", "team" 等
    private String title;
    private String content;
    private Integer sortOrder;           // 默认为 0
    private Boolean required;            // 是否强制要求，默认 true
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
