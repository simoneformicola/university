package com.project.examstudent.service.impl;

import com.project.examstudent.model.ExamStudent;
import com.project.examstudent.repository.ExamStudentRepository;
import com.project.examstudent.service.ExamStudentService;
import com.project.examstudent.service.dto.ExamStudentDTO;
import com.project.examstudent.service.mapper.ExamStudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ExamStudentServiceImpl implements ExamStudentService {

    private final ExamStudentRepository examStudentRepository;

    private final ExamStudentMapper examStudentMapper;

    public ExamStudentServiceImpl(ExamStudentRepository examStudentRepository, ExamStudentMapper examStudentMapper) {
        this.examStudentRepository = examStudentRepository;
        this.examStudentMapper = examStudentMapper;
    }

    @Override
    public List<ExamStudentDTO> findAll() throws ServiceException {
        try {
            List<ExamStudent> examStudents = examStudentRepository.findAll();
            List<ExamStudentDTO> examStudentDTOS = examStudents.stream().map(examStudentMapper::toDto).collect(Collectors.toList());
            return examStudentDTOS;
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public ExamStudentDTO findByIdStudente(Integer idStudente) throws ServiceException {
        try {
            ExamStudent examStudent = examStudentRepository.findByIdStudente(idStudente);
            ExamStudentDTO examStudentDTO = examStudentMapper.toDto(examStudent);
            return examStudentDTO;
        }catch (ServiceException e){
            e.getStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }
}
