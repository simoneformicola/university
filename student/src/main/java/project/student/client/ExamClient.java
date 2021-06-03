package project.student.client;

import com.project.commonlib.dto.ExamDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "exam" , url = "${feign.exam.url}")
public interface ExamClient {

    @GetMapping("/exam/{id}")
    ExamDTO getById(@PathVariable Integer id);

    @GetMapping("/exam}")
    List<ExamDTO> getAllExams();

    @GetMapping("exam/idexams")
    List<ExamDTO> getExamsById(List<Integer> ids);
}
