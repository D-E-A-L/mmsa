/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponenteDatos;

import ComponenteClase.Empleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Richard
 */
public abstract class BDEmpleado {

    public static Empleado insertarEmpleado(Empleado e) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("INSERT INTO EMPLEADO (dni, nombre, apellido, email, sexo, edad, telefono, celular,direccion,imagen) VALUES (?,?,?,?,?,?,?,?,?,?)");
        ps.setInt(1, e.getDni());
        ps.setString(2, e.getNombre());
        ps.setString(3, e.getApellido());
        ps.setString(4, e.getEmail());
        ps.setString(5, String.valueOf(e.getSexo()));
        ps.setInt(6, e.getEdad());
        ps.setString(7, e.getTelefono());
        ps.setString(8, e.getCelular());
        ps.setString(9, e.getDireccion());
        ps.setString(10, e.getImagen());
        ps.executeUpdate();
        PreparedStatement ps2 = cnn.prepareStatement("select max(nroEmpleado) from EMPLEADO");
        ResultSet r = ps2.executeQuery();
        if (r.next()) {
            int lastID = r.getInt(1);
            e.setNroEmpleado(lastID);
        }
        ps2.close();
        cnn.close();
        ps.close();
        return e;
    }

    public static Empleado buscarEmpleado(int nroemp) throws SQLException {
        return buscarEmpleado(nroemp, null);
    }

    public static Empleado buscarEmpleado(int nroemp, Empleado e) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("select * from EMPLEADO where nroEmpleado=?");
        ps.setInt(1, nroemp);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            if (e == null) {
                e = new Empleado() {};
            }
            e.setNroEmpleado(nroemp);
            e.setDni(rs.getInt("dni"));
            e.setNombre(rs.getString("nombre"));
            e.setApellido(rs.getString("apellido"));
            e.setEmail(rs.getString("email"));
            e.setSexo(rs.getString("sexo"));
            e.setEmail(rs.getString("edad"));
            e.setTelefono(rs.getString("telefono"));
            e.setCelular(rs.getString("celular"));
            e.setDireccion(rs.getString("direccion"));
            e.setImagen(rs.getString("imagen"));
        }
        cnn.close();
        ps.close();
        return e;
    }

     public static Empleado buscarEmpleadoSinIM(int nroemp) throws SQLException {
        return  buscarEmpleadoSinIM(nroemp, null);
    }

    public static Empleado  buscarEmpleadoSinIM(int nroemp, Empleado e) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("select * from EMPLEADO where nroEmpleado=?");
        ps.setInt(1, nroemp);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            if (e == null) {
                e = new Empleado() {};
            }
            e.setNroEmpleado(nroemp);
            e.setDni(rs.getInt("dni"));
            e.setNombre(rs.getString("nombre"));
            e.setApellido(rs.getString("apellido"));
            e.setEmail(rs.getString("email"));
            e.setSexo(rs.getString("sexo"));
            e.setEmail(rs.getString("edad"));
            e.setTelefono(rs.getString("telefono"));
            e.setCelular(rs.getString("celular"));
            e.setDireccion(rs.getString("direccion"));
        }
        cnn.close();
        ps.close();
        return e;
    }

    public static Empleado buscarEmpleadoDNI(int dni) throws SQLException {
        return buscarEmpleadoDNI(dni, null);
    }

    public static Empleado buscarEmpleadoDNI(int dni, Empleado e) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("select * from EMPLEADO where dni=?");
        ps.setInt(1, dni);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            e = e == null ? new Empleado() {} : e;
            e.setNroEmpleado(rs.getInt("nroEmpleado"));
            e.setDni(rs.getInt("dni"));
            e.setNombre(rs.getString("nombre"));
            e.setApellido(rs.getString("apellido"));
            e.setEdad(rs.getInt("edad"));
            e.setSexo(rs.getString("sexo"));
            e.setEmail(rs.getString("email"));
            e.setTelefono(rs.getString("telefono"));
            e.setCelular(rs.getString("celular"));
            e.setDireccion(rs.getString("direccion"));
            e.setImagen(rs.getString("imagen"));
        } else {
            e = null;
        }
        cnn.close();
        ps.close();
        return e;
    }

    public static Empleado buscarEmpleadoApe(String ape) throws SQLException {
        return buscarEmpleadoApe(ape, null);
    }

    public static Empleado buscarEmpleadoApe(String ape, Empleado e) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("select * from EMPLEADO where apellido=?");
        ps.setString(1, ape);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            e = e == null ? new Empleado() {} : e;
            e.setNroEmpleado(rs.getInt("nroEmpleado"));
            e.setDni(rs.getInt("dni"));
            e.setNombre(rs.getString("nombre"));
            e.setApellido(rs.getString("apellido"));
            e.setEmail(rs.getString("email"));
            e.setSexo(rs.getString("sexo"));
            e.setEmail(rs.getString("edad"));
            e.setTelefono(rs.getString("telefono"));
            e.setCelular(rs.getString("celular"));
            e.setDireccion(rs.getString("direccion"));
            e.setImagen(rs.getString("imagen"));
        } else {
            e = null;
        }
        cnn.close();
        ps.close();
        return e;
    }

    public static boolean eliminarEmpleado(int nroemp) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;

        ps = cnn.prepareStatement("delete from EMPLEADO WHERE nroemp=?");
        ps.setInt(1, nroemp);
        int rowsUpdated = ps.executeUpdate();
        cnn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean eliminarEmpleadoDNI(int dni) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;

        ps = cnn.prepareStatement("delete from EMPLEADO WHERE dni=?");
        ps.setInt(1, dni);
        int rowsUpdated = ps.executeUpdate();
        cnn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean actualizarEmpleado(Empleado e) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;

        ps = cnn.prepareStatement("UPDATE EMPLEADO SET dni=?, nombre=?, apellido=?, email=?, sexo=?, edad=?, telefono=?, celular=?, direccion=?,imagen=? WHERE nroEmpleado=" + e.getNroEmpleado());
        ps.setInt(1, e.getDni());
        ps.setString(2, e.getNombre());
        ps.setString(3, e.getApellido());
        ps.setString(4, e.getEmail());
        ps.setString(5, e.getSexo());
        ps.setInt(6, e.getEdad());
        ps.setString(7, e.getTelefono());
        ps.setString(8, e.getCelular());
        ps.setString(9, e.getDireccion());
        ps.setString(10, e.getImagen());
        int rowsUpdated = ps.executeUpdate();
        cnn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<Empleado> mostrarEmpleado() throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ArrayList<Empleado> lista = new ArrayList<Empleado>();

        ps = cnn.prepareStatement("SELECT * FROM Empleado");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Empleado e = new Empleado() {};
            e.setNroEmpleado(rs.getInt("NroEmpleado"));
            e.setDni(rs.getInt("dni"));
            e.setNombre(rs.getString("nombre"));
            e.setApellido(rs.getString("apellido"));
            e.setEmail(rs.getString("email"));
            e.setSexo(rs.getString("sexo"));
            e.setEdad(rs.getInt("edad"));
            e.setTelefono(rs.getString("telefono"));
            e.setCelular(rs.getString("celular"));
            e.setDireccion(rs.getString("direccion"));
            //e.setImagen(rs.getString("imagen"));
            lista.add(e);
        }
        cnn.close();
        ps.close();
        return lista;
    }
}
