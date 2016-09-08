/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.service;

import br.edu.ifsp.bri.euexisto.domain.TipoUser;
import br.edu.ifsp.bri.euexisto.domain.TipoUsuario;
import br.edu.ifsp.bri.euexisto.repository.TipoUserRepository;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author ifsp
 */
public class TipoUserService implements Serializable {
    
    private List<TipoUsuario> tipoUsuario;
    
    private TipoUserRepository tipoUserRepository;

    public TipoUserService() {
        tipoUserRepository = new TipoUserRepository();
        tipoUsuario = Arrays.asList(TipoUsuario.values());
    }

    public void addTipoUser(TipoUser tipoUser) {
        this.getTipoUserRepository().addTipoUser(tipoUser);
    }
    
    public void updateTipoUser(TipoUser tipoUser){
        this.getTipoUserRepository().updateTipoUser(tipoUser);
    }   
    
    public void removeTipoUser(TipoUser tipoUser){
        this.getTipoUserRepository().removeTipoUser(tipoUser.getCodigoTpUsuario());
    }
    
    public List<TipoUser> listaTipoUser(){
        return this.getTipoUserRepository().listaTipoUser();
    }
    
    public TipoUserRepository getTipoUserRepository() {
        return tipoUserRepository;
    }

    public void setTipoUserRepository(TipoUserRepository tipoUserRepository) {
        this.tipoUserRepository = tipoUserRepository;
    }

    public List<TipoUsuario> getTipoUsuario() {
        return tipoUsuario;
    }
    
}
