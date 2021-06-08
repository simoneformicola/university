package com.project.examstudent.service;

import com.project.commonlib.dto.ExamStudentDTO;
import org.hibernate.service.spi.ServiceException;

import java.util.List;

public interface ExamStudentService {

    List<ExamStudentDTO> findAll() throws Exception;
    List<ExamStudentDTO> findByIdStudente(Integer idStudente) throws Exception;
    String deleteAllxamStudentByIdStudent(Integer idStudente) throws Exception;
    List<ExamStudentDTO> getByStudentIds(List<Integer> studentIds) throws Exception;
}
