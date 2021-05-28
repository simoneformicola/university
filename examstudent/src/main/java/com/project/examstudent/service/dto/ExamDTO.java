package com.project.examstudent.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ExamDTO {
    private Integer id;
    private Integer code;
    private String name;
    private Integer credit;
}
