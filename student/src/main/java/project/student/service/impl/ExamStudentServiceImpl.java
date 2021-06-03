package project.student.service.impl;

import com.project.commonlib.dto.ExamDTO;
import com.project.commonlib.dto.ExamStudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.student.client.ExamClient;
import project.student.client.ExamStudentClient;
import project.student.model.Student;
import project.student.repository.StudentRepository;
import project.student.service.ExamStudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExamStudentServiceImpl implements ExamStudentService {

    @Autowired
    private ExamStudentClient examStudentClient;

    @Autowired
    private ExamClient examClient;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<ExamStudentDTO> getAlllExamStudentByIdStudente(Integer idStudente) throws Exception {
        try {
            List<ExamStudentDTO> examStudentDTOS = examStudentClient.getExamStudentByIdStudent(idStudente);
            return examStudentDTOS;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ExamDTO> getAllExamsByStudentEmail(String email) throws Exception {

        Optional<Student> student = studentRepository.findByEmail(email);
        if(!student.isPresent()){
            throw new Exception("studente con email: " + email + " non trovato");
        }
        Integer idStudente = student.get().getId();
        List<ExamStudentDTO> examStudentDTO = examStudentClient.getExamStudentByIdStudent(idStudente);
        List<Integer> idExams = new ArrayList<>();

        List<ExamDTO> examDTOS = new ArrayList<>();

        for(ExamStudentDTO examStudentDTO1 : examStudentDTO) {
            Integer idEsame = examStudentDTO1.getIdEsame();
            idExams.add(idEsame);
        }

        for(Integer exams : idExams){
            ExamDTO examDTO = examClient.getById(exams);
            examDTOS.add(examDTO);
        }

        return examDTOS;
    }

    @Override
    public List<ExamDTO> getAllExamsByEmail(String email) throws Exception {
        try {
            Optional<Student> student = studentRepository.findByEmail(email);
            Integer idStudente = student.get().getId();
            List<ExamStudentDTO> examStudentDTO = examStudentClient.getExamStudentByIdStudent(idStudente);
            List<Integer> idExams = new ArrayList<>();

            for(ExamStudentDTO eStudentDTO : examStudentDTO){
                idExams.add(eStudentDTO.getIdEsame());
            }

            List<ExamDTO> result = examClient.getExamsByIdList(idExams);

            return result;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }







    }
}
