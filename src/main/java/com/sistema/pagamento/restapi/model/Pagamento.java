package com.sistema.pagamento.restapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long codigoDebito;
    private String cpfCnpjPagador;
    private String metodoPagamento;
    private String numeroCartao;
    private double valor;
    private String status;
}

