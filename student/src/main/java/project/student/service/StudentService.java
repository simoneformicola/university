package project.student.service;

import org.hibernate.service.spi.ServiceException;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import project.student.service.dto.StudentDTO;

import java.util.List;

public interface StudentService {


    List<StudentDTO> findAll() throws ServiceException;
    StudentDTO findById(Integer id) throws ServiceException;
    StudentDTO save(StudentDTO student) throws ServiceException;
    void deleteById(Integer id) throws ServiceException;
    List<StudentDTO> findByFirstName(String name) throws ServiceException;
}
