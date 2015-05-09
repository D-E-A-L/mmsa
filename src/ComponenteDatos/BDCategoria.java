/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponenteDatos;

import ComponenteClase.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Richard
 */
public abstract class BDCategoria {

    public static void insertarCategoria(Categoria cat) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("INSERT INTO Categoria (nombre,imagen) VALUES (?,?)");
        ps.setString(1, cat.getNomcateg());
        ps.setString(2, cat.getImagen());
        ps.executeUpdate();
        cnn.close();
        ps.close();
    }

    public static Categoria buscarCategoria(int codcateg) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        Categoria cat = null;
        ps = cnn.prepareStatement("select * from CATEGORIA where codcateg=?");
        ps.setInt(1, codcateg);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            cat = new Categoria() {
            };
            cat.setCodcateg(codcateg);
            cat.setNomcateg(rs.getString("nombre"));
            cat.setImagen(rs.getString("imagen"));
        }
        cnn.close();
        ps.close();
        return cat;
    }

    public static Categoria buscarCategoriaNomCM(String nomcat) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        Categoria cat = null;
        ps = cnn.prepareStatement("select * from CATEGORIA where nombre=?");
        ps.setString(1, nomcat);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            cat = new Categoria() {
            };
            cat.setNomcateg(nomcat);
            cat.setImagen(rs.getString("imagen"));
            cat.setCodcateg(rs.getInt("codcateg"));
        }
        cnn.close();
        ps.close();
        return cat;
    }

    public static Categoria buscarCategoriaNom(String nomcat) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        Categoria cat = null;
        ps = cnn.prepareStatement("select * from CATEGORIA where nombre=?");
        ps.setString(1, nomcat);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            cat = new Categoria() {
            };
            cat.setNomcateg(nomcat);
            cat.setCodcateg(rs.getInt("codcateg"));
        }
        cnn.close();
        ps.close();
        return cat;
    }

    public static boolean eliminarCategoria(int codcateg) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("delete from CATEGORIA WHERE codcateg=?");
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

   /* public static boolean eliminarCategNom(String nombre) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ps = cnn.prepareStatement("delete from CATEGORIA WHERE nombre=?");
        ps.setString(1, nombre);
        int rowsUpdated = ps.executeUpdate();
        cnn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }*/

    public static boolean actualizarCategoria(Categoria cat) throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;

        ps = cnn.prepareStatement("UPDATE CATEGORIA SET imagen=? WHERE codcateg=" + cat.getCodcateg());
        ps.setString(1, cat.getImagen());
        int rowsUpdated = ps.executeUpdate();
        cnn.close();
        ps.close();
        if (rowsUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<Categoria> mostrarCategoria() throws SQLException {
        Connection cnn = BD.getConnection();
        PreparedStatement ps = null;
        ArrayList<Categoria> lista = new ArrayList<Categoria>();

        ps = cnn.prepareStatement("SELECT * FROM CATEGORIA");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Categoria cat = new Categoria() {
            };
            cat.setCodcateg(rs.getInt("codcateg"));
            cat.setNomcateg(rs.getString("nombre"));
            cat.setImagen(rs.getString("imagen"));
            lista.add(cat);
        }
        cnn.close();
        ps.close();
        return lista;
    }
}
