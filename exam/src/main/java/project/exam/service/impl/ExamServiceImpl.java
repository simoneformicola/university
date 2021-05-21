package project.exam.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import project.exam.model.Exam;
import project.exam.repository.ExamRepository;
import project.exam.service.ExamService;
import project.exam.service.dto.ExamDTO;
import project.exam.service.mapper.ExamMapper;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final ExamMapper examMapper;

    public ExamServiceImpl(ExamRepository examRepository, ExamMapper examMapper) {
        this.examRepository = examRepository;
        this.examMapper = examMapper;
    }


    @Override
    public List<ExamDTO> findAll() throws ServiceException {
        try {
            List<Exam> examList = examRepository.findAll();
            List<ExamDTO> examDTOList = examList.stream().map(examMapper::toDTO).collect(Collectors.toList());
            return examDTOList;
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public ExamDTO findByCode(Integer code) throws ServiceException {
        try {
            Optional<Exam> exam = examRepository.findById(code);
            if (exam.isPresent()) {
                ExamDTO examDTO = examMapper.toDTO(exam.get());
                return examDTO;
            } else {
                return null;
            }
        }catch (ServiceException e){
            e.getStackTrace();
            throw new ServiceException(e.getMessage());
        }

    }

    @Override
    public ExamDTO save(ExamDTO exam) {
        try{
            Exam toBeSaved = examMapper.toEntity(exam);
            Exam savedEntity = examRepository.save(toBeSaved);
            ExamDTO examDTO = examMapper.toDTO(savedEntity);
            return examDTO;
        }catch (ServiceException e){
            e.getStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void deleteExam(Exam exam) throws ServiceException {
        try{
            examRepository.delete(exam);
        }catch(Exception e){
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }


}
