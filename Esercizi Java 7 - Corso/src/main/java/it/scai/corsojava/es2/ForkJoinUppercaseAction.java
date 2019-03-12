package it.scai.corsojava.es2;

import java.util.concurrent.RecursiveAction;


public class ForkJoinUppercaseAction extends RecursiveAction {
	private static final long serialVersionUID = 1L;
	private String workLoad;
	private int threshold;
	
	public ForkJoinUppercaseAction(String workload, int threshold) {
		this.workLoad = workload;
		this.threshold = threshold;
	}
	
    @Override 
    protected void compute() {
    	if (workLoad.length() < threshold) {
    		String result = workLoad.toUpperCase();
    		System.out.println("Result: "+result);
    	}
    	else {
    		
    		int midway = workLoad.length()/2;
    		String partOne = workLoad.substring(0, midway);
    		String partTwo = workLoad.substring(midway, workLoad.length());
    		
    		ForkJoinUppercaseAction fj1 = new ForkJoinUppercaseAction(partOne, threshold);
    		fj1.fork();
    		
    		ForkJoinUppercaseAction fj2 = new ForkJoinUppercaseAction(partTwo, threshold);
    		fj2.compute();
    		
    		fj1.join();
    	}
    }
 
    
}
