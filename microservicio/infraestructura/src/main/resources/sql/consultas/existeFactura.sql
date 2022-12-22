SELECT count(1) FROM factura
WHERE placa_fk = :placaFk AND cant_dias IS NULL;