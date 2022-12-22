update factura
set cant_dias = :cantDias,
	valor = :valor
where placa_fk = :placaFk and cant_dias is null