package com.advancejava;

import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map.Entry;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import org.apache.commons.collections4.list.TreeList;

public class MenuOptions {

	static void mainMenu() {

				Scanner getInput = new Scanner(System.in);
			  	boolean isNumber;
			  	int loop = 1 ;
			  	while (loop > 0) {

					  	System.out.println("\n");
					  	System.out.println("What do you want to do?");
					  	System.out.println("1. Search");
					  	System.out.println("2. Edit");
					  	System.out.println("3. Print");
					  	System.out.println("4. Add New Row");
					  	System.out.println("5. Sort");
					  	System.out.println("6. Reset");
					  	System.out.println("7. Exit");
						System.out.print("\n");
					  	
						int select = 0;

					  	do {
					    	System.out.print("Please Select an Option: ");
					    	if (getInput.hasNextInt()) {
					    		select = getInput.nextInt();
					    		isNumber = true;
					    	} else {
					    		System.out.println("Please Input a valid number!");
					    		isNumber = false;
					    		getInput.next();
					    	}
					    } while (!(isNumber));

					    System.out.print("\n");

					    if (select == 1) {
					    	menuSearch();
					    }

					    if (select == 2) {
					    	menuEdit();
					    }

					  	if (select == 3) {
					    	menuPrint();
					    }

					    if (select == 4) {
					    	addRow();
					    }

						if (select == 5) {
					    	toSort();
					    }

					    if (select == 6) {
					    	menuReset();
					    }

					    if (select == 7) {
					    	menuExit();
					    }

					    if (select > 7){
					    	System.out.print("Please Select only from 1 - 7");
					    }
			 	}  	
	}

	static void menuSearch() {

			Scanner getInput = new Scanner(System.in);
			System.out.print("Search: ");
			String value = getInput.next();
			boolean flagKey = MainProgram.cellData.containsKey(value);
			boolean flagValue = MainProgram.cellData.containsValue(value);

			if (flagKey == true) {
				System.out.println("The Key Exist on our Table with the value of : " + MainProgram.cellData.get(value));
			} 
			if (flagValue == true) {
				System.out.println("The Value Exist on our Table Please Search the Key Instead");
			} 
			if (flagKey == false && flagValue == false) {
				System.out.println("The Key of Value Does Not Exist on our table!");
			}
	}

	static void menuEdit() {

			Scanner getInput = new Scanner(System.in);
			System.out.print("Input a Key to Edit Value : ");
			String inKey = getInput.next();

			if (MainProgram.cellData.containsKey(inKey) == false) {
				System.out.println("Key Not Found!");
				mainMenu();
			}

			System.out.print("Enter New Value : ");
			String newValue = getInput.next();

			int size = newValue.length();
			if (size <= 3) {
				MainProgram.cellData.replace(inKey, newValue);
				System.out.println("\nValue Successfuly Updated\n");
			} else {
				System.out.println("Please enter max of 3 characters");
				menuEdit();
			}
			updateData();
			menuPrint();
			mainMenu();
	}

	static void menuPrint() {
			int col = Integer.parseInt(MainProgram.storeDimension[1]);
			int counter = 0;
			Scanner openFile = null;

  			try {
				openFile = new Scanner(new File("data.txt"));

  				while (openFile.hasNextLine()) {
					String line = openFile.nextLine();
					System.out.print(line + " : " + openFile.nextLine() + "\t");	
					counter = counter + 1;

					if (counter >= col) {
						System.out.print("\n");
						counter = 0;
					}
				}
			} catch (FileNotFoundException e) {
				System.out.println("Unable to open file");
			}
			openFile.close();	
	}

	static void addRow(){

			Scanner getInput = new Scanner(System.in);
			MainProgram randomASCII = new MainProgram();
	    	int col = Integer.parseInt(MainProgram.storeDimension[1]);
	    	int updateRow;
			boolean isNumber;
	  		System.out.print("Please Enter Additinal Row: ");
	    	if (getInput.hasNextInt()) {
	    		updateRow = getInput.nextInt();
	    		isNumber = true;

	    		if (updateRow > 1000){
			    	System.out.print("Max of 1000 Rows");
			    	mainMenu();
			    }

	    		for(int index1 = 1 ; index1 <= updateRow ; index1++) {
			  		for(int index2 = 1 ; index2 <= col ; index2++) {
			  			MainProgram.generateASCII();
						String key = randomASCII.generateASCII();
						MainProgram.generateASCII();
						String value = randomASCII.generateASCII();
		    			MainProgram.cellData.put(key, value);
					}
				}	

				System.out.println("\nRow Successfuly Added!\n");		
    			updateData();
    			updateDimension(updateRow);
    			menuPrint();
    				
	    	} else {
	    		System.out.println("Please Input a valid number!");
	    		isNumber = false;
	    		getInput.next();
	    	}
	}

	static void deleteData(String filename) {
			File myFile = new File(filename);
			myFile.delete();
	}

	static void updateData() {
			
			deleteData("data.txt");

	    	try {

	    		FileWriter saveFile = new FileWriter(new File("data.txt"));

		    	for (String i : MainProgram.cellData.keySet()) {
		    		saveFile.write(i+"\n");
		    		saveFile.write(MainProgram.cellData.get(i)+"\n");
		    	}

	    		saveFile.close();
	    	} catch (IOException e) {
				System.out.println("Unable to open file for writing");
			}
	}

	static void updateDimension(int updateRow) {

			deleteData("dimension.txt");

	    	try {
	    		FileWriter saveFile = new FileWriter(new File("dimension.txt"));
	    		int newRow = Integer.parseInt(MainProgram.storeDimension[0]);
	    		newRow = newRow + updateRow;

	    		saveFile.write(newRow+"\n");
	    		saveFile.write(MainProgram.storeDimension[1]+"\n");
		    	
	    		saveFile.close();

	    	} catch (IOException e) {
				System.out.println("Unable to open file for writing");
			}

	}

	static void toSort() {

        	TreeList<Entry<String, String>> foundListEntry = new TreeList<Entry<String, String>>(MainProgram.cellData.entrySet());

        	Collections.sort(foundListEntry, new Comparator<Entry<String, String>>() {
        		  	public int compare(Entry<String, String> es1, Entry<String, String> es2) {
	                	return es1.getKey().compareTo(es2.getKey());
            		}
      		});

        	MainProgram.cellData.clear();

	        for(Entry<String, String> map : foundListEntry) {
	            MainProgram.cellData.put(map.getKey(), map.getValue());
	        }

	        System.out.println("\nThe table has been Successfuly Sorted!\n");

        	updateData();
        	menuPrint();
	}

	static void menuReset() {
			deleteData("data.txt");
			deleteData("dimension.txt");
			MainProgram.cellData.clear();
			System.out.println("Successfuly Resetted");
	    	MainProgram.progStart();
	}

	static void menuExit() {
			System.out.println(MainProgram.cellData.entrySet());
	  		/*System.out.println("Thank you for using our Software!");
	    	System.exit(0);*/
	}
}