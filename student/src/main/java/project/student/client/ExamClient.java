package project.student.client;

import com.project.commonlib.dto.ExamDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "exam" , url = "${feign.exam.url}")
public interface ExamClient {

    @GetMapping("/exam/{id}")
    ExamDTO getById(@PathVariable Integer id);

    
}
