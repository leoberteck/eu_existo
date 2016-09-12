/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.composite;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author gahsabio
 */
public class CidadaoComposite {
        
    private String numeroCep;
    private int    numeroIni;
    private int    numeroFim;
    private String numero;
    private String complemento;
    private String nomeEstado;
    private String uf;
    private String nomeCidade;
    private String nomeBairro;
    private String nomeRua;
    private String nomeCidadao;
    private String email;
    private String cpf;
    private String telefoneFixo;
    private String telefoneCelular;
    private Date   dataNasc;
    private String dataNascString;
    private String senha;
    private String sexo;
    
    
    public CidadaoComposite () {
        numeroCep       = "";
        numeroIni       = 0;
        numeroFim       = 0;
        numero          = "";
        complemento     = ""; 
        nomeEstado      = "";
        uf              = "";
        nomeCidade      = "";
        nomeBairro      = "";
        nomeRua         = "";
        nomeCidadao     = "";
        email           = "";
        cpf             = "";
        telefoneFixo    = "";
        telefoneCelular = "";
        dataNascString  = "";
        //dataNasc        = new Date();
        senha           = "";
        sexo            = "";
    }
    
    public CidadaoComposite (String numeroCep,   int    numeroIni,   int numeroFim,
                             String numero,      String complemento, String nomeEstado,
                             String uf,          String nomeCidade,  String nomeBairro,   String nomeRua,
                             String nomeCidadao, String cpf,         String telefoneFixo, String telefoneCelular,
                             Date   dataNasc,    String senha,       String email,        String sexo) {
        this.numeroCep       = numeroCep;
        this.numeroIni       = numeroIni;
        this.numeroFim       = numeroFim;
        this.numero          = numero;
        this.complemento     = complemento; 
        this.nomeEstado      = nomeEstado;
        this.uf              = uf;
        this.nomeCidade      = nomeCidade;
        this.nomeBairro      = nomeBairro;
        this.nomeRua         = nomeRua;
        this.nomeCidadao     = nomeCidadao;
        this.cpf             = cpf;
        this.telefoneFixo    = telefoneFixo;
        this.telefoneCelular = telefoneCelular;
        this.dataNasc        = dataNasc;
        this.senha           = senha;
        this.email           = email;
        this.numeroCep       = numeroCep;
        this.sexo            = sexo;
    }

    /**
     * @return the numeroCep
     */
    public String getNumeroCep() {
        return numeroCep;
    }

    /**
     * @param numeroCep the numeroCep to set
     */
    public void setNumeroCep(String numeroCep) {
        this.numeroCep = numeroCep;
    }

    /**
     * @return the numeroIni
     */
    public int getNumeroIni() {
        return numeroIni;
    }

    /**
     * @param numeroIni the numeroIni to set
     */
    public void setNumeroIni(int numeroIni) {
        this.numeroIni = numeroIni;
    }

    /**
     * @return the numeroFim
     */
    public int getNumeroFim() {
        return numeroFim;
    }

    /**
     * @param numeroFim the numeroFim to set
     */
    public void setNumeroFim(int numeroFim) {
        this.numeroFim = numeroFim;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the complemento
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * @param complemento the complemento to set
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    /**
     * @return the nomeEstado
     */
    public String getNomeEstado() {
        return nomeEstado;
    }

    /**
     * @param nomeEstado the nomeEstado to set
     */
    public void setNomeEstado(String nomeEstado) {
        this.nomeEstado = nomeEstado;
    }

    /**
     * @return the uf
     */
    public String getUf() {
        return uf;
    }

    /**
     * @param uf the uf to set
     */
    public void setUf(String uf) {
        this.uf = uf;
    }

    /**
     * @return the nomeCidade
     */
    public String getNomeCidade() {
        return nomeCidade;
    }

    /**
     * @param nomeCidade the nomeCidade to set
     */
    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    /**
     * @return the nomeBairro
     */
    public String getNomeBairro() {
        return nomeBairro;
    }

    /**
     * @param nomeBairro the nomeBairro to set
     */
    public void setNomeBairro(String nomeBairro) {
        this.nomeBairro = nomeBairro;
    }

    /**
     * @return the nomeRua
     */
    public String getNomeRua() {
        return nomeRua;
    }

    /**
     * @param nomeRua the nomeRua to set
     */
    public void setNomeRua(String nomeRua) {
        this.nomeRua = nomeRua;
    }

    /**
     * @return the nomeCidadao
     */
    public String getNomeCidadao() {
        return nomeCidadao;
    }

    /**
     * @param nomeCidadao the nomeCidadao to set
     */
    public void setNomeCidadao(String nomeCidadao) {
        this.nomeCidadao = nomeCidadao;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the cpf
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * @return the telefoneFixo
     */
    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    /**
     * @param telefoneFixo the telefoneFixo to set
     */
    public void setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }

    /**
     * @return the telefoneCelular
     */
    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    /**
     * @param telefoneCelular the telefoneCelular to set
     */
    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public String getDataNascString() {
        return dataNascString;
    }

    public void setDataNascString(String dataNascString)  {
        /*SimpleDateFormat sdf      = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date   dataNascUtil;
        java.sql.Date    dataNascSql;
        
        dataNascUtil = sdf.parse(dataNascString);
        dataNascSql  = new java.sql.Date(dataNascUtil.getTime());
        setDataNasc(dataNascSql);
        */
        this.dataNascString = dataNascString;
    }

    /**
     * @return the dataNasc
     */
    public Date getDataNasc() {
        return dataNasc;
    }

    /**
     * @param dataNasc the dataNasc to set
     */
    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
}// fim da classe CidadaoComposit
