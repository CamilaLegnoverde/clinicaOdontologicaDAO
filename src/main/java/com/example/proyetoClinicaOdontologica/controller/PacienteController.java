package com.example.proyetoClinicaOdontologica.controller;

import com.example.proyetoClinicaOdontologica.domain.Paciente;
import com.example.proyetoClinicaOdontologica.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    //Recibe la solicitud de la vista (hay que asociarlo con la vista), lo procesa y soluciona.
    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public List<Paciente> listarPacientes() {
        return pacienteService.listarPacientes();
    }

    @PostMapping
    //Requestbody convierte el json en un objeto paciente
    //Postman en clase 21 a partir min 51
    public Paciente registrarPaciente(@RequestBody Paciente paciente){
        return pacienteService.guardarPaciente(paciente);
    }

    //Para hacerlo en postman, el id si o si hay que ageregarlo. Ya que pusimos que en paciente requiera un id para buscar.
    @PutMapping
    public Paciente actualizarPaciente(@RequestBody Paciente paciente){
        return pacienteService.actualizarPaciente(paciente);
    }

    @GetMapping("/{id}") //No aplica a la vista
    public Paciente buscarPacientePorId(@PathVariable Long id){
        return pacienteService.buscarPacientePorId(id);
    }

    @DeleteMapping("/{id}")
    public String eliminarPaciente(@PathVariable Long id){
       String respuesta = "Error el id ingresado no es correcto";
       //Validamos que el paciente exista
        if(pacienteService.buscarPacientePorId(id) != null){
            pacienteService.eliminarPaciente(id);
            respuesta = "Se eliminó al paciente con id = " + id;
        }
       return respuesta;
    }
}