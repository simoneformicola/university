package project.exam.service;

import com.project.commonlib.dto.ExamDTO;
import org.hibernate.service.spi.ServiceException;
import project.exam.model.Exam;



import java.util.List;

public interface ExamService {

    ExamDTO findById(Integer id) throws ServiceException;
    List<ExamDTO> findAll() throws ServiceException;
    ExamDTO save(ExamDTO examDTO) throws ServiceException;
    void deleteExam(Exam exam) throws ServiceException;
    String findNameExamById(Integer id) throws ServiceException;
    Integer getCreditById(Integer id) throws ServiceException;

}
