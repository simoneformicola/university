package project.exam.controller;

import com.project.commonlib.dto.ExamDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.exam.model.Exam;
import project.exam.service.impl.TransactionExample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    private TransactionExample transactionExample;
    private static List<ExamDTO> examList = Arrays.asList(ExamDTO.builder().code(1).build(), ExamDTO.builder().code(2).build(), ExamDTO.builder().code(3).build(), ExamDTO.builder().code(4).build());

    public TestController(TransactionExample transactionExample) {
        this.transactionExample = transactionExample;
    }

    @PostMapping("/required")
    public void required() throws Exception {
        this.transactionExample.saveExamRequired(examList);
    }

    @PostMapping("/requires_new")
    public void requiresNew() throws Exception {
        this.transactionExample.saveExamRequiresNew(examList);
    }
}
