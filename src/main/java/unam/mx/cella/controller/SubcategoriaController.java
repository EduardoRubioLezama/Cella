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
    private CategoriaJpaController cjpa;
    private Subcategorias subcategoria;
    private List<String> categorias;
    private List<String> subcategorias;
 
    
    /**
     * Creates a new instance of SubcategoriaController
     */
    public SubcategoriaController() {
        emf = EntityProvider.provider();
        System.out.println("creado");
        FacesContext.getCurrentInstance().getViewRoot().setLocale(
                new Locale("es-Mx"));
        this.cjpa = new CategoriaJpaController(emf);
        this.subcategoria = new Subcategorias();
        this.categorias = cjpa.getNombresCategoria();
        this.subcategorias = categorias;
        subcategorias.remove(subcategoria.getNombrecategoria());
        
    }

    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }

    public List<String> getSubcategorias() {
        return subcategorias;
    }

    public void setSubcategorias(List<String> subcategorias) {
        this.subcategorias = subcategorias;
    }
    
    
    
    public Subcategorias getSubcategoria(){
        return subcategoria;
    }
    
    public void setSubcategoria(Subcategorias nueva){
        subcategoria = nueva;
    }

    
    public String addSubcategoria() {
       
            SubcategoriasJpaController sjc = new SubcategoriasJpaController(emf);
            Subcategorias subcateg = new Subcategorias();
                subcateg.setNombrecategoria(subcategoria.getNombrecategoria());
                subcateg.setNombresubcategoria(subcategoria.getNombresubcategoria());
                subcateg.setIdCategoria(subcategoria.getIdCategoria());
                sjc.create(subcategoria);
            
            
            FacesContext.getCurrentInstance().addMessage(null,
                                                         new FacesMessage(FacesMessage.SEVERITY_INFO,
                                                                          "El registro se ha realizado correctamente", ""));
         
        return null;
    }
   
    
}