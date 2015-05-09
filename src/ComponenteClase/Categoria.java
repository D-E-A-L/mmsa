/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponenteClase;

/**
 *
 * @author Richard
 */
public abstract class Categoria {

    private int codcateg;
    private String nomcateg;
    private String imagen;//contendra la Ruta de la imagen

    /**
     * @return the codcateg
     */
    public int getCodcateg() {
        return codcateg;
    }

    /**
     * @param codcateg the codcateg to set
     */
    public void setCodcateg(int codcateg) {
        this.codcateg = codcateg;
    }

    /**
     * @return the nomcateg
     */
    public String getNomcateg() {
        return nomcateg;
    }

    /**
     * @param nomcateg the nomcateg to set
     */
    public void setNomcateg(String nomcateg) {
        this.nomcateg = nomcateg;
    }

    /**
     * @return the imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
