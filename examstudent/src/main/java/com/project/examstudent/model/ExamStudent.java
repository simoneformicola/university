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

    @Column(name = "idStudente")
    private Integer idStudente;

    @Column(name = "idEsame")
    private Integer idEsame;

    @Column(name = "esito")
    private Integer result; //min 18 max 30

}
