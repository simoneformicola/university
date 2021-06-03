package project.student.controller;

import com.project.commonlib.dto.ExamDTO;
import com.project.commonlib.dto.ExamStudentDTO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.student.client.ExamClient;
import project.student.client.ExamStudentClient;
import project.student.service.ExamStudentService;
import project.student.service.StudentService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/studentexam")
public class StudentExamController {

    @Autowired
    private ExamStudentService examStudentService;
    
    @GetMapping("/exams/{email}")
    public List<ExamDTO> getExamsByEmail(@PathVariable String email) throws Exception {

        List<ExamDTO> examDTOS = this.examStudentService.getAllExamsByStudentEmail(email);

        return examDTOS;

    }

    @GetMapping("/allexams/{email}")
    public List<ExamDTO> getAllExamsByEmail(@PathVariable String email) throws Exception {

        List<ExamDTO> examDTOS = this.examStudentService.getAllExamsByEmail(email);

        return examDTOS;

    }

    @DeleteMapping("/delete/email/{email}")
    public String deleteByEmail(@PathVariable String email) throws Exception {
        String result = this.examStudentService.deleteStudentExamsByEmail(email);
        return result;

    }
    
}
