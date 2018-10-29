package unam.mx.cella.controller;
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
 * @author Pleyades
 */
@ManagedBean
@RequestScoped
public class CambEdoMatBusController{

	private final EntityManagerFactory emf;
    private Unidadmaterial umaterial;
    private String consulta;

    public String getConsulta(){
        return consulta;
    }
    
    public void setConsulta(String confirmacion){
        this.consulta = consulta;
    }

     public Unidadmaterial getUnidadmaterial() {
        return umaterial;
    }
    
    public void setUnidadmaterial(Unidadmaterial umaterial) {
        this.umaterial = umaterial;
    }

    /**
     * Creates a new instance of RegistroController
     */
    public CambEdoMatBusController() {
        emf = EntityProvider.provider();
        System.out.println("creado");
        FacesContext.getCurrentInstance().getViewRoot().setLocale(
                new Locale("es-Mx"));
        this.umaterial = new Unidadmaterial();
        consulta = "";
    }

    public String BuscaMaterial(Unidadmaterial umaterial) {
        if (!umaterial.getContrasena().equals(confirmacion)) {///comparara si el material esta en la BDD
            FacesContext.getCurrentInstance().addMessage(null
            , new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "El material que ingresaste no se encuentra registrado", ""));
        } else {
           // LoginJpaController ljpa = new LoginJpaController(emf);
            AlumnoJpaController ajpa = new AlumnoJpaController(emf);
            //UnidadmaterialJpaController
           
            consulta = new Alumno();
           

            FacesContext.getCurrentInstance().addMessage(null,
                                                         new FacesMessage(FacesMessage.SEVERITY_INFO,
                                                                          "Felicidades, el registro se ha realizado correctamente", ""));
        } 
        return null;
    }



}