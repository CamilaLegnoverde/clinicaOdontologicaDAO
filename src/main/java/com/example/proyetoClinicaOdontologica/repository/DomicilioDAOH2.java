package com.example.proyetoClinicaOdontologica.repository;

import com.example.proyetoClinicaOdontologica.domain.Domicilio;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class DomicilioDAOH2 implements IDAO<Domicilio>{

    //Conectamos
    private static Connection getConnection() throws Exception{
        Class.forName("org.h2.Driver").newInstance();
        return DriverManager.getConnection("jdbc:h2:~/clinicaOdontologos", "sa", "");
    }
    @Override
    public List<Domicilio> listarElementos() {
        return null;
    }

    @Override
    public Domicilio buscarid(Long id) {
        Connection connection = null;
        Domicilio domicilio = null;

        try {
            //Tenemos que ir a buscar al domicilio
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM domicilios WHERE id = ?");
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                domicilio = new Domicilio(rs.getLong(1),rs.getString(2),rs.getInt(3),rs.getString(4), rs.getString(5));
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
        return domicilio;
    }

    @Override
    public Domicilio guardar(Domicilio domicilio) {
        Connection connection = null;

        try {
            //Tenemos que ir a buscar al domicilio
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO domicilios(calle, numero, localidad, provincia) VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, domicilio.getCalle());
            preparedStatement.setInt(2, domicilio.getNumero());
            preparedStatement.setString(3, domicilio.getLocalidad());
            preparedStatement.setString(4, domicilio.getProvincia());
            preparedStatement.executeUpdate();

            //Viene el generador
            ResultSet claves = preparedStatement.getGeneratedKeys();

            while (claves.next()){
                //Recibo la clave por claves
                domicilio.setId(claves.getLong(1));
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
        return domicilio;
    }

    @Override
    public Domicilio actualizar(Domicilio domicilio) {
        Connection connection = null;

        try {
            //Tenemos que ir a buscar al domicilio
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE domicilios SET calle=?, numero=?, localidad=?, provincia=? WHERE id=?");
            preparedStatement.setString(1, domicilio.getCalle());
            preparedStatement.setInt(2, domicilio.getNumero());
            preparedStatement.setString(3, domicilio.getLocalidad());
            preparedStatement.setString(4, domicilio.getProvincia());
            preparedStatement.setLong(5, domicilio.getId());

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
        return domicilio;
    }

    @Override
    public void eliminar(Long id) {
        Connection connection = null;

        try {
            //Tenemos que ir a buscar al domicilio
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM domicilios WHERE id = ?");
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
