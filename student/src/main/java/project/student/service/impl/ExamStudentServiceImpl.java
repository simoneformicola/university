package project.student.service.impl;

import com.project.commonlib.dto.ExamDTO;
import com.project.commonlib.dto.ExamStudentDTO;
import com.project.commonlib.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.student.client.ExamClient;
import project.student.client.ExamStudentClient;
import project.student.model.Student;
import project.student.repository.StudentRepository;
import project.student.service.ExamStudentService;
import project.student.service.dto.StudentExamsDTO;
import project.student.service.mapper.StudentMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExamStudentServiceImpl implements ExamStudentService {


    private ExamStudentClient examStudentClient;

    private ExamClient examClient;

    private StudentRepository studentRepository;

    private StudentMapper studentMapper;

    public ExamStudentServiceImpl(ExamStudentClient examStudentClient, ExamClient examClient, StudentRepository studentRepository, StudentMapper studentMapper ) {
        this.examStudentClient = examStudentClient;
        this.examClient = examClient;
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

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
            if(!student.isPresent()){
                throw new Exception("studente con email: " + email + " non trovato");
            }
            Integer idStudente = student.get().getId();
            List<ExamStudentDTO> examStudentDTO = examStudentClient.getExamStudentByIdStudent(idStudente);
            //List<Integer> idExams = examStudentDTO.stream().collect(examStudentDTO.)

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

    @Override
    public String deleteStudentExamsByEmail(String email) throws Exception {
        try {
            Optional<Student> student = studentRepository.findByEmail(email);
            if (student.isPresent()) {
                Integer idStudente = student.get().getId();
                String deleteExamStudents = examStudentClient.deleteExamsByIdStudente(idStudente);
                studentRepository.deleteById(idStudente);
                return deleteExamStudents + " eliminazione studente effettuata";
            } else {
                throw new Exception("studente con email: " + email + " non trovato");
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public List<StudentExamsDTO> getAllStudentExams() throws Exception {
        try {
            List<StudentExamsDTO> result = new ArrayList<>();
            List<StudentDTO> studentDTOS = new ArrayList<>();
            List<Integer> listIdStudents = new ArrayList<>();
            List<Integer> idExams = new ArrayList<>();
            List<ExamStudentDTO> examStudentDTOS = new ArrayList<>();
            List<Student> students = studentRepository.findAll();
            ExamDTO currentexamDTO = new ExamDTO();
            for (Student s : students){
                studentDTOS.add(studentMapper.toDto(s));
                listIdStudents.add(s.getId());
            }
            examStudentDTOS = examStudentClient.getByIdStudente(listIdStudents);

            for (ExamStudentDTO e : examStudentDTOS ){
                idExams.add(e.getIdEsame());
            }
            List<ExamDTO> examDTOS = examClient.getExamsByIdList(idExams);
            for (StudentDTO s : studentDTOS){
                // 1. recuperare tutti gli examstudent con l'id dello studente = s.id
                // 2. dalla lista degli examStudent ottenuti da punto 1 estraggo gli id degli esami trovati
                // 3. dalla lista degli id degli esami vado a prendere il suo corrispondente examDTO
                List<ExamStudentDTO> currentExamStudent = new ArrayList<>();

                // .1
                for (ExamStudentDTO e : examStudentDTOS){
                    if(s.getId() == e.getIdStudente()){
                        currentExamStudent.add(e);
                    }
                }

                // .2
                List<Integer> examCurrent = new ArrayList<>();
                for (ExamStudentDTO e : currentExamStudent ){
                    examCurrent.add(e.getIdEsame());
                }

                //.3
                List <ExamDTO> ExamDtoForStudent = new ArrayList<>();
                for(Integer e : examCurrent){
                    for(ExamDTO d: examDTOS){
                        if(e == d.getId()){
                            currentexamDTO = d;
                            ExamDtoForStudent.add(currentexamDTO);
                        }
                    }
                }

                result.add(StudentExamsDTO.builder()
                        .studentDTO(s)
                        .examDTOS(ExamDtoForStudent)
                        .build());

            }
            return result;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

}
