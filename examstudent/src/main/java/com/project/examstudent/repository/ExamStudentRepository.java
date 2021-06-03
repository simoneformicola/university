package com.project.examstudent.repository;

import com.project.examstudent.model.ExamStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamStudentRepository extends JpaRepository<ExamStudent,Integer> {
    List<ExamStudent> findByIdStudente(Integer id);

    @Query( nativeQuery = true , value = "DELETE FROM esamestudente as e WHERE e.id_studente = :idStudente")
    void deleteAllExamStudentByIdStudente(@Param("idStudente") Integer idStudente);

}
