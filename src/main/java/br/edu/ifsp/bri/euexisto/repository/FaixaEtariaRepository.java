/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.repository;

import br.edu.ifsp.bri.euexisto.domain.FaixaEtaria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gahsabio
 */
public class FaixaEtariaRepository implements Serializable {
    
    public FaixaEtariaRepository() {
    }
    
    public void add(FaixaEtaria faixaEtaria){
        EntityManager entityManager = JPAConnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(faixaEtaria);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método add
    
    public void update(FaixaEtaria faixaEtaria){
        EntityManager entityManager = JPAConnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(faixaEtaria);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método update
    
    
    public void remove( int id ){
        EntityManager entityManager = JPAConnection.getEntityManager();
        entityManager.getTransaction().begin();
        FaixaEtaria faixaEtariaEncontrado = entityManager.find(FaixaEtaria.class, id );
        entityManager.remove(faixaEtariaEncontrado);
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método remove
    
    
    public List<FaixaEtaria> list(){
        List<FaixaEtaria> listaFaixaEtaria = new ArrayList<>();
        EntityManager entityManager = JPAConnection.getEntityManager();
        try {       
            Query query = entityManager.createQuery("SELECT tp FROM FaixaEtaria tp " + 
                                                    " order by idCidade, idadeIni, idadeFim ");
            listaFaixaEtaria = query.getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        return listaFaixaEtaria;
    }// fim do método list
    
    
    public List<FaixaEtaria> list(int idCidade){
        List<FaixaEtaria> listaFaixaEtaria = new ArrayList<>();
        EntityManager entityManager = JPAConnection.getEntityManager();
        try {       
            Query query = entityManager.createQuery("SELECT tp FROM FaixaEtaria tp where idCidade = " + idCidade +
                                                    " order by idCidade, idadeIni, idadeFim ");
            listaFaixaEtaria = query.getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        return listaFaixaEtaria;
    }// fim do método list
    
    
    // Buscar s faixa etária pela descrição 
    public List<FaixaEtaria> list(String descricao, int idCidade){
        String sql = "SELECT tp FROM FaixaEtaria tp "
                   + "where  upper(descricao) = '" + descricao.trim().toUpperCase() + "' "
                   + "and    idCidade         = "  + idCidade;
        
        List<FaixaEtaria> listaFaixaEtaria = new ArrayList<>();
        EntityManager entityManager = JPAConnection.getEntityManager();
        try {       
            Query query      = entityManager.createQuery(sql);
            listaFaixaEtaria = query.getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        return listaFaixaEtaria;
    }// fim do método list
    
    
    // Buscar s faixa etária para uma determinada idade
    public List<FaixaEtaria> list(int idade, int idCidade){
        String sql = "SELECT tp FROM FaixaEtaria tp "
                   + "where  idadeIni <  " + idade + " "
                   + "and    idadeFim >= " + idade + " "
                   + "and    idCidade  = " + idCidade;
        
        List<FaixaEtaria> listaFaixaEtaria = new ArrayList<>();
        EntityManager entityManager = JPAConnection.getEntityManager();
        try {       
            Query query      = entityManager.createQuery(sql);
            listaFaixaEtaria = query.getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        return listaFaixaEtaria;
    }// fim do método list
    
    
    // Verificar se a faixa etaria que está sendo incluída ja existe
    // 
    // Fazer essa verificação pela descricao e pela idadeIni e IdadeFim
    // idOld é o id do objeto que está sendo alterado. Zero quando for inclusao
    public int check(FaixaEtaria faixaEtaria, int idOld){
        // Verificar se já existe uma faixa etária cadastrada para a descrição
        List<FaixaEtaria> listaFaixaEtaria = new ArrayList<>();
        String            sql              = generateSQL(faixaEtaria.getDescricao(), faixaEtaria.getCidade().getId(), idOld);
        EntityManager     entityManager    = JPAConnection.getEntityManager();
        Query             query;
        try {       
            query            = entityManager.createQuery(sql);   
            listaFaixaEtaria = query.getResultList();
            
            if  (listaFaixaEtaria.size() == 0) {
                // Verificar se já existe uma faixa etaria cadastrada para a idadeIni
                // somente se não existe uma para a descricao
                sql              = generateSQL(faixaEtaria.getIdadeIni(), faixaEtaria.getCidade().getId(), idOld);
                query            = entityManager.createQuery(sql);
                listaFaixaEtaria = query.getResultList();
                           
                if  (listaFaixaEtaria.size() == 0) {
                    // Verificar se já existe uma faixa etaria cadastrada para a idadeFin
                    // somente se não existe uma para a descricao
                    sql              = generateSQL(faixaEtaria.getIdadeFim(), faixaEtaria.getCidade().getId(), idOld);
                    query            = entityManager.createQuery(sql);
                    listaFaixaEtaria = query.getResultList();
                }
            }
        } 
        catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        
        return listaFaixaEtaria.size();
    }// fim do método check
    

    private String generateSQL (String descricao, int idCidade, int idOld) {
        String sql = "";
        
        if   (idOld == 0) {
             sql = "SELECT id FROM FaixaEtaria "
                 + "where  upper(descricao) = '" + descricao.trim().toUpperCase() + "' "
                 + "and    idCidade         = "  + idCidade;
             }
        else {
             sql = "SELECT id FROM FaixaEtaria "
                 + "where  upper(descricao) = '" + descricao.trim().toUpperCase() + "' "
                 + "and    idCidade         = "  + idCidade                       + " "
                 + "and    id               <> " + idOld;
        }

        return sql;
    }// fim do método generateSQL  

    private String generateSQL (int idade, int idCidade, int idOld) {
        String sql = "";
        
        if   (idOld == 0) {
             sql = "SELECT id FROM FaixaEtaria "
                 + "where  idadeIni <  "  + idade + " "
                 + "and    idadeFim >  "  + idade + " "
                 + "and    idCidade =  "  + idCidade;
             }
        else {
             sql = "SELECT id FROM FaixaEtaria "
                 + "where  idadeIni <  " + idade    + " "
                 + "and    idadeFim >  " + idade    + " "
                 + "and    idCidade =  " + idCidade + " "
                 + "and    id       <> " + idOld;
        }

        return sql;
    }// fim do método generateSQL  
}// fim da classe FaixaEtariaRepository
