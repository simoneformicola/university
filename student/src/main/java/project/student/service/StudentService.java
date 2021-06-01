package project.student.service;

import com.project.commonlib.dto.StudentDTO;
import org.hibernate.service.spi.ServiceException;


import java.util.List;

public interface StudentService {


    List<StudentDTO> findAll() throws ServiceException;
    StudentDTO findById(Integer id) throws ServiceException;
    StudentDTO save(StudentDTO student) throws ServiceException;
    void deleteById(Integer id) throws ServiceException;
    List<StudentDTO> findByFirstName(String name) throws ServiceException;
    List<StudentDTO> findByLastName(String name) throws ServiceException;
    Integer getIdStudentByEmail(String email) throws ServiceException;
}
