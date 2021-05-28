package com.project.examstudent.repository;

import com.project.examstudent.model.ExamStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamStudentRepository extends JpaRepository<ExamStudent,Integer> {
    List<ExamStudent> findByIdStudente(Integer id);

}
