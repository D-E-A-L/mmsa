/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponenteDatos;

import ComponenteClase.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Richard
 */
public abstract class BDProveedor {

    public static Proveedor insertarProveedor(Proveedor prv) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("INSERT INTO PROVEEDOR (ruc, razonsocial, telefono,celular,email, lugar, direccion, tipo) VALUES (?,?,?,?,?,?,?,?)");
        ps.setString(1, prv.getRuc());
        ps.setString(2, prv.getRazonSocial());
        ps.setString(3, prv.getTelefono());
        ps.setString(4, prv.getCelular());
        ps.setString(5, prv.getEmail());
        ps.setString(6, prv.getLugar());
        ps.setString(7, prv.getDireccion());
        ps.setString(8, prv.getTipo());
        ps.executeUpdate();
        PreparedStatement ps2 = cnn.prepareStatement("select max(ruc) from PROVEEDOR");
        ResultSet r = ps2.executeQuery();
        if (r.next()) {
            String lastID = r.getString(1);
            prv.setRuc(lastID);
        }
        ps2.close();
        cnn.close();
        ps.close();
        return prv;
    }

    public static Proveedor buscarProveedor(String Ruc) throws SQLException {
        return buscarProveedor(Ruc, null);
    }

    public static Proveedor buscarProveedor(String Ruc, Proveedor prv) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("select * from PROVEEDOR where ruc=?");
        ps.setString(1, Ruc);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            if (prv == null) {
                prv = new Proveedor() {};
            }
            prv.setRuc(Ruc);
            prv.setRazonSocial(rs.getString("razonsocial"));
            prv.setTelefono(rs.getString("telefono"));
            prv.setCelular(rs.getString("celular"));
            prv.setEmail(rs.getString("email"));
            prv.setLugar(rs.getString("lugar"));
            prv.setDireccion(rs.getString("direccion"));
            prv.setTipo(rs.getString("Tipo"));
        }
        cnn.close();
        ps.close();
        return prv;
    }

    public static boolean eliminarProveedor(String ruc) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;

        ps = cnn.prepareStatement("delete from PROVEEDOR WHERE ruc=?");
        ps.setString(1, ruc);
        int rowsUpdated = ps.executeUpdate();
        cnn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean actualizarProveedor(Proveedor p) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;

        ps = cnn.prepareStatement("UPDATE PROVEEDOR SET razonsocial=?, telefono=?, celular=?, email=?, lugar=?, direccion=?, Tipo=? WHERE ruc=" + p.getRuc());
        ps.setString(1, p.getRazonSocial());
        ps.setString(2, p.getTelefono());
        ps.setString(3, p.getCelular());
        ps.setString(4, p.getEmail());
        ps.setString(5, p.getLugar());
        ps.setString(6, p.getDireccion());
        ps.setString(7, p.getTipo());
        int rowsUpdated = ps.executeUpdate();
        cnn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<Proveedor> mostrarProveedor() throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ArrayList<Proveedor> lista = new ArrayList<Proveedor>();

        ps = cnn.prepareStatement("SELECT * FROM PROVEEDOR");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Proveedor e = new Proveedor() {};
            e.setRuc(rs.getString("ruc"));
            e.setRazonSocial(rs.getString("razonsocial"));
            e.setEmail(rs.getString("email"));
            e.setTelefono(rs.getString("telefono"));
            e.setCelular(rs.getString("celular"));
            e.setLugar(rs.getString("lugar"));
            e.setDireccion(rs.getString("direccion"));
            e.setTipo(rs.getString("Tipo"));
            lista.add(e);
        }
        cnn.close();
        ps.close();
        return lista;
    }
}
