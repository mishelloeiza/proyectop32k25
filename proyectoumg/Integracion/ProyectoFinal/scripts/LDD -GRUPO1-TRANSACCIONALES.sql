CREATE TABLE detalle_movimientos_bancarios (
    id_detalle INT AUTO_INCREMENT PRIMARY KEY,
    id_movimiento INT NOT NULL,
    id_tipo_operacion INT NOT NULL,
    id_tipo_pago INT NOT NULL,
    tipo_movimiento VARCHAR(100) NOT NULL,
    monto DECIMAL(15, 2) NOT NULL CHECK (monto > 0),
    FOREIGN KEY (id_movimiento) REFERENCES movimientos_bancarios(id_movimiento) ON DELETE CASCADE,
    FOREIGN KEY (id_tipo_operacion) REFERENCES tipo_operacion_bancaria(id_tipo_operacion) ON DELETE CASCADE,
    FOREIGN KEY (id_tipo_pago) REFERENCES tipo_pago(id_tipo_pago) ON DELETE CASCADE,
    INDEX (id_movimiento),
    INDEX (id_tipo_operacion),
    INDEX (id_tipo_pago)
);

CREATE TABLE cuentas_bancarias (
    id_cuenta INT AUTO_INCREMENT PRIMARY KEY,
    id_banco INT NOT NULL,
    id_tipo_cuenta INT NOT NULL,
    id_tipo_moneda INT NOT NULL,
    saldo DECIMAL(15, 2) NOT NULL DEFAULT 0.00,
    FOREIGN KEY (id_banco) REFERENCES bancos(id_banco) ON DELETE CASCADE,
    FOREIGN KEY (id_tipo_cuenta) REFERENCES tipo_cuenta(id_tipo_cuenta) ON DELETE CASCADE,
    FOREIGN KEY (id_tipo_moneda) REFERENCES tipo_moneda(id_tipo_moneda) ON DELETE CASCADE,
    INDEX (id_banco),
    INDEX (id_tipo_cuenta)
);

CREATE TABLE conciliacion_bancaria (
    id_conciliacion INT AUTO_INCREMENT PRIMARY KEY,
    id_tipo_cuenta INT NOT NULL,  -- Esta columna debe referenciar a la tabla tipo_cuenta
  fecha datetime DEFAULT NULL, 
    status VARCHAR(20) NOT NULL DEFAULT 'pendiente',
    FOREIGN KEY (id_tipo_cuenta) REFERENCES tipo_cuenta(id_tipo_cuenta) ON DELETE CASCADE,  -- Referencia a tipo_cuenta
    INDEX (id_tipo_cuenta)
);

CREATE TABLE movimientos_bancarios (
  id_movimiento_bancario int NOT NULL AUTO_INCREMENT,
  id_tipo_cuenta int DEFAULT NULL,
  fecha datetime DEFAULT NULL,
  PRIMARY KEY (id_movimiento_bancario),
  KEY id_tipo_cuenta (id_tipo_cuenta),
  CONSTRAINT movimientos_bancarios_ibfk_1 FOREIGN KEY (id_tipo_cuenta) REFERENCES tipo_cuenta (id_tipo_cuenta)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;