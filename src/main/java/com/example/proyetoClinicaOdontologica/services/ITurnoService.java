package com.example.proyetoClinicaOdontologica.services;

import com.example.proyetoClinicaOdontologica.domain.Odontologo;
import com.example.proyetoClinicaOdontologica.domain.Turno;

import java.util.List;

public interface ITurnoService {
    List<Turno> listarTurnos();

    Turno buscarTurnoPorId(Long id);

    Turno guardarTurno(Turno turno);

    Turno actualizarTurno(Turno turno);

    void eliminarTurno(Long id);
}
