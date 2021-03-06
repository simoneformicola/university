package project.exam.service.impl;

import com.project.commonlib.dto.ExamDTO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import project.exam.model.Exam;
import project.exam.repository.ExamRepository;
import project.exam.service.ExamService;
import project.exam.service.mapper.ExamMapper;


import java.util.ArrayList;
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
    @Cacheable(value = "exams" , key = "'all'")
    public List<ExamDTO> findAll() throws Exception {
        try {
            Thread.sleep(3000);
            List<Exam> examList = examRepository.findAll();
            List<ExamDTO> examDTOList = examList.stream().map(examMapper::toDTO).collect(Collectors.toList());
            return examDTOList;
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ExamDTO findById(Integer id) throws Exception {
        try {
            Optional<Exam> exam = examRepository.findById(id);
            if (exam.isPresent()) {
                ExamDTO examDTO = examMapper.toDTO(exam.get());
                return examDTO;
            } else {
                return null;
            }
        }catch (Exception e){
            e.getStackTrace();
            throw new Exception(e.getMessage());
        }

    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "exams" , key = "'all'"),
    })
    public ExamDTO save(ExamDTO exam) throws Exception {
        try{
            Exam toBeSaved = examMapper.toEntity(exam);
            Exam savedEntity = examRepository.save(toBeSaved);
            ExamDTO examDTO = examMapper.toDTO(savedEntity);
            return examDTO;
        }catch (Exception e){
            e.getStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteExam(Exam exam) throws Exception {
        try{
            examRepository.delete(exam);
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public String findNameExamById(Integer id) throws Exception {
        try{
            Optional<Exam> exam = examRepository.findById(id);
            if(exam.isPresent()){
                String examName = exam.get().getName();
                return examName;
            }else {
                return null;
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Integer getCreditById(Integer id) throws Exception {
        try{
            Optional<Exam> exam = examRepository.findById(id);
            if (exam.isPresent()){
                Integer credit = exam.get().getCredit();
                return credit;
            }else{
                return null;
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ExamDTO> getByIdList(List<Integer> ids) throws Exception {
        try{
            List<ExamDTO> examDTOS = new ArrayList<>();
            List<Exam> examList = examRepository.findByIdIn(ids);
            examDTOS = examList.stream().map(examMapper::toDTO).collect(Collectors.toList());
            return examDTOS;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    // transaction example

    // 2a. utilizzo la stessa transazione creata dentro transaction example
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void saveRequired(ExamDTO examDTO) throws Exception {

        this.examRepository.save(this.examMapper.toEntity(examDTO));

        if (examDTO.getCode() == 3) {
            throw new Exception("random error");
        } else {
            log.info("saving exam with code {}", examDTO.getCode());
        }
    }

    // 2b. utilizzo sempre una nuova transazione e NON quella creata dentro transaction example
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void saveRequiresNew(ExamDTO examDTO) throws Exception {

        this.examRepository.save(this.examMapper.toEntity(examDTO));

        if (examDTO.getCode() == 3) {
            throw new Exception("random error");
        } else {
            log.info("saving exam with code {}", examDTO.getCode());
        }
    }
}
