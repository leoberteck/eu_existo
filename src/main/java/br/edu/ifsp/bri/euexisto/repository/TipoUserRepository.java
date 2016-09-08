/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.repository;

import br.edu.ifsp.bri.euexisto.domain.TipoUser;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author ifsp
 */
public class TipoUserRepository implements Serializable {
    
    private TipoUser tipoUser; 

    public TipoUserRepository() {
    }
    
    public void addTipoUser(TipoUser tipoUser){
        EntityManager entityManager = JPAConnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(tipoUser);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }
    
    public void updateTipoUser(TipoUser tipoUser ){
        EntityManager entityManager = JPAConnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(tipoUser);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }
    
    
    public void removeTipoUser( int id ){
        EntityManager entityManager = JPAConnection.getEntityManager();
        entityManager.getTransaction().begin();
        TipoUser tipoUserEncontrado = entityManager.find(TipoUser.class, id );
        entityManager.remove(tipoUserEncontrado);
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
        
    }
    
    
    public List<TipoUser> listaTipoUser(){
        List<TipoUser> listaTipoUser = new ArrayList<>();
        EntityManager entityManager = JPAConnection.getEntityManager();
        try {       
            Query query = entityManager.createQuery("SELECT tp FROM TipoUser tp ");
            listaTipoUser = query.getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        return listaTipoUser;
    }
    
}
