CREATE TABLE vehiculo(
    placa VARCHAR(20) PRIMARY KEY,
    identificacion BIGINT NOT NULL,
    telefono_contacto BIGINT NOT NULL
);

CREATE TABLE factura(
    id_factura SERIAL PRIMARY KEY,
    fecha_ingreso DATE,
    cant_dias INT,
    valor REAL,
    placa_fk VARCHAR (20) NOT NULL,
    FOREIGN KEY (placa_fk) REFERENCES vehiculo(placa) ON DELETE CASCADE ON UPDATE CASCADE
);

