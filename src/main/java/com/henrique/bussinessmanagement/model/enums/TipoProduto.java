package com.henrique.bussinessmanagement.model.enums;

import java.util.ArrayList;
import java.util.List;

public enum TipoProduto {
    COMPRA_DIRETA, ESTOQUE, SERVICO;

    public static List<String> todosOsTipos(){
        List<String> tipos = new ArrayList<>();
        TipoProduto[] enumProdutos = TipoProduto.values();

        for (TipoProduto tipo : enumProdutos) {
            tipos.add(tipo.name());
        }

        return tipos;
    }
}
