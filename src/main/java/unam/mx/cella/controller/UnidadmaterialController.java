/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unam.mx.cella.controller;

import java.util.List;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import unam.mx.cella.modelo.EntityProvider;
import unam.mx.cella.modelo.Unidadmaterial;
import unam.mx.cella.modelo.controller.UnidadmaterialJpaController;

/**
 *
 * @author rossa
 */
@ManagedBean
@RequestScoped
public class UnidadmaterialController {

    /**
     * Creates a new instance of UnidadmaterialController
     */
    private final EntityManagerFactory emf;
    private Unidadmaterial u_material;
    private UnidadmaterialJpaController ujp;
    public UnidadmaterialController() {
        emf = EntityProvider.provider();
        FacesContext.getCurrentInstance().getViewRoot().setLocale(
                new Locale("es-Mx"));
    }
    
    public Unidadmaterial getUnidadMaterial(){
        return u_material;
    }
    
    public void setUnidadMaterial(Unidadmaterial u_mat){
        u_material=u_mat;
    }
    
    public String addUnidadMaterial(){
        UnidadmaterialJpaController umjpa = new UnidadmaterialJpaController(emf);
        Unidadmaterial umt = new Unidadmaterial();
        umt.setNombrematerial(u_material.getNombrematerial());
        umt.setEstado(u_material.getEstado());
        umt.setIdMaterial(u_material.getIdMaterial());
        umjpa.create(u_material);
    
        FacesContext.getCurrentInstance().addMessage(null,
                                                         new FacesMessage(FacesMessage.SEVERITY_INFO,
                                                                          "Felicidades, el registro se ha realizado correctamente", ""));
        return null;
    }
    
    
}
