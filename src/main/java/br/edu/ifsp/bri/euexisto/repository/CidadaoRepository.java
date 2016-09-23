/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.repository;

import br.edu.ifsp.bri.euexisto.domain.Cidadao;
import br.edu.ifsp.bri.euexisto.domain.CidadaoSexoQtde;
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
        
    public List<CidadaoSexoQtde> listCidadaoEstadoSexoQtde(){
        List<CidadaoSexoQtde> listaCidadaoSexoQtde = new ArrayList<>();
        EntityManager entityManager = JPAConnection.getEntityManager();
        try {
            Query query = entityManager.createNativeQuery("select valor as valor, "                                    +
                                                    "       sum(qtdeFem)  as qtdeFem, "                    +
                                                    "       sum(qtdeMasc) as qtdeMasc "                    +
                                                    "from ("                                               +
                                                    "select Estado.uf as valor, "                                   +
                                                    "       count(Estado.id) as qtdeFem, "                 +
                                                    "       0 as qtdeMasc "                                +
                                                    "from   Estado, Cidade, Cep, Endereco, Cidadao "       +
                                                    "where  Estado.id    = Cidade.idEstado "               +
                                                    "and    Cidade.id    = Cep.idCidade "                  +
                                                    "and    Cep.id       = Endereco.idCep "                +
                                                    "and    Endereco.id  = Cidadao.idEndereco "            +
                                                    "and    Cidadao.sexo = 'F' "                           +
                                                    "group  by Estado.uf "                                 +
                                                    "union "                                               +
                                                    "select Estado.uf as valor, "                                   +
                                                    "       0 as qtdeFem, "                                +
                                                    "       count(Estado.id) as qtdeMasc "                 +
                                                    "from   Estado, Cidade, Cep, Endereco, Cidadao "       +
                                                    "where  Estado.id    = Cidade.idEstado "               +
                                                    "and    Cidade.id    = Cep.idCidade "                  +
                                                    "and    Cep.id       = Endereco.idCep "                +
                                                    "and    Endereco.id  = Cidadao.idEndereco "            +
                                                    "and    Cidadao.sexo = 'M' "                           +
                                                    "group  by Estado.uf "                                 +
                                                    ")tmp "                                                +
                                                    "group  by valor "                                     +
                                                    "order  by valor ", 
                                                    CidadaoSexoQtde.class);

            listaCidadaoSexoQtde = query.getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        return listaCidadaoSexoQtde;
    }// fim do método listCidadaoEstadoSexoQtde
               
    public List<CidadaoSexoQtde> listCidadaoCidadeSexoQtde(int idEstado){
        List<CidadaoSexoQtde> listaCidadaoSexoQtde = new ArrayList<>();
        EntityManager entityManager = JPAConnection.getEntityManager();
        try {
            Query query = entityManager.createNativeQuery("select valor as valor, "                                    +
                                                    "       sum(qtdeFem)  as qtdeFem, "                    +
                                                    "       sum(qtdeMasc) as qtdeMasc "                    +
                                                    "from ("                                               +
                                                    "select Cidade.nome as valor, "                                   +
                                                    "       count(Cidade.id) as qtdeFem, "                 +
                                                    "       0 as qtdeMasc "                                +
                                                    "from   Cidadao, Endereco, Cep, Cidade "               +
                                                    "where  Cidadao.idEndereco = Endereco.id "             +
                                                    "and    Cidadao.sexo       = 'F' "                     +
                                                    "and    Endereco.idCep     = Cep.id "                  +
                                                    "and    Cep.idCidade       = Cidade.id "               +
                                                    "and    Cidade.idEstado    = " + idEstado              +
                                                    "group  by Cidade.nome "                               +
                                                    "union "                                               +
                                                    "select Cidade.nome as valor, "                        +
                                                    "       0 as qtdeFem, "                                +
                                                    "       count(Cidade.id) as qtdeMasc "                 +
                                                    "from   Cidadao, Endereco, Cep, Cidade "               +
                                                    "where  Cidadao.idEndereco = Endereco.id "             +
                                                    "and    Cidadao.sexo       = 'M' "                     +
                                                    "and    Endereco.idCep     = Cep.id "                  +
                                                    "and    Cep.idCidade       = Cidade.id "               +
                                                    "and    Cidade.idEstado    = " + idEstado              +
                                                    "group  by Cidade.nome "                                 +
                                                    ")tmp "                                                +
                                                    "group  by valor "                                     +
                                                    "order  by valor ", 
                                                    CidadaoSexoQtde.class);

            listaCidadaoSexoQtde = query.getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        return listaCidadaoSexoQtde;
    }// fim do método listCidadaoCidadeSexoQtde
    
    public int getCidadaoSexoQtdeMax(){
        int qtdeMax = 0;
        EntityManager entityManager = JPAConnection.getEntityManager();
        try {
            Query query = entityManager.createNativeQuery("select max(tmp.qtde) as qtdeMax "                  +
                                                    "from ("                                               +
                                                    "select Estado.uf, "                                   +
                                                    "       count(Estado.id) as qtde "                     +
                                                    "from   Estado, Cidade, Cep, Endereco, Cidadao "       +
                                                    "where  Estado.id    = Cidade.idEstado "               +
                                                    "and    Cidade.id    = Cep.idCidade "                  +
                                                    "and    Cep.id       = Endereco.idCep "                +
                                                    "and    Endereco.id  = Cidadao.idEndereco "            +
                                                    "and    Cidadao.sexo = 'F' "                           +
                                                    "group  by Estado.uf "                                 +
                                                    "union "                                               +
                                                    "select Estado.uf, "                                   +
                                                    "       count(Estado.id) as qtde "                     +
                                                    "from   Estado, Cidade, Cep, Endereco, Cidadao "       +
                                                    "where  Estado.id    = Cidade.idEstado "               +
                                                    "and    Cidade.id    = Cep.idCidade "                  +
                                                    "and    Cep.id       = Endereco.idCep "                +
                                                    "and    Endereco.id  = Cidadao.idEndereco "            +
                                                    "and    Cidadao.sexo = 'M' "                           +
                                                    "group  by Estado.uf "                                 +
                                                    ")tmp ");

            qtdeMax = Integer.parseInt(query.getSingleResult().toString());
        } catch (Exception e){
            e.printStackTrace();
        }
        entityManager.close();
        return qtdeMax;
    }// fim do método listCidadaoEstadoSexoQtde
    
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
