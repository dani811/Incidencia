package com.micromax.incidencia.controller;

import com.micromax.incidencia.dto.UsuarioDTO;
import com.micromax.incidencia.service.UsuarioService;
import com.micromax.incidencia.viewmodel.UsuarioViewmodel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.micromax.incidencia.domain.Constants.*;

@Controller
public class UsuarioCrudController {

    @Autowired
    private MainController mainController;

    @Autowired
    private UsuarioService usuarioService;

    /*-------------- USUARIO -------------*/
    @GetMapping("/usuarioC")
    public String usuarioC(Model model){
        UsuarioViewmodel viewmodel = new UsuarioViewmodel();
        viewmodel.setUsuarioDTO(new UsuarioDTO());
        viewmodel.setRoles(usuarioService.getRoles());

        model = MasterCrudController.setTemplateToModel(model, USUARIO,"usuarioC");
        model.addAttribute(DATA, viewmodel);
        model.addAttribute(TITLE, "Crear Usuario");
        return mainController.homeRoute(model);
    }

    @GetMapping("/usuarioE")
    public String usuarioE(@RequestParam long id, Model model){
        UsuarioViewmodel viewmodel = new UsuarioViewmodel();
        viewmodel.setUsuarioDTO(new UsuarioDTO(usuarioService.getUsuarioById(id)));
        viewmodel.setRoles(usuarioService.getRoles());
        viewmodel.setMessage("");

        model = MasterCrudController.setTemplateToModel(model, USUARIO,"usuarioE")
                .addAttribute(DATA, viewmodel)
                .addAttribute(TITLE,"Editar Usuario");
        return mainController.homeRoute(model);
    }

    @GetMapping("/usuarioL")
    public String usuarioL(Model model){
        model = MasterCrudController.setTemplateToModel(model, USUARIO,"usuarioL")
                .addAttribute("usuarios", usuarioService.getUsuariosActivos())
                .addAttribute(TITLE,"Ver incidencias");

        return mainController.homeRoute(model);
    }

    /*USUARIO*/
    @PostMapping("/usuarioC")
    public String postUsuarioC(@ModelAttribute UsuarioViewmodel viewmodel, BindingResult errors, Model model) {

        usuarioService.guardarUsuario(viewmodel.getUsuarioDTO(),true);
        return "redirect:/usuarioL";
    }

    @PostMapping("/usuarioE")
    public String postUsuarioE(@RequestParam UsuarioViewmodel viewmodel, BindingResult errors, Model model) {
        usuarioService.guardarUsuario(viewmodel.getUsuarioDTO(), false);
        return "redirect:/usuarioL";
    }
}