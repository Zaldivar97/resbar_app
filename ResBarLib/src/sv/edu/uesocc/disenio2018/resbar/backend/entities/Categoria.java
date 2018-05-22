/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.disenio2018.resbar.backend.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author zaldivar
 */
@Entity
@Table(name = "Categoria", catalog = "resbar", schema = "")
@NamedQueries({
    @NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c")
    , @NamedQuery(name = "Categoria.findAllWithoutDetails", query = "SELECT c.idCategoria,c.nombre FROM Categoria c")
//    , @NamedQuery(name = "Categoria.maxId", query = "SELECT MAX(c.idCategoria) FROM Categoria c")
    , @NamedQuery(name = "Categoria.findByIdCategoria", query = "SELECT c FROM Categoria c WHERE c.idCategoria = :idCategoria")
    , @NamedQuery(name = "Categoria.findByNombre", query = "SELECT c FROM Categoria c WHERE c.nombre = :nombre")})
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idCategoria", nullable = false)
    public Integer idCategoria;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 200)
    public String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategoria", fetch = FetchType.EAGER)
    public List<Producto> productos;

//    public Categoria() {
//    }
//
//    public Categoria(Integer idCategoria) {
//        this.idCategoria = idCategoria;
//    }
//
//    public Categoria(Integer idCategoria, String nombre) {
//        this.idCategoria = idCategoria;
//        this.nombre = nombre;
//    }
//
//    public Integer IdCategoria() {
//        return idCategoria;
//    }
//
//    public void IdCategoria(Integer idCategoria) {
//        this.idCategoria = idCategoria;
//    }
//
//    public String nombre() {
//        return nombre;
//    }
//
//    public void nombre(String nombre) {
//        this.nombre = nombre;
//    }
//
//    public List<Producto> productos() {
//        return productos;
//    }
//
//    public void productos(List<Producto> productos) {
//        this.productos = productos;
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (idCategoria != null ? idCategoria.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Categoria)) {
//            return false;
//        }
//        Categoria other = (Categoria) object;
//        if ((this.idCategoria == null && other.idCategoria != null) || (this.idCategoria != null && !this.idCategoria.equals(other.idCategoria))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "sv.edu.uesocc.disenio2018.resbar.backend.entities.Categoria[ idCategoria=" + idCategoria + " ]";
//    }
//    
}
