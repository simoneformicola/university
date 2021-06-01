package com.project.examstudent.service;

import com.project.commonlib.dto.ExamStudentDTO;
import org.hibernate.service.spi.ServiceException;

import java.util.List;

public interface ExamStudentService {

    List<ExamStudentDTO> findAll() throws ServiceException;
    List<ExamStudentDTO> findByIdStudente(Integer idStudente) throws ServiceException;
}
