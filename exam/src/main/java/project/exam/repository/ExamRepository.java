package project.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.exam.model.Exam;
import project.exam.service.dto.ExamDTO;

@Repository
public interface ExamRepository extends JpaRepository<Exam,Integer> {

    Exam getNameExamById(Integer id);
    Exam getCreditById(Integer id);


}
