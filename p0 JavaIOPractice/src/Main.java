//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Course:          COMP SCI 400 SPRING 2020
// Author:          Yucheng Qiu
// Email:           yqiu56@wisc.edu
// Lecture Number:  001
// Description:     
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * This class implements a Tic-Tac-Toe Game.
 * @author pc
 * @version 1.0
 */
public class Main {
	
	private static final Scanner KeyboardScanner = new Scanner(System.in);
	private static Scanner FileScanner;
	private static final String title = "Yucheng Qiu, yqiu56@wisc.edu, LEC001";
	static String[][] gameboard = new String[3][3];
	
	/**
	 * Implements a Tic-Tac-Toe Game.
	 * @param args
	 */
	public static void main(String[] args) {
		
		// UI Menu
		System.out.println("Welcome to YC's Tic-Tac-Toe Game!");
		System.out.println("Author information" + title);
		System.out.println("Please Choose Your opponent:");
		System.out.println("'1' AI, '2' YC");
		
		String Oppo = KeyboardScanner.next(); // A string represents opponent's code
		
		while (!Oppo.equals("1") && !Oppo.equals("2")){
			// Check if input is invalid
			System.out.println("Wrong input. Please input again.");
			Oppo = KeyboardScanner.next();
		} 
		
		switch (Oppo) {
		case "1":
			System.out.println("You choose AI as your opponent.");
			break;
		case "2":
			System.out.println("You choose YC as your opponent.");
		default:
			break;
		}
		System.out.println("Your AI Opponent 'YC' is ready");
		
		System.out.println("You received a cheating card, it makes you go first!");
		GamePrinter(); // Print an empty game board
		int x = 0, y = 0; // Position of move
		boolean TruePosition = false; // If inputting position is valid
		while (!TruePosition) {
			// Ask for the move position
			System.out.println("Please enter the position you would like to move in:");
			System.out.println("Input format: two integers between 1 and 3, separated by space");
			// Ask for x
			if(KeyboardScanner.hasNextInt()) x = KeyboardScanner.nextInt();
			else {
				// Re-input
				String string = KeyboardScanner.next();
				System.out.println("Invalid input!");
				continue;
			}
			// Ask for y
			if(KeyboardScanner.hasNextInt()) y = KeyboardScanner.nextInt();
			else {
				String string = KeyboardScanner.next();
				System.out.println("Invalid input!");
				continue;
			}
			
			// If valid input
			if(!(x > 3 || x < 1 ||y > 3 || y < 1)) TruePosition = true;
			else System.out.println("Invalid input!");
		}
		
		System.out.println("Move to "+ x +", " + y);
		GamePrinter(x, y, "O");// Print the game board after.
		// Opponent's move
		System.out.println("Your opponent's turn:");
		System.out.println("He received a cheating card, can have 7 extra free turns!");
		FinalGamePrinter();
		System.out.println("You Lose.");
		// Menu after game over
		System.out.println("Input '1' for upload complaints from a file,"
			+ " '2' for download game information into a file.");
		int EndService = 0;
		//Check if EndService is input correctly
		while(EndService < 1 || EndService > 2) {
			if(KeyboardScanner.hasNextInt()) EndService = KeyboardScanner.nextInt();
			else {
				String string = KeyboardScanner.next();
				System.out.println("Invalid input!");
				continue;
			}
			if(EndService == 1 || EndService == 2) break;
			else System.out.println("Invalid input!");
		}
		if(EndService == 1){
			// If the user wants Fileinput
			System.out.println("Input the name of the file:");
			String filename = KeyboardScanner.next();
			File inputFile = new File(filename);
			try {
				FileScanner = new Scanner(inputFile);
			} catch (FileNotFoundException e) {
				// If file is not found
				e.printStackTrace();
			}
			System.out.println("Your suggestions successfully loaded:");
			while(FileScanner.hasNextLine()) {
				String s = FileScanner.nextLine();
				System.out.println(s);
			}
			FileScanner.close();
			System.out.println("Your suggestion is uploaded. Thanks for your participation.");
		}
		else if(EndService == 2) {
			// If the user wants file output
			System.out.println("You choose 2, download game information into a file");
			try {
				// Try to open the file 'output.txt'
				PrintWriter myFileWriter = new PrintWriter("output.txt");
				myFileWriter.print("This game is designed by YC, CS student @U-Madison");
				myFileWriter.close();
			}
			catch (FileNotFoundException e) {e.printStackTrace();}
			System.out.println("File output complete. Please check your output.txt");
		}
		
		
	}
	
	/**
	 * A printer that prints an empty game board.
	 */
	private static void GamePrinter() {
		String gameboard_void = "_|_|_";
		for(int i = 0; i<3; i++) System.out.println(gameboard_void);
	}
	
	/**
	 * A printer that helps print the game board after the user moves.
	 * @param x x-position
	 * @param y y-position
	 * @param Sign the character to represent user's move
	 */
	private static void GamePrinter(int x, int y,String Sign) {
		//Generate an empty game board
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				gameboard[i][j] = "_";
			}
		}
		
		gameboard[x-1][y-1] = Sign;
		
		//Print the renewed game board
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<3; j++) {
				System.out.print(gameboard[i][j]);
				if( j == 2) System.out.println();
				else {
					System.out.print("|");
				}
			}
		}
	}
	
	/**
	 * Prints the final game board.
	 */
	private static void FinalGamePrinter(){
		// YC's move
		for(int i=0;i<3;i++) {
			for(int j=0; j<3;j++) {
				if(gameboard[i][j].equals("_")) gameboard[i][j] = "X";
			}
		}
		
		// Prints the board.
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<3; j++) {
				System.out.print(gameboard[i][j]);
				if( j == 2) System.out.println();
				else {
					System.out.print("|");
				}
			}
		}
	}
	
	
}
