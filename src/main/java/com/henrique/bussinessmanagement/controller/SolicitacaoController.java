package com.henrique.bussinessmanagement.controller;


import com.henrique.bussinessmanagement.dto.BuscaDTO;
import com.henrique.bussinessmanagement.dto.DetalheSolicitacaoDeCompraDTO;
import com.henrique.bussinessmanagement.dto.SolicitacaoWrapper;
import com.henrique.bussinessmanagement.model.CentroDeCusto;
import com.henrique.bussinessmanagement.model.DetalheSolicitacaoDeCompra;
import com.henrique.bussinessmanagement.model.Produto;
import com.henrique.bussinessmanagement.model.SolicitacaoDeCompra;
import com.henrique.bussinessmanagement.model.enums.Status;
import com.henrique.bussinessmanagement.repository.CentroDeCustoRepository;
import com.henrique.bussinessmanagement.repository.DetalheSolicitacaoDeCompraRepository;
import com.henrique.bussinessmanagement.repository.ProdutoRepository;
import com.henrique.bussinessmanagement.repository.SolicitacaoDeCompraRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/solicitacao_compra")
public class SolicitacaoController {

    @Autowired
    SolicitacaoDeCompraRepository solicitacaoDeCompraRepository;
    @Autowired
    DetalheSolicitacaoDeCompraRepository detalheSolicitacaoDeCompraRepository;
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    CentroDeCustoRepository centroDeCustoRepository;

    @GetMapping()
    public ModelAndView findAllSolicitacaoDeCompra(BuscaDTO buscaDTO){
        ModelAndView mv = new ModelAndView();

        List<SolicitacaoDeCompra> solicitacoesDeCompra = solicitacaoDeCompraRepository.findAll();
        mv.addObject("solicitacoesDeCompra", solicitacoesDeCompra);
        mv.setViewName("SolicitacaoCompra");

        return mv;
    }

    @PostMapping("/buscar")
    public ModelAndView findSolicitacao(BuscaDTO buscaDTO) {
        List<SolicitacaoDeCompra> solicitacoes = buscaSolicitacao(buscaDTO);

        ModelAndView mv = new ModelAndView();
        mv.addObject("solicitacoesDeCompra", solicitacoes);
        mv.setViewName("SolicitacaoCompra");

        return mv;
    }

    @GetMapping("/detalhes/{ParamId}")
    public ModelAndView showDetalheSolicitacao(@PathVariable String ParamId) {

        int id = Integer.parseInt(ParamId);
        SolicitacaoDeCompra solicitacaoDeCompra = solicitacaoDeCompraRepository.findById(id).get(0);


        List<DetalheSolicitacaoDeCompra> detalhesSolicitacao = new ArrayList<>();
        detalhesSolicitacao = detalheSolicitacaoDeCompraRepository.findBySolicitacaoDeCompra(solicitacaoDeCompra);


        ModelAndView mv = new ModelAndView();
        mv.addObject("solicitacaoDeCompra",solicitacaoDeCompra);
        mv.addObject("detalhesSolicitacao",detalhesSolicitacao);
        mv.setViewName("DetalheSolicitacao");

        return mv;
    }

    @GetMapping("/nova")
    public ModelAndView formularioNovaSolicitacaoDeCompra(DetalheSolicitacaoDeCompraDTO requisicaoDetalhe){
         return setSolicitacaoForm();
    }

    public ModelAndView setSolicitacaoForm(){
        ModelAndView mv = new ModelAndView();

        SolicitacaoWrapper solicitacaoWrapper = new SolicitacaoWrapper();

        for(int i = 0; i<8; i++) {
            solicitacaoWrapper.addRequisicaoDetalhe(new DetalheSolicitacaoDeCompraDTO());
        }

        List<Produto> produtos = produtoRepository.findAll();
        List<CentroDeCusto> centrosDeCustos = centroDeCustoRepository.findAll();


        mv.addObject("produtos", produtos);
        mv.addObject("centrosDeCusto", centrosDeCustos);
        mv.addObject("requisicoes", solicitacaoWrapper);
        mv.setViewName("FormSolicitacaoCompra");

        return mv;
    }

    @PostMapping("/nova/criar")
    @Transactional
    public ModelAndView cadastraNovaSolicitacaoDeCompra(@ModelAttribute SolicitacaoWrapper requisicao, BindingResult result){
       if(!requisicao.isValid()){
           return setSolicitacaoForm();
       }
        saveAllDetalhes(requisicao);

        return new ModelAndView("redirect:/solicitacao_compra");
    }

    public List<SolicitacaoDeCompra> buscaSolicitacao(BuscaDTO buscaDTO){
        List<SolicitacaoDeCompra> solicitacoes = new ArrayList<>();

        switch (buscaDTO.getAtributo().toLowerCase()) {
            case "produto" -> {
                if (validProduto(buscaDTO.getValor())) {
                    List<Produto> produtos = produtoRepository.findByCodigo(buscaDTO.getValor());
                        List<DetalheSolicitacaoDeCompra> detalhes = detalheSolicitacaoDeCompraRepository.findByProduto(produtos.get(0));
                    for (DetalheSolicitacaoDeCompra detalhe : detalhes) {
                        solicitacoes.add(detalhe.getSolicitacaoDeCompra());
                    }
                } else return solicitacoes;
            }
            case "codigo" -> {
                if (validCodigo(buscaDTO.getValor())) {
                    solicitacoes.add(solicitacaoDeCompraRepository.findByCodigo(buscaDTO.getValor()).get(0));
                } else return solicitacoes;
            }
            case "centro" -> {
                if (validCentroDeCusto(buscaDTO.getValor())) {
                    CentroDeCusto centroDeCusto = centroDeCustoRepository.findByCodigo(buscaDTO.getValor()).get(0);
                    List<DetalheSolicitacaoDeCompra> detalhes = detalheSolicitacaoDeCompraRepository.findByCentroDeCusto(centroDeCusto);
                    for (DetalheSolicitacaoDeCompra detalhe : detalhes) {
                        solicitacoes.add(detalhe.getSolicitacaoDeCompra());
                    }
                } else return solicitacoes;
            }
            case "status" -> {
                if (validStatus(buscaDTO.getValor())){
                    solicitacoes.addAll(solicitacaoDeCompraRepository.findByStatus(Status.valueOf(buscaDTO.getValor())));
                }


            }
        }
         return solicitacoes;
    }

public SolicitacaoDeCompra createSolicitacaoDeCompra(){
    SolicitacaoDeCompra solicitacaoDeCompra = new SolicitacaoDeCompra();

    solicitacaoDeCompra = solicitacaoDeCompraRepository.save(solicitacaoDeCompra);
    solicitacaoDeCompra.setCodigo();
    solicitacaoDeCompraRepository.save(solicitacaoDeCompra);

    return solicitacaoDeCompra;
}

public void saveAllDetalhes(SolicitacaoWrapper wrapper){
        SolicitacaoDeCompra solicitacaoDeCompra = createSolicitacaoDeCompra();

    for (DetalheSolicitacaoDeCompraDTO requisicaoDetalhe : wrapper.getDetalhes()) {
        if (requisicaoDetalhe.getIdProduto() != 0) {
            Produto produto = produtoRepository.findById(requisicaoDetalhe.getIdProduto());
            CentroDeCusto centroDeCusto = centroDeCustoRepository.findById(requisicaoDetalhe.getIdCentroDeCusto());
            DetalheSolicitacaoDeCompra detalheSolicitacaoDeCompra = requisicaoDetalhe.toDetalheSolicitacaoDeCompra();
            detalheSolicitacaoDeCompra.setProduto(produto);
            detalheSolicitacaoDeCompra.setCentroDeCusto(centroDeCusto);
            detalheSolicitacaoDeCompra.setValorTotal();

            detalheSolicitacaoDeCompra.LinkToNewSolicitacao(solicitacaoDeCompra);
            detalheSolicitacaoDeCompraRepository.save(detalheSolicitacaoDeCompra);
            solicitacaoDeCompra.setValorTotal(BigDecimal.valueOf(solicitacaoDeCompraRepository.ValorTotal(solicitacaoDeCompra)));

        }
    }

}

    public void getValorTotal(){
        double valorTotal;

    }

    public boolean validCodigo (String valor){
        if (!solicitacaoDeCompraRepository.findByCodigo(valor).isEmpty()){
            return true;
        } else
            return false;
    }
    public boolean validProduto (String valor){
        if (!produtoRepository.findByCodigo(valor).isEmpty()){
            return true;
        } else
            return false;
    }

    public boolean validCentroDeCusto(String valor){
        if (!centroDeCustoRepository.findByCodigo(valor).isEmpty()){
            return true;
        } else
            return false;
    }
    public boolean validStatus(String valor){
        if (!solicitacaoDeCompraRepository.findByStatus(Status.valueOf(valor.toUpperCase())).isEmpty()){
            return true;
        } else
            return false;
    }






}
