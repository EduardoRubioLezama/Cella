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
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.persistence.EntityManagerFactory;
import unam.mx.cella.modelo.Kit;
import unam.mx.cella.modelo.EntityProvider;
import unam.mx.cella.modelo.KitJpaController;
import unam.mx.cella.modelo.Material;
import unam.mx.cella.modelo.MaterialJpaController;

/**
 *
 * @author eduar
 */
@ManagedBean
@ApplicationScoped
public class KitController {

    private Kit kit;
    private final EntityManagerFactory emf;
    private List<String> materiales;
    private Material material;
    private MaterialJpaController mjpa;
    private List<String> seleccionados;
    /**
     * Creates a new instance of CrearKitController
     * @return 
     */
    
    public List<String> getMateriales() {
        return materiales;
    }
    public Kit getKit() {
        return kit;
    }
    
    
    
    public void setKit(Kit kit) {
        this.kit = kit;
    }
    
    public KitController() {
         emf = EntityProvider.provider();
        
        FacesContext.getCurrentInstance().getViewRoot().setLocale(
                new Locale("es-Mx"));
        this.kit = new Kit();
        this.mjpa = new MaterialJpaController(emf);
        this.materiales = mjpa.getNombresMaterial();
    }
    
     public boolean verificaKit(String nombreKit){
        
        KitJpaController ajpa = new KitJpaController(emf);
        return ajpa.verificaNombre(nombreKit) == null;
    }
    
     
     
    public String addKit() throws IOException {
       
        if(!(verificaKit(kit.getNombrekit()))){
            FacesContext.getCurrentInstance().addMessage(null
            , new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Intenta con otro nombre, el kit con el nombre" + this.kit.getNombrekit()+ " ya existe", ""));
        } 
             
        else {
            
            KitJpaController kjpa = new KitJpaController(emf);
            Kit nkit = new Kit();
           
            nkit.setNombrekit(kit.getNombrekit());
            nkit.setMateria(kit.getMateria());
           

            kjpa.create(nkit);
            
           /* FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Felicidades, el registro se ha realizado correctamente", ""));*/
              FacesContext.getCurrentInstance().getExternalContext().redirect("SeleccionarMateriales.xhtml");
             
        }
        return null;
    }    
}
