create table esame
(
id int identity primary key,
codice int,
nome varchar(255),
crediti int
);

INSERT INTO esame (codice,nome,crediti)
VALUES
(01,'Informatica',12),
(02,'Fisica',14),
(03,'Matematica',24);