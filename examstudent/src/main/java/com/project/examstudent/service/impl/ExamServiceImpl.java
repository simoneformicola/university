package com.project.examstudent.service.impl;

import com.project.examstudent.client.ExamClient;
import com.project.examstudent.service.ExamService;
import com.project.examstudent.service.dto.ExamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamClient examClient;

    @Override
    public ExamDTO getExamById(Integer idEsame) {
        ExamDTO examDTO = examClient.getExamByIdEsame(idEsame);
        return examDTO;

    }
}
