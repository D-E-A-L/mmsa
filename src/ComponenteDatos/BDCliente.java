/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponenteDatos;

import ComponenteClase.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Richard
 */
public abstract class BDCliente {

    public static Cliente insertarCliente(Cliente c) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("INSERT INTO CLIENTE (codcliente,nomdocumento, numdocumento,nombre,telefono) VALUES (?,?,?,?,?)");
        ps.setString(1, c.getCodCliente());
        ps.setString(2, c.getNomDocumento());
        ps.setString(3, c.getNumDocumento());
        ps.setString(4, c.getNombre());
        ps.setString(5, c.getTelefono());
        ps.executeUpdate();
        PreparedStatement ps2 = cnn.prepareStatement("select max(codcliente) from CLIENTE");
        ResultSet r = ps2.executeQuery();
        if (r.next()) {
            String lastID = r.getString(1);
            c.setCodCliente(lastID);
        }
        ps2.close();
        cnn.close();
        ps.close();
        return c;
    }

    public static Cliente buscarCliente(String cod) throws SQLException {
        return buscarCliente(cod, null);
    }

     public static Cliente buscarCliente(String codcliente, Cliente c) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("select * from CLIENTE where codcliente=?");
        ps.setString(1, codcliente);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            if (c == null) {
                c = new Cliente() {};
            }
            c.setCodCliente(codcliente);
            c.setNomDocumento(rs.getString("nomdocumento"));
            c.setNumDocumento(rs.getString("numdocumento"));
            c.setNombre(rs.getString("nombre"));
            c.setTelefono(rs.getString("telefono"));
        }
        cnn.close();
        ps.close();
        return c;
    }

    public static Cliente buscarClienteNumDoc(String numdoc) throws SQLException {
        return buscarClienteNumDoc(numdoc, null);
    }

     public static Cliente buscarClienteNumDoc(String numdoc, Cliente c) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("select * from CLIENTE where numdocumento=?");
        ps.setString(1, numdoc);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            if (c == null) {
                c = new Cliente() {};
            }
            c.setNumDocumento(numdoc);
            c.setCodCliente(rs.getString("codcliente"));
            c.setNomDocumento(rs.getString("nomdocumento"));
            c.setNombre(rs.getString("nombre"));
            c.setTelefono(rs.getString("telefono"));
        }
        cnn.close();
        ps.close();
        return c;
    }


    public static Cliente buscarClienteNombre(String nombre) throws SQLException {
        return buscarClienteNombre(nombre, null);
    }

    public static Cliente buscarClienteNombre(String nombre, Cliente cliente) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("select * from CLIENTE where nombre=?");
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            cliente = cliente == null ? new Cliente() {} : cliente;
            cliente.setCodCliente(rs.getString("codcliente"));
            cliente.setNomDocumento(rs.getString("nomdocumento"));
            cliente.setNumDocumento(rs.getString("numdocumento"));
            cliente.setNombre(rs.getString("nombre"));
            cliente.setTelefono(rs.getString("telefono"));
        } else {
            cliente = null;
        }
        cnn.close();
        ps.close();
        return cliente;
    }

    public static boolean eliminarCliente(String codcliente) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;

        ps = cnn.prepareStatement("delete from CLIENTE WHERE codcliente=?");
        ps.setString(1, codcliente);
        int rowsUpdated = ps.executeUpdate();
        cnn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }

     public static boolean actualizarCliente(Cliente c) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;

        ps = cnn.prepareStatement("UPDATE CLIENTE SET nomdocumento=?, numdocumento=?, nombre=?, telefono=? WHERE codcliente=" + c.getCodCliente());
        ps.setString(1, c.getNomDocumento());
        ps.setString(2,c.getNumDocumento());
        ps.setString(3, c.getNombre());
        ps.setString(4, c.getTelefono());
        int rowsUpdated = ps.executeUpdate();
        cnn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }

     public static ArrayList<Cliente> mostrarCliente() throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ArrayList<Cliente> lista = new ArrayList<Cliente>();

        ps = cnn.prepareStatement("SELECT * FROM CLIENTE");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
           Cliente c = new Cliente() {};
            c.setCodCliente(rs.getString("codcliente"));
            c.setNomDocumento(rs.getString("nomdocumento"));
            c.setNumDocumento(rs.getString("numdocumento"));
            c.setNombre(rs.getString("nombre"));
            c.setTelefono(rs.getString("telefono"));
            lista.add(c);
        }
        cnn.close();
        ps.close();
        return lista;
    }
}
