DROP TABLE IF EXISTS curso;

-- tabla cursos
CREATE TABLE curso(
    idcurso	int(11) NOT NULL AUTO_INCREMENT,
    titulo	varchar(200) NOT NULL,
    horas	int(6) NOT NULL,
    horario	varchar(50) NOT NULL,
    fechainicio DATE NOT NULL,
    PRIMARY KEY	(idcurso),
    UNIQUE KEY IDX_cursos_1 (titulo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- insertando filas
INSERT INTO curso(titulo, horas, horario,fechainicio) 
VALUES('Java Advance', 140,'viernes-sabado','2018-02-21');
INSERT INTO curso(titulo, horas, horario,fechainicio) 
VALUES('SQL Fundamentos', 40, 'martes-jueves','2018-02-21');


