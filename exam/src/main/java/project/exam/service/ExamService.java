package project.exam.service;

import com.project.commonlib.dto.ExamDTO;
import org.hibernate.service.spi.ServiceException;
import project.exam.model.Exam;
import java.util.List;

public interface ExamService {

    ExamDTO findById(Integer id) throws Exception;
    List<ExamDTO> findAll() throws Exception;
    ExamDTO save(ExamDTO examDTO) throws Exception;
    void deleteExam(Exam exam) throws Exception;
    String findNameExamById(Integer id) throws Exception;
    Integer getCreditById(Integer id) throws Exception;
    List<ExamDTO> getByIdList(List<Integer> ids) throws Exception;

    void saveRequiresNew(ExamDTO examDTO) throws Exception;
    void saveRequired(ExamDTO examDTO) throws Exception;

}
