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
import unam.mx.cella.modelo.MaterialJpaController;
import unam.mx.cella.modelo.UnidadmaterialJpaController;

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
    private List<String> materiales;
    private String busqueda;
    private List<String> resultado;
    public BusquedaController() {
        emf = EntityProvider.provider();
        FacesContext.getCurrentInstance().getViewRoot().setLocale(
                new Locale("es-Mx"));
        this.material = new Material();
        this.materiales = new ArrayList<>();
        busqueda = "";
    } 
    
    public Material getMaterial(){
        return material;
    }
    
    public void setMaterial(Material material){
        this.material = material;
    }
    
    public List<String> getMateriales(){
        return materiales;
    }

    public String getBusqueda(){
        return busqueda;
    }
    
    public void setBusqueda(String busqueda){
        this.busqueda = busqueda;
    }
    
    public List<String> getResultado(){
        return resultado;
    }

    public void setResultado(List<String> resultado){
        this.resultado = resultado;
    }
    
    public void loadResultBusqueda(){
        MaterialJpaController mjpa = new MaterialJpaController(emf);
        materiales = mjpa.getNombresMaterial();
        materiales.sort(String.CASE_INSENSITIVE_ORDER);
        resultado = new ArrayList<>();
        CharSequence busquedaChar = new StringBuffer(busqueda);
        for (String s :materiales){
            if(s.contains(busquedaChar)){
                resultado.add(s);
            }
        }
    }
    
    private EntityManager getEntityManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
