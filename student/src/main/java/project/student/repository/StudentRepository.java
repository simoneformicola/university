package project.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.student.model.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    List<Student> findByFirstName(String fisrtName);
    List<Student> findByLastName(String lastName);
    Optional<Student> findByEmail(String email);


}
