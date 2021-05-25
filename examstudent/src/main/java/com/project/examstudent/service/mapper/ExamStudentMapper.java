package com.project.examstudent.service.mapper;

import com.project.examstudent.model.ExamStudent;
import com.project.examstudent.service.dto.ExamStudentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExamStudentMapper {

    ExamStudentDTO toDto(ExamStudent examStudent);
    ExamStudent toEntity(ExamStudentDTO examStudentDTO);
}
