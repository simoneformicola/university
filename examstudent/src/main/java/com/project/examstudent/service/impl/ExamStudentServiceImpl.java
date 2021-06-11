package com.project.examstudent.service.impl;

import com.project.examstudent.model.ExamStudent;
import com.project.examstudent.repository.ExamStudentRepository;
import com.project.examstudent.service.ExamStudentService;
import com.project.commonlib.dto.ExamStudentDTO;
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
    public List<ExamStudentDTO> findAll() throws Exception {
        try {
            List<ExamStudent> examStudents = examStudentRepository.findAll();
            List<ExamStudentDTO> examStudentDTOS = examStudents.stream().map(examStudentMapper::toDto).collect(Collectors.toList());
            return examStudentDTOS;
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ExamStudentDTO> findByIdStudente(Integer idStudente) throws Exception {
        try {
            List<ExamStudent> examStudents = examStudentRepository.findByIdStudente(idStudente);
            List<ExamStudentDTO> examStudentDTOas = examStudents.stream().map(examStudentMapper::toDto).collect(Collectors.toList());
            return examStudentDTOas;
        }catch (Exception e){
            e.getStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public String deleteAllxamStudentByIdStudent(Integer idStudente) throws Exception {
        try {
            examStudentRepository.deleteByIdStudente(idStudente);
            return "eliminazione Esami Passati per studente con id: " + idStudente + " riuscita";
        }catch (Exception e){
            e.getStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ExamStudentDTO> getByStudentIds(List<Integer> studentIds) throws Exception {
        try {
            List<ExamStudent> examStudents = examStudentRepository.findByIdStudenteIn(studentIds);
            List<ExamStudentDTO> result = examStudents.stream().map(examStudentMapper::toDto).collect(Collectors.toList());
            return result;
        }catch (Exception e){
            e.getStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ExamStudentDTO> getEvenExamStudent() throws Exception {
        try {
            List<ExamStudent> examStudents = examStudentRepository.findAll();
            List<ExamStudentDTO> result = examStudents.stream().filter(examStudent -> examStudent.getResult() % 2 == 0).map(examStudentMapper::toDto).collect(Collectors.toList());
            return result;
        }catch (Exception e){
            throw new Exception("non è stato possibile ottenere risultati pari");
        }
    }

    @Override
    public List<ExamStudentDTO> getOddExamStudent() throws Exception {
        try {
            List<ExamStudent> examStudents = examStudentRepository.findAll();
            List<ExamStudentDTO> result = examStudents.stream().filter(examStudent -> examStudent.getResult() % 2 != 0).map(examStudentMapper::toDto).collect(Collectors.toList());
            return result;
        }catch (Exception e){
            throw new Exception("non è stato possibile ottenere risultati dispari");
        }
    }
}
