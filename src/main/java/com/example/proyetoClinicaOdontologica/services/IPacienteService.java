package com.example.proyetoClinicaOdontologica.services;

import com.example.proyetoClinicaOdontologica.domain.Paciente;

import java.util.List;

public interface IPacienteService {
    //Hacemos una plantilla para que la impelemente pacienteService
    public List<Paciente> listarPacientes();

    public Paciente buscarPacientePorId(Long id);

    public Paciente guardarPaciente(Paciente paciente);

    public Paciente actualizarPaciente(Paciente paciente);
    public void eliminarPaciente(Long id);

}
