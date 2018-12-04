/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unam.mx.cella.controller;

import static com.sun.faces.facelets.util.Path.context;
import java.util.Locale;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.ByteArrayContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import unam.mx.cella.modelo.Alumno;
import unam.mx.cella.modelo.AlumnoJpaController;
import unam.mx.cella.modelo.EntityProvider;
import unam.mx.cella.modelo.Profesor;
import unam.mx.cella.modelo.ProfesorJpaController;

/**
 *
 * @author eduar
 */
@ManagedBean
@RequestScoped
public class EditarPerfil {

    @ManagedProperty("#{inicioSesion}")
    private InicioSesion inicioSesionRol;
    
    @ManagedProperty("#{ISID}")
    private InicioSesion isid;
    
    private final EntityManagerFactory emf;
    private Alumno alumno;
    private AlumnoJpaController ajpa;
    private Profesor profesor;
    private ProfesorJpaController pjpa;

    public InicioSesion getIsid() {
        return isid;
    }

    public void setIsid(InicioSesion isid) {
        this.isid = isid;
    }
    private UploadedFile fotografia;
    private int rol;

    //private ByteArrayContent content;
    public InicioSesion getInicioSesionRol() {
        return inicioSesionRol;
    }

    public void setInicioSesionRol(InicioSesion inicioSesionRol) {
        this.inicioSesionRol = inicioSesionRol;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
    

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public UploadedFile getFotografia() {
        return fotografia;
    }

    public void setFotografia(UploadedFile fotografia) {
        this.fotografia = fotografia;
    }

    /**
     * Creates a new instance of EditarPerfil
     */
    public EditarPerfil() {
        emf = EntityProvider.provider();
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("es-Mx"));
        ajpa = new AlumnoJpaController(emf);
        alumno = new Alumno();
        pjpa = new ProfesorJpaController(emf);
        profesor = new Profesor();
        
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        inicioSesionRol = application.evaluateExpressionGet(context, "#{rol}", InicioSesion.class);
        isid = application.evaluateExpressionGet(context, "#{id}", InicioSesion.class);
//        rol = (int) FacesContext.getCurrentInstance().getApplication().getVariableResolver().resolveVariable(facesContext, "rol");
    }

    public StreamedContent getMiFoto() {
        Alumno u;
        u = ajpa.findAlumno(7);
        if (u.getFoto() != null) {
            return new ByteArrayContent(u.getFoto());
        }
        return null;
    }

    public void fileUploadListener(FileUploadEvent e) {
        this.fotografia = e.getFile();
    }

    public String addFoto(int id) throws Exception {
        if (inicioSesionRol.getRol() == 1) {
            alumno = ajpa.findAlumno(id);
            if (alumno == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Alumno nulo" + id, ""));
            } else if (fotografia != null) {
                alumno.setFoto(fotografia.getContents());
                //ujpa.create(user);
                ajpa.edit(alumno);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Felicidades", ""));
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Foto nula", ""));
            }
        }
        if (inicioSesionRol.getRol() == 3) {
            profesor = pjpa.findProfesor(id);
            if (profesor == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Profesor nulo" + id, ""));
            } else if (fotografia != null) {
                profesor.setFoto(fotografia.getContents());
                //ujpa.create(user);
                pjpa.edit(profesor);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Felicidades", ""));
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Foto nula", ""));
            }
        }

        return null;
    }
}
