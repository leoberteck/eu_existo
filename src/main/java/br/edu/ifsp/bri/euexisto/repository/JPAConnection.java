/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ifsp
 */
public class JPAConnection {
    
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EuExistoPU");
    
    public static EntityManager getEntityManager(){
        return entityManagerFactory.createEntityManager();
    }

}
