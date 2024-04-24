# Sistema de Pagamento API RESTful

Este é um sistema de API RESTful desenvolvido em Spring Boot para lidar com pagamentos de débitos de pessoas físicas e jurídicas.

## Funcionalidades

- Recebimento de pagamentos
- Atualização do status de pagamentos
- Listagem de pagamentos com filtros
- Exclusão lógica de pagamentos

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Banco de dados H2 (embutido)
- Maven

## Configuração e Execução

1. Certifique-se de ter o JDK 17 e o Maven instalados em seu sistema.
2. Clone este repositório: `git clone https://github.com/seu-usuario/sistema-pagamento-api.git`
3. Navegue até o diretório do projeto: `cd sistema-pagamento-api`
4. Execute o aplicativo: `mvn spring-boot:run`

O aplicativo será executado localmente em [http://localhost:8080](http://localhost:8080).

## Endpoints da API

### Receber Pagamento

- **URL:** `/pagamentos/receber`
- **Método:** POST
- **Payload:** JSON contendo os dados do pagamento
- **Exemplo de Payload:**
  ```json
  {
    "codigoDebito": 123,
    "cpfCnpjPagador": "12345678900",
    "metodoPagamento": "boleto",
    "valorPagamento": 100.00
  }
  
![image](https://github.com/Jp0liveira/SistemaPagamentoAPIRESTful/assets/106454449/7fa9458f-c8eb-45eb-b8c6-1cb2781dc58d)

### Atualizar Status do Pagamento
- **URL:**  `/pagamentos/{id}`
- **Método:** PUT
- **Parâmetros de Query:** ID do pagamento e novo status do pagamento
- - **Exemplo de Uso:**
  `pagamentos/1?novoStatus=Processado com Sucesso`

![image](https://github.com/Jp0liveira/SistemaPagamentoAPIRESTful/assets/106454449/574f2bf5-a44a-4c2f-9fe5-d2f2ba69b920)

### Listar Pagamentos

- **URL:** `/pagamentos`
- **Método:** GET
- **Parâmetros de Query:** Código do débito, CPF/CNPJ do pagador, status do pagamento
- **Exemplo de Uso:**
  `/pagamentos/listar?codigoDebito=123&cpfCnpjPagador=12345678900&statusPagamento=Pendente de Processamento`

![image](https://github.com/Jp0liveira/SistemaPagamentoAPIRESTful/assets/106454449/256905a9-156a-43b0-b979-bb5c81d0018c)

### Excluir Pagamento

- **URL:** `/pagamentos/{id}`
- **Método:** DELETE
- **Parâmetros de Path:** ID do pagamento

![image](https://github.com/Jp0liveira/SistemaPagamentoAPIRESTful/assets/106454449/2969478c-a4ee-4878-b55a-8a2fc71b5fee)

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir um problema ou enviar um pull request.

## Licença

Este projeto está licenciado sob a [Licença MIT](https://opensource.org/licenses/MIT).
