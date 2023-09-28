package com.henrique.bussinessmanagement.dto;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public class RequisicaoSolicitacaoWrapper {

    @Valid
    List<RequisicaoDetalheSolicitacaoDeCompra> detalhes = new ArrayList<>();

    public boolean isValid(){
        if (this.detalhes.get(0).isFirstValid()){
            for (RequisicaoDetalheSolicitacaoDeCompra requisicao : detalhes){
                if(!requisicao.isValid()) return false;
            }
        }else return false;
        return true;
    }

    public void addRequisicaoDetalhe(RequisicaoDetalheSolicitacaoDeCompra requisicaoDetalhe){
        this.detalhes.add(requisicaoDetalhe);
    }

    public RequisicaoSolicitacaoWrapper(List<RequisicaoDetalheSolicitacaoDeCompra> detalhes) {
        this.detalhes = detalhes;
    }


    public RequisicaoSolicitacaoWrapper() {
    }



    public List<RequisicaoDetalheSolicitacaoDeCompra> getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(List<RequisicaoDetalheSolicitacaoDeCompra> detalhes) {
        this.detalhes = detalhes;
    }
}
