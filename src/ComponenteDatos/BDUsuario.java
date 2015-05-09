/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponenteDatos;

import ComponenteClase.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Richard
 */
public abstract class BDUsuario {

    public static void insertarUsuario(Usuario u) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("INSERT INTO USUARIO (id,password,nroEmpleado) VALUES (?,?,?)");
        ps.setString(1, u.getId());
        ps.setString(2, u.getPassword());
        ps.setInt(3, u.getEmpleado().getNroEmpleado());
        ps.executeUpdate();
        cnn.close();
        ps.close();
    }

    public static Usuario buscarUsuario(String idusuario) throws SQLException {
        return buscarUsuario(idusuario, null);
    }

    public static Usuario buscarUsuario(String idusuario, Usuario u) throws SQLException {
        Connection cnn = BD.getConnection();
        if(cnn != null)
        {
           PreparedStatement ps = null;
            ps = cnn.prepareStatement("select * from USUARIO where id=?");
            ps.setString(1, idusuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (u == null) {
                    u = new Usuario() {
                    };
                }
                u.setId(idusuario);
                u.setPassword(rs.getString("password"));
                u.setEmpleado(BDEmpleado.buscarEmpleado(rs.getInt("nroEmpleado")));
            }
            cnn.close();
            ps.close();
            return u; 
        }
        return null;
        
    }

    public static boolean eliminarUsuario(String iduser) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("delete from USUARIO WHERE id=?");
        ps.setString(1, iduser);
        int rowsUpdated = ps.executeUpdate();
        cnn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean actualizarUsuario(Usuario u) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("UPDATE USUARIO SET password=? WHERE nroEmpleado="+u.getEmpleado().getNroEmpleado());
        ps.setString(1, u.getPassword());
        int rowsUpdated = ps.executeUpdate();
        cnn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<Usuario> mostrarUsuario() throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ArrayList<Usuario> lista = new ArrayList<Usuario>();

        ps = cnn.prepareStatement("SELECT * FROM USUARIO");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Usuario u = new Usuario() {
            };
            u.setId(rs.getString("id"));
            u.setPassword(rs.getString("password"));
            u.setEmpleado(BDEmpleado.buscarEmpleado(rs.getInt("nroEmpleado")));
            lista.add(u);
        }
        cnn.close();
        ps.close();
        return lista;
    }
}
