/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponenteClase;

/**
 *
 * @author Richard
 */
public abstract class Producto {

    private int idProducto;
    private String nombre;
    private int stock;
    private String UnidadMed;
    private Categoria categoria;
    private float preciounidad;
    private String imagen;

    /**
     * @return the idProducto
     */
    public int getIdProducto() {
        return idProducto;
    }

    /**
     * @param idProducto the idProducto to set
     */
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the preciounidad
     */
    public float getPreciounidad() {
        return preciounidad;
    }

    /**
     * @param preciounidad the preciounidad to set
     */
    public void setPreciounidad(float preciounidad) {
        this.preciounidad = preciounidad;
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

    /**
     * @return the UnidadMed
     */
    public String getUnidadMed() {
        return UnidadMed;
    }

    /**
     * @param UnidadMed the UnidadMed to set
     */
    public void setUnidadMed(String UnidadMed) {
        this.UnidadMed = UnidadMed;
    }
}
