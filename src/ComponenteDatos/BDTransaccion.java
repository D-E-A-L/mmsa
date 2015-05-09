/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ComponenteDatos;

import ComponenteClase.Transaccion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Richard
 */
public abstract class BDTransaccion {


     public static void insertarTransaccion(Transaccion t) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("INSERT INTO TRANSACCION (idcompra,idProducto,cantidad) VALUES (?,?,?)");
        ps.setInt(1, t.getCompra().getIdCompra());
        ps.setInt(2, t.getProducto().getIdProducto());
        ps.setInt(3, t.getCantidad());
        ps.executeUpdate();
        cnn.close();
        ps.close();
    }

    public static boolean eliminarTransaccion(int idcomp,int idprod) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("delete from TRANSACCION WHERE idcompra=? and idProducto=?");
        ps.setInt(1, idcomp);
        ps.setInt(2, idprod);
        int rowsUpdated = ps.executeUpdate();
        cnn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<Transaccion> mostrarTransaccion() throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ArrayList<Transaccion> lista = new ArrayList<Transaccion>();

        ps = cnn.prepareStatement("SELECT * FROM TRANSACCION");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Transaccion u = new Transaccion() {};
            u.setCompra(BDCompra.buscarCompra(rs.getInt("idcompra")));
            u.setProducto(BDProducto.buscarProducto(rs.getInt("idProducto")));
            u.setCantidad(rs.getInt("cantidad"));
            lista.add(u);
        }
        cnn.close();
        ps.close();
        return lista;
    }
}
