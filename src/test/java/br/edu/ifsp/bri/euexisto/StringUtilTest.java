/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto;

import br.edu.ifsp.bri.euexisto.domain.Cidadao;
import br.edu.ifsp.bri.euexisto.util.StringUtil;
import java.text.ParseException;

/**
 *
 * @author leo
 */
public class StringUtilTest {
    
    static final String[] ACENTOS = new String[]{
        "Á", "á", "Â", "â", "À","à", "Å", "å", "Ã", "ã", "Ä", 
        "ä", "Æ", "æ", "ï", "É", "é", "Ê", "ê", "Ë", "ë", "Í", 
        "î", "Ì", "ì", "Ï", "Ù", "ó", "Ô", "È", "è", "ò", "Ø", 
        "ð", "Ó", "í", "Î", "ô", "Ò", "õ", "Ö", "Ú", "û", "ù", 
        "Ü", "ø", "Ç", "ç", "Ñ", "ñ", "Õ", "Ý", "ý", "ö", "\"",
        "<", "ú", "Û", "®", "©", "Þ", "ü", ">", "&", "þ", "ß"};
    
    public static void main (String args[]) throws ParseException   {    
        System.out.println("Validação de remoção de acentos : " + TesteRemoveAcento());  
        System.out.println("Validação de remoção de acentos de um obejto : " + TesteRemoveAcentoFromT());
    }
    
    private static String TesteRemoveAcento(){
        String result = "OK";
        
        String resultStr = StringUtil.RemoveAcento("ÁáÂâÀàÅåÃãÄäÆæïÉéÊêËëÍîÌìÏÙóÔÈèòØðÓíÎôÒõÖÚûùÜøÇçÑñÕÝýö<úÛ®©Þü>&þß");
        if(StringUtil.Contains(resultStr, ACENTOS, true))
        {
            result = "Falhou no teste de remoção de caracteres acentuados";
        }
        System.out.println(resultStr);
        return result;
    }
    
    private static String TesteRemoveAcentoFromT(){
        String result = "OK";
        
        Cidadao mock = new Cidadao();
        mock.setNome("ÁáÂâÀàÅåÃãÄäÆæïÉéÊêËëÍîÌìÏÙóÔÈèòØðÓíÎôÒõÖÚûùÜøÇçÑñÕÝýö<úÛ®©Þü>&þß");
        mock.setEmail("ÁáÂâÀàÅåÃãÄäÆæïÉéÊêËëÍîÌìÏÙóÔÈèòØðÓíÎôÒõÖÚûùÜøÇçÑñÕÝýö<úÛ®©Þü>&þß");
        
        mock = StringUtil.RemoveAcentoFromT(mock);
        if(StringUtil.Contains(mock.getNome(), ACENTOS, true) || 
        StringUtil.Contains(mock.getEmail(), ACENTOS, true)){
            result = "Falhou no teste de remoção de caracteres acentuados de um objeto";
        }
        
        return result;
    }
}
