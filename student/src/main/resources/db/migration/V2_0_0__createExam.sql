create table esame
(
codice int identity primary key,
nome varchar(255),
numero_studenti int
);

INSERT INTO esame (nome,numero_studenti)
VALUES
('Informatica',12),
('Fisica',14),
('Matematica',4);