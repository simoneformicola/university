package project.student.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.student.service.dto.ExamStudentDTO;

import java.util.List;

@FeignClient(name = "examstudent" , url = "${feign.examstudent.url}") // http://localhost:7080
public interface ExamStudentClient {

    @GetMapping("/examstudent/idstudente/{idStudente}")
    List<ExamStudentDTO> getExamStudentByIdStudent(@RequestParam Integer idStudente);

}
