/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.repository;

import br.edu.ifsp.bri.euexisto.domain.Endereco;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gahsabio
 */
public class EnderecoRepository implements Serializable {
    
    public EnderecoRepository() {
    }
    
    public void add(Endereco endereco){
        EntityManager entityManager = JPAConnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(endereco);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método add
    
    public void update(Endereco endereco){
        EntityManager entityManager = JPAConnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(endereco);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método update
    
    
    public void remove( int id ){
        EntityManager entityManager = JPAConnection.getEntityManager();
        entityManager.getTransaction().begin();
        Endereco enderecoEncontrado = entityManager.find(Endereco.class, id );
        entityManager.remove(enderecoEncontrado);
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método remove
    
    
    public List<Endereco> list(){
        List<Endereco> listaEndereco = new ArrayList<>();
        EntityManager entityManager = JPAConnection.getEntityManager();
        try {       
            Query query = entityManager.createQuery ("SELECT tp FROM Endereco tp order by idCep, numero, complemento");
            listaEndereco = query.getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        return listaEndereco;
    }// fim do método list
    
    
    // Buscar o endereco pelo cep e numero
    public List<Endereco> list(int idCidade){
        String sql = "SELECT tp FROM Endereco tp, Cep p, Cidade c "
                   + "where  tp.cep   = p " 
                   + "and    p.cidade = c "
                   + "and    c.id     = " + idCidade;
        
        List<Endereco> listaEndereco = new ArrayList<>();
        EntityManager entityManager = JPAConnection.getEntityManager();
        try {       
            Query query   = entityManager.createQuery(sql);
            listaEndereco = query.getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        return listaEndereco;
    }// fim do método list    
    
    // Buscar o endereco pelo cep e numero
    public List<Endereco> list(int idCep, String numero){
        String sql = "SELECT tp FROM Endereco tp "
                   + "where  idCep  = "  + idCep         + " "
                   + "and    numero = '" + numero.trim() + "' ";
        
        List<Endereco> listaEndereco = new ArrayList<>();
        EntityManager entityManager = JPAConnection.getEntityManager();
        try {       
            Query query   = entityManager.createQuery(sql);
            listaEndereco = query.getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        return listaEndereco;
    }// fim do método list
        
    
    // Buscar o endereco pelo cep, rua e numero
    public List<Endereco> list(int idCep, int idRua, String numero){
        String sql = "SELECT tp FROM Endereco tp "
                   + "where  idCep  = "  + idCep                       + " "
                   + "and    idRua  = "  + idRua                       + " "
                   + "and    numero = '" + numero.trim().toUpperCase() + "' ";
        
        List<Endereco> listaEndereco = new ArrayList<>();
        EntityManager entityManager = JPAConnection.getEntityManager();
        try {       
            Query query   = entityManager.createQuery(sql);
            listaEndereco = query.getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        return listaEndereco;
    }// fim do método list
    
    
    // Verificar se o endereco que está sendo incluído ja existe
    // 
    // Fazer essa verificação pelo cep/numero e ceprua/numero
    // idOld é o id do objeto que está sendo alterado. Zero quando for inclusao
    public int check(int idCep, int idRua, String numero, int idOld){
        // Verificar se já existe um cep cadastrado para a descrição
        List<Endereco> listaEndereco = new ArrayList<>();
        String         sql           = generateSQL(idCep, idRua, numero, idOld);
        EntityManager  entityManager = JPAConnection.getEntityManager();
        Query          query;
        try {       
            query         = entityManager.createQuery(sql);   
            listaEndereco = query.getResultList();
        } 
        catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        
        return listaEndereco.size();
    }// fim do método check
    

    private String generateSQL (int idCep, int idRua, String numero, int idOld) {
        String sql = "";
        
        if   (idOld == 0) {
             sql = "SELECT id FROM Endereco tp "
                 + "where  idCep  = "  + idCep + " "
                 + "and    numero = '" + numero.trim().toUpperCase() + "' ";
             }
        else {
             sql = "SELECT id FROM Endereco tp "
                 + "where  idCep  = "  + idCep                       + " " 
                 + "and    numero = '" + numero.trim().toUpperCase() + "' "
                 + "and    id     <> " + idOld                       + " ";
        }

        if   (idRua != 0) {
             sql = sql + "and idRua = " + idRua;
        }
        
        return sql;
    }// fim do método generateSQL  
  
}// fim da classe EnderecoRepository