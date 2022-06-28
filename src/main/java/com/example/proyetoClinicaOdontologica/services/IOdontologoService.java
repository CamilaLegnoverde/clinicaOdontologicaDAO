package com.example.proyetoClinicaOdontologica.services;

import com.example.proyetoClinicaOdontologica.domain.Odontologo;

import java.util.List;

public interface IOdontologoService {
    List<Odontologo> listarOdontologos();
    Odontologo buscarOdontologoPorId(Long id);

    Odontologo guardarOdontologo(Odontologo odontologo);

    Odontologo actualizarOdotologo(Odontologo odontologo);

    void eliminarOdontologo(Long id);
}
