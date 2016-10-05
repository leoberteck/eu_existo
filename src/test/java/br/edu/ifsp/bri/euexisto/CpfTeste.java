/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto;

import br.edu.ifsp.bri.euexisto.service.CidadaoService;
import java.text.ParseException;

/**
 *
 * @author leo
 */
public class CpfTeste {
    public static void main (String args[]) throws ParseException   {    
        System.out.println("Validação de CPF : " + TesteValidadorCPF());  
    }
    
    private static String TesteValidadorCPF()
    {
        CidadaoService cidadaoService = new CidadaoService();
        //É um cpf VALIDO
        String result = cidadaoService.isCPF("53701233659") ? "OK" : "Não validou cpf sem pontuacao" ;
        if(result.equals("OK"))
        {
            //É um cpf INVALIDO
            result = !cidadaoService.isCPF("54578955478") ? "OK" : "Validou cpf inválido";
            if(result.equals("OK"))
            {
                //É um cpf VALIDO com pontuacao
                result = cidadaoService.isCPF("252.800.784-17") ? "OK" : "Não validou cpf com pontuação";
            }
        }
        return result;
    } 
}
