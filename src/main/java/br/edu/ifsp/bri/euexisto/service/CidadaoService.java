/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.service;

import br.edu.ifsp.bri.euexisto.domain.Cidadao;
import br.edu.ifsp.bri.euexisto.domain.CidadaoSexoQtde;
import br.edu.ifsp.bri.euexisto.repository.CidadaoRepository;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.InputMismatchException;
import java.util.List;

/**
 *
 * @author gahsabio
 */
public class CidadaoService {
    
    private CidadaoRepository cidadaoRepository;

    public CidadaoService() {
        cidadaoRepository = new CidadaoRepository();
    }
    
    // Esse método deve fazer as seguintes verificações
    // Inclusão:  não pode incluir um cidadao que já exista
    // Alteração: não pode alterar um cidadao para um que já exista
    public void add(Cidadao cidadao) {
        //   na inclusão, id = 0
        if   (check(cidadao, 0))
        {
            String senha = this.convertStringToMd5(cidadao.getSenha());
            cidadao.setSenha(senha);
            this.getCidadaoRepository().add(cidadao); 
             
        }
             
    }// fim do metodo add
    
    public void update(Cidadao cidadao){
        //   na alteração, passar o id do cidadao que está sendo alterado
        if   (check(cidadao, cidadao.getId()))
        {
            String senha = this.convertStringToMd5(cidadao.getSenha());
            cidadao.setSenha(senha);
            this.getCidadaoRepository().update(cidadao);   
        }
              
    }// fim do método update
    
    public void remove(Cidadao cidadao){
        this.getCidadaoRepository().remove(cidadao.getId());
    }// fim do método remove
    
    // na inclusão,  id = 0
    // na alteração, id do objeto que está sendo alterado
    public boolean check (Cidadao cidadao, int idOld) {
        boolean cidadaoOk = true;

        if   (cidadao.getNome().trim().equals("")){
             cidadaoOk = false;
             System.out.println("Um " + cidadaoOk);
        }
        else if   (cidadao.getCpf().trim().equals("")){
                  cidadaoOk = false;
             System.out.println("Dois " + cidadaoOk);
        }
        else if   (cidadao.getDataNasc().equals("")){
                  cidadaoOk = false;
             System.out.println("Três " + cidadaoOk);
        }
        else if   (cidadao.getSenha().trim().equals("")){
                  cidadaoOk = false;
             System.out.println("Quatro " + cidadaoOk);
        }
        else if   (cidadao.getEndereco().getId() == 0) {
                  cidadaoOk = false;
             System.out.println("Cinco " + cidadaoOk);
        }
        else if   ((cidadao.getSexo().trim().equals("")) || 
                   (!(cidadao.getSexo().trim().equals("F"))) &&
                   (!(cidadao.getSexo().trim().equals("M")))) {
                  cidadaoOk = false;
             System.out.println("Seis " + cidadaoOk);
        }
        else if   (this.cidadaoRepository.check(cidadao.getEmail(), cidadao.getCpf(), idOld) > 0) {
            cidadaoOk = false;
            System.out.println("Sete " + cidadaoOk);
        }
        else if(!isCPF(cidadao.getCpf()))
        {
            cidadaoOk = false;
            System.out.println("Oito " + cidadaoOk);
        }

        return  cidadaoOk;
    }// fim do método check
    
    //Converter senha para MD5
    private String convertStringToMd5(String valor) {
        MessageDigest mDigest;
        try {
            mDigest = MessageDigest.getInstance("MD5");
            byte[] valorMD5 = mDigest.digest(valor.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (byte b : valorMD5) {
                sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isCPF(String CPF) {
        CPF = CPF.replace(".", "");
        CPF = CPF.replace("-", "");
        
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") || CPF.equals("11111111111")
                || CPF.equals("22222222222") || CPF.equals("33333333333")
                || CPF.equals("44444444444") || CPF.equals("55555555555")
                || CPF.equals("66666666666") || CPF.equals("77777777777")
                || CPF.equals("88888888888") || CPF.equals("99999999999")
                || (CPF.length() != 11)) {
            return (false);
        }
        
        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
            // converte o i-esimo caractere do CPF em um numero:
            // por exemplo, transforma o caractere '0' no inteiro 0         
            // (48 eh a posicao de '0' na tabela ASCII)         
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico
            }
            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }
    
    public List<Cidadao> list(){
        return this.getCidadaoRepository().list();
    }// fim do método list
        
    // Buscar os cidadão que residem em uma cidade, pelo idCidade
    // ou
    // Buscar os cidadão que residem em uma cidade, pelo idEndereco
    // tipoBusca
    // C --> por cidade,
    // E --> por endereco
    public List<Cidadao> list(int valor, String tipoBusca){
        return this.getCidadaoRepository().list(valor, tipoBusca);
    }// fim do método list
    
    // Buscar o cidadao pelo email ou pelo cpf
    // Valor é o conteúdo a ser pesquisado: email ou cpf
    // tipoBusca
    // E --> busca pelo email
    // C --> busca pelo cpf 
    public List<Cidadao> list(String valor, String tipoBusca){
        return this.getCidadaoRepository().list(valor, tipoBusca);
    }// fim do método list
      
    public List<CidadaoSexoQtde> listCidadaoEstadoSexoQtde(){
        return this.getCidadaoRepository().listCidadaoEstadoSexoQtde();
    }// fim do método ListCidadaoEstadoSexoQtde
      
    public List<CidadaoSexoQtde> listCidadaoCidadeSexoQtde(int idEstado){
        return this.getCidadaoRepository().listCidadaoCidadeSexoQtde(idEstado);
    }// fim do método ListCidadaoCidadeSexoQtde
    
    public int getCidadaoSexoQtdeMax(){
        return this.getCidadaoRepository().getCidadaoSexoQtdeMax();
    }// fim do método getCidadaoSexoQtdeMax
    
    public CidadaoRepository getCidadaoRepository() {
        return cidadaoRepository;
    }// fim do metodo getCidadaoRepository

    public void setCidadaoRepository(CidadaoRepository cidadaoRepository) {
        this.cidadaoRepository = cidadaoRepository;
    }// fim do método setCidadaoRepository
        
}
