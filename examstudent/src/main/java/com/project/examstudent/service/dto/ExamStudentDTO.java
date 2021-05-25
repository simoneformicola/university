package com.project.examstudent.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ExamStudentDTO {

    private Integer id;
    private Integer idStudente;
    private Integer idEsame;
    private Integer result; //min 18 max 30
}
