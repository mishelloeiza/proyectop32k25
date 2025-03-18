

CREATE TABLE `aplicacion` (
  `id_aplicacion` INT NOT NULL auto_increment,
  `nombre_aplicacion` varchar(25) NOT NULL,
  `estatus_aplicacion` varchar(25) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `aplicacion`
--

INSERT INTO `aplicacion` (`id_aplicacion`, `nombre_aplicacion`, `estatus_aplicacion`) VALUES
(1, 'Compras', '1');

