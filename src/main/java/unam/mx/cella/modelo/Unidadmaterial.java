/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unam.mx.cella.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author eduar
 */
@MappedSuperclass
@Table(catalog = "cella", schema = "cella", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nombrematerial"})})
@XmlRootElement
public class Unidadmaterial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false, length = 2147483647)
    private String nombrematerial;
    @Basic(optional = false)
    @Column(nullable = false, length = 2147483647)
    private String estado;
    @Lob
    private byte[] foto;
    @JoinColumn(name = "id_material", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Material idMaterial;
    @OneToMany(mappedBy = "idUnidadMaterial")
    private List<Solicitarprestamoprofesor> solicitarprestamoprofesorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUnidadMaterial")
    private List<Solicitarprestamoalumno> solicitarprestamoalumnoList;

    public Unidadmaterial() {
    }

    public Unidadmaterial(Integer id) {
        this.id = id;
    }

    public Unidadmaterial(Integer id, String nombrematerial, String estado) {
        this.id = id;
        this.nombrematerial = nombrematerial;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombrematerial() {
        return nombrematerial;
    }

    public void setNombrematerial(String nombrematerial) {
        this.nombrematerial = nombrematerial;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Material getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Material idMaterial) {
        this.idMaterial = idMaterial;
    }

    @XmlTransient
    public List<Solicitarprestamoprofesor> getSolicitarprestamoprofesorList() {
        return solicitarprestamoprofesorList;
    }

    public void setSolicitarprestamoprofesorList(List<Solicitarprestamoprofesor> solicitarprestamoprofesorList) {
        this.solicitarprestamoprofesorList = solicitarprestamoprofesorList;
    }

    @XmlTransient
    public List<Solicitarprestamoalumno> getSolicitarprestamoalumnoList() {
        return solicitarprestamoalumnoList;
    }

    public void setSolicitarprestamoalumnoList(List<Solicitarprestamoalumno> solicitarprestamoalumnoList) {
        this.solicitarprestamoalumnoList = solicitarprestamoalumnoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Unidadmaterial)) {
            return false;
        }
        Unidadmaterial other = (Unidadmaterial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "unam.mx.cella.modelo.Unidadmaterial[ id=" + id + " ]";
    }
    
}
