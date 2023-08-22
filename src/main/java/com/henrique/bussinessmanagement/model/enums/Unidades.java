package com.henrique.bussinessmanagement.model.enums;

import java.util.ArrayList;
import java.util.List;

public enum Unidades {
    BARRA, BLOCO,BOBINA, CENTO, CONJUNTO, CENTIMETRO_QUADRADO, CAIXA,
    DUZIA, FOLHA, FRASCO, GALAO, GRAMA, JOGO, KILOGRAMA, LATA, LITRO,
    MILIMETRO, METRO, METRO_QUADRADO, METROS_CUBICOS, PAR, PECA, PACOTE,
    ROLO,SACO,SERVICO,TALAO,TAMBOR,TONELADA,TUBO, UNIDADE;



    public static List<String> TodasUnidades() {
        List<String> unidades = new ArrayList<>();
        Unidades[] enumUnidades = Unidades.values();

        for (Unidades unidade : enumUnidades) {
            unidades.add(unidade.name());
        }

        return unidades;
    }


}
