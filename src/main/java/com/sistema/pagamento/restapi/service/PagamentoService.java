package com.sistema.pagamento.restapi.service;

import com.sistema.pagamento.restapi.model.Pagamento;
import com.sistema.pagamento.restapi.repository.PagamentoRepository;
import com.sistema.pagamento.restapi.validator.PagamentoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PagamentoService {
    private final PagamentoRepository pagamentoRepository;
    public static final String STATUS_PENDENTE = "Pendente de Processamento";
    public static final String STATUS_SUCESSO = "Processado com Sucesso";
    public static final String STATUS_FALHA = "Processado com Falha";
    public static final String STATUS_INATIVO = "Inativo";
    private final PagamentoValidator pagamentoValidator;

    @Autowired
    public PagamentoService(PagamentoRepository pagamentoRepository, PagamentoValidator pagamentoValidator) {
        this.pagamentoRepository = pagamentoRepository;
        this.pagamentoValidator = pagamentoValidator;
    }

    public ResponseEntity<?> receberPagamento(Pagamento pagamento) {
        if (!pagamentoValidator.isMetodoPagamentoValido(pagamento.getMetodoPagamento())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Método de pagamento inválido.");
        }

        if (pagamentoValidator.isNumeroCartaoRequired(pagamento.getMetodoPagamento()) && pagamento.getNumeroCartao() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Número do cartão é obrigatório para pagamento com cartão.");
        }

        pagamento.setStatus(STATUS_PENDENTE);
        Pagamento novoPagamento = pagamentoRepository.save(pagamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPagamento);
    }

    public ResponseEntity<?> atualizarStatusPagamento(Long id, String novoStatus) {
        Pagamento pagamento = pagamentoRepository.getById(id);

        if (!pagamentoValidator.isStatusValido(pagamento.getStatus(), novoStatus)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não é possível atualizar o status do pagamento para o novo status fornecido.");
        }
        pagamento.setStatus(novoStatus);
        Pagamento pagamentoAtualizado = pagamentoRepository.save(pagamento);
        return ResponseEntity.ok(pagamentoAtualizado);
    }

    public ResponseEntity<?> listarPagamentos(Long codigoDebito, String cpfCnpjPagador, String statusPagamento) {
        List<Pagamento> pagamentos;

        if (codigoDebito != null || cpfCnpjPagador != null || statusPagamento != null) {
            pagamentos =  pagamentoRepository.findByCodigoDebitoAndCpfCnpjPagadorAndStatus(codigoDebito, cpfCnpjPagador, statusPagamento);
        } else {
            pagamentos = pagamentoRepository.findAll();
        }

        if (pagamentos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum pagamento encontrado com os filtros fornecidos.");
        }

        return ResponseEntity.ok(pagamentos);
    }

    public ResponseEntity<?> excluirPagamento(Long id) {
        Pagamento pagamento = pagamentoRepository.getById(id);

        if (pagamento == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pagamento não encontrado com o ID fornecido.");
        }

        if (!STATUS_PENDENTE.equals(pagamento.getStatus())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não é possível excluir um pagamento que não esteja com status Pendente de Processamento.");
        }

        pagamento.setStatus(STATUS_INATIVO);
        pagamentoRepository.save(pagamento);
        return ResponseEntity.ok("Pagamento excluído com sucesso.");
    }
}
