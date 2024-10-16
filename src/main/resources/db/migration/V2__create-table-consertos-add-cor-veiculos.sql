CREATE TABLE consertos (
            id BIGINT AUTO_INCREMENT PRIMARY KEY,
            data_entrada VARCHAR(255),
            data_saida VARCHAR(255),
            mecanico_nome VARCHAR(255),
            mecanico_anos_experiencia INT,
            veiculo_marca VARCHAR(255),
            veiculo_modelo VARCHAR(255),
            veiculo_ano VARCHAR(255),
            veiculo_cor VARCHAR(255)
);
