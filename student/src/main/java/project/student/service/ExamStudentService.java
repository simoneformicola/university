package project.student.service;

import project.student.service.dto.ExamStudentDTO;

import java.util.List;

public interface ExamStudentService {
    List<ExamStudentDTO> getAlllExamStudentByIdStudente(Integer idStudente);
}
