package com.example.proyetoClinicaOdontologica.services;

import com.example.proyetoClinicaOdontologica.domain.Turno;
import com.example.proyetoClinicaOdontologica.repository.IDAO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class TurnoService implements ITurnoService {

    IDAO<Turno> turnoList;

    public TurnoService(IDAO<Turno> turnoList) {
        this.turnoList = turnoList;
    }

    @Override
    public List<Turno> listarTurnos() {
        return turnoList.listarElementos();
    }

    @Override
    public Turno buscarTurnoPorId(Long id) {
        return turnoList.buscarid(id);
    }

    @Override
    public Turno guardarTurno(Turno turno) {
        return turnoList.guardar(turno);
    }

    @Override
    public Turno actualizarTurno(Turno turno) {
        return turnoList.actualizar(turno);
    }

    @Override
    public void eliminarTurno(Long id) {
        turnoList.eliminar(id);
    }
}
