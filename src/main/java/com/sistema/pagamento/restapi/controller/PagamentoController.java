package com.sistema.pagamento.restapi.controller;

import com.sistema.pagamento.restapi.model.Pagamento;
import com.sistema.pagamento.restapi.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {
    private final PagamentoService pagamentoService;

    @Autowired
    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping("/receber")
    public ResponseEntity<?> receberPagamento(@RequestBody Pagamento pagamento) {
        return pagamentoService.receberPagamento(pagamento);
    }

    @PutMapping("/{id}/atualizar-status")
    public ResponseEntity<?> atualizarStatusPagamento(@PathVariable Long id, @RequestParam String novoStatus) {
        return pagamentoService.atualizarStatusPagamento(id, novoStatus);
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarPagamentos(@RequestParam(required = false, name = "codigoDebito") Long codigoDebito,
                                              @RequestParam(required = false, name = "cpfCnpjPagador") String cpfCnpjPagador,
                                              @RequestParam(required = false, name = "statusPagamento") String statusPagamento) {
        return pagamentoService.listarPagamentos(codigoDebito, cpfCnpjPagador, statusPagamento);
    }

}
