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
@Table(name = "CEP")
@SequenceGenerator(name = "CEP_SEQ", sequenceName = "CEP_SEQ",allocationSize = 1 ) 
public class Cep implements Serializable {
    
    private int    id;
    private String numeroCep;
    private int    numeroIni;
    private int    numeroFim;
    private Cidade cidade;
    private Bairro bairro;
    private Rua    rua;

    public Cep() {
        id        = 0;
        numeroCep = "";
        numeroIni = 0;
        numeroFim = 0;
        cidade    = new Cidade();
        bairro    = new Bairro();
        rua       = new Rua();
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CEP_SEQ")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "NUMEROCEP", length = 10, nullable = false)
    public String getNumeroCep() {
        return numeroCep;
    }

    public void setNumeroCep(String numeroCep) {
        this.numeroCep = numeroCep;
    } 
    
    @Column(name = "NUMEROINI", nullable = false)
    public int getNumeroIni() {
        return numeroIni;
    }

    public void setNumeroIni(int numeroIni) {
        this.numeroIni = numeroIni;
    } 
    
    @Column(name = "NUMEROFIM", nullable = false)
    public int getNumeroFim() {
        return numeroFim;
    }

    public void setNumeroFim(int numeroFim) {
        this.numeroFim = numeroFim;
    } 
    
    @ManyToOne //(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Cidade.class)
    @JoinColumn(name = "IDCIDADE", nullable = false, referencedColumnName = "ID")
    //@Column(name = "IDESTADO", nullable = false )
    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
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
        int hash = 15;
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
        final Cep other = (Cep) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cep{" + "id="         + id               + ", cep="       + numeroCep     + ", " +
                        " numeroIni=" + numeroIni        + ", numeroFim=" + numeroFim     + ", " +
                        " bairro="    + bairro.getNome() + ", rua="       + rua.getNome() + ", " +
                        " cidade="    + cidade.getNome() + ", estado="    + cidade.getEstado().getNome() + '}';
    }   
    
}// fim da classe Cep
