/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unam.mx.cella.controller;

import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import unam.mx.cella.modelo.EntityProvider;
import unam.mx.cella.modelo.Unidadmaterial;
import unam.mx.cella.modelo.UnidadmaterialJpaController;

/**
 *
 * @author chars
 */
@ManagedBean
@RequestScoped
public class SolicitarPrestamo {

    private final EntityManagerFactory emf;
    private String articulo;
    private Unidadmaterial material;
    private UnidadmaterialJpaController mjc;

    /**
     * Creates a new instance of SolicitarPrestamo
     */
    public SolicitarPrestamo() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("es-Mx"));
        emf = EntityProvider.provider();
        articulo = "";
        material = new Unidadmaterial();
        mjc = new UnidadmaterialJpaController(emf);
    }

    /**
     * @return the articulo
     */
    public String getArticulo() {
        return articulo;
    }

    /**
     * @param articulo the articulo to set
     */
    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    /**
     * @return the material
     */
    public Unidadmaterial getMaterial() {
        return material;
    }

    /**
     * @param material the material to set
     */
    public void setMaterial(Unidadmaterial material) {
        this.material = material;
    }

    /**
     * @return the mjc
     */
    public UnidadmaterialJpaController getMjc() {
        return mjc;
    }

    /**
     * @param mjc the mjc to set
     */
    public void setMjc(UnidadmaterialJpaController mjc) {
        this.mjc = mjc;
    }

    
    public String prestamoMaterial() {
        boolean existencia = mjc.findUnidadmaterialByNombre(articulo);

        if (existencia && mjc.findUnidadmaterialEstadoByNombre(articulo).getEstado().equals("disponible")) {
            
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "material en existencia y disponible", ""));
            return "mensaje?faces-redirect=true";
        }
        return null;
    }
}
