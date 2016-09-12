/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.domain;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Transient;

/**
 *
 * @author gahsabio
 */
@Entity
@Table(name = "CIDADAO")
@SequenceGenerator(name = "CIDADAO_SEQ", sequenceName = "CIDADAO_SEQ",allocationSize = 1 ) 
public class Cidadao implements Serializable{
    
    private int      id;
    private String   nome;
    private String   cpf;
    private String   email;
    private String   telefoneFixo;
    private String   telefoneCelular;
    private Date     dataNasc;
    private String   senha;
    private Endereco endereco;
    private String   sexo;
    //private Cidadao  responsavel1;
    //private Cidadao  responsavel2;

    public Cidadao() {
        id              = 0;
        nome            = "";
        cpf             = "";
        email           = "";
        telefoneFixo    = "";
        telefoneCelular = "";
        dataNasc        = new Date();
        senha           = "";
        endereco        = new Endereco();
        sexo            = "";
        //responsavel1    = new Cidadao();
        //responsavel2    = new Cidadao();
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CIDADAO_SEQ")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "NOME", length = 250, nullable = false)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }   
    
    @Column(name = "CPF", length = 14, nullable = false)
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    @Column(name = "EMAIL", length = 100, nullable = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column(name = "TELEFONEFIXO", length = 20, nullable = true)
    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    public void setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }   
    
    @Column(name = "TELEFONECELULAR", length = 20, nullable = true)
    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }
     
    @Column(name = "DATANASC", nullable = false)
    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }  
    
    @Column(name = "SEXO", length = 1, nullable = false)
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    @Column(name = "SENHA", length = 100, nullable = false)
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
        
    @ManyToOne //(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Endereco.class)
    @JoinColumn(name = "IDENDERECO", nullable = false, referencedColumnName = "ID")
    //@Column(name = "IDESTADO", nullable = false )
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    @Transient
    public String getEnderecoCompleto() {
        String enderecoCompleto = "";
        if   (endereco.getRua().equals(null)) {
             enderecoCompleto = endereco.getCep().getRua().getNome()    + " "      +
                                endereco.getNumero()                    + ", "     +
                                endereco.getCep().getBairro().getNome() + ", CEP " +
                                endereco.getCep().getNumeroCep();
        }
        else {
             enderecoCompleto = endereco.getRua().getNome()             + " "      +
                                endereco.getNumero()                    + ", "     +
                                endereco.getBairro().getNome()          + ", CEP " +
                                endereco.getCep().getNumeroCep();

        }
        
        return enderecoCompleto;
    }
        
    /*
    @ManyToOne //(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Cidadao.class)
    @JoinColumn(name = "IDRESPONSAVEL1", nullable = true, referencedColumnName = "ID")
    //@Column(name = "IDESTADO", nullable = false )
    public Cidadao getResponsavel1() {
        return responsavel1;
    }

    public void setResponsavel1(Cidadao responsavel1) {
        this.responsavel1 = responsavel1;
    }

    @ManyToOne //(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Cidadao.class)
    @JoinColumn(name = "IDRESPONSAVEL3", nullable = true, referencedColumnName = "ID")
    //@Column(name = "IDESTADO", nullable = false )
    public Cidadao getResponsavel2() {
        return responsavel2;
    }

    public void setResponsavel2(Cidadao responsavel2) {
        this.responsavel2 = responsavel2;
    }
    */
    @Override
    public int hashCode() {
        int hash = 19;
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
        final Cidadao other = (Cidadao) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cidadao{" + "id="            + id           + ", nome="            + nome                + ", " +
                            " cpf="          + cpf          + ", email="           + email               + ", " +
                            " telefoneFixo=" + telefoneFixo + ", telefoneCelular=" + telefoneCelular     + ", " +
                            " dataNasc="     + dataNasc     + ", endereco="        + endereco.toString() + ", " +
                            " sexo="         + sexo         + "}";
                            //" responsavel1=" + responsavel1.toString() + 
                            //" responsavel2=" + responsavel2.toString() + "}";
    }   
}// fim da classe Cidadao
