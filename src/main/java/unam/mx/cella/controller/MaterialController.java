package unam.mx.cella.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import unam.mx.cella.modelo.EntityProvider;
import unam.mx.cella.modelo.Material;
import unam.mx.cella.modelo.Unidadmaterial;
import unam.mx.cella.modelo.MaterialJpaController;
import unam.mx.cella.modelo.UnidadmaterialJpaController;
import unam.mx.cella.modelo.exceptions.NonexistentEntityException;

/**
 *
 * @author rossa
 */
@ManagedBean
@RequestScoped
public class MaterialController {

    /**
     * Creates a new instance of MaterialController
     */
    
    private final EntityManagerFactory emf;
    private Material material;
    private List<String> materiales;
    private Unidadmaterial unidadmaterial;
    private String nombrematerial;
    private String descripcion;
    private String estado;
    private MaterialJpaController mjpa;
    private UploadedFile fotografia;

    public UploadedFile getFotografia() {
        return fotografia;
    }

    public void setFotografia(UploadedFile fotografia) {
        this.fotografia = fotografia;
    }
    
    public MaterialController() {
        emf = EntityProvider.provider();
        mjpa = new MaterialJpaController(emf);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(
                new Locale("es-Mx"));
        this.material = new Material();
        this.unidadmaterial = new Unidadmaterial();
        nombrematerial = "";
        descripcion = "";
        estado = "";
        materiales = mjpa.getNombresMaterial();
    }
    
    public Material getMaterial(){
        return material;
    }
    
    public void setMaterial(Material material){
        this.material = material;
    }

     public List<String> getMateriales() {
        return materiales;
    }

    public void setMateriales(List<String> materiales) {
        this.materiales = materiales;
    }
    
    public Unidadmaterial getUnidadMaterial(){
        return unidadmaterial;
    }
    
    public void setUnidadMatrial(Unidadmaterial unidadmaterial){
        this.unidadmaterial = unidadmaterial;
    }
    
     public void fileUploadListener(FileUploadEvent e) {
        this.fotografia = e.getFile();
    }
    
    public String getNombrematerial(){
        return nombrematerial;
    }
    
    public void setNombrematerial(String nombrematerial){
        this.nombrematerial = nombrematerial;
    }
    
    public String getDescripcion(){
        return descripcion;
    }
    
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    public String getEstado(){
        return estado;
    }
    
    public void setEstado(String estado){
        this.estado = estado;
    }

    public void enMantenimiento(){
        this.setEstado("en mantenimiento");
    }
    
    public void disponible(){
        this.setEstado("disponible");
    
    }
    
    public void addNuevo() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("AgregarNuevoMaterial.xhtml");
    }
    
    public String addMaterial() throws NonexistentEntityException, Exception{
                       
        UnidadmaterialJpaController umjpa = new UnidadmaterialJpaController(emf);
        Material mt = new Material();
        Unidadmaterial umt = new Unidadmaterial();
        material = mjpa.findMaterial(nombrematerial);
        if(material == null){
            mt.setNombrematerial(nombrematerial);
            mt.setDescripcion(descripcion);
            if(fotografia != null){
                mt.setFoto(fotografia.getContents());
            }
            
            mjpa.create(mt);
        }
        else if(material != null){
            material.setFoto(fotografia.getContents());
            mjpa.edit(material);
        }
        mt = mjpa.findMaterial(nombrematerial);
        umt.setNombrematerial(nombrematerial);
        umt.setEstado(estado.toLowerCase());
        
        umt.setIdMaterial(mt);
        umjpa.create(umt);
    
        FacesContext.getCurrentInstance().addMessage(null,
                                            new FacesMessage(FacesMessage.SEVERITY_INFO,
                                               "Se ha agregado una unidad del tipo: " + nombrematerial + " con el id " + umt.getId() +" y estado: "+ umt.getEstado() , ""));
        return null;
    }

    private EntityManager getEntityManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}