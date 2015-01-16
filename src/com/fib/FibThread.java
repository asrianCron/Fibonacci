package com.fib;

import java.math.BigDecimal;

public class FibThread extends Thread {
	int n;
	BigDecimal result;
	
	public FibThread(int n){
		this.n = n;
	}
	
	@Override
	public void run() {
		result = getFibRec(n);		
	}
	
	public BigDecimal getFibRec(int n) {
		//all of this is fucked up
//		if (n <= 0)
//			return new BigDecimal("0.0");
//		if (n == 1)
//			return new BigDecimal("1.0");
//		return getFibRec(n - 1).add(getFibRec(n - 2));
		
		if (n <= 0)
			return new BigDecimal("0.0");
		if (n == 1)
			return new BigDecimal("1.0");
		
		FibThread first = new FibThread(n - 1);
		FibThread second = new FibThread(n - 2);
		first.start();
		second.start();
		try {
			first.join();
			second.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return first.result.add(second.result);
	}
	
}
