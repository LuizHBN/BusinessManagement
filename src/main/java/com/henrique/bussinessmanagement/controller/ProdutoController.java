package com.henrique.bussinessmanagement.controller;


import com.henrique.bussinessmanagement.dto.RequisicaoProduto;
import com.henrique.bussinessmanagement.model.Produto;
import com.henrique.bussinessmanagement.repository.ProdutoRepository;
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
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping()
    public ModelAndView findAllProduto(){
        ModelAndView mv = new ModelAndView();

        List<Produto> produtos = produtoRepository.findAll();
        mv.addObject(produtos);
        mv.setViewName("Produto");


        return mv;
    }

    @GetMapping("/{param}")
    public String findProdutos(@PathVariable("param") String param, Model model) {
        List<Produto> produtos;

        if (param.equalsIgnoreCase("codigo")) {
            produtos = produtoRepository.findByCodigo(param);
            model.addAttribute("codigo", param);
        } else if (param.equalsIgnoreCase("descricao")) {
            produtos = produtoRepository.findByDescricao(param);
            model.addAttribute("descricao", param);
        } else {
            return "redirect:/produto";
        }

        model.addAttribute("produtos", produtos);
        return "produto";
    }

    @GetMapping("/novo_produto")
    public ModelAndView formularioNovoProduto(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("FormProduto");

        return mv;
    }
    @PostMapping("/criar")
    public String cadastraNovoProduto(RequisicaoProduto requisicaoProduto){
        Produto produto = requisicaoProduto.toProduto();

        produtoRepository.save(produto);

        return "redirect:/produto";
    }

}
