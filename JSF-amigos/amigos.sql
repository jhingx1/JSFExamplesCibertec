DROP TABLE IF EXISTS amigos;

CREATE TABLE amigos (
  idamigo       int(11) NOT NULL AUTO_INCREMENT,
  nombre       varchar(200) NOT NULL,
  correo        varchar(200) NULL,
  telefono      varchar(50) NULL,
  direccion     varchar(200) NULL,
  nacimiento    date NOT NULL,

  PRIMARY KEY (idamigo),
  UNIQUE KEY IDX_amigos_1 (nombre)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO amigos(nombre,correo,telefono,direccion,nacimiento) 
VALUES('Albert Einstein','siste@gmail.com','999999999','Calle Java','2017-02-21');

INSERT INTO amigos(nombre,correo,telefono,direccion,nacimiento) 
VALUES('Albert Luis','siste@gmail.com','999999999','Calle Java','2017-02-21');

INSERT INTO amigos(nombre,correo,telefono,direccion,nacimiento) 
VALUES('Albert corso','siste@gmail.com','999999999','Calle Java','2017-02-21');

INSERT INTO amigos(nombre,correo,telefono,direccion,nacimiento) 
VALUES('Albert Mayor','siste@gmail.com','999999999','Calle Java','2017-02-21');

