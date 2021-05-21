package project.student.model;

import lombok.*;
import org.hibernate.annotations.Columns;

import javax.persistence.*;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "studente")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nome")
    private String firstName;
    @Column(name = "cognome")
    private String lastName;
    private String email;

}
