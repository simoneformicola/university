package project.exam.model;

import lombok.*;

import javax.persistence.*;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "esame")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codice")
    private Integer code;
    @Column(name = "nome")
    private String name;
    @Column(name = "numeroStudenti")
    private Integer n_students; //numero studenti
}
