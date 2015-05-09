/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponenteDatos;

import ComponenteClase.Compra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Richard
 */
public abstract class BDCompra {

    public static void insertarCompra(Compra comp) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("INSERT INTO COMPRA (idcompra,codcliente,nroEmpleado,fechacompra,Tipocomprobante) VALUES (?,?,?,?,?)");
        ps.setInt(1, comp.getIdCompra());
        ps.setString(2, comp.getCliente().getCodCliente());
        ps.setInt(3, comp.getempleado().getNroEmpleado());
        ps.setDate(4, new java.sql.Date(comp.getFechaCompra().getTime()));
        ps.setString(5, comp.getTipoComprobante());
        ps.executeUpdate();
        cnn.close();
        ps.close();
    }

    public static Compra buscarCompra(int idcompra) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        Compra comp = null;
        ps = cnn.prepareStatement("select * from COMPRA where idcompra=?");
        ps.setInt(1, idcompra);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            comp = new Compra() {};
            comp.setIdCompra(idcompra);
            comp.setCliente(BDCliente.buscarCliente(rs.getString("codcliente")));
            comp.setempleado(BDEmpleado.buscarEmpleado(rs.getInt("nroEmpleado")));
            comp.setFechaCompra((java.util.Date) rs.getDate("fechacompra"));
            comp.setTipoComprobante(rs.getString("Tipocomprobante"));
        }
        cnn.close();
        ps.close();
        return comp;
    }

    public static boolean eliminarCompra(int codcateg) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("delete from COMPRA WHERE idcompra=?");
        ps.setInt(1, codcateg);
        int rowsUpdated = ps.executeUpdate();
        cnn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<Compra> mostrarCompra() throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ArrayList<Compra> lista = new ArrayList<Compra>();

        ps = cnn.prepareStatement("SELECT * FROM COMPRA");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Compra comp = new Compra() {};
            comp.setIdCompra(rs.getInt("idcompra"));
            comp.setCliente(BDCliente.buscarCliente(rs.getString("codcliente")));
            comp.setempleado(BDEmpleado.buscarEmpleado(rs.getInt("nroEmpleado")));
            comp.setFechaCompra((java.util.Date) rs.getDate("fechaCompra"));
            lista.add(comp);
        }
        cnn.close();
        ps.close();
        return lista;
    }
}
