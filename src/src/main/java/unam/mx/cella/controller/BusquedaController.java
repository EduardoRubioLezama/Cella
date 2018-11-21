/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unam.mx.cella.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import unam.mx.cella.modelo.EntityProvider;
import unam.mx.cella.modelo.Material;
import unam.mx.cella.modelo.Unidadmaterial;
import unam.mx.cella.modelo.controller.MaterialJpaController;
import unam.mx.cella.modelo.controller.UnidadmaterialJpaController;

/**
 *
 * @author rossa
 */
@ManagedBean
@RequestScoped
public class BusquedaController {

    /**
     * Creates a new instance of BusquedaController
     */
    private final EntityManagerFactory emf;
    private Material material;
    private List<Material> materiales;
    private String nombrematerial;
    public BusquedaController() {
        emf = EntityProvider.provider();
        FacesContext.getCurrentInstance().getViewRoot().setLocale(
                new Locale("es-Mx"));
        this.material = new Material();
        this.materiales = new ArrayList<>();
        nombrematerial = "";
    } 
    
    public Material getMaterial(){
        return material;
    }
    
    public void setMaterial(Material material){
        this.material = material;
    }
    
    public List<Material> getMateriales(){
        return materiales;
    }

    public String getNombrematerial(){
        return nombrematerial;
    }
    
    public void setNombrematerial(String nombrematerial){
        this.nombrematerial = nombrematerial;
    }
    
    public List<Material> ResultBusqueda(){
        MaterialJpaController mjpa = new MaterialJpaController(emf);
        List<Material> mt = mjpa.findMaterials(nombrematerial);
        return mt;
    }
    
    private EntityManager getEntityManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
