/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponenteOtros;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.URL;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Richard
 */
public abstract class metodos {

    private BufferedImage _image = null;
    private FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de Imagen", "jpg", "png");
    private URL obtener;

    public metodos() {
    }
    /* dada una direccion fisica de un archivo de imagen,
     * coloca esta en el objeto BufferedImage, o sea en memoria */

    public void cargar_imagen(URL _url) {
        //se llena el buffer con la imagen        
        try {
            _image = ImageIO.read(_url);
        } catch (IOException ex) {
            Logger.getLogger(metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* retorna el objeto almacenado en memoria */
    public BufferedImage Obtener_imagen_de_Buffer() {
        return _image;
    }

    /* Metod que muestra una ventana de dialgo para aÃ±adir "archivo de imagen"
     *  en memoria  */
    public void Abrir_Dialogo(JPanel p) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                //se asigna a "url" el archivo de imagen seleccionado
                setObtener(fileChooser.getSelectedFile().toURL());
                URL url = fileChooser.getSelectedFile().toURL();
                //se lo coloca en memoria
                cargar_imagen(url);
                //se aÃ±ade al contenedor
                p.add(new mipanel(Obtener_imagen_de_Buffer(), p.getSize()) {});
                p.setVisible(true);
                p.repaint();
            } catch (IOException ex) {
                Logger.getLogger(metodos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void Mostrar(JPanel p) {
        try {
            //se asigna a "url" el archivo de imagen seleccionado
            URL url = getObtener();
            //se lo coloca en memoria
            cargar_imagen(url);
            //se aÃ±ade al contenedor
            p.add(new mipanel(Obtener_imagen_de_Buffer(), p.getSize()) {});
            p.setVisible(true);
            p.repaint();
        } catch (Exception ex) {
            Logger.getLogger(metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String ObtenerUrl() {
        return " " + getObtener() + " ";

    }

    public URL getObtener() {
        return obtener;
    }

    public void setObtener(URL obtener) {
        this.obtener = obtener;
    }
}
