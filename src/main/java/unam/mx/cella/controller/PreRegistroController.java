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
import unam.mx.cella.modelo.Profesor;
import unam.mx.cella.modelo.EntityProvider;
//import unam.mx.cella.modelo.AlumnoJpaController;

/**
 *
 * @author eduar
 */
@ManagedBean
@RequestScoped
public class PreRegistroController {

    private final EntityManagerFactory emf;
    private Profesor profesor;
    private String confirmacion;
    
    
    public Profesor getProfesor() {
        return profesor;
    }
    
    

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
    /**
     * Creates a new instance of PreRegistroController
     */
    public PreRegistroController() {
         emf = EntityProvider.provider();
        System.out.println("creado");
        FacesContext.getCurrentInstance().getViewRoot().setLocale(
                new Locale("es-Mx"));
        this.profesor = new Profesor();
        confirmacion = "";
    
    }
    
}
