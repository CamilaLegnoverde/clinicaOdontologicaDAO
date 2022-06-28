package com.example.proyetoClinicaOdontologica.services;

import com.example.proyetoClinicaOdontologica.domain.Odontologo;
import com.example.proyetoClinicaOdontologica.domain.Paciente;
import com.example.proyetoClinicaOdontologica.repository.IDAO;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class OdontologoService implements IOdontologoService {

    //Agregamos un DAO
    IDAO<Odontologo> odontologoIDAO;

    //Constructor
    public OdontologoService(IDAO<Odontologo> odontologoIDAO) {
        this.odontologoIDAO = odontologoIDAO;
    }

    @Override
    public List<Odontologo> listarOdontologos(){
        return odontologoIDAO.listarElementos();
    }

    @Override
    public Odontologo buscarOdontologoPorId(Long id){
        return odontologoIDAO.buscarid(id);
    }

    @Override
    public Odontologo guardarOdontologo(Odontologo odontologo) {
        return odontologoIDAO.guardar(odontologo);
    }

    @Override
    public Odontologo actualizarOdotologo(Odontologo odontologo) {
        return odontologoIDAO.actualizar(odontologo);
    }

    @Override
    public void eliminarOdontologo(Long id) {
        odontologoIDAO.eliminar(id);
    }
}
