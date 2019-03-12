/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.scai.corsojava.es2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.logging.Logger;

/**
 *
 * @author denismaggiorotto
 */
public class ForkJoinUppercaseTask extends RecursiveTask<String> {

    private String workload;
    private int threshold;
    
    public ForkJoinUppercaseTask(String workload, int threshold) {
    	this.workload = workload;
    	this.threshold = threshold;
    }

    @Override
    protected String compute() {
       	if (workload.length() < threshold) {
    		return workload.toUpperCase();
    	}
    	else {
    		
    		int midway = workload.length()/2;
    		String partOne = workload.substring(0, midway);
    		String partTwo = workload.substring(midway, workload.length());
    		
    		ForkJoinUppercaseTask fj1 = new ForkJoinUppercaseTask(partOne, threshold);
    		ForkJoinUppercaseTask fj2 = new ForkJoinUppercaseTask(partTwo, threshold);
    		fj1.fork();
    		
    		return fj2.compute() + fj1.join();
    	}
    }


}
