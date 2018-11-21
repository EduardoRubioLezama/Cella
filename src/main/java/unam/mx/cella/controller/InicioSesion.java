/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unam.mx.cella.controller;

import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import static javax.faces.context.FacesContext.getCurrentInstance;
import javax.persistence.EntityManagerFactory;
import unam.mx.cella.modelo.Administrador;
import unam.mx.cella.modelo.Alumno;
import unam.mx.cella.modelo.EntityProvider;
import unam.mx.cella.modelo.Profesor;
import unam.mx.cella.modelo.ProfesorJpaController;
import unam.mx.cella.modelo.AdministradorJpaController;
import unam.mx.cella.modelo.AlumnoJpaController;

/**
 *
 * @author chars
 */
@ManagedBean
@SessionScoped
public class InicioSesion {

    private final EntityManagerFactory emf;
    private Alumno alum;
    private Administrador admin;
    private Profesor prof;

    private final ProfesorJpaController pjc;
    private final AdministradorJpaController adjc;
    private final AlumnoJpaController ajc;
    private String email ;
    private String pass;
    
    public InicioSesion() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("es-Mx"));
        emf = EntityProvider.provider();
        alum = new Alumno();
        admin = new Administrador();
        prof = new Profesor();
        pjc = new ProfesorJpaController(emf);
        adjc = new AdministradorJpaController(emf);
        ajc = new AlumnoJpaController(emf);
        email = "";
        pass = "";
    }

    /**
     * @return the alum
     */
    public Alumno getAlum() {
        return alum;
    }

    /**
     * @param alum the alum to set
     */
    public void setAlum(Alumno alum) {
        this.alum = alum;
    }

    /**
     * @return the admin
     */
    public Administrador getAdmin() {
        return admin;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin(Administrador admin) {
        this.admin = admin;
    }

    /**
     * @return the prof
     */
    public Profesor getProf() {
        return prof;
    }

    /**
     * @param prof the prof to set
     */
    public void setProf(Profesor prof) {
        this.prof = prof;
    }

    public String canLogin() {                              
        boolean loggedAlumno = ajc.findAlumnoCorreoYContra(email, pass);      
        boolean loggedAdmin = adjc.findAdministradorCorreoYContra(email, pass);
        boolean loggedProfesor = pjc.findProfesorCorreoYContra(email, pass);
                
        if (loggedAlumno) {                                             
            Alumno alumno = ajc.findAlumno(email);
            FacesContext context = getCurrentInstance();
            context.getExternalContext().getSessionMap().put("usuario", alumno);
            
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Felicidades, el alumno si existe en ele sistema", ""));
            return "secured/Principal?faces-redirect=true";
        } else if (loggedAdmin){
            Administrador administrador = adjc.findAdministrador(email);
            FacesContext context = getCurrentInstance();
            context.getExternalContext().getSessionMap().put("usuario", administrador);
            
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Felicidades, el alumno si existe en ele sistema", ""));
            return "secured/Principal?faces-redirect=true";
        } else if (loggedProfesor){
            Profesor profesor = pjc.findCorreo(email);
            FacesContext context = getCurrentInstance();
            context.getExternalContext().getSessionMap().put("usuario", profesor);
            
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Felicidades, el alumno si existe en ele sistema", ""));
            return "secured/Principal?faces-redirect=true";
        }
        System.out.println("no entre al metodo");
        return "Registrar?faces-redirect=true";
    }

    public String logout() {
        FacesContext context = getCurrentInstance();
        context.getExternalContext().invalidateSession();
        return "/../Inicio?faces-redirect=true";
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }
}
