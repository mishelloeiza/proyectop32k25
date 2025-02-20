CREATE SCHEMA IF NOT EXISTS `umg` DEFAULT CHARACTER SET utf8 ;
USE `umg` ;

CREATE TABLE IF NOT EXISTS alumnos(
    carnet_alumno INT NOT NULL AUTO_INCREMENT,
    nombre_alumno VARCHAR(45),
    direccion_alumno VARCHAR(45),
    telefono_alumno VARCHAR(45),
    email_alumno VARCHAR(20),
    estatus_alumno VARCHAR(1),
    PRIMARY KEY (carnet_alumno))
ENGINE = INNODB CHARACTER SET = LATIN1;

CREATE TABLE IF NOT EXISTS maestros(
    codigo_maestro INT NOT NULL auto_increment,
    nombre_maestro VARCHAR(45),
    direccion_maestro VARCHAR(45),
    telefono_maetro VARCHAR(45),
    email_maestro VARCHAR(20),
    estatus_maestro VARCHAR(1),
    PRIMARY KEY (codigo_maestro))
ENGINE = INNODB CHARACTER SET = LATIN1;

CREATE TABLE IF NOT EXISTS facultades(
	codigo_facultad INT NOT NULL auto_increment,
    nombre_facultad VARCHAR(45),
    estatus_facultad VARCHAR(1),
    PRIMARY KEY (codigo_facultad))
ENGINE = INNODB CHARACTER SET = LATIN1;

CREATE TABLE IF NOT EXISTS carreras(
	codigo_carrera INT NOT NULL auto_increment,
    nombre_carrera VARCHAR(45),
    codigo_facultad INT NOT NULL,
    estatus_carrera VARCHAR(1),
    PRIMARY KEY (codigo_carrera),
    FOREIGN KEY (codigo_facultad) REFERENCES facultades(codigo_facultad))
ENGINE = INNODB CHARACTER SET = LATIN1;

CREATE TABLE IF NOT EXISTS cursos(
    codigo_curso INT NOT NULL auto_increment,
    nombre_curso VARCHAR(45),
    estatus_curso VARCHAR(1),
    PRIMARY KEY (codigo_curso))
ENGINE = INNODB CHARACTER SET = LATIN1;

CREATE TABLE IF NOT EXISTS secciones(
    codigo_seccion INT NOT NULL auto_increment,
    nombre_seccion VARCHAR(45),
    estatus_seccion VARCHAR(1),
    PRIMARY KEY (codigo_seccion))
ENGINE = INNODB CHARACTER SET = LATIN1;

CREATE TABLE IF NOT EXISTS sedes(
    codigo_sede INT NOT NULL auto_increment,
    nombre_sede VARCHAR(45),
    estatus_sede VARCHAR(1),
    PRIMARY KEY (codigo_sede))
ENGINE = INNODB CHARACTER SET = LATIN1;

CREATE TABLE IF NOT EXISTS aulas(
    codigo_aula INT NOT NULL auto_increment,
    nombre_aula VARCHAR(45),
    estatus_aula VARCHAR(1),
    PRIMARY KEY (codigo_aula))
ENGINE = INNODB CHARACTER SET = LATIN1;

CREATE TABLE IF NOT EXISTS jornadas(
    codigo_jornada INT NOT NULL auto_increment,
    nombre_jornada VARCHAR(45),
    estatus_jornada VARCHAR(1),
    PRIMARY KEY (codigo_jornada))
ENGINE = INNODB CHARACTER SET = LATIN1;

CREATE TABLE IF NOT EXISTS asignacioncursosalumnos(
    codigo_carrera INT NOT NULL,
    codigo_sede INT NOT NULL,
    codigo_jornada INT NOT NULL,
    codigo_seccion INT NOT NULL,
    codigo_aula INT NOT NULL,
    codigo_curso INT NOT NULL,
    carnet_alumno INT NOT NULL,
    nota_asignacioncursoalumnos FLOAT(10,2), 
    PRIMARY KEY (codigo_carrera, codigo_sede, codigo_jornada, codigo_seccion, codigo_aula, codigo_curso, carnet_alumno),
    FOREIGN KEY (codigo_carrera) REFERENCES carreras(codigo_carrera),
    FOREIGN KEY (codigo_sede) REFERENCES sedes(codigo_sede),
    FOREIGN KEY (codigo_jornada) REFERENCES jornadas(codigo_jornada),
    FOREIGN KEY (codigo_seccion) REFERENCES secciones(codigo_seccion),
    FOREIGN KEY (codigo_aula) REFERENCES aulas(codigo_aula),
    FOREIGN KEY (codigo_curso) REFERENCES cursos(codigo_curso),
    FOREIGN KEY (carnet_alumno) REFERENCES alumnos(carnet_alumno))
ENGINE = INNODB CHARACTER SET = LATIN1;

CREATE TABLE IF NOT EXISTS asignacioncursosmaestros(
    codigo_carrera INT NOT NULL,
    codigo_sede INT NOT NULL,
    codigo_jornada INT NOT NULL,
    codigo_seccion INT NOT NULL,
    codigo_aula INT NOT NULL,
    codigo_curso INT NOT NULL,
    codigo_maestro INT NOT NULL,
    PRIMARY KEY (codigo_carrera, codigo_sede, codigo_jornada, codigo_seccion, codigo_aula, codigo_curso),
    FOREIGN KEY (codigo_carrera) REFERENCES carreras(codigo_carrera),
    FOREIGN KEY (codigo_sede) REFERENCES sedes(codigo_sede),
    FOREIGN KEY (codigo_jornada) REFERENCES jornadas(codigo_jornada),
    FOREIGN KEY (codigo_seccion) REFERENCES secciones(codigo_seccion),
    FOREIGN KEY (codigo_aula) REFERENCES aulas(codigo_aula),
    FOREIGN KEY (codigo_curso) REFERENCES cursos(codigo_curso),
    FOREIGN KEY (codigo_maestro) REFERENCES maestros(codigo_maestro))
ENGINE = INNODB CHARACTER SET = LATIN1;

CREATE TABLE IF NOT EXISTS usuario(
    id_usuario INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(60),
    password VARCHAR(25),
    PRIMARY KEY (ID_USUARIO))
ENGINE = INNODB CHARACTER SET = LATIN1;

INSERT INTO usuario
	(`username`,`password`)
    VALUES ('usuprueba','123456');

CREATE TABLE IF NOT EXISTS bitacora (
  id_bitacora INT NOT NULL AUTO_INCREMENT,
  accion VARCHAR(60) NOT NULL,
  fecha_hora DATETIME NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (id_bitacora))
ENGINE = InnoDB CHARACTER SET = latin1;

CREATE TRIGGER `log_alumnos` AFTER INSERT ON `alumnos` FOR EACH ROW INSERT INTO bitacora (accion) Values ('IngresoCorrectoDeAlumno');
CREATE TRIGGER `log_alumnosd` AFTER DELETE ON `alumnos` FOR EACH ROW insert into bitacora (accion) values ('EliminacionCorrectaDeAlumnos');
CREATE TRIGGER `log_alumnosu` AFTER UPDATE ON `alumnos` FOR EACH ROW insert into bitacora (accion) values ('ModificacionCorrectaDeAlumnos');

CREATE TRIGGER `log_maestros` AFTER INSERT ON `maestros` FOR EACH ROW INSERT INTO bitacora (accion) Values ('IngresoCorrectoDeMaestros');
CREATE TRIGGER `log_maestrosd` AFTER DELETE ON `maestros` FOR EACH ROW insert into bitacora (accion) values ('EliminacionCorrectaDeMaestros');
CREATE TRIGGER `log_maestrosu` AFTER UPDATE ON `maestros` FOR EACH ROW insert into bitacora (accion) values ('ModificacionCorrectaDeMaestros');

CREATE TRIGGER `log_facultades` AFTER INSERT ON `facultades` FOR EACH ROW INSERT INTO bitacora (accion) Values ('IngresoCorrectoDeFacultades');
CREATE TRIGGER `log_facultadesd` AFTER DELETE ON `facultades` FOR EACH ROW insert into bitacora (accion) values ('EliminacionCorrectaDeFacultades');
CREATE TRIGGER `log_facultadesu` AFTER UPDATE ON `facultades` FOR EACH ROW insert into bitacora (accion) values ('ModificacionCorrectaDeFacultades');

CREATE TRIGGER `log_carreras` AFTER INSERT ON `carreras` FOR EACH ROW INSERT INTO bitacora (accion) Values ('IngresoCorrectoDeCarreras');
CREATE TRIGGER `log_carrerasd` AFTER DELETE ON `carreras` FOR EACH ROW insert into bitacora (accion) values ('EliminacionCorrectaDeCarreras');
CREATE TRIGGER `log_carrerasu` AFTER UPDATE ON `carreras` FOR EACH ROW insert into bitacora (accion) values ('ModificacionCorrectaDeCarreras');

CREATE TRIGGER `log_cursos` AFTER INSERT ON `cursos` FOR EACH ROW INSERT INTO bitacora (accion) Values ('IngresoCorrectoDeCursos');
CREATE TRIGGER `log_cursosd` AFTER DELETE ON `cursos` FOR EACH ROW insert into bitacora (accion) values ('EliminacionCorrectaDeCursos');
CREATE TRIGGER `log_cursosu` AFTER UPDATE ON `cursos` FOR EACH ROW insert into bitacora (accion) values ('ModificacionCorrectaDeCursos');

CREATE TRIGGER `log_secciones` AFTER INSERT ON `secciones` FOR EACH ROW INSERT INTO bitacora (accion) Values ('IngresoCorrectoDeSecciones');
CREATE TRIGGER `log_seccionesd` AFTER DELETE ON `secciones` FOR EACH ROW insert into bitacora (accion) values ('EliminacionCorrectaDeSecciones');
CREATE TRIGGER `log_seccionesu` AFTER UPDATE ON `secciones` FOR EACH ROW insert into bitacora (accion) values ('ModificacionCorrectaDeSecciones');

CREATE TRIGGER `log_sedes` AFTER INSERT ON `sedes` FOR EACH ROW INSERT INTO bitacora (accion) Values ('IngresoCorrectoDeSedes');
CREATE TRIGGER `log_sedesd` AFTER DELETE ON `sedes` FOR EACH ROW insert into bitacora (accion) values ('EliminacionCorrectaDeSedes');
CREATE TRIGGER `log_sedesu` AFTER UPDATE ON `sedes` FOR EACH ROW insert into bitacora (accion) values ('ModificacionCorrectaDeSedes');

CREATE TRIGGER `log_aulas` AFTER INSERT ON `aulas` FOR EACH ROW INSERT INTO bitacora (accion) Values ('IngresoCorrectoDeAulas');
CREATE TRIGGER `log_aulasd` AFTER DELETE ON `aulas` FOR EACH ROW insert into bitacora (accion) values ('EliminacionCorrectaDeAulas');
CREATE TRIGGER `log_aulasu` AFTER UPDATE ON `aulas` FOR EACH ROW insert into bitacora (accion) values ('ModificacionCorrectaDeAulas');

CREATE TRIGGER `log_jornadas` AFTER INSERT ON `jornadas` FOR EACH ROW INSERT INTO bitacora (accion) Values ('IngresoCorrectoDeJornadas');
CREATE TRIGGER `log_jornadasd` AFTER DELETE ON `jornadas` FOR EACH ROW insert into bitacora (accion) values ('EliminacionCorrectaDeJornadas');
CREATE TRIGGER `log_jornadasu` AFTER UPDATE ON `jornadas` FOR EACH ROW insert into bitacora (accion) values ('ModificacionCorrectaDeJornadas');

CREATE TRIGGER `log_asignacionalumnos` AFTER INSERT ON `asignacioncursosalumnos` FOR EACH ROW INSERT INTO bitacora (accion) Values ('IngresoCorrectoDeAsignacionCursosAlumnos');
CREATE TRIGGER `log_asignacionalumnosd` AFTER DELETE ON `asignacioncursosalumnos` FOR EACH ROW insert into bitacora (accion) values ('EliminacionCorrectaDeAsignacionCursosAlumnos');
CREATE TRIGGER `log_asignacionalumnosu` AFTER UPDATE ON `asignacioncursosalumnos` FOR EACH ROW insert into bitacora (accion) values ('ModificacionCorrectaDeAsignacionCursosAlumnos');

CREATE TRIGGER `log_asignacionmaestros` AFTER INSERT ON `asignacioncursosmaestros` FOR EACH ROW INSERT INTO bitacora (accion) Values ('IngresoCorrectoDeAsignacionCursosMaestros');
CREATE TRIGGER `log_asignacionmaestrosd` AFTER DELETE ON `asignacioncursosmaestros` FOR EACH ROW insert into bitacora (accion) values ('EliminacionCorrectaDeAsignacionCursosMaestros');
CREATE TRIGGER `log_asignacionmaestrosu` AFTER UPDATE ON `asignacioncursosmaestros` FOR EACH ROW insert into bitacora (accion) values ('ModificacionCorrectaDeAsignacionCursosMaestros');