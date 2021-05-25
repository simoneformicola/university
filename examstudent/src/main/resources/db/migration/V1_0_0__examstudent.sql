create table esamestudente
(
id int identity primary key,
id_studente int,
id_esame int,
esito int
);

INSERT INTO esamestudente (id_studente,id_esame,esito)
VALUES
(1,1,25),
(1,2,27),
(2,1,19);