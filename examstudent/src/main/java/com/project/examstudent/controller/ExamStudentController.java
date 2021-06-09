package com.project.examstudent.controller;

import com.project.examstudent.service.ExamStudentService;
import com.project.commonlib.dto.ExamStudentDTO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/examstudent")
public class ExamStudentController {

    @Autowired
    private ExamStudentService examStudentService;

    @GetMapping
    public List<ExamStudentDTO> getAll() throws Exception {
        return this.examStudentService.findAll();
    }

    @GetMapping("/idstudente/{idStudente}")
    public List<ExamStudentDTO> getByIdStudente(@PathVariable Integer idStudente)  throws Exception {
        return this.examStudentService.findByIdStudente(idStudente);
    }

    @DeleteMapping("/delete/id/{idStudente}")
    public String deleteExamsByIdStudente(@PathVariable Integer idStudente)  throws Exception {
        return this.examStudentService.deleteAllxamStudentByIdStudent(idStudente);
    }

    @GetMapping("/all/id")
    public List<ExamStudentDTO> getByIdStudente(@RequestParam List<Integer> studentIds)  throws Exception {
        return this.examStudentService.getByStudentIds(studentIds);
    }

    // risultati esami numero pari tramite filter
    @GetMapping("/even")
    public List<ExamStudentDTO> getEvenResult()  throws Exception {
        return this.examStudentService.getEvenExamStudent();
    }

    // risultati esami numero dispari tramite filter
    @GetMapping("/odd")
    public List<ExamStudentDTO> getOddResult()  throws Exception {
        return this.examStudentService.getOddExamStudent();
    }

}


