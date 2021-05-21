create table studente
(
id int identity primary key,
nome varchar(255),
cognome varchar(255),
email varchar(255)
);

INSERT INTO studente (nome,cognome,email)
VALUES
('Mario','Rossi','mariorossi@gmail.com'),
('Lorenzo','Insigne','lorenzoinsigne@gmail.com'),
('Federica','Pellegrini','federicapellegrini@gmail.com');

