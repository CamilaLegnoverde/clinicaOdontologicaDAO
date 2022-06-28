package com.example.proyetoClinicaOdontologica.repository;

import com.example.proyetoClinicaOdontologica.domain.Domicilio;
import com.example.proyetoClinicaOdontologica.domain.Odontologo;
import com.example.proyetoClinicaOdontologica.domain.Paciente;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository //Va a esar a disposicion un objeto dao
public class PacienteDAOH2 implements IDAO<Paciente>{

    //Conectamos
    private static Connection getConnection() throws Exception{
        Class.forName("org.h2.Driver").newInstance();
        return DriverManager.getConnection("jdbc:h2:~/clinicaOdontologos", "sa", "");
    }

    @Override
    public List<Paciente> listarElementos() {
        //Listamos elementos que vienen de tabla paciente
        Connection connection = null;
        List<Paciente> listaPacientes = new ArrayList<>();
        //Cargamos la info
        Paciente paciente = null;
        Domicilio domicilio = null;
        Odontologo odontologo = null;

        //Conectamos
        try {
            //Instanciamos domicilio y odontologo
            DomicilioDAOH2 domicilioDAOH2 = new DomicilioDAOH2();
            OdontologoDAOH2 odontologoDAOH2 = new OdontologoDAOH2();
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM pacientes");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                //Obtenemos el id de domicilio para ir a buscarlo
                Long id_dom = rs.getLong(7);
                Long id_odo = rs.getLong(8);

                //Tenemos que tener el dao de domicilio y de odontologo para traer los datos
                domicilio = domicilioDAOH2.buscarid(id_dom); //Tiene que ir a bucar a la base el id que le pasamos
                odontologo = odontologoDAOH2.buscarid(id_odo);

                //Creamos un paciente y le damos valores
                paciente = new Paciente(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6).toLocalDate(), domicilio, odontologo);

                //Ya que tenemos un paciente, tenemos que agregarlo a mi colección. Agregamos el paciente que acabamos de encontrar
                listaPacientes.add(paciente);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally { //Intentamos cerrar la conexión
            try {
                connection.close();
            }
            catch (SQLException ex){
                ex.printStackTrace();
            };
        }
        return listaPacientes;
    }

    @Override
    public Paciente buscarid(Long id) {
        //Listamos elementos que vienen de tabla paciente
        Connection connection = null;

        //Cargamos la info
        Paciente paciente = null;
        Domicilio domicilio = null;
        Odontologo odontologo = null;

        //Conectamos
        try {
            //Instanciamos domicilio y odontologo
            DomicilioDAOH2 domicilioDAOH2 = new DomicilioDAOH2();
            OdontologoDAOH2 odontologoDAOH2 = new OdontologoDAOH2();
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM pacientes WHERE id = ?");
            preparedStatement.setLong(1, id);
            //Ejecutamos la consulta
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                //Obtenemos el id de domicilio para ir a buscarlo
                Long id_dom = rs.getLong(7);
                Long id_odo = rs.getLong(8);

                //Tenemos que tener el dao de domicilio y de odontologo para traer los datos
                domicilio = domicilioDAOH2.buscarid(id_dom); //Tiene que ir a bucar a la base el id que le pasamos
                odontologo = odontologoDAOH2.buscarid(id_odo);

                //Creamos un paciente y le damos valores
                paciente = new Paciente(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6).toLocalDate(), domicilio, odontologo);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally { //Intentamos cerrar la conexión
            try {
                connection.close();
            }
            catch (SQLException ex){
                ex.printStackTrace();
            };
        }
        return paciente;
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        Connection connection = null;

        try {
            //Tenemos que ir a buscar al domicilio
            connection = getConnection();
            DomicilioDAOH2 domicilioDAOH2 = new DomicilioDAOH2();
            OdontologoDAOH2 odontologoDAOH2 = new OdontologoDAOH2();
            //Guardamos el domicilio
            Domicilio domicilio = domicilioDAOH2.guardar(paciente.getDomicilio());
            Odontologo odontologo = odontologoDAOH2.guardar(paciente.getOdontologo());

            //Cargo el id de domicilio
            paciente.getDomicilio().setId(domicilio.getId());
            paciente.getOdontologo().setId(odontologo.getId());

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO pacientes(apellido, nombre, email, dni," +
                    "fecha_ingreso, domicilio_id, odontologo_id) VALUES(?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, paciente.getApellido());
            preparedStatement.setString(2, paciente.getNombre());
            preparedStatement.setString(3, paciente.getEmail());
            preparedStatement.setString(4, paciente.getDNI());
            preparedStatement.setDate(5, Date.valueOf(paciente.getFechaIngreso()));
            preparedStatement.setLong(6, paciente.getDomicilio().getId());
            preparedStatement.setLong(7, paciente.getOdontologo().getId());

            preparedStatement.executeUpdate();

            //Viene el generador
            ResultSet claves = preparedStatement.getGeneratedKeys();

            while (claves.next()){
                //Recibo la clave por claves
                paciente.setId(claves.getLong(1));
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
        return paciente;
    }

    @Override
    public Paciente actualizar(Paciente paciente) {
        Connection connection = null;

        try {
            //Tenemos que ir a buscar al domicilio
            connection = getConnection();
            DomicilioDAOH2 domicilioDAOH2 = new DomicilioDAOH2();
            OdontologoDAOH2 odontologoDAOH2 = new OdontologoDAOH2();
            //Guardamos el domicilio
            Domicilio domicilio = domicilioDAOH2.actualizar(paciente.getDomicilio());
            Odontologo odontologo = odontologoDAOH2.actualizar(paciente.getOdontologo());

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE pacientes apellido=?, nombre=?, email=?, dni=?," +
                    "fecha_ingreso=?, domicilio_id=?, odontologo_id=? WHERE id=?");
            preparedStatement.setString(1, paciente.getApellido());
            preparedStatement.setString(2, paciente.getNombre());
            preparedStatement.setString(3, paciente.getEmail());
            preparedStatement.setString(4, paciente.getDNI());
            preparedStatement.setDate(5, Date.valueOf(paciente.getFechaIngreso()));
            preparedStatement.setLong(6, paciente.getDomicilio().getId());
            preparedStatement.setLong(7, paciente.getOdontologo().getId());
            preparedStatement.setLong(8, paciente.getId());

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
        return paciente;
    }

    @Override
    public void eliminar(Long id) {
        Connection connection = null;
        try {
            connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM pacientes WHERE id = ?");
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
