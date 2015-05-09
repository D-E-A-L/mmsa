/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponenteDatos;

import ComponenteClase.DetalleProveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Richard
 */
public abstract class BDDetalleProveedor {

    public static DetalleProveedor insertarDetalleProveedor(DetalleProveedor dp) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("INSERT INTO DETALLE_PROVEEDOR (nrofactura,ruc, idProducto,cantidad,preciocompra,precioventa) VALUES (?,?,?,?,?,?)");
        ps.setString(1, dp.getNroFactura());
        ps.setString(2, dp.getProveedor().getRuc());
        ps.setInt(3, dp.getProducto().getIdProducto());
        ps.setInt(4, dp.getCantidad());
        ps.setFloat(5, dp.getPreciocompra());
        ps.setFloat(6, dp.getPrecioventa());
        ps.executeUpdate();
        PreparedStatement ps2 = cnn.prepareStatement("select max(nrofactura) from DETALLE_PROVEEDOR");
        ResultSet r = ps2.executeQuery();
        if (r.next()) {
            String  lastID = r.getString(1);
            dp.setNroFactura(lastID);
        }
        ps2.close();
        cnn.close();
        ps.close();
        return dp;
    }

    public static DetalleProveedor buscarDetalleProveedor(String nrofactura) throws SQLException {
        return buscarDetalleProveedor(nrofactura, null);
    }

    public static DetalleProveedor buscarDetalleProveedor(String nrofactura, DetalleProveedor dp) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("select * from DETALLE_PROVEEDOR where nrofactura=?");
        ps.setString(1, nrofactura);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            if (dp == null) {
                dp = new DetalleProveedor() {};
            }
            dp.setNroFactura(nrofactura);
            dp.setProveedor(BDProveedor.buscarProveedor(rs.getString("ruc")));
            dp.setProducto(BDProducto.buscarProducto(rs.getInt("idProducto")));
            dp.setCantidad(rs.getInt("cantidad"));
            dp.setPreciocompra(rs.getInt("preciocompra"));
            dp.setPrecioventa(rs.getInt("precioventa"));
        }
        cnn.close();
        ps.close();
        return dp;
    }

    public static boolean eliminarDetalleProveedor(String nrofactura) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;

        ps = cnn.prepareStatement("delete from DETALLE_PROVEEDOR WHERE nrofactura=?");
        ps.setString(1, nrofactura);
        int rowsUpdated = ps.executeUpdate();
        cnn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<DetalleProveedor> mostrarDetalleProveedor() throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ArrayList<DetalleProveedor> lista = new ArrayList<DetalleProveedor>();

        ps = cnn.prepareStatement("SELECT * FROM DETALLE_PROVEEDOR");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            DetalleProveedor dp = new DetalleProveedor() {};
            dp.setNroFactura(rs.getString("nroFactura"));
            dp.setProveedor(BDProveedor.buscarProveedor(rs.getString("ruc")));
            dp.setProducto(BDProducto.buscarProducto(rs.getInt("idProducto")));
            dp.setCantidad(rs.getInt("cantidad"));
            dp.setPreciocompra(rs.getInt("preciocompra"));
            dp.setPrecioventa(rs.getInt("precioventa"));
            lista.add(dp);
        }
        cnn.close();
        ps.close();
        return lista;
    }
}
