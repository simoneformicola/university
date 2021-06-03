package project.student.service;

import com.project.commonlib.dto.StudentDTO;
import org.hibernate.service.spi.ServiceException;


import java.util.List;

public interface StudentService {


    List<StudentDTO> findAll() throws Exception;
    StudentDTO findById(Integer id) throws Exception;
    StudentDTO save(StudentDTO student) throws Exception;
    void deleteById(Integer id) throws Exception;
    List<StudentDTO> findByFirstName(String name) throws Exception;
    List<StudentDTO> findByLastName(String name) throws Exception;
    Integer getIdStudentByEmail(String email) throws Exception;
}
