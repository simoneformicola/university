package project.student.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import project.student.model.Student;
import project.student.service.dto.StudentDTO;
import project.student.repository.StudentRepository;
import project.student.service.StudentService;
import project.student.service.mapper.StudentMapper;

import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    @Cacheable(value = "students" , key = "'all'" )
    public List<StudentDTO> findAll() throws ServiceException {
        try {
            Thread.sleep(3000);
            return studentRepository.findAll().stream().map(studentMapper::toDto).collect(Collectors.toList());
        }catch (Exception e){
            throw new ServiceException("error FindAllStudents");
        }
    }

    @Override
    public StudentDTO findById(Integer id) throws ServiceException {
        try {
            Optional<Student> optStudent = studentRepository.findById(id);
            if (optStudent.isPresent()){
                StudentDTO studentDTO = studentMapper.toDto(optStudent.get());
                return studentDTO;
            }else {
                return null;
            }
        }catch (Exception e){
            throw new ServiceException("error FindById");
        }
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "students" , key = "'all'" ),
            @CacheEvict(value = "students", key = "#student.firstName"),
            @CacheEvict(value = "students", key = "#student.lastName")
    })
    public StudentDTO save(StudentDTO student) throws ServiceException {
        try {
            Student toBeSaved = studentMapper.toEntity(student);
            Student savedEntity = studentRepository.save(toBeSaved);
            StudentDTO studentDTO = studentMapper.toDto(savedEntity);
            return studentDTO;
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    @CacheEvict(value = "students" , key = "'all'")
    public void deleteById(Integer id) throws ServiceException {
        try{
            studentRepository.deleteById(id);
        }catch(Exception e){
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    @Cacheable(value = "students" , key = "#firstName")
    public List<StudentDTO> findByFirstName(String firstName) throws ServiceException {
        try {
            Thread.sleep(3000);
            List<Student> entityList = studentRepository.findByFirstName(firstName);
            List<StudentDTO> studentDTOS = entityList.stream().map(studentMapper::toDto).collect(Collectors.toList());
            return studentDTOS;
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException(e.getMessage());

        }
    }

    @Override
    @Cacheable(value = "students" , key = "#lastName")
    public List<StudentDTO> findByLastName(String lastName) throws ServiceException {
        try {
            Thread.sleep(3000);
            List<Student> entityList = studentRepository.findByLastName(lastName);
            List<StudentDTO> studentDTOS = entityList.stream().map(studentMapper::toDto).collect(Collectors.toList());
            return studentDTOS;
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException(e.getMessage());

        }
    }

}
