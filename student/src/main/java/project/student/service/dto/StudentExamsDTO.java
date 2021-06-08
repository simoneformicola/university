package project.student.service.dto;

import com.project.commonlib.dto.ExamDTO;
import com.project.commonlib.dto.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class StudentExamsDTO {

    private StudentDTO studentDTO;
    private List<ExamDTO> examDTOS;
}
