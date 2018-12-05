/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unam.mx.cella.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManagerFactory;
import unam.mx.cella.modelo.EntityProvider;
import unam.mx.cella.modelo.Unidadmaterial;
import unam.mx.cella.modelo.UnidadmaterialJpaController;
import unam.mx.cella.modelo.exceptions.NonexistentEntityException;

/**
 *
 * @author chars
 */
@ManagedBean
@RequestScoped
public class PrestamoController {

    private final EntityManagerFactory emf;
    private final Unidadmaterial um;
    private final UnidadmaterialJpaController umj;
    private String estado;
    private String nombre;

    /**
     * Creates a new instance of prestamo
     */
    public PrestamoController() {
        emf = EntityProvider.provider();
        um = new Unidadmaterial();
        umj = new UnidadmaterialJpaController(emf);
        estado = "";
        nombre = "";
    }

    public void SolicitarPrestamo() throws NonexistentEntityException, Exception {
        boolean dis = umj.findDisponibilidadMaterial(nombre);
        if (dis) {
            um.setEstado("no disponible");
            umj.edit(um);
        } else {
            
        }
    }

}
