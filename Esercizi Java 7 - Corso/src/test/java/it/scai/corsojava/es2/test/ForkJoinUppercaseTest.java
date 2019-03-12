/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.scai.corsojava.es2.test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import it.scai.corsojava.es2.ForkJoinUppercaseAction;
import it.scai.corsojava.es2.ForkJoinUppercaseTask;

/**
 *
 * @author denismaggiorotto
 */
public class ForkJoinUppercaseTest {

    private final int THRESHOLD = 100;
    private final int CHAR_ARRAY_COUNT = 4096;

    private char[] fillArray(char[] charArray) {
        Random rnd = new Random();
        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = (char) (rnd.nextInt(26) + 'a');
        }
        return charArray;
    }

    @Test
    public void testForkJoinUppercaseAction() {

        char[] charArray = new char[CHAR_ARRAY_COUNT];
        charArray = fillArray(charArray);

        String workload = new String(charArray);
        
        

        ForkJoinUppercaseAction forkJoinUppercase = new ForkJoinUppercaseAction(workload, THRESHOLD);
        
        ForkJoinPool fjp = new ForkJoinPool();
        fjp.execute(forkJoinUppercase);
        
        
        //forkJoinUppercase.invoke();

        //System.out.println(workload);
    }

    @Test
    public void testForkJoinUppercaseTask() {

        char[] charArray = new char[CHAR_ARRAY_COUNT];
        charArray = fillArray(charArray);

        String workload = new String(charArray);

        ForkJoinUppercaseTask forkJoinUppercase = new ForkJoinUppercaseTask(workload, THRESHOLD);
        //String result = forkJoinUppercase.invoke();
        
        ForkJoinPool fjp = new ForkJoinPool();
        String result = fjp.invoke(forkJoinUppercase);

        System.out.println(sort(workload));
        System.out.println(sort(result));


        assertTrue(workload.length() == result.length());
        assertEquals("",sort(workload),sort(result));
    }
    
    
    private static String sort(String text) {
        char[] chars = prepare(text).toCharArray();
 
        Arrays.sort(chars);
        return new String(chars);
    }
    
    private static String prepare(String text) {
        return text.toLowerCase()
          .trim()
          .replaceAll("\\s+", "");
    }

}
