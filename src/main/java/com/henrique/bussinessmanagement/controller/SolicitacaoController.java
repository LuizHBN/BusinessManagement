package com.henrique.bussinessmanagement.controller;


import com.henrique.bussinessmanagement.dto.RequisicaoBusca;
import com.henrique.bussinessmanagement.dto.RequisicaoDetalheSolicitacaoDeCompra;
import com.henrique.bussinessmanagement.dto.RequisicaoSolicitacaoDeCompra;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
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

    @GetMapping("/{param}")
    public String findSolicitacoes(@PathVariable("param") String param, Model model) {
        List<SolicitacaoDeCompra> solicitacoes;

        if (param.equalsIgnoreCase("codigo")) {
            solicitacoes = solicitacaoDeCompraRepository.findByCodigo(param);
            model.addAttribute("codigo", param);
        } else if (param.equalsIgnoreCase("status")) {
            solicitacoes = solicitacaoDeCompraRepository.findByStatus(Status.valueOf(param));
            model.addAttribute("status", param);
        } else {
            return "redirect:/pagina_de_erro";
        }

        model.addAttribute("solicitacoes", solicitacoes);
        return "solicitacao_compra";
    }


    @GetMapping("/detalhes/{param}")
    public ModelAndView showDetalheSolicitacao(@PathVariable String param) {
        int id = Integer.parseInt(param);
        List<SolicitacaoDeCompra> solicitacaoDeCompra = solicitacaoDeCompraRepository.findById(id);


        List<DetalheSolicitacaoDeCompra> detalhesSolicitacao = new ArrayList<>();
        for (SolicitacaoDeCompra solicitacao : solicitacaoDeCompra) {
            detalhesSolicitacao.addAll(detalheSolicitacaoDeCompraRepository.findBySolicitacaoDeCompra(solicitacao)) ;
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("solicitacaoDeCompra",solicitacaoDeCompra);
        mv.addObject("detalhesSolicitacao",detalhesSolicitacao);
        mv.setViewName("DetalheSolicitacao");

        return mv;
    }



    @GetMapping("/nova")
    public ModelAndView formularioNovaSolicitacaoDeCompra(RequisicaoDetalheSolicitacaoDeCompra requisicaoDetalheSolicitacaoDeCompra){
        ModelAndView mv = new ModelAndView();

        List<Produto> produtos = produtoRepository.findAll();
        List<CentroDeCusto> centrosDeCustos = centroDeCustoRepository.findAll();

        mv.addObject("produtos", produtos);
        mv.addObject("centrosDeCusto", centrosDeCustos);
        mv.setViewName("FormSolicitacaoCompra");

        return mv;
    }
    @PostMapping("/nova/criar")
    @Transactional
    public ModelAndView cadastraNovaSolicitacaoDeCompra(RequisicaoDetalheSolicitacaoDeCompra requisicaoDetalhe){
        SolicitacaoDeCompra solicitacaoDeCompra = new SolicitacaoDeCompra();
       solicitacaoDeCompra = solicitacaoDeCompraRepository.save(solicitacaoDeCompra);

        solicitacaoDeCompra.setCodigo();
        solicitacaoDeCompraRepository.save(solicitacaoDeCompra);

        Optional<Produto> produto = produtoRepository.findById(requisicaoDetalhe.getIdProduto().longValue());
        Optional<CentroDeCusto> centroDeCusto = centroDeCustoRepository.findById(requisicaoDetalhe.getIdCentroDeCusto().longValue());

        DetalheSolicitacaoDeCompra detalheSolicitacaoDeCompra = requisicaoDetalhe.toDetalheSolicitacaoDeCompra(produto.get(), centroDeCusto.get());
        detalheSolicitacaoDeCompra.LinkToNewSolicitacao(solicitacaoDeCompra);
        detalheSolicitacaoDeCompraRepository.save(detalheSolicitacaoDeCompra);


        return new ModelAndView("redirect:/solicitacao_compra");
    }


}
