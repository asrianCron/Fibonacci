package com.fib;

import java.math.BigDecimal;

public class Main {

	public static void main(String[] args) {
		boolean printDuration = false;
		BigDecimal result;
		double startTime = System.nanoTime();
		double duration = 0;

		switch (args.length) {
		case 0:
			System.out.println("First paramater should be the n'th number you want, second parameter is the method used(rec, for, threc), the third parameter is if you wish for the duration to be printed as well");
			break;
		case 1:
			result = getFibFor(Integer.parseInt(args[0]));
			System.out.printf("%.0f", result);
			break;
		case 2:
			if(args[1].equalsIgnoreCase("for")){
				result = getFibFor(Integer.parseInt(args[0]));
				System.out.printf("%.0f", result);
			} else if(args[1].equalsIgnoreCase("rec")){
				result = getFibRec(Integer.parseInt(args[0]));
				System.out.printf("%.0f", result);
			}
			break;
		case 3:
			printDuration = true;
			if(args[1].equalsIgnoreCase("for")){
				result = getFibFor(Integer.parseInt(args[0]));
				System.out.printf("%.0f", result);
			} else if(args[1].equalsIgnoreCase("rec")){
				result = getFibRec(Integer.parseInt(args[0]));
				System.out.printf("%.0f", result);
			} else if(args[1].equalsIgnoreCase("threc")){
				result = getFibRecThread(Integer.parseInt(args[0]));
				System.out.printf("%.0f", result);
			}
			duration = System.nanoTime() - startTime;
			break;
		}
		
		if(printDuration){
			System.out.println(" in " + duration/1000000 + " miliseconds ");
		}
		
	}

	/**
	 * Returns n'th Fibonacci number, using recursion
	 * 
	 * @param n
	 * @return
	 */
	public static BigDecimal getFibRec(int n) {

		if (n <= 0)
			return new BigDecimal("0.0");
		if (n == 1)
			return new BigDecimal("1.0");
		return getFibRec(n - 1).add(getFibRec(n - 2));

	}
	
	public static BigDecimal getFibRecThread(int n) {
		
//		FibThread first = new FibThread(n - 1);
//		FibThread second = new FibThread(n - 2);
//		first.start();
//		second.start();
//		try {
//			first.join();
//			second.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		return first.result.add(second.result);
		FibThread worker = new FibThread(n);
		worker.start();
		try {
			worker.join();
		} catch (InterruptedException ex){
			ex.printStackTrace();
		}
		return worker.result;
	}

	/**
	 * Returns n'th Fibonacci number, using a for loop
	 * 
	 * @param n
	 * @return
	 */
	public static BigDecimal getFibFor(int n) {
		if (n == 0)
			return new BigDecimal("0.0");
		BigDecimal current = new BigDecimal("1.0");
		BigDecimal past = new BigDecimal("0.0");
		BigDecimal aux = new BigDecimal("0.0");
		for (int i = 1; i < n; i++) {
			aux = current;
			current = current.add(past);
			past = aux;
		}
		return current;

	}
}
