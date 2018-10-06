DROP TABLE IF EXISTS `cursos`;

CREATE TABLE `cursos` (
  `idcurso` int(11) NOT NULL AUTO_INCREMENT,
  `curso` varchar(50) NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaFin` date NOT NULL,
  `cantidadHoras` int(11) NOT NULL,
  `ambiente` varchar(50) NOT NULL,

  PRIMARY KEY (`idcurso`),
  UNIQUE KEY `IDX_cursos_2` (`curso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO cursos(curso, fechaInicio, fechaFin,cantidadHoras,ambiente) 
VALUES('Java Advance','2018-02-21','2018-02-21',140,'siste');

INSERT INTO cursos(curso, fechaInicio, fechaFin,cantidadHoras,ambiente) 
VALUES('Java','2018-02-21','2018-02-21',140,'siste');
