package com.project.examstudent.controller;

import com.project.examstudent.service.ExamStudentService;
import com.project.commonlib.dto.ExamStudentDTO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/examstudent")
public class ExamStudentController {

    @Autowired
    private ExamStudentService examStudentService;

    @GetMapping
    public List<ExamStudentDTO> getAll() throws ServiceException {
        return this.examStudentService.findAll();
    }

    @GetMapping("/idstudente/{idStudente}")
    public List<ExamStudentDTO> getByIdStudente(@PathVariable Integer idStudente) throws ServiceException {
        return this.examStudentService.findByIdStudente(idStudente);
    }


}


