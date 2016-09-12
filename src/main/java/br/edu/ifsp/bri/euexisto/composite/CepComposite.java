/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.composite;

/**
 *
 * @author gahsabio
 */
public class CepComposite {
    
    private String numeroCep;
    private int    numeroIni;
    private int    numeroFim;
    private String nomeEstado;
    private String uf;
    private String nomeCidade;
    private String nomeBairro;
    private String nomeRua;
    
    public CepComposite () {
        numeroCep  = "";
        numeroIni  = 0;
        numeroFim  = 0;
        nomeEstado = "";
        uf         = "";
        nomeCidade = "";
        nomeBairro = "";
        nomeRua    = "";
    }
    
    public CepComposite (String numeroCep, int    numeroIni,  int    numeroFim,  String nomeEstado,
                         String uf,        String nomeCidade, String nomeBairro, String nomeRua) {
        this.numeroCep  = numeroCep;
        this.numeroIni  = numeroIni;
        this.numeroFim  = numeroFim;
        this.nomeEstado = nomeEstado;
        this.uf         = uf;
        this.nomeCidade = nomeCidade;
        this.nomeBairro = nomeBairro;
        this.nomeRua    = nomeRua;
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
    
}// fim da classe CepComposite
