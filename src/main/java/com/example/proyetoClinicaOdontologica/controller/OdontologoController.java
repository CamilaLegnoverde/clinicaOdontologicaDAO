package com.example.proyetoClinicaOdontologica.controller;

import com.example.proyetoClinicaOdontologica.domain.Odontologo;
import com.example.proyetoClinicaOdontologica.services.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    @Autowired
    public OdontologoService odontologoService;

    @GetMapping
    public List<Odontologo> listarOdontologos(){
        return odontologoService.listarOdontologos();
    }

    @GetMapping("/{id}") //COn esto no estamos usando el html, chequear eso
    public Odontologo mostrarOdontologoPorId(@PathVariable Long id, Model model){

        Odontologo odontologo = odontologoService.buscarOdontologoPorId(id);

        model.addAttribute("id", odontologo.getId());
        model.addAttribute("apellido", odontologo.getApellido());

        return odontologo;
    }
    @PostMapping
    public Odontologo guardarOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.guardarOdontologo(odontologo);
    }

    @PutMapping
    public Odontologo actualizarOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.actualizarOdotologo(odontologo);
    }

    @DeleteMapping("/{id}")
    public String eliminarOdontologo(@PathVariable Long id){
        String respuesta = "Error al eliminar el odontólogo";
        if (odontologoService.buscarOdontologoPorId(id) != null){
            odontologoService.eliminarOdontologo(id);
            respuesta = "El odontologo con id " + id + " se ha eliminado";
        }
        return respuesta;
    }
}
