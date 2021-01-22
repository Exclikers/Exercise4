package com.advancejava;

import java.util.Scanner;


public class UserInput {

	static int row = 0;	
	static int col = 0;

	public static void getDimension() {

				Scanner getInput = new Scanner(System.in);
		  		boolean isNumber;	
    			int inrow = 0;
    			int incol = 0;
    			
				do {
			    		System.out.print("Enter Number of Row: ");
				    	if (getInput.hasNextInt()) {
				    		row = getInput.nextInt();
				    		inrow = row;
				    		isNumber = true;
				    	} else {
				    		System.out.println("Please Input a valid number!");
				    		isNumber = false;
				    		getInput.next();
				    	}

				    	if ((inrow > 1000) || (inrow < 1)) {
					    	System.out.println("Not allowed! Please Select from 1 - 1000 \n");
					    	isNumber = false;
				    	}
		    	} while (!(isNumber));

			    do {
				    	System.out.print("Enter Number of Column: ");
				    	if (getInput.hasNextInt()) {
				    		col = getInput.nextInt();
				    		incol = col;
				    		isNumber = true;
				    	} else {
				    		System.out.println("Please Input a valid number!");
				    		isNumber = false;
				    		getInput.next();
				    	}
				    	if ((incol > 1000) || (incol < 1)) {
					    	System.out.println("Not allowed! Please Select from 1 - 1000 \n");
					    	isNumber = false;
				   		}
			    } while (!(isNumber));
	}

}