package com.project.examstudent.client;


import com.project.examstudent.model.StudentResponse;
import com.project.examstudent.service.dto.ExamDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "exam", url ="${feign.exam.url}") // http://localhost:8081
public interface ExamClient {

    @GetMapping("/exam/{id}")
    ExamDTO getExamByIdEsame(@RequestParam Integer idEsame);
}
