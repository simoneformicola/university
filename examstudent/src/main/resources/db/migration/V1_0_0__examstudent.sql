create table esamestudente
(
id int identity primary key,
idStudente int,
idEsame int,
esito int
);

INSERT INTO esamestudente (idStudente,idEsame,esito)
VALUES
(1,1,25),
(1,2,27),
(2,1,19);