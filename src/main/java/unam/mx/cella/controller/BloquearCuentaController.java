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
import unam.mx.cella.modelo.Unidadmaterial;
import unam.mx.cella.modelo.exceptions.NonexistentEntityException;
/**
 *
 * @author Asus
 */
@ManagedBean
@RequestScoped
public class BloquearCuentaController {

	private final EntityManagerFactory emf;
    private Integer noCuenta;
    private String rfc;
    private String temp;
    private String estado;
    private String usuario;
     
    /**
     * Creates a new instance of CambiarEdoMaterial
     */
    public BloquearCuentaController() {
        emf = EntityProvider.provider();
        FacesContext.getCurrentInstance().getViewRoot().setLocale(
                new Locale("es-Mx"));
        this.noCuenta = 0000000000;
        this.rfc = "";
        this. estado = "";
        this.usuario = "";
    }
    
     public String getEstado(){
        return estado;
    }
    
    public void setEstado(String estado){
        this.estado = estado;
    }
    
    public Integer getnoCuenta(){
        return noCuenta;
    }
    
    public void setnoCuenta(Integer noCuenta){
        this.noCuenta = noCuenta;
    }
    
     public String getRfc(){
        return rfc;
    }
    
    public void setRfc(String rfc){
        this.rfc = rfc;
    }

    public String getUsuario(){
    	return usuario;
    }

    public void setUsuario(String usuario){
    	this.usuario = usuario;
    }
    
    public String bloquearActivar() throws NonexistentEntityException, Exception{
    	if (usuario.equals("Alumno")){
	        AlumnoJpaController ajpa = new AlumnoJpaController(emf);
	        Alumno alum = new Alumno();
	        int idn = noCuenta;
	        alum = ajpa.findAlumno(idn);
	        alum.setEdocuenta(estado);
	        ajpa.edit(alum);

	        FacesContext.getCurrentInstance().addMessage(null,
	                                            new FacesMessage(FacesMessage.SEVERITY_INFO,
	                                               "Se ha cambiado el estado del alumno con id: " + alum.getId() + " a estado: "+ alum.getEdocuenta(), ""));
    	}else{
	    	ProfesorJpaController pjpa = new ProfesorJpaController(emf);
	    	Profesor profe = new Profesor();
	    	int idn = noCuenta;
	    	profe = pjpa.findProfesor(idn);
	    	profe.setEdocuenta(estado);
	    	pjpa.edit(profe);
	    	
	    	FacesContext.getCurrentInstance().addMessage(null,
	                                            new FacesMessage(FacesMessage.SEVERITY_INFO,
	                                               "Se ha cambiado el estado del profesor con id: " + profe.getId() + " a estado: "+ profe.getEdocuenta() , ""));
	 
    	}
        return null;
    }
    
    
}
