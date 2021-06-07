package project.exam.service.impl;

import com.project.commonlib.dto.ExamDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import project.exam.service.ExamService;

import java.util.List;

@Service
@Slf4j
public class TransactionExample {

    private final ExamService examService;


    public TransactionExample(ExamService examService) {
        this.examService = examService;
    }

    // 1. creo la transazione
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void saveExamRequired(List<ExamDTO> examList) throws Exception {
        for (ExamDTO examDTO : examList) {
            this.examService.saveRequired(examDTO);
        }
    }

    // 1. creo la transazione
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void saveExamRequiresNew(List<ExamDTO> examList) throws Exception {
        for (ExamDTO examDTO : examList) {
            this.examService.saveRequiresNew(examDTO);
        }
    }

    // TODO:
    /*
        1. controllare che la transazione di saveExamRequiresNew non viene cancellata chiamando examService.saveRequiresNew.
        2. fare in modo che saveExamRequiresNew salvi anche l'esame con code 4 con il minor numero di modifiche, modificando solo il metodo saveExamRequiresNew
     */
}
