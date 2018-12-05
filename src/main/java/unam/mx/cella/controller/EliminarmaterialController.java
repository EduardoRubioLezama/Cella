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
import unam.mx.cella.modelo.controller.exceptions.IllegalOrphanException;
import unam.mx.cella.modelo.controller.exceptions.NonexistentEntityException;
import unam.mx.cella.modelo.EntityProvider;
import unam.mx.cella.modelo.Material;
import unam.mx.cella.modelo.Unidadmaterial;
import unam.mx.cella.modelo.MaterialJpaController;
import unam.mx.cella.modelo.UnidadmaterialJpaController;

/**
 *
 * @author rossa
 */
@ManagedBean
@RequestScoped
public class EliminarmaterialController {

    /**
     * Creates a new instance of EliminarmaterialController
     */
    private final EntityManagerFactory emf;
    private Unidadmaterial unidadmaterial;
    private int id;
    
    public EliminarmaterialController() {
        emf = EntityProvider.provider();
        FacesContext.getCurrentInstance().getViewRoot().setLocale(
                new Locale("es-Mx"));
        this.unidadmaterial = new Unidadmaterial();
               
    }
    
    public Unidadmaterial getUnidadMaterial(){
        return unidadmaterial;
    }
    
    public void setUnidadMatrial(Unidadmaterial unidadmaterial){
        this.unidadmaterial = unidadmaterial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
        
    public String removeUnidadMaterial() throws IllegalOrphanException, NonexistentEntityException, unam.mx.cella.modelo.exceptions.IllegalOrphanException, unam.mx.cella.modelo.exceptions.NonexistentEntityException{
        
        UnidadmaterialJpaController umjpa = new UnidadmaterialJpaController(emf);
        Unidadmaterial umt = umjpa.findUnidadmaterial(id);
        if(umt == null){
             FacesContext.getCurrentInstance().addMessage(null,
                               new FacesMessage(FacesMessage.SEVERITY_INFO,
                               "El material con el id " + id + " no existe, intenta de nuevo" , ""));
        }
        else{
            umjpa.destroy(umt.getId());
       
            FacesContext.getCurrentInstance().addMessage(null,
                               new FacesMessage(FacesMessage.SEVERITY_INFO,
                               "El material con el id "+ id+ " fue eliminado correctamente" , ""));
        }
        return null;
    }
}