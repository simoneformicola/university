package project.student.client;

import com.project.commonlib.dto.ExamStudentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "examstudent" , url = "${feign.examstudent.url}") // http://localhost:8083
public interface ExamStudentClient {

    @GetMapping("/examstudent/idstudente/{idStudente}")
    List<ExamStudentDTO> getExamStudentByIdStudent(@RequestParam Integer idStudente);

}
