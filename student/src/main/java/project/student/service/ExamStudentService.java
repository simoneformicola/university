package project.student.service;

import com.project.commonlib.dto.ExamDTO;
import com.project.commonlib.dto.ExamStudentDTO;

import java.util.List;

public interface ExamStudentService {
    List<ExamStudentDTO> getAlllExamStudentByIdStudente(Integer idStudente);
    List<ExamDTO> getAllExamsByStudentEmail(String email);
    //List<ExamDTO> getAllExamsByEmail(String email);
}
