CREATE TABLE perfiles(
    id_perfil INT(10) NOT NULL,
    nombre_perfil VARCHAR(85),
    estatus_perfil VARCHAR (1),
  	PRIMARY KEY (id_perfil)
) ENGINE = INNODB CHARSET =latin1;

INSERT INTO perfiles (`id_perfil`, `nombre_perfil`, `estatus_perfil`) VALUES
('111','Gerentes', '1'),
('222','Proveedores', '1'),
('333','Inversores', '1');