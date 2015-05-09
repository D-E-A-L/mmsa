/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ComponenteDatos;

import ComponenteClase.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Richard
 */
public abstract class BDProducto {

    
    public static Producto insertarProducto(Producto p) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("INSERT INTO PRODUCTO (nombre, stock, codcateg, preciounidad,unidadmed,imagen) VALUES (?,?,?,?,?,?)");
        ps.setString(1, p.getNombre());
        ps.setInt(2, p.getStock());
        ps.setInt(3,p.getCategoria().getCodcateg());
        ps.setDouble(4,p.getPreciounidad());
        ps.setString(5,p.getUnidadMed());
        ps.setString(6, p.getImagen());
        ps.executeUpdate();
        PreparedStatement ps2 = cnn.prepareStatement("select max(idProducto) from PRODUCTO");
        ResultSet r = ps2.executeQuery();
        if (r.next()) {
            int lastID = r.getInt(1);
            p.setIdProducto(lastID);
        }
        ps2.close();
        cnn.close();
        ps.close();
        return p;
    }

    public static Producto buscarProducto(int idprod) throws SQLException {
        return buscarProducto(idprod, null);
    }

    public static Producto buscarProducto(int idprod, Producto p) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("select * from PRODUCTO where idProducto=?");
        ps.setInt(1, idprod);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            if (p == null) {
                p = new Producto() {};
            }
            p.setIdProducto(idprod);
            p.setNombre(rs.getString("nombre"));
            p.setStock(rs.getInt("stock"));
            p.setCategoria(BDCategoria.buscarCategoria(rs.getInt("codcateg")));
            p.setPreciounidad(rs.getFloat("preciounidad"));
            p.setUnidadMed(rs.getString("unidadmed"));
            p.setImagen(rs.getString("imagen"));
        }
        cnn.close();
        ps.close();
        return p;
    }

     public static Producto buscarProductoNombre(String nom) throws SQLException {
        return buscarProductoNombre(nom, null);
    }

    public static Producto buscarProductoNombre(String nom, Producto p) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("select * from PRODUCTO where nombre=?");
        ps.setString(1, nom);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            if (p == null) {
                p = new Producto() {};
            }
            p.setNombre(nom);
            p.setIdProducto(rs.getInt("idProducto"));
            p.setStock(rs.getInt("stock"));
            p.setCategoria(BDCategoria.buscarCategoria(rs.getInt("codcateg")));
            p.setPreciounidad(rs.getFloat("preciounidad"));
            p.setUnidadMed(rs.getString("unidadmed"));
            p.setImagen(rs.getString("imagen"));
        }
        cnn.close();
        ps.close();
        return p;
    }


    public static Producto buscarProductoSinIm(int idprod) throws SQLException {
        return buscarProductoSinIm(idprod, null);
    }

    public static Producto buscarProductoSinIm(int idprod, Producto p) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("select * from PRODUCTO where idProducto=?");
        ps.setInt(1, idprod);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            p = p== null ? new Producto() {} : p;
            p.setIdProducto(idprod);
            p.setNombre(rs.getString("nombre"));
            p.setStock(rs.getInt("stock"));
            p.setCategoria(BDCategoria.buscarCategoria(rs.getInt("codcateg")));
            p.setPreciounidad(rs.getFloat("preciounidad"));
            p.setUnidadMed(rs.getString("unidadmed"));
        }
        cnn.close();
        ps.close();
        return p;
    }

    public static Producto buscarProductoCateg(int codcateg) throws SQLException {
        return buscarProductoCateg(codcateg, null);
    }

    public static Producto buscarProductoCateg(int codcateg, Producto p) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("select * from PRODUCTO where codcateg=?");
        ps.setInt(1, codcateg);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            p = p== null ? new Producto() {} : p;
            p.setIdProducto(rs.getInt("idProducto"));
            p.setNombre(rs.getString("nombre"));
            p.setStock(rs.getInt("stock"));
            p.setCategoria(BDCategoria.buscarCategoria(rs.getInt("codcateg")));
            p.setPreciounidad(rs.getFloat("preciounidad"));
            p.setUnidadMed(rs.getString("unidadmed"));
            p.setImagen(rs.getString("imagen"));
        } else {
            p = null;
        }
        cnn.close();
        ps.close();
        return p;
    }

    public static boolean eliminarProducto(int idprod) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;

        ps = cnn.prepareStatement("delete from PRODUCTO WHERE idProducto=?");
        ps.setInt(1, idprod);
        int rowsUpdated = ps.executeUpdate();
        cnn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean actualizarProducto(Producto p) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;

        ps = cnn.prepareStatement("UPDATE PRODUCTO SET nombre=?, stock=?, codcateg=?, preciounidad=?,unidadmed=?,imagen=? WHERE idProducto=" + p.getIdProducto());
        ps.setString(1, p.getNombre());
        ps.setInt(2, p.getStock());
        ps.setInt(3, p.getCategoria().getCodcateg());
        ps.setFloat(4,p.getPreciounidad());
        ps.setString(5,p.getUnidadMed());
        ps.setString(6, p.getImagen());
        int rowsUpdated = ps.executeUpdate();
        cnn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }

     public static ArrayList<Producto> mostrarProducto() throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ArrayList<Producto> lista = new ArrayList<Producto>();

        ps = cnn.prepareStatement("SELECT * FROM PRODUCTO");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Producto e = new Producto() {};
            e.setIdProducto(rs.getInt("idProducto"));
            e.setNombre(rs.getString("nombre"));
            e.setStock(rs.getInt("stock"));
            e.setCategoria(BDCategoria.buscarCategoria(rs.getInt("codcateg")));
            e.setPreciounidad(rs.getFloat("preciounidad"));
            e.setUnidadMed(rs.getString("unidadmed"));
            lista.add(e);
        }
        cnn.close();
        ps.close();
        return lista;
    }
}
