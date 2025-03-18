

CREATE TABLE `aplicacion` (
  `id_aplicacion` INT auto_increment,
  `nombre_aplicacion` varchar(25) NOT NULL,
  `estatus_aplicacion` varchar(25) NOT NULL,
  primary key (id_aplicacion)
) ENGINE = InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `aplicacion`
--

INSERT INTO `aplicacion` (`nombre_aplicacion`, `estatus_aplicacion`) VALUES
('Compras', '1');

