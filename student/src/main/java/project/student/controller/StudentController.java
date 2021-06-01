package project.student.controller;

import com.project.commonlib.dto.StudentDTO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.student.service.StudentService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<StudentDTO> getAll() throws ServiceException {
        return this.studentService.findAll();
    }

    @GetMapping("/id/{id}")
    public StudentDTO getById(@PathVariable Integer id) throws ServiceException {
        return this.studentService.findById(id);
    }

    @PostMapping
    public StudentDTO save(@RequestBody StudentDTO student) throws ServiceException {
        return this.studentService.save(student);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) throws ServiceException{
        this.studentService.deleteById(id);
    }

    @GetMapping("/firstname/{firstName}")
    public List<StudentDTO> getByFirstName(@PathVariable String firstName) throws ServiceException{
        return this.studentService.findByFirstName(firstName);
    }

    @GetMapping("/lastname/{lastName}")
    public List<StudentDTO> getByLastName(@PathVariable String lastName) throws ServiceException{
        return this.studentService.findByLastName(lastName);
    }

    

    
    
}
