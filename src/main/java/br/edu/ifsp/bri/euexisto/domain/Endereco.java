/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 *
 * @author gahsabio
 */
@Entity
@Table(name = "ENDERECO")
@SequenceGenerator(name = "ENDERECO_SEQ", sequenceName = "ENDERECO_SEQ",allocationSize = 1 ) 
public class Endereco implements Serializable {
    
    private int    id;
    private String numero;
    private String complemento; 
    private Cep    cep;
    private Bairro bairro;
    private Rua    rua;

    public Endereco() {
        id          = 0;
        numero      = "";
        complemento = "";
        cep         = new Cep();
        bairro      = new Bairro();
        rua         = new Rua();
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ENDERECO_SEQ")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "NUMERO", length = 10, nullable = false)
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    } 

    @Column(name = "COMPLEMENTO", length = 200, nullable = true)
    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }     
    
    @ManyToOne //(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Cep.class)
    @JoinColumn(name = "IDCEP", nullable = false, referencedColumnName = "ID")
    //@Column(name = "IDESTADO", nullable = false )
    public Cep getCep() {
        return cep;
    }

    public void setCep(Cep cep) {
        this.cep = cep;
    }
    
    @ManyToOne //(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Bairro.class)
    @JoinColumn(name = "IDBAIRRO", nullable = true, referencedColumnName = "ID")
    //@Column(name = "IDESTADO", nullable = false )
    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }
    
    @ManyToOne //(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Rua.class)
    @JoinColumn(name = "IDRUA", nullable = true, referencedColumnName = "ID")
    //@Column(name = "IDESTADO", nullable = false )
    public Rua getRua() {
        return rua;
    }

    public void setRua(Rua rua) {
        this.rua = rua;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 29 * hash + this.id;
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
        final Endereco other = (Endereco) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Endereco{" + "id="           + id                      + ", numero=" + numero             + ", " +
                             " complemento=" + complemento             + ", cep="    + cep.getNumeroCep() + ", " +
                             " bairro="    + bairro.getNome()          + ", rua="    + rua.getNome()      + ", " +
                             " cidade="    + cep.getCidade().getNome() + ", estado=" + cep.getCidade().getEstado().getNome() + '}';
    }   
}// fim da classe Endereco
