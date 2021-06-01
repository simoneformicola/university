package project.student.service;

import com.project.commonlib.dto.ExamStudentDTO;

import java.util.List;

public interface ExamStudentService {
    List<ExamStudentDTO> getAlllExamStudentByIdStudente(Integer idStudente);
}
