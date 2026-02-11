package com.itgmm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAbilityDimension {

    private Long id;
    private Long userId;
    private Integer reasoning;
    private Integer calculation;
    private Integer memory;
    private Integer creativity;
    private Integer spatial;
    private Integer observation;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}