package com.sistema.pagamento.restapi.repository;

import com.sistema.pagamento.restapi.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    @Query("SELECT p FROM Pagamento p WHERE (:codigoDebito IS NULL OR p.codigoDebito = :codigoDebito) " +
            "AND (:cpfCnpjPagador IS NULL OR p.cpfCnpjPagador = :cpfCnpjPagador) " +
            "AND (:statusPagamento IS NULL OR p.status = :statusPagamento)")
    List<Pagamento> findByCodigoDebitoAndCpfCnpjPagadorAndStatus(Long codigoDebito, String cpfCnpjPagador, String statusPagamento);

}
