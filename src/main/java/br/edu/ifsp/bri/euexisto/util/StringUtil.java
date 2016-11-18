/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.util;

import java.lang.reflect.Field;
import java.text.Normalizer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public static <T> T RemoveAcentoFromT(T instance){
        Field[] listField = instance.getClass().getDeclaredFields();
        for(Field _field : listField){
            if(_field.getGenericType() == String.class)
            {
                _field.setAccessible(true);
                try {
                    String value = _field.get(instance).toString();
                    value = RemoveAcento(value);
                    _field.set(instance, value);
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(StringUtil.class.getName()).log(Level.SEVERE, "On field : " + _field.getName(), ex);
                }
                _field.setAccessible(false);
            }
        }
        return instance;
    }   
}
