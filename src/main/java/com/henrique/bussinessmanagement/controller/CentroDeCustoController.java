package com.henrique.bussinessmanagement.controller;

import com.henrique.bussinessmanagement.dto.RequisicaoBuscaCentro;
import com.henrique.bussinessmanagement.dto.RequisicaoBuscaProduto;
import com.henrique.bussinessmanagement.dto.RequisicaoCentroDeCusto;
import com.henrique.bussinessmanagement.dto.RequisicaoProduto;
import com.henrique.bussinessmanagement.model.CentroDeCusto;
import com.henrique.bussinessmanagement.model.Produto;
import com.henrique.bussinessmanagement.model.enums.TipoProduto;
import com.henrique.bussinessmanagement.model.enums.Unidades;
import com.henrique.bussinessmanagement.repository.CentroDeCustoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/centro_de_custo")
public class CentroDeCustoController {

    @Autowired
    CentroDeCustoRepository centroDeCustoRepository;

    @GetMapping()
    public ModelAndView findAllCentroDeCusto(RequisicaoBuscaCentro requisicaoBuscaCentro){
        ModelAndView mv = new ModelAndView();

        List<CentroDeCusto> centrosDeCusto = centroDeCustoRepository.findAll();
        mv.addObject("centrosDeCusto", centrosDeCusto);
        mv.setViewName("CentroDeCusto");

        return mv;
    }

    @PostMapping("/buscar")
    public ModelAndView findCentroDeCusto(RequisicaoBuscaCentro requisicaoBuscaCentro) {
        List<CentroDeCusto> centroDeCustos = buscaCentro(requisicaoBuscaCentro);

        ModelAndView mv = new ModelAndView();
        mv.addObject("centrosDeCusto", centroDeCustos);
        mv.setViewName("CentroDeCusto");

        return mv;
    }

    public List<CentroDeCusto> buscaCentro(RequisicaoBuscaCentro requisicaoBuscaCentro){
        List<CentroDeCusto> centros;

        if (requisicaoBuscaCentro.getAtributo().equalsIgnoreCase("descricao")){
            centros= centroDeCustoRepository.findByDescricao(requisicaoBuscaCentro.getValor());
        }
        else if(requisicaoBuscaCentro.getAtributo().equalsIgnoreCase("codigo")){
            centros = centroDeCustoRepository.findByCodigo(requisicaoBuscaCentro.getValor());
        }
        else centros = centroDeCustoRepository.findAll();

        return centros;
    }

    @GetMapping("/novo")
    public ModelAndView formularioNovoCentroDeCusto(RequisicaoCentroDeCusto requisicaoCentroDeCusto){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("FormCentroDeCusto");
        return mv;
    }

    @PostMapping("/novo/criar")
    public ModelAndView cadastraNovoCentroDeCusto(@Valid @ModelAttribute("requisicaoCentroDeCusto") RequisicaoCentroDeCusto requisicaoCentroDeCusto, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("FormCentroDeCusto");
            return mv;
        }

        CentroDeCusto centroDeCusto = requisicaoCentroDeCusto.toCentroDeCusto();
        CentroDeCusto salvo = centroDeCustoRepository.save(centroDeCusto);
        salvo.setCodigo();

        centroDeCustoRepository.save(salvo);

        redirectAttributes.addFlashAttribute("redirect", true);
        return new ModelAndView("redirect:/centro_de_custo");

    }

}
