package project.student.service.impl;

import com.project.commonlib.dto.StudentDTO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import project.student.model.Student;
import project.student.repository.StudentRepository;
import project.student.service.StudentService;
import project.student.service.mapper.StudentMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    @Cacheable(value = "students" , key = "'all'" )
    public List<StudentDTO> findAll() throws Exception {
        try {
            Thread.sleep(3000);
            return studentRepository.findAll().stream().map(studentMapper::toDto).collect(Collectors.toList());
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public StudentDTO findById(Integer id) throws Exception {
        try {
            Optional<Student> optStudent = studentRepository.findById(id);
            if (optStudent.isPresent()){
                StudentDTO studentDTO = studentMapper.toDto(optStudent.get());
                return studentDTO;
            }else {
                return null;
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "students" , key = "'all'" ),
            @CacheEvict(value = "students", key = "#student.firstName"),
            @CacheEvict(value = "students", key = "#student.lastName")
    })
    public StudentDTO save(StudentDTO student) throws Exception {
        try {
            Student toBeSaved = studentMapper.toEntity(student);
            Student savedEntity = studentRepository.save(toBeSaved);
            StudentDTO studentDTO = studentMapper.toDto(savedEntity);
            return studentDTO;
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
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
    public List<StudentDTO> findByFirstName(String firstName) throws Exception {
        try {
            Thread.sleep(3000);
            List<Student> entityList = studentRepository.findByFirstName(firstName);
            List<StudentDTO> studentDTOS = entityList.stream().map(studentMapper::toDto).collect(Collectors.toList());
            return studentDTOS;
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());

        }
    }

    @Override
    @Cacheable(value = "students" , key = "#lastName")
    public List<StudentDTO> findByLastName(String lastName) throws Exception {
        try {
            Thread.sleep(3000);
            List<Student> entityList = studentRepository.findByLastName(lastName);
            List<StudentDTO> studentDTOS = entityList.stream().map(studentMapper::toDto).collect(Collectors.toList());
            return studentDTOS;
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());

        }
    }

    @Override
    public Integer getIdStudentByEmail(String email) throws Exception {
        try {
            Optional<Student> optStudent = studentRepository.findByEmail(email);
            if (optStudent.isPresent()){
                StudentDTO studentDTO = studentMapper.toDto(optStudent.get());
                Integer idStudente = studentDTO.getId();
                return idStudente;
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());

        }
    }

}
