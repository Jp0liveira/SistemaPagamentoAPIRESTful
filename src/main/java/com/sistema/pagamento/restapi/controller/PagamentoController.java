package com.sistema.pagamento.restapi.controller;

import com.sistema.pagamento.restapi.model.Pagamento;
import com.sistema.pagamento.restapi.service.PagamentoService;
import com.sistema.pagamento.restapi.validator.PagamentoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {
    private final PagamentoService pagamentoService;
    private final PagamentoValidator pagamentoValidator;

    @Autowired
    public PagamentoController(PagamentoService pagamentoService, PagamentoValidator pagamentoValidator) {
        this.pagamentoService = pagamentoService;
        this.pagamentoValidator = pagamentoValidator;
    }

    @PostMapping("/receber")
    public ResponseEntity<?> receberPagamento(@RequestBody Pagamento pagamento) {
        if (!pagamentoValidator.isMetodoPagamentoValido(pagamento.getMetodoPagamento())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Método de pagamento inválido.");
        }

        if (pagamentoValidator.isNumeroCartaoRequired(pagamento.getMetodoPagamento()) && pagamento.getNumeroCartao() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Número do cartão é obrigatório para pagamento com cartão.");
        }

        Pagamento novoPagamento = pagamentoService.receberPagamento(pagamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPagamento);
    }

    @PutMapping("/{id}/atualizar-status")
    public Pagamento atualizarStatusPagamento(@PathVariable Long id, @RequestParam String novoStatus) {
        return pagamentoService.atualizarStatusPagamento(id, novoStatus);
    }

    @GetMapping("/listar")
    public List<Pagamento> listarPagamentos() {
        return pagamentoService.listarPagamentos();
    }

}
