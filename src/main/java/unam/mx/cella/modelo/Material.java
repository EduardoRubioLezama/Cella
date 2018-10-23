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
import javax.persistence.Lob;
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
public class Material implements Serializable {

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
    private String descripcion;
    @Lob
    private byte[] foto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMaterial")
    private List<ContenerKitMaterial> contenerKitMaterialList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMaterial")
    private List<Unidadmaterial> unidadmaterialList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMaterial")
    private List<PertenecerMaterialCategoria> pertenecerMaterialCategoriaList;

    public Material() {
    }

    public Material(Integer id) {
        this.id = id;
    }

    public Material(Integer id, String nombrematerial, String descripcion) {
        this.id = id;
        this.nombrematerial = nombrematerial;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @XmlTransient
    public List<ContenerKitMaterial> getContenerKitMaterialList() {
        return contenerKitMaterialList;
    }

    public void setContenerKitMaterialList(List<ContenerKitMaterial> contenerKitMaterialList) {
        this.contenerKitMaterialList = contenerKitMaterialList;
    }

    @XmlTransient
    public List<Unidadmaterial> getUnidadmaterialList() {
        return unidadmaterialList;
    }

    public void setUnidadmaterialList(List<Unidadmaterial> unidadmaterialList) {
        this.unidadmaterialList = unidadmaterialList;
    }

    @XmlTransient
    public List<PertenecerMaterialCategoria> getPertenecerMaterialCategoriaList() {
        return pertenecerMaterialCategoriaList;
    }

    public void setPertenecerMaterialCategoriaList(List<PertenecerMaterialCategoria> pertenecerMaterialCategoriaList) {
        this.pertenecerMaterialCategoriaList = pertenecerMaterialCategoriaList;
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
        if (!(object instanceof Material)) {
            return false;
        }
        Material other = (Material) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "unam.mx.cella.modelo.Material[ id=" + id + " ]";
    }
    
}
