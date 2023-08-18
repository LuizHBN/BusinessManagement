package com.henrique.bussinessmanagement.controller;

import com.henrique.bussinessmanagement.dto.RequisicaoDetalheSolicitacaoDeCompra;
import com.henrique.bussinessmanagement.dto.RequisicaoSolicitacaoDeCompra;
import com.henrique.bussinessmanagement.model.DetalheSolicitacaoDeCompra;
import com.henrique.bussinessmanagement.model.SolicitacaoDeCompra;
import com.henrique.bussinessmanagement.model.enums.Status;
import com.henrique.bussinessmanagement.repository.DetalheSolicitacaoDeCompraRepository;
import com.henrique.bussinessmanagement.repository.SolicitacaoDeCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/solicitacao_compra")
public class SolicitacaoController {

    @Autowired
    SolicitacaoDeCompraRepository solicitacaoDeCompraRepository;
    DetalheSolicitacaoDeCompraRepository detalheSolicitacaoDeCompraRepository;

    @GetMapping()
    public ModelAndView findAllSolicitacaoDeCompra(){
        ModelAndView mv = new ModelAndView();

       List<SolicitacaoDeCompra> solicitacoesDeCompra = solicitacaoDeCompraRepository.findAll();
        mv.addObject(solicitacoesDeCompra);
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

    @GetMapping("/nova_solicitacao")
    public ModelAndView formularioNovaSolicitacaoDeCompra(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("FormSolicitacaoCompra");

        return mv;
    }
    @PostMapping("/criar")
    public String cadastraNovaSolicitacaoDeCompra(RequisicaoSolicitacaoDeCompra requisicaoSolicitacao,
                                                  RequisicaoDetalheSolicitacaoDeCompra requisicaoDetalhe){

        SolicitacaoDeCompra solicitacao = requisicaoSolicitacao.toSolicitacaoDeCompra();
        DetalheSolicitacaoDeCompra detalhe = requisicaoDetalhe.toDetalheSolicitacaoDeCompra(solicitacao);

        solicitacaoDeCompraRepository.save(solicitacao);
        detalheSolicitacaoDeCompraRepository.save(detalhe);

        return "redirect:/solicitacao_compra";
    }


}
