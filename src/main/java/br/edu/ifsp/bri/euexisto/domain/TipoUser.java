/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author ifsp
 */
@Entity
@Table(name = "TAB_TIPOUSER")
public class TipoUser {
    
    private int codigoTpUsuario;
    private TipoUsuario tipoUsuario;
    private List<Usuario> listaUsuario;

    public TipoUser() {
    }

    @Id
    @Column(name = "TAB_IDTPUSER")
    @SequenceGenerator(name = "SEQ_TIPOUSER", sequenceName = "SEQ_TAB_TIPOUSER",allocationSize = 1 ) 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TIPOUSER")
    public int getCodigoTpUsuario() {
        return codigoTpUsuario;
    }

    public void setCodigoTpUsuario(int codigoTpUsuario) {
        this.codigoTpUsuario = codigoTpUsuario;
    }

    @Column(name = "TAB_TIPOUSUARIO", length = 15, nullable = false)
    @Enumerated(EnumType.STRING)
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @OneToMany(mappedBy = "tipoUser")
    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }
    

    @Override
    public int hashCode() {
        int hash = 13;
        hash = 29 * hash + this.codigoTpUsuario;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TipoUser other = (TipoUser) obj;
        if (this.codigoTpUsuario != other.codigoTpUsuario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TipoUser{" + "codigoUsuario=" + codigoTpUsuario + ", tipoUsuario=" + tipoUsuario + '}';
    }
    
}
