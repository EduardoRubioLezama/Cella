/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unam.mx.cella.controller;

import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
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

/**
 *
 * @author eduar
 */
@ManagedBean
@RequestScoped
public class EditarPerfil {
    
    private final EntityManagerFactory emf;
    private Alumno alumno;
    private AlumnoJpaController ajpa;
    private UploadedFile fotografia;
    //private ByteArrayContent content;

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
       
            alumno = ajpa.findAlumno(id);
            if(alumno == null){ FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Alumno nulo"+ id , ""));}
            else if (fotografia != null) {
                alumno.setFoto(fotografia.getContents());
                  //ujpa.create(user);
                ajpa.edit(alumno);
                FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Felicidades" , ""));
            }
            else{
                 FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Foto nula" , ""));
            }
          
        
        return null;
    }
}
