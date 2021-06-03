package project.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.exam.model.Exam;

import java.util.List;


@Repository
public interface ExamRepository extends JpaRepository<Exam,Integer> {

    Exam getNameExamById(Integer id);
    Exam getCreditById(Integer id);


    @Query( nativeQuery = true, value = "SELECT * FROM studente as s WHERE s.id IN(:ids)")
    List<Exam> getAllExamsById(List<Integer> ids);


}
