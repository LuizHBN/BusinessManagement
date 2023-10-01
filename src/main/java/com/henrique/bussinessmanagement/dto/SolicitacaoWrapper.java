package com.henrique.bussinessmanagement.dto;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public class SolicitacaoWrapper {

    @Valid
    List<DetalheSolicitacaoDeCompraDTO> detalhes = new ArrayList<>();

    public boolean isValid(){
        if (this.detalhes.get(0).isFirstValid()){
            for (DetalheSolicitacaoDeCompraDTO requisicao : detalhes){
                if(!requisicao.isValid()) return false;
            }
        }else return false;
        return true;
    }

    public void addRequisicaoDetalhe(DetalheSolicitacaoDeCompraDTO requisicaoDetalhe){
        this.detalhes.add(requisicaoDetalhe);
    }

    public SolicitacaoWrapper(List<DetalheSolicitacaoDeCompraDTO> detalhes) {
        this.detalhes = detalhes;
    }


    public SolicitacaoWrapper() {
    }



    public List<DetalheSolicitacaoDeCompraDTO> getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(List<DetalheSolicitacaoDeCompraDTO> detalhes) {
        this.detalhes = detalhes;
    }
}
