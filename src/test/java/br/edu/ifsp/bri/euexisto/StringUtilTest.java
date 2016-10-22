/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto;

import br.edu.ifsp.bri.euexisto.util.StringUtil;
import java.text.ParseException;

/**
 *
 * @author leo
 */
public class StringUtilTest {
    public static void main (String args[]) throws ParseException   {    
        System.out.println("Validação de remoção de acentos : " + TesteRemoveAcento());  
    }
    
    private static String TesteRemoveAcento(){
        String result = "OK";
        String[] acentos = new String[]{
        "Á", "á", "Â", "â", "À","à", "Å", "å", "Ã", "ã", "Ä", 
        "ä", "Æ", "æ", "ï", "É", "é", "Ê", "ê", "Ë", "ë", "Í", 
        "î", "Ì", "ì", "Ï", "Ù", "ó", "Ô", "È", "è", "ò", "Ø", 
        "ð", "Ó", "í", "Î", "ô", "Ò", "õ", "Ö", "Ú", "û", "ù", 
        "Ü", "ø", "Ç", "ç", "Ñ", "ñ", "Õ", "Ý", "ý", "ö", "\"",
        "<", "ú", "Û", "®", "©", "Þ", "ü", ">", "&", "þ", "ß"};
        String resultStr = StringUtil.RemoveAcento("ÁáÂâÀàÅåÃãÄäÆæïÉéÊêËëÍîÌìÏÙóÔÈèòØðÓíÎôÒõÖÚûùÜøÇçÑñÕÝýö<úÛ®©Þü>&þß");
        if(StringUtil.Contains(resultStr, acentos, true))
        {
            result = "Falhou no teste de remoção de caracteres acentuados";
        }
        System.out.println(resultStr);
        return result;
    }
}
