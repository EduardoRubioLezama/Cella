package unam.mx.cella.controller;

import unam.mx.cella.modelo.EntityProvider;
import unam.mx.cella.modelo.Alumno;
import unam.mx.cella.modelo.AlumnoJpaController;
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

@ManagedBean
@RequestScoped
public class PerfilController {

    private final EntityManagerFactory emf;
    private Alumno alumno;
    private UploadedFile fotografia;
    private AlumnoJpaController ajpa;
    ByteArrayContent content;

    public ByteArrayContent getContent() {
        return content;
    }

    public void setContent(ByteArrayContent content) {
        this.content = content;
    }
    
    public PerfilController() {
        emf = EntityProvider.provider();
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("es-Mx"));
        ajpa = new AlumnoJpaController(emf);
        alumno = ajpa.findAlumno(7);
        content = new ByteArrayContent(alumno.getFoto());
    }
    
    

    public UploadedFile getFotografia() {
        return fotografia;
    }

    public void setFotografia(UploadedFile fotografia) {
        this.fotografia = fotografia;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }


    public void fileUploadListener(FileUploadEvent e) {
        this.fotografia = e.getFile();
    }
    
    public StreamedContent getMiFoto() {
       // Alumno a = ajpa.findUsuarioByLoginId;
        if (alumno.getFoto() != null) {
            return new ByteArrayContent(alumno.getFoto());
        }
        return null;
    }
    
    public String addFoto() throws Exception {
       
            ajpa = new AlumnoJpaController(emf);
            alumno = ajpa.findAlumno(alumno.getId());
            
            if (fotografia != null) {
                alumno.setFoto(fotografia.getContents());
            }
            //ujpa.create(user);
            ajpa.edit(alumno);
            FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Felicidades" + alumno.getFoto(), ""));
        
        return null;
    }

}
