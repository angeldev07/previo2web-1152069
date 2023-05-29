package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Seleccion;
import com.example.demo.repository.ContinenteRepository;
import com.example.demo.repository.SeleccionRepository;

@Controller
@RequestMapping("selecciones")
public class SeleccionController {
    
    @Autowired
    SeleccionRepository sr;

    @Autowired
    ContinenteRepository cr;

    /**
     * Método para listar las selecciones
     * @return  index donde se muestran las selecciones.
     */
    @GetMapping
    public String listar(Model model) {
        List<Seleccion> lista = sr.findAll();
        model.addAttribute("selecciones", lista);
        return "index";
    }

    /**
     * Metodo para borrar una seleccion 
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        System.out.println(id);
        sr.deleteById(Integer.parseInt(id));
        return "redirect:/selecciones";
    }

    /**
     * Método para agregar una nueva seleccion.
     * @param model
     * @return
     */
    @GetMapping("/agregar")
    public String agregar(Model model){
        Optional<Seleccion> seleccion = Optional.empty();
        //se envia un modelo vacio y los continente 
        model.addAttribute("continentes", cr.findAll());
        model.addAttribute("seleccion", seleccion);

        return "agregar";
    }

    /**
     * Metodo para editar la seleccion
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model  ){
        //se envia un modelo vacio y los continente 
        model.addAttribute("continentes", cr.findAll());
        model.addAttribute("seleccion", sr.findById(Integer.parseInt(id)));

        return "agregar";
    }

    /**
     * Metodo para agregar una nueva seleccion desde el formulario 
     * @param seleccion
     * @return
     */
    @PostMapping("/addS")
    public String createSelecc(@ModelAttribute("seleccion") Seleccion seleccion) {
    	sr.save(seleccion);
        return "redirect:/selecciones";
    }

    @GetMapping("/grupo")
    public String buscador(@RequestParam("grupo") String grupo, Model model) {
        List<Seleccion> lista = sr.findByGrupo(grupo);
        model.addAttribute("selecciones", lista);
        return "index";
    }

}
