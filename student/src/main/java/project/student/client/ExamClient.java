package project.student.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import project.student.service.dto.ExamDTO;
import project.student.service.dto.ExamStudentDTO;

import java.util.List;

@FeignClient(name = "exam" , url = "${feign.exam.url}")
public interface ExamClient {

    @GetMapping("/exam/{id}")
    ExamDTO getById(@PathVariable Integer id);

    
}
