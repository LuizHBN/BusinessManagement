package com.henrique.bussinessmanagement;

import com.henrique.bussinessmanagement.controller.SolicitacaoController;
import com.henrique.bussinessmanagement.dto.RequisicaoDetalheSolicitacaoDeCompra;
import com.henrique.bussinessmanagement.dto.RequisicaoSolicitacaoDeCompra;
import com.henrique.bussinessmanagement.model.CentroDeCusto;
import com.henrique.bussinessmanagement.model.DetalheSolicitacaoDeCompra;
import com.henrique.bussinessmanagement.model.Produto;
import com.henrique.bussinessmanagement.model.SolicitacaoDeCompra;
import com.henrique.bussinessmanagement.model.enums.Status;
import com.henrique.bussinessmanagement.model.enums.Unidades;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
class BussinessManagementApplicationTests {

    @Test
    void contextLoads() {
    }

}
