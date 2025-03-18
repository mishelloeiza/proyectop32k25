CREATE TABLE perfiles(
    id_perfil INT AUTO_INCREMENT,
    nombre_perfil VARCHAR(85),
    estatus_perfil VARCHAR (1),
  	PRIMARY KEY (id_perfil)
) ENGINE = INNODB CHARSET =latin1;

INSERT INTO perfiles (`nombre_perfil`, `estatus_perfil`) VALUES
('Gerentes', '1'),
('Proveedores', '1'),
('Inversores', '0');