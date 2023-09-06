package com.henrique.bussinessmanagement.controller;


import com.henrique.bussinessmanagement.dto.RequisicaoBusca;
import com.henrique.bussinessmanagement.dto.RequisicaoProduto;
import com.henrique.bussinessmanagement.model.DetalheSolicitacaoDeCompra;
import com.henrique.bussinessmanagement.model.Produto;
import com.henrique.bussinessmanagement.model.enums.TipoProduto;
import com.henrique.bussinessmanagement.model.enums.Unidades;
import com.henrique.bussinessmanagement.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping()
    public ModelAndView findAllProduto(RequisicaoBusca requisicaoBusca){
        ModelAndView mv = new ModelAndView();

        List<Produto> produtos = produtoRepository.findAll();
        mv.addObject("produtos", produtos);
        mv.setViewName("Produto");

        return mv;
    }

    @PostMapping("/buscar")
    public ModelAndView findProduto(RequisicaoBusca requisicaoBusca) {
        List<Produto> produtos = buscaProduto(requisicaoBusca);

        ModelAndView mv = new ModelAndView();
        mv.addObject("produtos", produtos);
        mv.setViewName("Produto");

        return mv;
    }


    @GetMapping("/novo")
    public ModelAndView formularioNovoProduto(RequisicaoProduto requisicaoProduto){
        return setProdutoForm();
    }
    @PostMapping("/novo/criar")
    public ModelAndView cadastraNovoProduto(@Valid @ModelAttribute("requisicaoProduto") RequisicaoProduto requisicaoProduto, BindingResult result) {
        if (result.hasErrors()) {
            return setProdutoForm();
        }
        
        Produto produto = requisicaoProduto.toProduto();
        Produto salvo = produtoRepository.save(produto);
        salvo.setCodigo();

        produtoRepository.save(salvo);


        return new ModelAndView("redirect:/produto");

    }

    public ModelAndView setProdutoForm(){
        ModelAndView mv = new ModelAndView();
        List<String> unidades = Unidades.TodasUnidades();
        List<String> tipoProduto = TipoProduto.todosOsTipos();


        mv.setViewName("FormProduto");
        mv.addObject("unidades",unidades);
        mv.addObject("tipoProduto", tipoProduto);

        return mv;
    }

    public List<Produto> buscaProduto(RequisicaoBusca requisicaoBusca){
        List<Produto> produtos;

        if (requisicaoBusca.getAtributo().equalsIgnoreCase("descricao")){
            produtos= produtoRepository.findByDescricao(requisicaoBusca.getValor());
        }
        else if(requisicaoBusca.getAtributo().equalsIgnoreCase("codigo")){
            produtos = produtoRepository.findByCodigo(requisicaoBusca.getValor());
        }
        else produtos = produtoRepository.findAll();

        return produtos;
    }

}
