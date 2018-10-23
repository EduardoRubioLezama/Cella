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
    @UniqueConstraint(columnNames = {"nombrecategoria"})})
@XmlRootElement
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false, length = 2147483647)
    private String nombrecategoria;
    @Basic(optional = false)
    @Column(nullable = false, length = 2147483647)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategoria")
    private List<Subcategorias> subcategoriasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategoria")
    private List<PertenecerMaterialCategoria> pertenecerMaterialCategoriaList;

    public Categoria() {
    }

    public Categoria(Integer id) {
        this.id = id;
    }

    public Categoria(Integer id, String nombrecategoria, String descripcion) {
        this.id = id;
        this.nombrecategoria = nombrecategoria;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombrecategoria() {
        return nombrecategoria;
    }

    public void setNombrecategoria(String nombrecategoria) {
        this.nombrecategoria = nombrecategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Subcategorias> getSubcategoriasList() {
        return subcategoriasList;
    }

    public void setSubcategoriasList(List<Subcategorias> subcategoriasList) {
        this.subcategoriasList = subcategoriasList;
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
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "unam.mx.cella.modelo.Categoria[ id=" + id + " ]";
    }
    
}
