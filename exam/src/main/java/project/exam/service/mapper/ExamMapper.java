package project.exam.service.mapper;

import org.mapstruct.Mapper;
import project.exam.model.Exam;
import project.exam.service.dto.ExamDTO;

import java.util.Optional;


@Mapper(componentModel = "spring")
public interface ExamMapper {

    ExamDTO toDTO(Exam exam);
    Exam toEntity(ExamDTO examDTO);

}
