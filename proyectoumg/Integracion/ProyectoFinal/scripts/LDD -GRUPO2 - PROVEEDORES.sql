CREATE TABLE IF NOT EXISTS proveedor(
    id_proveedor INT,
    nombre_proveedor VARCHAR(60),
    direccion_proveedor VARCHAR(60),
    telefono_proveedor VARCHAR(20),
    email_proveedor VARCHAR(60),
    saldo_proveedor INT,
    estatus_proveedor INT,
    fecha_registro DATETIME,
    PRIMARY KEY (id_proveedor)
)ENGINE=InnoDB CHARACTER SET = latin1;