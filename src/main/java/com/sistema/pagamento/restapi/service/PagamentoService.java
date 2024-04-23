package com.sistema.pagamento.restapi.service;

import com.sistema.pagamento.restapi.model.Pagamento;
import com.sistema.pagamento.restapi.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoService {
    private final PagamentoRepository pagamentoRepository;

    @Autowired
    public PagamentoService(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    public Pagamento receberPagamento(Pagamento pagamento) {
        pagamento.setStatus("Pendente de Processamento");
        return pagamentoRepository.save(pagamento);
    }

    public Pagamento atualizarStatusPagamento(Long id, String novoStatus) {
        Pagamento pagamento = pagamentoRepository.getById(id);
        pagamento.setStatus(novoStatus);
        return pagamentoRepository.save(pagamento);
    }

    public List<Pagamento> listarPagamentos() {
        return pagamentoRepository.findAll();
    }

}
