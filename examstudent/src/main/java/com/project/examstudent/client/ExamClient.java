package com.project.examstudent.client;


import com.project.commonlib.dto.ExamDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "exam", url ="${feign.exam.url}") // http://localhost:8081
public interface ExamClient {

    @GetMapping("/exam/{id}")
    ExamDTO getExamByIdEsame(@RequestParam Integer idEsame);
}
