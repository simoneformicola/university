package project.exam.controller;

import com.project.commonlib.dto.ExamDTO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.exam.model.Exam;
import project.exam.service.ExamService;

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
        return this.examService.findById(id);
    }

    @PostMapping
    public ExamDTO save(@RequestBody ExamDTO exam) throws ServiceException{
        return this.examService.save(exam);
    }

    @DeleteMapping
    public void deleteExam(@RequestBody Exam exam) throws ServiceException{
        examService.deleteExam(exam);
    }

    @GetMapping("examname/{id}")
    public String getExamNameById(@PathVariable Integer id) throws ServiceException {
        return this.examService.findNameExamById(id);
    }

    @GetMapping("credit/{id}")
    public Integer getCreditById(@PathVariable Integer id) throws ServiceException {
        return this.examService.getCreditById(id);
    }

}
