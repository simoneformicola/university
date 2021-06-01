package project.exam.service.mapper;

import com.project.commonlib.dto.ExamDTO;
import org.mapstruct.Mapper;
import project.exam.model.Exam;



@Mapper(componentModel = "spring")
public interface ExamMapper {

    ExamDTO toDTO(Exam exam);
    Exam toEntity(ExamDTO examDTO);

}
