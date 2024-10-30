ALTER TABLE consertos
    ADD ativo tinyint;

update consertos
set ativo = 1;