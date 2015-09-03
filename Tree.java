//Vaishakhi Kulkarni
//vpk140230

//package SkipList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

//To check the TreeMap runtime
public class Tree {
	public static void main(String[] args) {
		Scanner sc = null;
		String operation = "";
		long operand = 0;
		int modValue = 997;
		long result = 0;

		if (args.length > 0) {
			File file = new File(args[0]);
			try {
				sc = new Scanner(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			sc = new Scanner(System.in);
		}

		// Initialize the tree set.
		TreeSet<Long> tree = new TreeSet<Long>();
		long startTime = System.currentTimeMillis();
		// Read the file entries . Operation <operand>
		while (!((operation = sc.next()).equals("End"))) {
			switch (operation) {
			case "Insert":
			case "Add":
				operand = sc.nextLong();
				tree.add(operand);
				result = (result + 1) % modValue;
				break;
			case "Find":
			case "Contains":
				operand = sc.nextLong();
				if (tree.contains(operand)) {
					result = (result + 1) % modValue;
				}
				break;
			case "Delete":
			case "Remove":
				operand = sc.nextLong();
				if (tree.remove(operand)) {
					result = (result + 1) % modValue;
				}
				break;
			}
		}
	
		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;
		
		System.out.println("Result" + result + "Time: " + elapsedTime);

	}
}
