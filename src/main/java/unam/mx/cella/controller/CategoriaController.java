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
    private List<String> categorias;
    private CategoriaJpaController cjpa;
    private Categoria categoria;
    private Subcategorias subcategoria;
    private String descripcion;
    private String nombrecategoria;
    private String nombresubcategoria;
    private List<String> seleccionados;

    /**
     * Creates a new instance of CategoriaController
     */
    public CategoriaController() {
        emf = EntityProvider.provider();
        this.cjpa = new CategoriaJpaController(emf);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(
                new Locale("es-Mx"));
        this.categoria = new Categoria();
        this.categorias = cjpa.getNombresCategoria();
        this.seleccionados = new ArrayList<>();
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

    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }

    public List<String> getSeleccionados() {
        return seleccionados;
    }

    public void setSeleccionados(List<String> seleccionados) {
        this.seleccionados = seleccionados;
    }

    
    
    public String addCategoria() {
       
            CategoriaJpaController cjc = new CategoriaJpaController(emf);
            Categoria categ = new Categoria();
            Categoria aux = cjc.findCategoria(categoria.getNombrecategoria());
            if (aux != null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Categoria "+ categoria.getNombrecategoria() + " ya ha sido registrada", ""));
            }
            else{
            categ.setNombrecategoria(categoria.getNombrecategoria());
            categ.setDescripcion(categoria.getDescripcion());
            cjc.create(categ);
            
            FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Categoria "+ categoria.getNombrecategoria()+" agregada correctamente", ""));
            }
        return null;
    }
    private EntityManager getEntityManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}