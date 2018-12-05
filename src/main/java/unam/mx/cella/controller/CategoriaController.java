/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unam.mx.cella.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.persistence.EntityManager; 
import javax.persistence.EntityManagerFactory;
import unam.mx.cella.modelo.EntityProvider;
import unam.mx.cella.modelo.Categoria;
import unam.mx.cella.modelo.CategoriaJpaController;
import unam.mx.cella.modelo.Subcategorias;
import unam.mx.cella.modelo.SubcategoriasJpaController;

/**
 *
 * @author Janeth
 */
@ManagedBean
@RequestScoped
public class CategoriaController {

    private final EntityManagerFactory emf;
    private Categoria categoria;
    private Subcategorias subcategoria;
    private String descripcion;
    private String nombrecategoria;
    private String nombresubcategoria;
    private List<Categoria> categorias;
    private List<Subcategorias> subcategorias;
    private CategoriaJpaController cjpa;
    private SubcategoriasJpaController scjpa;
    private List<Subcategorias> subcategsresult;

    /**
     * Creates a new instance of CategoriaController
     */
    public CategoriaController() {
         emf = EntityProvider.provider();
        System.out.println("creado");
        FacesContext.getCurrentInstance().getViewRoot().setLocale(
                new Locale("es-Mx"));
        this.categoria = new Categoria();
        this.cjpa = new CategoriaJpaController(emf);
        this.scjpa = new SubcategoriasJpaController(emf);
        this.categorias = cjpa.findCategoriaEntities();
        this.subcategorias = scjpa.findSubcategoriasEntities();
        this.subcategoria = new Subcategorias();
        nombrecategoria = "";
        nombresubcategoria = "";
        descripcion = "";
    }
    public Categoria getCategoria(){
        return categoria;
    }
    public Categoria setCategoria(Categoria nueva){
        return categoria = nueva;
    }
   
    public Subcategorias getSubcategoria(){
        return subcategoria;
    }
    
    public void setSubcategoria(Subcategorias nueva){
        subcategoria = nueva;
    }

    public String getNombrecategoria(){
        return nombrecategoria;
    }
    public void setNombrecategoria(String nuevo){
        nombrecategoria = nuevo;
    }
    public String getNombresubcategoria(){
        return nombresubcategoria;
    }
    public void setNombresubcategoria(String nuevo){
        nombresubcategoria = nuevo;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public void setDescripcion(String nueva){
        descripcion = nueva;
    }
    
    public List<Categoria> getCategorias(){
        return categorias;
    }
    
    public void  setCategorias(List<Categoria> categorias){
        this.categorias = categorias;
    }
    
    public List<Subcategorias> getSubcategsResult(){
        return subcategsresult;
    }

    public void setSubcategsResult(List<Subcategorias> subcategsresult){
        this.subcategsresult = subcategsresult;
    }

    public String addCategoria() {
       
        CategoriaJpaController cjpa = new CategoriaJpaController(emf);
        categoria = new Categoria();
        
        if(cjpa.findCategoria(nombrecategoria) == null){
            categoria.setNombrecategoria(nombrecategoria);
            categoria.setDescripcion(descripcion);
            cjpa.create(categoria);
        }
        
    
        FacesContext.getCurrentInstance().addMessage(null,
                                            new FacesMessage(FacesMessage.SEVERITY_INFO,
                                               "Se ha agregado una unidad del tipo: " + categoria + " con el id " + categoria.getId() , ""));
        return null;
    }
    
    public void loadSubcategoria(Categoria categoria) throws IOException{
        subcategsresult = new ArrayList<>();
        for (Subcategorias sc :subcategorias){
            if((sc.getIdCategoria()).equals(categoria)){
                subcategsresult.add(sc);
            }
        }
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("Subcategoria.xhtml");
    }
    
    private EntityManager getEntityManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}