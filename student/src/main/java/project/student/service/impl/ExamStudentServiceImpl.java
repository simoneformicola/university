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

            List<Integer> idExams = examStudentDTO.stream().map(ExamStudentDTO::getIdEsame).collect(Collectors.toList());

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
            List<Student> students = studentRepository.findAll();
            //ExamDTO currentexamDTO = new ExamDTO();

            studentDTOS = students.stream().map(student -> studentMapper.toDto(student)).collect(Collectors.toList());
            listIdStudents = students.stream().map(student -> student.getId()).collect(Collectors.toList());

            List<ExamStudentDTO> examStudentDTOS = examStudentClient.getByIdStudente(listIdStudents);

            idExams = examStudentDTOS.stream().map(e -> e.getIdEsame()).collect(Collectors.toList());


            List<ExamDTO> examDTOS = examClient.getExamsByIdList(idExams);

            studentDTOS.forEach(s -> {

                // 1. recuperare tutti gli examstudent con l'id dello studente = s.id
                // 2. dalla lista degli examStudent ottenuti da punto 1 estraggo gli id degli esami trovati
                // 3. dalla lista degli id degli esami vado a prendere il suo corrispondente examDTO
                //currentExamStudent = new ArrayList<>();

                // .1
                List<ExamStudentDTO>  currentExamStudent =  examStudentDTOS.stream().filter(e -> e.getIdStudente().equals(s.getId())).collect(Collectors.toList());


                // .2
                List<Integer> examCurrent = currentExamStudent.stream().map(c -> c.getIdEsame()).collect(Collectors.toList());


                List<ExamDTO> examList = examClient.getAllExams();

                //.3
                List <ExamDTO> examDtoForStudent = new ArrayList<>();

                examDtoForStudent = examList.stream()
                        .filter(exam -> examCurrent.contains(exam.getId()))
                        .collect(Collectors.toList());

                result.add(StudentExamsDTO.builder()
                        .studentDTO(s)
                        .examDTOS(examDtoForStudent)
                        .build());

            });
            return result;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public ExamDTO getExamByOddCreditByEmail(String email) throws Exception {
        try{
            ExamDTO result = new ExamDTO();
            StudentExamsDTO studentExamsDTO = new StudentExamsDTO();
            // 1. recuperare lo studente tramte email
            Optional<Student> s = studentRepository.findByEmail(email);
            if (s.isPresent()){
                StudentDTO studentDTO = studentMapper.toDto(s.get());

                // 2. recuperare la lista degli esami di quello studente
                List<ExamStudentDTO> exams = examStudentClient.getExamStudentByIdStudent(studentDTO.getId());

                // 3. ottenere gli id degli esami dalla list aottenuta prima degli esami effettuati dallo studente
                List<Integer> idExams = exams.stream()
                        .map(ExamStudentDTO::getIdEsame)
                        .collect(Collectors.toList());

                // 4. ottenre la lista degli esami tramite la lista degli id ottenuti in precedenza
                List<ExamDTO> examDTOS = examClient.getExamsByIdList(idExams);

                // 5. costruire l'oggetto con dentro lo studente e la lista degli esami effettuati
                studentExamsDTO.setStudentDTO(studentDTO);
                studentExamsDTO.setExamDTOS(examDTOS);

                /*result = studentExamsDTO.getExamDTOS().stream()
                        .filter(ExamDTO -> ExamDTO.getCredit() %2 != 0 )
                        .findAny().get();*/

                result = studentExamsDTO.getExamDTOS().stream().filter(e -> e.getCredit() % 2 != 0).findAny().orElse(null);
                
            }
            return result;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
