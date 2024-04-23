-- Inserir valores pr�-definidos na tabela Pagamento
INSERT INTO Pagamento (codigo_debito, cpf_cnpj_pagador, metodo_pagamento, numero_cartao, valor, status)
VALUES (1, '12345678900', 'boleto', null, 100.00, 'Pendente de Processamento'),
       (2, '98765432100', 'cartao_credito', '1234 5678 9012 3456', 150.00, 'Processado com Sucesso'),
       (3, '98765432100', 'cartao_debito', '1234 5678 9012 3456', 540.00, 'Pendente de Processamento'),
       (4, '98765432100', 'boleto', null, 0, 'Processado com Falha'),
       (5, '98765432100', 'pix', null, 0, 'Inativo');

