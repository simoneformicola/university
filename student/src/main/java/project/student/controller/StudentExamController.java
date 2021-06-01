package project.student.controller;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.student.client.ExamClient;
import project.student.client.ExamStudentClient;
import project.student.service.StudentService;
import project.student.service.dto.ExamDTO;
import project.student.service.dto.ExamStudentDTO;
import project.student.service.dto.StudentDTO;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/studentexam")
public class StudentExamController {
    
    @Autowired
    private ExamStudentClient examStudentClient;
    
    @Autowired
    private ExamClient examClient;
    
    @Autowired
    private StudentService studentService;
    
    @GetMapping("/exams/{email}")
    public List<ExamDTO> getExamsByEmail(@PathVariable String email) throws ServiceException {
        
        Integer idStudente = studentService.getIdStudentByEmail(email);
        List<ExamStudentDTO> examStudentDTO = examStudentClient.getExamStudentByIdStudent(idStudente);
        List<Integer> idExams = null;

        List<ExamDTO> examDTOS = null;

        for(ExamStudentDTO examStudentDTO1 : examStudentDTO) {
            Integer idEsame = examStudentDTO1.getIdEsame();
            idExams.add(idEsame);
        }

        for(Integer exams : idExams){
            ExamDTO examDTO = examClient.getById(exams);
            examDTOS.add(examDTO);
        }

        return examDTOS;
    }
    
}
