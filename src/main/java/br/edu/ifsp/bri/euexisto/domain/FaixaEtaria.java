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
@Table(name = "FAIXAETARIA")
@SequenceGenerator(name = "FAIXAETARIA_SEQ", sequenceName = "FAIXAETARIA_SEQ",allocationSize = 1 ) 
public class FaixaEtaria implements Serializable {
    
    private int    id;
    private String descricao;
    private int    idadeIni;
    private int    idadeFim;
    private Cidade cidade;

    public FaixaEtaria() {
        id        = 0;
        descricao = "";
        idadeIni  = 0;
        idadeFim  = 0;
        cidade    = new Cidade();
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FAIXAETARIA_SEQ")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "DESCRICAO", length = 100, nullable = false)
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    } 
    
    @Column(name = "IDADEINI", nullable = false)
    public int getIdadeIni() {
        return idadeIni;
    }

    public void setIdadeIni(int idadeIni) {
        this.idadeIni = idadeIni;
    } 
    
    @Column(name = "IDADEFIM", nullable = false)
    public int getIdadeFim() {
        return idadeFim;
    }

    public void setIdadeFim(int idadeFim) {
        this.idadeFim = idadeFim;
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

    @Override
    public int hashCode() {
        int hash = 3;
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
        final FaixaEtaria other = (FaixaEtaria) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FaixaEtaria{" + "id="    + id               + ", descricao=" + descricao + ", " +
                            " idadeIni=" + idadeIni         + ", idadeFim="  + idadeFim  + ", " +
                            " cidade="   + cidade.getNome() + ", estado="    + cidade.getEstado().getNome() + '}';
    }   
}// fim da classe Faixaetaria
