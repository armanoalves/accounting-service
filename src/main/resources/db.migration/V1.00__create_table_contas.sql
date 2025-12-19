CREATE TABLE contas (
    id BIGSERIAL PRIMARY KEY,
    data_vencimento DATE NOT NULL,
    data_pagamento DATE,
    valor NUMERIC(19, 2) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    situacao VARCHAR(50) NOT NULL,
    CONSTRAINT chk_situacao_valida CHECK (situacao IN ('PENDENTE', 'PAGO', 'ATRASADO', 'CANCELADO'))
);