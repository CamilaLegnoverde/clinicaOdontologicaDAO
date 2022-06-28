package com.example.proyetoClinicaOdontologica.controller;

import com.example.proyetoClinicaOdontologica.domain.Odontologo;
import com.example.proyetoClinicaOdontologica.domain.Paciente;
import com.example.proyetoClinicaOdontologica.domain.Turno;
import com.example.proyetoClinicaOdontologica.repository.OdontologoDAOH2;
import com.example.proyetoClinicaOdontologica.repository.PacienteDAOH2;
import com.example.proyetoClinicaOdontologica.repository.TurnoList;
import com.example.proyetoClinicaOdontologica.services.OdontologoService;
import com.example.proyetoClinicaOdontologica.services.PacienteService;
import com.example.proyetoClinicaOdontologica.services.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    //Hacemos esto para instanciar turnoService y que utilice TurnoList
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;


    @GetMapping
    //Usamos resoponse entity para manejar la devolución
    public ResponseEntity<List<Turno>> listarTurnos(){
        //Tenemos que devolver algo de response
        return ResponseEntity.ok(turnoService.listarTurnos());//Acá va lo que nosotros devolvimos
    }

    @PostMapping
    public ResponseEntity<Turno> guardarTurno(@RequestBody Turno turno){
        ResponseEntity<Turno> respuesta;
        //----------------------------Controlar si los id son existentes
        //Con esto buscamos al paciente
        Paciente paciente = pacienteService.buscarPacientePorId(turno.getPaciente().getId());

        //Buscamos al odontologo
        Odontologo odontologo = odontologoService.buscarOdontologoPorId(turno.getOdontologo().getId());

        //Controlamos
        if (paciente != null && odontologo != null){
            //Agregamos el turno
            respuesta = ResponseEntity.ok(turnoService.guardarTurno(turno));
        }
        else {
            //Si no se cumple, hacemos que la respuesta cambie
            respuesta=ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return respuesta;
    }

    @PutMapping
    public ResponseEntity<Turno> actualizarTurno(@RequestBody Turno turno){
        return ResponseEntity.ok(turnoService.actualizarTurno(turno));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id){
        ResponseEntity<String> respuesta;
        //----------------------------Controlar si los id son existentes
        //Controlamos
        if (turnoService.buscarTurnoPorId(id) != null){
            //Eliminamos el turno
            turnoService.eliminarTurno(id);
            respuesta = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        }
        else {
            //Si no se cumple, hacemos que la respuesta cambie
            respuesta=ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return respuesta;
    }
}
