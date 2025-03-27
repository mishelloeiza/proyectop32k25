CREATE TABLE IF NOT EXISTS perfiles(
    id_perfil INT(10) NOT NULL,
    nombre_perfil VARCHAR(85),
    estatus_perfil VARCHAR (1),
  	PRIMARY KEY (id_perfil)
) ENGINE = INNODB CHARSET =latin1;

CREATE TABLE IF NOT EXISTS relusuapl(
    id_relusuapl INT(10) NOT NULL,
    id_aplicacion VARCHAR (25) NOT NULL,
    id_usuario VARCHAR (1) NOT NULL,
    der_insertar VARCHAR(1) NOT NULL,
    der_editar VARCHAR(1) NOT NULL,
    der_eliminar VARCHAR(1) NOT NULL,
    der_imprimir VARCHAR(1) NOT NULL,
    PRIMARY KEY (id_relusuapl),
    FOREIGN KEY (id_aplicacion) references aplicacion(id_aplicacion),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
) ENGINE = INNODB CHARSET =latin1;