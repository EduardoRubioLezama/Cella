/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unam.mx.cella.controller;


import java.util.List;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.persistence.EntityManagerFactory;
import unam.mx.cella.modelo.Categoria;
import unam.mx.cella.modelo.CategoriaJpaController;
import unam.mx.cella.modelo.EntityProvider;
import unam.mx.cella.modelo.Subcategorias;
import unam.mx.cella.modelo.SubcategoriasJpaController;

/**
 *
 * @author Janeth
 */
@ManagedBean
@RequestScoped
public class SubcategoriaController {

    private final EntityManagerFactory emf;
    private Subcategorias subcategoria; 
    private Categoria categoria;
    private String nombrecategoria;
    private String nombresubcategoria;
    private SubcategoriasJpaController scjpa;
    private List<Subcategorias> subcategorias;
    
    
    /**
     * Creates a new instance of SubcategoriaController
     */
    public SubcategoriaController() {
        emf = EntityProvider.provider();
        System.out.println("creado");
        FacesContext.getCurrentInstance().getViewRoot().setLocale(
                new Locale("es-Mx"));
        this.subcategoria = new Subcategorias();
        this.categoria = new Categoria();
        this.scjpa = new SubcategoriasJpaController(emf);
        subcategorias = scjpa.findSubcategoriasEntities();
        this.nombresubcategoria = "";
        this.nombrecategoria = "";
        
    }
    public Subcategorias getSubcategoria(){
        return subcategoria;
    }
    
    public void setSubcategoria(Subcategorias nueva){
        this.subcategoria = nueva;
    }

    public Categoria getCategoria(){
        return categoria;
    }
    
    public void setCategoria(Categoria categoria){
        this.categoria = categoria;
    }
    
    public String getNombresubcategoria(){
        return nombresubcategoria;
    }
    
    public void setNombresubcategoria(String nombresubcategoria){
        this.nombresubcategoria = nombresubcategoria;
    }
    
    public String getNombrecategoria(){
        return nombrecategoria;
    }
    
    public void setNombrecategoria(String nombrecategoria){
        this.nombrecategoria = nombrecategoria;
    }
    
    public List<Subcategorias> getSubcategorias(){
        return subcategorias;
    }
    
    public void setSubcategorias(List<Subcategorias> subcategorias){
        this.subcategorias = subcategorias;
    }
    
    public String addSubcategoria() {
       
            SubcategoriasJpaController sjc = new SubcategoriasJpaController(emf);
            CategoriaJpaController cjpa = new CategoriaJpaController(emf);
            subcategoria.setNombresubcategoria(nombresubcategoria);
            subcategoria.setNombrecategoria(nombrecategoria);
            categoria = cjpa.findCategoria(subcategoria.getNombrecategoria());
            subcategoria.setIdCategoria(categoria);
            sjc.create(subcategoria);
            
            
            FacesContext.getCurrentInstance().addMessage(null,
                                                         new FacesMessage(FacesMessage.SEVERITY_INFO,
                                                                          "El registro se ha realizado correctamente", ""));
         
        return null;
    }
   
    
}