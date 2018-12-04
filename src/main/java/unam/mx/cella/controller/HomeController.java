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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import org.primefaces.model.ByteArrayContent;
import org.primefaces.model.StreamedContent;
import unam.mx.cella.modelo.Alumno;
import unam.mx.cella.modelo.ContenerKitMaterial;
import unam.mx.cella.modelo.ContenerKitMaterialJpaController;
import unam.mx.cella.modelo.EntityProvider;
import unam.mx.cella.modelo.Kit;
import unam.mx.cella.modelo.Material;
import unam.mx.cella.modelo.MaterialJpaController;

/**
 *
 * @author eduar
 */
@ManagedBean
@SessionScoped
public class HomeController {

    private final EntityManagerFactory emf;
    private List<String> materiales;
    private List<Material> materiales2;
    private Material material;
    private MaterialJpaController mjpa;
    private Alumno alumno;
    private String busqueda;
    private List<String> resultado;

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public List<String> getResultado() {
        return resultado;
    }

    public void setResultado(List<String> resultado) {
        this.resultado = resultado;
    }
    
    
    
    public List<Material> getMateriales2() {
        return materiales2;
    }

    public void setMateriales2(List<Material> materiales2) {
        this.materiales2 = materiales2;
    }
    

    public List<String> getMateriales() {
        return materiales;
    }

    public void setMateriales(List<String> materiales) {
        this.materiales = materiales;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public List<String> getSeleccionados() {
        return seleccionados;
    }

    public void setSeleccionados(List<String> seleccionados) {
        this.seleccionados = seleccionados;
    }
    private List<String> seleccionados;
    /**
     * Creates a new instance of HomeController
     */
    public HomeController() {
        emf = EntityProvider.provider();
        FacesContext.getCurrentInstance().getViewRoot().setLocale(
                new Locale("es-Mx"));
        this.mjpa = new MaterialJpaController(emf);
        this.materiales = mjpa.getNombresMaterial();
        this.materiales2 = mjpa.getMateriales();
       // this.seleccionados = new ArrayList<>();
        this.material = new Material();
        busqueda = "";
    }
    
    public StreamedContent getMiFoto() {
        
        if (material.getFoto() != null) {
            return new ByteArrayContent(material.getFoto());
        }
        return null;
    }
    
     public void loadResultBusqueda() throws IOException{
        //MaterialJpaController mjpa = new MaterialJpaController(emf);
       // materiales = mjpa.getNombresMaterial();
        materiales.sort(String.CASE_INSENSITIVE_ORDER);
        resultado = new ArrayList<>();
        CharSequence busquedaChar = new StringBuffer(busqueda);
        for (String s :materiales){
            if(s.contains(busquedaChar)){
                resultado.add(s);
            }
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect("BusquedaResultado.xhtml");
    }
    
}
