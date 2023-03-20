ALTER TABLE medico ADD COLUMN ativo tinyint;
UPDATE medico set ativo = 1;