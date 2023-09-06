package com.henrique.bussinessmanagement.controller;

import com.henrique.bussinessmanagement.dto.RequisicaoBusca;
import com.henrique.bussinessmanagement.dto.RequisicaoCentroDeCusto;
import com.henrique.bussinessmanagement.model.CentroDeCusto;
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
    public ModelAndView findAllCentroDeCusto(RequisicaoBusca requisicaoBusca){
        ModelAndView mv = new ModelAndView();

        List<CentroDeCusto> centrosDeCusto = centroDeCustoRepository.findAll();
        mv.addObject("centrosDeCusto", centrosDeCusto);
        mv.setViewName("CentroDeCusto");

        return mv;
    }

    @PostMapping("/buscar")
    public ModelAndView findCentroDeCusto(RequisicaoBusca requisicaoBusca) {
        List<CentroDeCusto> centroDeCustos = buscaCentro(requisicaoBusca);

        ModelAndView mv = new ModelAndView();
        mv.addObject("centrosDeCusto", centroDeCustos);
        mv.setViewName("CentroDeCusto");

        return mv;
    }

    public List<CentroDeCusto> buscaCentro(RequisicaoBusca requisicaoBusca){
        List<CentroDeCusto> centros;

        if (requisicaoBusca.getAtributo().equalsIgnoreCase("descricao")){
            centros= centroDeCustoRepository.findByDescricao(requisicaoBusca.getValor());
        }
        else if(requisicaoBusca.getAtributo().equalsIgnoreCase("codigo")){
            centros = centroDeCustoRepository.findByCodigo(requisicaoBusca.getValor());
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
