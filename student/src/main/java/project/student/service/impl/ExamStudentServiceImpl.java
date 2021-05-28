package project.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.student.client.ExamStudentClient;
import project.student.service.ExamStudentService;
import project.student.service.dto.ExamStudentDTO;

import java.util.List;

@Service
public class ExamStudentServiceImpl implements ExamStudentService {

    @Autowired
    private ExamStudentClient examStudentClient;

    @Override
    public List<ExamStudentDTO> getAlllExamStudentByIdStudente(Integer idStudente) {
        List<ExamStudentDTO> examStudentDTOS =  examStudentClient.getExamStudentByIdStudent(idStudente);
        return examStudentDTOS;
    }
}
