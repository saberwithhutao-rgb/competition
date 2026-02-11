package com.itgmm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class University {
    // 主键
    private Integer id;

    // 学校全称（唯一）
    private String name;

    // 简称（如：清华、北大）
    private String shortName;

    // 教育部院校代码（如：10003）
    private String code;

    // 所在省份
    private String province;

    // 所在城市
    private String city;

    // 是否985高校
    private Boolean is985;

    // 是否211高校
    private Boolean is211;

    // 是否“双一流”建设高校
    private Boolean isDoubleFirstClass;

    // 是否有博士点
    private Boolean hasDoctorate;

    // 是否有硕士点
    private Boolean hasMaster;

    // 院校类型（如：综合类、工科类、师范类等）
    private String institutionType;

    // 标签（JSON字符串，如：["985","双一流","教育部直属"]）
    private String tags;

    // ✅ 学校官方网站（你提到的“学校网址”）
    private String officialWebsite;

    // 记录创建时间
    private LocalDateTime createdAt;

    // 记录最后更新时间
    private LocalDateTime updatedAt;
}
