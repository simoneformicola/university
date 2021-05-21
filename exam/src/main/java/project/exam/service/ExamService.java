package project.exam.service;

import org.hibernate.service.spi.ServiceException;
import project.exam.model.Exam;
import project.exam.service.dto.ExamDTO;


import java.util.List;

public interface ExamService {

    List<ExamDTO> findAll() throws ServiceException;
    ExamDTO findByCode(Integer code) throws ServiceException;
    ExamDTO save(ExamDTO examDTO) throws ServiceException;
    void deleteExam(Exam exam) throws ServiceException;
}
