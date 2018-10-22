package com.micromax.incidencia.controller;

import com.micromax.incidencia.domain.entities.incidencias.TipoIncidencia;
import com.micromax.incidencia.dto.CategoriaDTO;
import com.micromax.incidencia.dto.IncidenciaDTO;
import com.micromax.incidencia.service.IncidenciaService;
import com.micromax.incidencia.service.ItemListService;
import com.micromax.incidencia.viewmodel.IncidenciaViewmodel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MasterCrudController {

    @Autowired
    private IncidenciaService incidenciaService;


    @Autowired
    private MainController mainController;

    @Autowired
    private ItemListService itemListService;

    /*===================== GETS ================================*/
    @GetMapping("/incidenciaC")
    public String formularioCrear(Model model){
        model = setTemplateToModel(model,"/incidencia/","incidenciaC")
                .addAttribute("data", new IncidenciaViewmodel(new IncidenciaDTO(), "",itemListService.getCategoriasNivelUno() ))
                .addAttribute("title","Crear Incidencia");
        return mainController.homeRoute(model);
    }

    @GetMapping("/incidenciaL")
    public String listar(Model model){
        model = setTemplateToModel(model,"/incidencia/","incidenciaL")
                .addAttribute("incidencias", incidenciaService.getIncidencias())
                .addAttribute("title","Ver incidencias");
        return mainController.homeRoute(model);
    }

    @GetMapping("/categoriaC")
    public String crearCategoria(Model model){
        model.addAttribute("categoria", new CategoriaDTO());
        model.addAttribute("categorias", itemListService.getCategoriaByNivel(1));
        return "master/crearCat";
    }

    @GetMapping("/tipoIncidenciaC")
    public String crearTipoIncidencia(Model model){
        model.addAttribute("tipoIncidencia",new TipoIncidencia());
        return "incidencia/crearTipIncid";
    }

    @PostMapping("/incidenciaC")
    public String crearIncidencia(@ModelAttribute IncidenciaDTO incidencia, BindingResult errors, Model model){
        incidenciaService.createIncidencia(incidencia, SecurityContextHolder.getContext().getAuthentication().getName());
        return formularioCrear(model);
    }

    @PostMapping("/categoriaC")
    public String crearCategoria(@ModelAttribute CategoriaDTO cat, BindingResult errors, Model model){
        itemListService.guardar(cat);
        return crearCategoria(model);
    }

    private Model setTemplateToModel(Model model, String location, String template){
        return model.addAttribute("location", location).addAttribute("template", template);
    }

    @PostMapping("/tipoIncidenciaC")
    public String crearTipoIncidencia(@ModelAttribute TipoIncidencia tipoIncid, BindingResult errors, Model model){
        itemListService.guardar(tipoIncid);
        return crearTipoIncidencia(model);
    }
}