/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unam.mx.cella.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eduar
 */
@MappedSuperclass
@Table(catalog = "cella", schema = "cella", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nombreusuario"})
    , @UniqueConstraint(columnNames = {"correo"})})
@XmlRootElement
public class Alumno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false, length = 2147483647)
    private String nombreusuario;
    @Basic(optional = false)
    @Column(nullable = false, length = 2147483647)
    private String correo;
    @Basic(optional = false)
    @Column(nullable = false, length = 2147483647)
    private String contrasena;
    @Basic(optional = false)
    @Column(nullable = false, length = 2147483647)
    private String nombre;
    @Basic(optional = false)
    @Column(nullable = false, length = 2147483647)
    private String apellidop;
    @Basic(optional = false)
    @Column(nullable = false, length = 2147483647)
    private String apellidom;
    @Lob
    private byte[] foto;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean edocuenta;

    public Alumno() {
    }

    public Alumno(Integer id) {
        this.id = id;
    }

    public Alumno(Integer id, String nombreusuario, String correo, String contrasena, String nombre, String apellidop, String apellidom, boolean edocuenta) {
        this.id = id;
        this.nombreusuario = nombreusuario;
        this.correo = correo;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.apellidop = apellidop;
        this.apellidom = apellidom;
        this.edocuenta = edocuenta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidop() {
        return apellidop;
    }

    public void setApellidop(String apellidop) {
        this.apellidop = apellidop;
    }

    public String getApellidom() {
        return apellidom;
    }

    public void setApellidom(String apellidom) {
        this.apellidom = apellidom;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public boolean getEdocuenta() {
        return edocuenta;
    }

    public void setEdocuenta(boolean edocuenta) {
        this.edocuenta = edocuenta;
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
        if (!(object instanceof Alumno)) {
            return false;
        }
        Alumno other = (Alumno) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "unam.mx.cella.modelo.Alumno[ id=" + id + " ]";
    }
    
    public boolean confirmarContra(String contra) {
        return (contrasena == contra);
    }
    
}
