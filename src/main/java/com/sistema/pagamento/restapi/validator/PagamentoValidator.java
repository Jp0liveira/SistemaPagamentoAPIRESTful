package com.sistema.pagamento.restapi.validator;

import org.springframework.stereotype.Component;

@Component
public class PagamentoValidator {

    public boolean isNumeroCartaoRequired(String metodoPagamento) {
        return "cartao_credito".equals(metodoPagamento) || "cartao_debito".equals(metodoPagamento);
    }

    public boolean isMetodoPagamentoValido(String metodoPagamento) {
        return "boleto".equals(metodoPagamento) || "pix".equals(metodoPagamento) || "cartao_credito".equals(metodoPagamento) || "cartao_debito".equals(metodoPagamento);
    }

}
