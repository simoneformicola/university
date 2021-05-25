package com.project.examstudent.repository;

import com.project.examstudent.model.ExamStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamStudentRepository extends JpaRepository<ExamStudent,Integer> {
    ExamStudent findByIdStudente(Integer id);

}
