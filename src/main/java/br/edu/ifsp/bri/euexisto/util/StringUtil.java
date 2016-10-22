/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.util;

import java.text.Normalizer;

/**
 *
 * @author leo
 */
public class StringUtil {
    public static String RemoveAcento(String str){
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
    
    public static boolean Contains(String str, String[] strArr, boolean failIfConteins){
        boolean result = failIfConteins;
        for(String test : strArr){
            if(str.contains(test)){
                result = !failIfConteins;
            }
        }
        return result;
    }
}
