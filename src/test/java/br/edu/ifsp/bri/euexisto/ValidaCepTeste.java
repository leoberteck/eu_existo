/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto;

import br.edu.ifsp.bri.euexisto.service.CepService;
import java.io.Console;

/**
 *
 * @author leo
 */
public class ValidaCepTeste {
    public static void main (String args[]) {
        String result = "OK";
        CepService service = new CepService();
        //Valida cep válido sem máscara
        if(service.check("16204000", 1, 1))
        {
            result = "Erro - Validou cep válido sem máscara";
        }
        //Valida cep inválido com máscara
        else if(service.check("abc.def-000", 1, 1))
        {
            result = "Erro - Validou cep inválido com máscara";
        }
        //Valida cep com número maior que caracteres
        else if(service.check("16204000122", 1, 1))
        {
            result = "Erro - Validou cep com número maior que caracteres";
        }
        //Valida cep com número menor de caracteres
        else if(service.check("16.204-", 1, 1))
        {
            result = "Erro - Validou cep com número menor de caracteres";
        }
        //Valida cep sem a quantidade correta de números
        else if(service.check("16.204----", 1, 1))
        {
            result = "Erro - Validou cep sem a quantidade correta de números";
        }
        //Cep único de cidade com bairro ou rua
        else if(service.check("16.204-000", 2131, 12))
        {
            result = "Erro - Validou Cep único de cidade com bairro ou rua";
        }
        //Cep de rua sem bairro ou rua
        else if(service.check("16.204-999", 0, 0))
        {
            result = "Erro - Validou Cep de rua sem bairro ou rua";
        }
        
        System.out.println("Validação de CEP : " + result);
    }
}
