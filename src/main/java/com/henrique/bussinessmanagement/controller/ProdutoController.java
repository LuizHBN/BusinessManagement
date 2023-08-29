package com.henrique.bussinessmanagement.controller;


import com.henrique.bussinessmanagement.dto.RequisicaoBuscaProduto;
import com.henrique.bussinessmanagement.dto.RequisicaoProduto;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping()
    public ModelAndView findAllProduto( RequisicaoBuscaProduto requisicaoBuscaProduto){
        ModelAndView mv = new ModelAndView();

        List<Produto> produtos = produtoRepository.findAll();
        mv.addObject("produtos", produtos);
        mv.setViewName("Produto");

        return mv;
    }

    @PostMapping("/buscar")
    public ModelAndView findProduto(RequisicaoBuscaProduto requisicaoBuscaProduto) {
        List<Produto> produtos = buscaProduto(requisicaoBuscaProduto);

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
    public ModelAndView cadastraNovoProduto(@Valid @ModelAttribute("requisicaoProduto") RequisicaoProduto requisicaoProduto, BindingResult result,  RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return setProdutoForm();
        }
        
        Produto produto = requisicaoProduto.toProduto();
        Produto salvo = produtoRepository.save(produto);
        salvo.setCodigo();

        produtoRepository.save(salvo);

        redirectAttributes.addFlashAttribute("redirect", true);
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

    public List<Produto> buscaProduto(RequisicaoBuscaProduto requisicaoBuscaProduto){
        List<Produto> produtos;

        if (requisicaoBuscaProduto.getAtributo().equalsIgnoreCase("descricao")){
            produtos= produtoRepository.findByDescricao(requisicaoBuscaProduto.getValor());
        }
        else if(requisicaoBuscaProduto.getAtributo().equalsIgnoreCase("codigo")){
            produtos = produtoRepository.findByCodigo(requisicaoBuscaProduto.getValor());
        }
        else produtos = produtoRepository.findAll();

        return produtos;
    }
}
