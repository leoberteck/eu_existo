/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author ifsp
 */

@Entity
@Table(name = "TAB_USUARIO")
public class Usuario {
    
    private int codigoUsuario;
    private String nomeUsuario;
    private String emailUsuario;
    private String senhaUsuario;
    private Status statusUsuario;
    private TipoUser tipoUser; 

    public Usuario() {
    }
 
    @Id
    @Column(name = "TAB_IDUSUARIO")
    @SequenceGenerator(name = "SEQ_USUARIO", sequenceName = "SEQ_TAB_USUARIO",allocationSize = 1 ) 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIO")
    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    @Column(name = "TAB_NOMEUSUARIO",length = 100, nullable = false)
    public String getNomeUsuario() {
        return nomeUsuario;
    }

    
    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    @Column(name = "TAB_EMAILUSUARIO", length = 100, nullable = false, unique = true)
    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }
    
    

    @Column(name = "TAB_SENHAUSUARIO", length = 50, nullable = false)
    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    @Column(name = "TAB_STATUSUSUARIO", nullable = false, length = 10 )
    @Enumerated(EnumType.STRING)
    public Status getStatusUsuario() {
        return statusUsuario;
    }

    public void setStatusUsuario(Status statusUsuario) {
        this.statusUsuario = statusUsuario;
    }

    @ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = TipoUser.class)
    @JoinColumn(name = "TAB_IDTIPOUSER", nullable = false, referencedColumnName = "TAB_IDTPUSER")
    //@Column(name = "TAB_IDTIPOUSER", nullable = false )
    public TipoUser getTipoUser() {
        return tipoUser;
    }

    public void setTipoUser(TipoUser tipoUser) {
        this.tipoUser = tipoUser;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.codigoUsuario;
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
        final Usuario other = (Usuario) obj;
        if (this.codigoUsuario != other.codigoUsuario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "codigoUsuario=" + codigoUsuario + ", nomeUsuario=" + nomeUsuario + ", senhaUsuario=" + senhaUsuario + ", statusUsuario=" + statusUsuario + ", tipoUsuario=" + tipoUser + '}';
    }
    
    
    
}
