/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponenteClase;

import java.util.Date;

/**
 *
 * @author Richard
 */
public abstract class Compra {

    private int idCompra;
    private Cliente cliente;
    private Empleado empleado;
    private Date fechaCompra;
    private String tipoComprobante;

    /**
     * @return the idCompra
     */
    public int getIdCompra() {
        return idCompra;
    }

    /**
     * @param idCompra the idCompra to set
     */
    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    /**
     * @return the empleado
     */
    public Empleado getempleado() {
        return empleado;
    }

    /**
     * @param nroEmpleado the nroEmpleado to set
     */
    public void setempleado(Empleado empleado) {
        this.empleado = empleado;
    }

    /**
     * @return the fechaCompra
     */
    public Date getFechaCompra() {
        return fechaCompra;
    }

    /**
     * @param fechaCompra the fechaCompra to set
     */
    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    /**
     * @return the tipoComprobante
     */
    public String getTipoComprobante() {
        return tipoComprobante;
    }

    /**
     * @param tipoComprobante the tipoComprobante to set
     */
    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
