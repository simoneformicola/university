package com.project.examstudent.service;

import com.project.examstudent.service.dto.ExamStudentDTO;
import org.hibernate.service.spi.ServiceException;

import java.util.List;

public interface ExamStudentService {

    List<ExamStudentDTO> findAll() throws ServiceException;
    ExamStudentDTO findByIdStudente(Integer idStudente) throws ServiceException;
}
