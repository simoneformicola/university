package project.student.service.mapper;

import org.mapstruct.*;
import project.student.model.Student;
import project.student.service.dto.StudentDTO;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(target = "fullName", source = "student",qualifiedByName = "fullNameByFirstAndLastName")
    StudentDTO toDto(Student student);
    Student toEntity(StudentDTO studentDTO);


    @Named("fullNameByFirstAndLastName")
    default String fullNameByFirstAndLastName(Student student){
        return student.getFirstName().concat(" ").concat(student.getLastName());

    }


}



