package project.exam.controller;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.exam.model.Exam;
import project.exam.service.ExamService;
import project.exam.service.dto.ExamDTO;


import java.util.List;

@Slf4j
@RestController
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private ExamService examService;

    @GetMapping
    public List<ExamDTO> getAll() throws ServiceException {
        return this.examService.findAll();
    }

    @GetMapping("/{id}")
    public ExamDTO getById(@PathVariable Integer id) throws ServiceException {
        return this.examService.findByCode(id);
    }

    @PostMapping
    public ExamDTO save(@RequestBody ExamDTO exam) throws ServiceException{
        return this.examService.save(exam);
    }

    @DeleteMapping
    public void deleteExam(@RequestBody Exam exam) throws ServiceException{
        examService.deleteExam(exam);
    }

}
