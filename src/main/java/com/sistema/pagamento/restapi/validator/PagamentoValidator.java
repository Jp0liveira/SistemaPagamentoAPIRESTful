package com.sistema.pagamento.restapi.validator;

import com.sistema.pagamento.restapi.service.PagamentoService;
import org.springframework.stereotype.Component;

@Component
public class PagamentoValidator {

    public boolean isNumeroCartaoRequired(String metodoPagamento) {
        return "cartao_credito".equals(metodoPagamento) || "cartao_debito".equals(metodoPagamento);
    }

    public boolean isMetodoPagamentoValido(String metodoPagamento) {
        return "boleto".equals(metodoPagamento) || "pix".equals(metodoPagamento) || "cartao_credito".equals(metodoPagamento) || "cartao_debito".equals(metodoPagamento);
    }

    public boolean isStatusValido(String statusAtual, String novoStatus) {
        if (PagamentoService.STATUS_PENDENTE.equals(statusAtual)) {
            return PagamentoService.STATUS_SUCESSO.equals(novoStatus) ||  PagamentoService.STATUS_FALHA.equals(novoStatus);
        }else if (PagamentoService.STATUS_FALHA.equals(statusAtual)) {
            return PagamentoService.STATUS_PENDENTE.equals(novoStatus);
        }
        return false;
    }

}
