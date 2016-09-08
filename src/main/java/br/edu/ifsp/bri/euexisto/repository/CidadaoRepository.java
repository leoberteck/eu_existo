/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.repository;

import br.edu.ifsp.bri.euexisto.domain.Cidadao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author gahsabio
 */
public class CidadaoRepository implements Serializable {
    
    public CidadaoRepository() {
    }
    
    public void add(Cidadao cidadao){
        EntityManager entityManager = JPAConnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(cidadao);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método add
    
    public void update(Cidadao cidadao){
        EntityManager entityManager = JPAConnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(cidadao);  //grava um novo registro
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método update
    
    
    public void remove( int id ){
        EntityManager entityManager = JPAConnection.getEntityManager();
        entityManager.getTransaction().begin();
        Cidadao cidadaoEncontrado = entityManager.find(Cidadao.class, id );
        entityManager.remove(cidadaoEncontrado);
        entityManager.getTransaction().commit();  //executa o banco para grava 
        entityManager.close();
    }// fim do método remove
    
    
    public List<Cidadao> list(){
        List<Cidadao> listaCidadao = new ArrayList<>();
        EntityManager entityManager = JPAConnection.getEntityManager();
        try {       
            Query query = entityManager.createQuery ("SELECT tp FROM Cidadao tp order by nome");
            listaCidadao = query.getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        return listaCidadao;
    }// fim do método list
    
    
    // Buscar os cidadão que residem em uma cidade, pelo idCidade
    // ou
    // Buscar os cidadão que residem em uma cidade, pelo idEndereco
    // tipoBusca
    // C --> por cidade,
    // E --> por endereco
    public List<Cidadao> list(int valor, String tipoBusca){
        String sql = "";
        if  (tipoBusca.equals("C"))
             sql = "SELECT tp FROM Cidadao tp, Endereco e, Cep p, Cidade c "
                 + "where  tp.endereco = e "
                 + "and    e.cep       = p " 
                 + "and    p.cidade    = c "
                 + "and    c.id        = " + valor;
        else sql = "SELECT tp FROM cidadao tp "
                 + "where  idEndereco = "  + valor + "' ";
        
        List<Cidadao> listaCidadao = new ArrayList<>();
        EntityManager entityManager = JPAConnection.getEntityManager();
        try {       
            Query query   = entityManager.createQuery(sql);
            listaCidadao = query.getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        return listaCidadao;
    }// fim do método list    
    
    // Buscar o cidadao pelo email ou pelo cpf
    // Valor é o conteúdo a ser pesquisado: email ou cpf
    // tipoBusca
    // E --> busca pelo email
    // C --> busca pelo cpf 
    public List<Cidadao> list(String valor, String tipoBusca){
        String sql = "SELECT tp FROM Cidadao tp ";
        if   (tipoBusca.equals("E"))
             sql = sql + "where  upper(email) = '" + valor.trim().toUpperCase() + "' ";
        else sql = sql + "where  cpf          = '" + valor.trim() + "' ";
        
        List<Cidadao> listaCidadao = new ArrayList<>();
        EntityManager entityManager = JPAConnection.getEntityManager();
        try {       
            Query query   = entityManager.createQuery(sql);
            listaCidadao = query.getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        return listaCidadao;
    }// fim do método list
        
    
    // Verificar se o cidadao que está sendo incluído/alterado ja existe
    // 
    // Fazer essa verificação pelo email e cpf
    // idOld é o id do objeto que está sendo alterado. Zero quando for inclusao
    public int check(String email, String cpf, int idOld){
        // Verificar se já existe um cidadao cadastrado para o email
        List<Cidadao> listaCidadao = new ArrayList<>();
        String         sql           = generateSQL(email, "E", idOld);
        EntityManager  entityManager = JPAConnection.getEntityManager();
        Query          query;
        try {       
            query         = entityManager.createQuery(sql);   
            listaCidadao = query.getResultList();
            if   (listaCidadao.size()==0) {
                 sql          = generateSQL(cpf, "C", idOld);
                 query        = entityManager.createQuery(sql);   
                 listaCidadao = query.getResultList();
            }
        } 
        catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        
        return listaCidadao.size();
    }// fim do método check
    

    // tipoValidacao
    // E --> email já cadastrado
    // C --> cpf já cadastrado
    private String generateSQL (String valor, String tipoValidacao, int idOld) {
        String sql = "";
        
        if   (tipoValidacao.equals("E")) {
             if   (idOld == 0) {
                  sql = "SELECT id FROM Cidadao tp "
                      + "where  upper(email) = '" + valor.trim().toUpperCase() + "' ";
                  }
             else {
                  sql = "SELECT id FROM Cidadao tp "
                      + "where  upper(email) = '" + valor.trim().toUpperCase() + "' " 
                      + "and    id     <> " + idOld                     + " ";
             }
        }
        else {
              if   (idOld == 0) {
                  sql = "SELECT id FROM Cidadao tp "
                      + "where  cpf  = '"  + valor.trim().toUpperCase() + "' ";
                  }
             else {
                  sql = "SELECT id FROM Cidadao tp "
                      + "where  cpf  = '"  + valor.trim().toUpperCase() + "' " 
                      + "and    id     <> " + idOld                     + " ";
             }
        }
        
        return sql;
    }// fim do método generateSQL      
}// fim da classe CidadaoRepository
