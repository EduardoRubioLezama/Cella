/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unam.mx.cella.controller;
import java.io.IOException;
import java.util.Locale;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

/**
 *
 * @author eduar
 */

@ManagedBean
@RequestScoped
public class PrincipalController {
    
   
    /**
     * Creates a new instance of PrincipalController
     */
    public PrincipalController() {
        
    }
   
    
    public void redireccionaRegistro() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("Registrar.xhtml");
    }
     
    public void redireccionaInicioSesion() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("inicioSesion.xhtml");
    }
    
    
}
