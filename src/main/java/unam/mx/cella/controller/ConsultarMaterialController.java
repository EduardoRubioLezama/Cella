/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unam.mx.cella.controller;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import unam.mx.cella.modelo.ContenerKitMaterial;
import unam.mx.cella.modelo.ContenerKitMaterialJpaController;
import unam.mx.cella.modelo.EntityProvider;
import unam.mx.cella.modelo.Kit;
import unam.mx.cella.modelo.Material;
import unam.mx.cella.modelo.MaterialJpaController;

/**
 *
 * @author Janeth
 */
@ManagedBean
@RequestScoped
public class ConsultarMaterialController {
    
    private final EntityManagerFactory emf;
    private Kit kit;
    private ContenerKitMaterial ckm;
    private List<Material> materiales;
    private List<String> seleccionados;
    private ContenerKitMaterialJpaController ckmjpa;
    private String nombrekit;
    
    public void setCkm(ContenerKitMaterial ckm){
        this.ckm = ckm;
    }
    
    public ContenerKitMaterial getCkm(){
        return ckm;
    }
    
    public void setNombreKit(String nombre){
        this.nombrekit = nombre;
    }
    
    public String getNombreKit(){
        return nombrekit;
    }
    
    public void setKit(Kit kit){
        this.kit = kit;
    }
    
    public Kit getKit(){
        return kit;
    }
    
    
    public List<String> getSeleccionados() {
        return seleccionados;
    }
     
    public void setSeleccionados(List<String> seleccionados)
    {
        this.seleccionados = seleccionados;
    } 
    
    /**
     * Creates a new instance of VerKit
     */
    public ConsultarMaterialController(){
        emf = EntityProvider.provider();
        ckm = new ContenerKitMaterial();
        ckmjpa = new ContenerKitMaterialJpaController(emf);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(
                new Locale("es-Mx"));
        FacesContext facesContext = FacesContext. getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map params = externalContext.getRequestParameterMap();
        nombrekit = (String) params.get("kit");
        this.kit = new Kit();
        //nombrekit = "";
        System.out.println(nombrekit);
        this.seleccionados = ckmjpa.getNombresMaterial(nombrekit);
        //this.seleccionados = new ArrayList<String>();
       
        
    }
    
}