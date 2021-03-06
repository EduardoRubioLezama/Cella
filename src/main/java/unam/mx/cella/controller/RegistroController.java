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
import javax.persistence.EntityManagerFactory;
import unam.mx.cella.modelo.Alumno;
import unam.mx.cella.modelo.EntityProvider;
import unam.mx.cella.modelo.AlumnoJpaController;



/**
 *
 * @author eduar
 */
@ManagedBean
@RequestScoped
public class RegistroController {

    private final EntityManagerFactory emf;
    private Alumno alumno;
    private String confirmacion;
        
    public String getConfirmacion(){
        return confirmacion;
    }
    
    public void setConfirmacion(String confirmacion){
        this.confirmacion = confirmacion;
    }
    
    public Alumno getAlumno() {
        return alumno;
    }
    
    

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    /**
     * Creates a new instance of RegistroController
     */
    public RegistroController() {
        emf = EntityProvider.provider();
        
        FacesContext.getCurrentInstance().getViewRoot().setLocale(
                new Locale("es-Mx"));
        this.alumno = new Alumno();
        confirmacion = "";
        

    }
    
 
    public boolean verificaUsuario(String userName){
        
        AlumnoJpaController ajpa = new AlumnoJpaController(emf);
        return ajpa.findAlumnoNU(userName) == null;
    }
    
    public boolean verificaCorreo(String correo){
        
        AlumnoJpaController ajpa = new AlumnoJpaController(emf);
        return ajpa.findAlumno(correo) == null;
    }

    
    
    public String addUser() throws IOException {
        if (!alumno.getContrasena().equals(confirmacion)) {
            FacesContext.getCurrentInstance().addMessage(null
            , new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Fallo de registro: Las contraseņas deben coincidir", ""));
        } 
        else if(!(verificaUsuario(alumno.getNombreusuario()))){
            FacesContext.getCurrentInstance().addMessage(null
            , new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Fallo de registro: Nombre de usuario existente, elige otro", ""));
        } 
        
        else if(!(verificaCorreo(alumno.getCorreo()))){
            FacesContext.getCurrentInstance().addMessage(null
            , new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Fallo de registro: correo ya registrado en el sistema", ""));
        } 
        
        else {
            
            AlumnoJpaController pjpa = new AlumnoJpaController(emf);
            
            Alumno alum = new Alumno();
            
           //Se envia correo con link de confirmacion 
           
           
            alum.setNombre(alumno.getNombre());
            alum.setApellidop(alumno.getApellidop());
            alum.setApellidom(alumno.getApellidom());
            alum.setCorreo(alumno.getCorreo());
            alum.setNombreusuario(alumno.getNombreusuario());
            alum.setContrasena(alumno.getContrasena());
            alum.setEdocuenta("inactiva");

            //pjpa.create(alum);
            
            CorreoController cc = new CorreoController();
            cc.setTo(alumno.getCorreo());
            cc.setFrom(alumno.getCorreo());
            cc.setUsername(alumno.getCorreo());
            
            String resultado = cc.submitEmail();
            
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                                      "Resultado del email:" + resultado , ""));
        }
        //FacesContext.getCurrentInstance().getExternalContext().redirect(".xhtml");
        return null;
    }

    
}
