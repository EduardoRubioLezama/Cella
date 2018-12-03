/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unam.mx.cella.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import unam.mx.cella.modelo.EntityProvider;
import unam.mx.cella.modelo.Material;
import unam.mx.cella.modelo.MaterialJpaController;
import unam.mx.cella.modelo.Unidadmaterial;
import unam.mx.cella.modelo.UnidadmaterialJpaController;

/**
 *
 * @author rossa
 */
@ManagedBean
@RequestScoped
public class VistaMaterialController {

    /**
     * Creates a new instance of VistaMaterialController
     */
    private final EntityManagerFactory emf;
    private Material material;
    private List<Material> materiales;
    private List<Unidadmaterial> uMateriales;
    private String nombrematerial;
    private Integer numDisponibles;
    private Integer numEnMantenimiento;
    private String descripcion;
    public VistaMaterialController() {
        emf = EntityProvider.provider();
        FacesContext.getCurrentInstance().getViewRoot().setLocale(
                new Locale("es-Mx"));
        this.materiales = new ArrayList<>();
        this.uMateriales = new ArrayList<>();
        nombrematerial="";
        
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
    
    public void setMateriales(List<Material> materiales){
        this.materiales = materiales;
    }
    
       public List<Unidadmaterial> getUMateriales(){
        return uMateriales;
    }
    
    public void setUMateriales(List<Unidadmaterial> uMateriales){
        this.uMateriales = uMateriales;
    }
    
    public String getNombreMaterial(){
        return nombrematerial;
    }
    
    public void setNombreMaterial(String nombrematerial){
        this.nombrematerial = nombrematerial;
    }
    
    public Integer getNumDisponibles(){
        return numDisponibles;
    }
    
    public void setNumDisponibles(Integer numDisponibles){
        this.numDisponibles = numDisponibles;
    }
    
    public Integer getNumEnMantenimiento(){
        return numDisponibles;
    }
    
    public void setNumEnMantenimiento(Integer numEnMantenimiento){
        this.numEnMantenimiento = numEnMantenimiento;
    }
    
    public String getDescripcion(){
        return descripcion;
    }
    
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    public void loadVistaMaterial(){
        MaterialJpaController mjpa = new MaterialJpaController(emf);
        material = mjpa.findMaterial(nombrematerial);
        descripcion = material.getDescripcion();
        UnidadmaterialJpaController umjpa = new UnidadmaterialJpaController(emf);
        uMateriales = umjpa.findUnidadmaterialByNombre();
        numDisponibles=0;
        numEnMantenimiento=0;
        for(Unidadmaterial u:uMateriales){
            if((u.getIdMaterial()).equals(material)){
                if((u.getEstado()).equals("disponible")){
                    numDisponibles++;
                }
                else{
                    numEnMantenimiento++;
                }
            }
        }
    }
    
    private EntityManager getEntityManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
