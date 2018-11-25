/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unam.mx.cella.controller;


import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.persistence.EntityManagerFactory;
import unam.mx.cella.modelo.EntityProvider;
import unam.mx.cella.modelo.Subcategorias;
<<<<<<< HEAD
import unam.mx.cella.modelo.SubcategoriasJpaController;
=======
import unam.mx.cella.modelo.controller.SubcategoriasJpaController;
>>>>>>> 11c02a74516c4419684805ff421a552ecd4272f1

/**
 *
 * @author Janeth
 */
@ManagedBean
@RequestScoped
public class SubcategoriaController {

    private final EntityManagerFactory emf;
    private Subcategorias subcategoria;
 
    
    /**
     * Creates a new instance of SubcategoriaController
     */
    public SubcategoriaController() {
        emf = EntityProvider.provider();
        System.out.println("creado");
        FacesContext.getCurrentInstance().getViewRoot().setLocale(
                new Locale("es-Mx"));
        this.subcategoria = new Subcategorias();
        
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
   
    
<<<<<<< HEAD
}
=======
}
>>>>>>> 11c02a74516c4419684805ff421a552ecd4272f1
