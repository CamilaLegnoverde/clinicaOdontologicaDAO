package com.example.proyetoClinicaOdontologica.repository;

import com.example.proyetoClinicaOdontologica.domain.Odontologo;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class OdontologoDAOH2 implements IDAO<Odontologo>{
    //Conexión
    public static Connection getConnection() throws Exception{
        Class.forName("org.h2.Driver").newInstance();
        return DriverManager.getConnection("jdbc:h2:~/clinicaOdontologos", "sa", "");
    }

    @Override
    public List<Odontologo> listarElementos() {
        Connection connection = null;
        List<Odontologo> listaOdontologos = new ArrayList<>();
        //Cargamos la información
        Odontologo odontologo = null;

        //Conectamos
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM odontologos");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                //Creamos odontologo
                odontologo = new Odontologo(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4));

                //Listamos
                listaOdontologos.add(odontologo);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return listaOdontologos;
    }

    @Override
    public Odontologo buscarid(Long id) {
        Connection connection = null;

        //Cargamos la información
        Odontologo odontologo = null;

        //Conectamos
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM odontologos WHERE id = ?");
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                //Creamos odontologo
                odontologo = new Odontologo(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return odontologo;
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO odontologos(apellido, nombre, matricula)" +
                    "VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, odontologo.getApellido());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getMatricula());
            preparedStatement.executeUpdate();

            //Viene el generador
            ResultSet claves = preparedStatement.getGeneratedKeys();

            while (claves.next()){
                //Recibo la clave por claves
                odontologo.setId(claves.getLong(1));
            }
            preparedStatement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return odontologo;
    }

    @Override
    public Odontologo actualizar(Odontologo odontologo) {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE odontologos apellido=?, nombre=?, matricula=?" +
                    "WHERE id=?");
            preparedStatement.setString(1, odontologo.getApellido());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getMatricula());
            preparedStatement.setLong(4, odontologo.getId());

            preparedStatement.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return odontologo;
    }

    @Override
    public void eliminar(Long id) {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM odontologos WHERE id = ?");
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }

}
