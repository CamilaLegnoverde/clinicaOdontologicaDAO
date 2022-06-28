package com.example.proyetoClinicaOdontologica.services;

import com.example.proyetoClinicaOdontologica.domain.Domicilio;
import com.example.proyetoClinicaOdontologica.domain.Odontologo;
import com.example.proyetoClinicaOdontologica.domain.Paciente;
import com.example.proyetoClinicaOdontologica.repository.IDAO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class PacienteService implements IPacienteService{

    //EL servicio nos va a ofrecer lo que necesitamos para nuestras vistas, pero él a su vez necesita hablar
    //con el dao para obtener información desde la base de datos

    //Agregamos un DAO poara poder trabajar
    private IDAO<Paciente> pacienteIDAO;

    //Creamos un constructor para que podamos pasar a nuestro service y que el se encargue de realizar la tarea de ir a buscar una
    //Lista de pacientes e ir a buscar pacientes por id

    //Va la implementacion del dao, no la interface
    public PacienteService(IDAO<Paciente> pacienteIDAO) {
        this.pacienteIDAO = pacienteIDAO;
    }

    //Implementamos método para listar los pacientes
    @Override
    public List<Paciente> listarPacientes(){
        //Tenemos que devolver el paciente que nos traiga nuestro DAO
        return pacienteIDAO.listarElementos();
    }

    //Implementamos el método de buscar el paciente según su id
    @Override
    public Paciente buscarPacientePorId(Long id){
        return pacienteIDAO.buscarid(id);
    }

    @Override
    public Paciente guardarPaciente(Paciente paciente) {
        return pacienteIDAO.guardar(paciente);
    }

    @Override
    public Paciente actualizarPaciente(Paciente paciente) {
        return pacienteIDAO.actualizar(paciente);
    }

    @Override
    public void eliminarPaciente(Long id) {
        pacienteIDAO.eliminar(id);
    }
}
