package unam.mx.cella.controller;

import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import unam.mx.cella.modelo.EntityProvider;
import unam.mx.cella.modelo.Alumno;
import unam.mx.cella.modelo.AlumnoJpaController;
import unam.mx.cella.modelo.Profesor;
import unam.mx.cella.modelo.ProfesorJpaController;
import unam.mx.cella.modelo.exceptions.NonexistentEntityException;
/**
 *
 * @author Asus
 */
@ManagedBean
@RequestScoped
public class BloquearCuentaController {

    private final EntityManagerFactory emf;
    private String rol;
    private String nombreUsuario;
    private String estado;
     
    /**
     * Creates a new instance of CambiarEdoMaterial
     */
    public BloquearCuentaController() {
        emf = EntityProvider.provider();
        FacesContext.getCurrentInstance().getViewRoot().setLocale(
                new Locale("es-Mx"));
        rol = "";
        nombreUsuario = "";
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    
    
     public String getEstado(){
        return estado;
    }
    
    public void setEstado(String estado){
        this.estado = estado;
    }
            
    public String bloquearActivar() throws NonexistentEntityException, Exception{
    	if (rol.equals("Alumno")){
	        AlumnoJpaController ajpa = new AlumnoJpaController(emf);
	        Alumno alum = new Alumno();
	        alum = ajpa.findAlumnoNU(nombreUsuario);
                if(alum == null){
                 FacesContext.getCurrentInstance().addMessage(null,
	                                            new FacesMessage(FacesMessage.SEVERITY_INFO,
	                                               "Este nombre de usuario no existe en ningun alumno. Verificar los datos" , ""));
                }else{
	        alum.setEdocuenta(estado);
	        ajpa.edit(alum);

	        FacesContext.getCurrentInstance().addMessage(null,
	                                            new FacesMessage(FacesMessage.SEVERITY_INFO,
	                                               "Se ha cambiado el estado del usuario " + nombreUsuario + " a estado: "+ alum.getEdocuenta() , ""));
                }
    	}else{
	    	ProfesorJpaController pjpa = new ProfesorJpaController(emf);
	    	Profesor profe = new Profesor();
	    	
	    	profe = pjpa.findProfesor(estado);
                if(profe == null){
                 FacesContext.getCurrentInstance().addMessage(null,
	                                            new FacesMessage(FacesMessage.SEVERITY_INFO,
	                                               "Este nombre de usuario no existe en ningun profesor. Verificar los datos." , ""));
                }else{
	    	profe.setEdocuenta(estado);
	    	pjpa.edit(profe);
	    	
	    	FacesContext.getCurrentInstance().addMessage(null,
	                                            new FacesMessage(FacesMessage.SEVERITY_INFO,
	                                               "Se ha cambiado el estado del profesor " + nombreUsuario + " a estado: "+ profe.getEdocuenta() , ""));
                }
    	}
        return null;
    }
    
    
}