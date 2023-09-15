package com.henrique.bussinessmanagement.controller;


import com.henrique.bussinessmanagement.dto.RequisicaoBusca;
import com.henrique.bussinessmanagement.dto.RequisicaoDetalheSolicitacaoDeCompra;
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
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ModelAndView findAllSolicitacaoDeCompra(RequisicaoBusca requisicaoBusca){
        ModelAndView mv = new ModelAndView();

        List<SolicitacaoDeCompra> solicitacoesDeCompra = solicitacaoDeCompraRepository.findAll();
        mv.addObject("solicitacoesDeCompra", solicitacoesDeCompra);
        mv.setViewName("SolicitacaoCompra");

        return mv;
    }

    @PostMapping("/buscar")
    public ModelAndView findSolicitacao(RequisicaoBusca requisicaoBusca) {
        List<SolicitacaoDeCompra> solicitacoes = buscaSolicitacao(requisicaoBusca);

        ModelAndView mv = new ModelAndView();
        mv.addObject("solicitacoesDeCompra", solicitacoes);
        mv.setViewName("SolicitacaoCompra");

        return mv;
    }

    @GetMapping("/detalhes/{ParamId}")
    public ModelAndView showDetalheSolicitacao(@PathVariable String ParamId) {
        //TODO -> Gerar erro caso o parâmetro não for válido

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
    public ModelAndView formularioNovaSolicitacaoDeCompra(RequisicaoDetalheSolicitacaoDeCompra requisicaoDetalheSolicitacaoDeCompra){
         return setSolicitacaoForm();
    }

    @PostMapping("/nova/criar")
    @Transactional
    public ModelAndView cadastraNovaSolicitacaoDeCompra(@Valid RequisicaoDetalheSolicitacaoDeCompra requisicaoDetalhe, BindingResult result){
        if (result.hasErrors()) {
            return setSolicitacaoForm();
        }

        SolicitacaoDeCompra solicitacaoDeCompra = new SolicitacaoDeCompra();

        solicitacaoDeCompra = solicitacaoDeCompraRepository.save(solicitacaoDeCompra);
        solicitacaoDeCompra.setCodigo();
        solicitacaoDeCompraRepository.save(solicitacaoDeCompra);


            Produto produto = produtoRepository.findById(requisicaoDetalhe.getIdProduto());
            CentroDeCusto centroDeCusto = centroDeCustoRepository.findById(requisicaoDetalhe.getIdCentroDeCusto());


            DetalheSolicitacaoDeCompra detalheSolicitacaoDeCompra = requisicaoDetalhe.toDetalheSolicitacaoDeCompra();
            detalheSolicitacaoDeCompra.setProduto(produto);
            detalheSolicitacaoDeCompra.setCentroDeCusto(centroDeCusto);

            detalheSolicitacaoDeCompra.LinkToNewSolicitacao(solicitacaoDeCompra);
            detalheSolicitacaoDeCompraRepository.save(detalheSolicitacaoDeCompra);


        return new ModelAndView("redirect:/solicitacao_compra");
    }

    public List<SolicitacaoDeCompra> buscaSolicitacao(RequisicaoBusca requisicaoBusca){
        List<SolicitacaoDeCompra> solicitacoes = new ArrayList<>();

        switch (requisicaoBusca.getAtributo().toLowerCase()) {
            case "produto" -> {
                if (validProduto(requisicaoBusca.getValor())) {
                    Produto produto = produtoRepository.findByCodigo(requisicaoBusca.getValor()).get(0);
                    List<DetalheSolicitacaoDeCompra> detalhes = detalheSolicitacaoDeCompraRepository.findByProduto(produto);
                    solicitacoes.add(detalhes.get(0).getSolicitacaoDeCompra());
                } else return solicitacoes;
            }
            case "codigo" -> {
                if (validCodigo(requisicaoBusca.getValor())) {
                    solicitacoes.add(solicitacaoDeCompraRepository.findByCodigo(requisicaoBusca.getValor()).get(0));
                } else return solicitacoes;
            }
            case "centro" -> {
                if (validCentroDeCusto(requisicaoBusca.getValor())) {
                    CentroDeCusto centroDeCusto = centroDeCustoRepository.findByCodigo(requisicaoBusca.getValor()).get(0);
                    List<DetalheSolicitacaoDeCompra> detalhes = detalheSolicitacaoDeCompraRepository.findByCentroDeCusto(centroDeCusto);
                    solicitacoes.add(detalhes.get(0).getSolicitacaoDeCompra());
                } else return solicitacoes;
            }
            case "status" -> {
                if (validStatus(requisicaoBusca.getValor())){
                    solicitacoes.addAll(solicitacaoDeCompraRepository.findByStatus(Status.valueOf(requisicaoBusca.getValor())));
                }


            }
        }


         return solicitacoes;
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

    public ModelAndView setSolicitacaoForm(){
        ModelAndView mv = new ModelAndView();

        List<Produto> produtos = produtoRepository.findAll();
        List<CentroDeCusto> centrosDeCustos = centroDeCustoRepository.findAll();

        mv.addObject("produtos", produtos);
        mv.addObject("centrosDeCusto", centrosDeCustos);
        mv.setViewName("FormSolicitacaoCompra");

        return mv;
    }




}
