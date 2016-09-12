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
public class FaixaEtariaComposite {
    
    private String nomeEstado;
    private String uf;
    private String nomeCidade;
    private String descricao;
    private int    idadeIni;
    private int    idadeFim;

    public FaixaEtariaComposite () {
        nomeEstado = "";
        uf         = "";
        nomeCidade = "";
        descricao  = "";
        idadeIni   = 0;
        idadeFim   = 0;
    }
    
    public FaixaEtariaComposite (String nomeEstado, String uf,       String nomeCidade, 
                                 String descricao,  int    idadeIni, int    idadeFim) {
        this.nomeEstado = nomeEstado;
        this.uf         = uf;
        this.nomeCidade = nomeCidade;
        this.descricao  = descricao;
        this.idadeIni   = idadeIni;
        this.idadeFim   = idadeFim;
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
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the idadeIni
     */
    public int getIdadeIni() {
        return idadeIni;
    }

    /**
     * @param idadeIni the idadeIni to set
     */
    public void setIdadeIni(int idadeIni) {
        this.idadeIni = idadeIni;
    }

    /**
     * @return the idadeFim
     */
    public int getIdadeFim() {
        return idadeFim;
    }

    /**
     * @param idadeFim the idadeFim to set
     */
    public void setIdadeFim(int idadeFim) {
        this.idadeFim = idadeFim;
    }

}// fim da classe FaixaEtariaComposite
