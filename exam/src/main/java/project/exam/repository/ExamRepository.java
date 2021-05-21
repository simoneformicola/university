package project.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.exam.model.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam,Integer> {


}
