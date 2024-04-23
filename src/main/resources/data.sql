-- Inserir valores pré-definidos na tabela Pagamento
INSERT INTO Pagamento (codigo_debito, cpf_cnpj_pagador, metodo_pagamento, numero_cartao, valor, status)
VALUES (1, '12345678900', 'boleto', null, 100.00, 'Pendente de Processamento'),
       (2, '98765432100', 'cartao_credito', '1234 5678 9012 3456', 150.00, 'Pendente de Processamento'),
       (3, '98765432100', 'pix', null, 200.00, 'Pendente de Processamento');
