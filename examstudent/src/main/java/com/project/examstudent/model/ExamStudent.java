package com.project.examstudent.model;

import lombok.*;

import javax.persistence.*;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "esamestudente")
public class ExamStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_studente")
    private Integer idStudente;

    @Column(name = "id_esame")
    private Integer idEsame;

    @Column(name = "esito")
    private Integer result; //min 18 max 30

}
