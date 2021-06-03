package project.student.service;

import com.project.commonlib.dto.ExamDTO;
import com.project.commonlib.dto.ExamStudentDTO;

import java.util.List;

public interface ExamStudentService {
    List<ExamStudentDTO> getAlllExamStudentByIdStudente(Integer idStudente) throws Exception;
    List<ExamDTO> getAllExamsByStudentEmail(String email) throws Exception;
    List<ExamDTO> getAllExamsByEmail(String email) throws Exception;
    String deleteStudentExamsByEmail(String email) throws Exception;
}
