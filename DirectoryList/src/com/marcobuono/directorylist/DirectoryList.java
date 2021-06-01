package com.marcobuono.directorylist;

import java.io.File;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * This program lists list all the files in a directory,
 * specified by the user,  and all its subdirectories, to
 * any level of nesting. The user is asked to type in a
 * directory name. If the name entered by the user is not
 * a directory, a message is printed and the program ends.
 *  
 * @author marcobuono
 * 
 */
public class DirectoryList {
	
	public static void main(String[] args) {
		String directoryName;  // Directory name entered by the user.
		File directory;        // File object referring to the directory.
		
		try {
			Scanner scanner;       // For reading a line of input from the user.
			scanner = new Scanner(System.in);  // scanner reads from standard input.
			
			System.out.print("Enter a directory name: ");
			directoryName = scanner.nextLine().trim();
			int slashLastPosition = directoryName.lastIndexOf("\\");
			String dir = directoryName.substring(0, slashLastPosition + 1);
			String fileName = directoryName.substring(slashLastPosition + 1);
			directory = new File(dir, fileName);
			scanDirectory(directory, 0);
			scanner.close();
		} catch (Exception e) {
			System.out.println("Sorry, an error occurred while "
					+ "to scan Directory:\n" + e);
		}
	} // end main()
	
	/**
    * The method scan directories recursively
    * and print them and their files.
    * @param directory to scan
    * @param level, the level deep of the tree
    */
	public static void scanDirectory(File directory, int level) {
		String[] files;        // Array of file names in the directory.
		String spaces = "|   "; // characters to create mast
		String dashes = "---"; // characters to create mast
		if (directory.isDirectory() == false) {
			if (directory.exists() == false)
				System.out.println("There is no such directory!");
			else
				System.out.println("That file is not a directory.");
		} else if (!directory.canRead()) {
			System.out.println("That directory cannot be read.");
		} else if(directory.list() != null){
			files = directory.list();
			System.out.println("|" + dashes + directory.getName());
			for(String item : files) {
				File file = new File(directory, item);
				if(file.isDirectory()) {
					System.out.print(spaces.repeat(level + 1));
					scanDirectory(file, level + 1);
				} else if(file.isFile()) {
					System.out.println(spaces.repeat(level + 1) + "|" + dashes + item);
				}
			}
		}
	}
} // end class DirectoryList