package project.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.exam.model.Exam;

import java.util.List;


@Repository
public interface ExamRepository extends JpaRepository<Exam,Integer> {

    Exam getNameExamById(Integer id);
    Exam getCreditById(Integer id);


    @Query( nativeQuery = true , value = "SELECT * FROM esame as e WHERE e.id IN (:ids)")
    List<Exam> getAllExamsById(@Param("ids") List<Integer> ids);


}
