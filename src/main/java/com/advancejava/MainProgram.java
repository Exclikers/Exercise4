package com.advancejava;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Random;

import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.map.LinkedMap;

public class MainProgram {

	static String[] storeDimension = {"0", "0"};
	static OrderedMap<String, String> cellData = new LinkedMap<String, String>();
	
	public static void main(String[] args) {
		
		
		progStart();


	}

	static void progStart() {
				
			int fileFound = readFile();

			if (fileFound == 1) {
				createFile();
			}
	
	}

	static String generateASCII() {

			String randomString;
			String ascii;
			String alphabet; 		
  			StringBuilder as = new StringBuilder();

  			for (int c = 33 ; c <= 126 ; c++) {
					ascii = String.format("%c",c);
					as.append(ascii);
			}
			
  			alphabet = as.toString();
			StringBuilder sb = new StringBuilder();
  			Random random = new Random();

		  	for (int i = 0 ; i < 3 ; i++) {
		  		int index = random.nextInt(alphabet.length());
		  		char randomChar = alphabet.charAt(index);
		  		sb.append(randomChar);
		  	}
		  	randomString = sb.toString();
	  		return randomString;	  		  
	}

	static int readFile() {

			int fileFound = 0;
			Scanner openFile = null;
			Scanner openData = null;

			try {
				openFile = new Scanner(new File("dimension.txt"));
				openData = new Scanner(new File("data.txt"));
				int dim = 0;

				while (openFile.hasNextLine()) {
						String line = openFile.nextLine();
										
						storeDimension[dim] = line;
						dim = dim + 1;
				}
				
				openFile.close();
				System.out.println("\nData Files Successfully Loaded\n");

				while (openData.hasNextLine()) {
					String key = openData.nextLine();
					String value = openData.nextLine();			
					cellData.put(key, value);
				}
				
				openData.close();
				MenuOptions.menuPrint();
				MenuOptions.mainMenu();

			} catch (FileNotFoundException e) {
				UserInput userIn = new UserInput();
				fileFound = 1;
				System.out.println("File not found");
				System.out.println("\nData will be Created, Please Enter Table Dimension");
				userIn.getDimension();
			}

			return fileFound;
	}

	static void createFile() {
			UserInput userIn = new UserInput();
			try {
				FileWriter saveFile = new FileWriter(new File("data.txt"));
				FileWriter saveDimension = new FileWriter(new File("dimension.txt"));

				for(int index1 = 1 ; index1 <= (userIn.row * 2) ; index1++) {
				  		for(int index2 = 1 ; index2 <= (userIn.col) ; index2++) {
							saveFile.write(generateASCII()+"\n");
						}
				}			
				saveDimension.write(userIn.row+"\n");
				saveDimension.write(userIn.col+"\n");
				saveDimension.close();
				saveFile.close();
				System.out.println("\n");
				System.out.println("You Successfully created a table");
				progStart();

			} catch (IOException e) {
				System.out.println("Unable to open file for writing");
			}
	}

}