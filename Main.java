import java.util.Scanner;

public class Main {
	static Scanner input = new Scanner(System.in);
	static String[][] ocean = new String[10][10];
	static int userShipLeft, computerShipLeft;
	final static int MAX_NO_OF_SHIPS = 5;
	
	public static void main(String[] args) {
		
		// Create the ocean map using a single 2D array
		System.out.println("****Welcome to Battle Ships game****");
		
		System.out.println("\n  Right now, the sea is empty.");
		
		// This shows the ocean empty.
		printOceanB4GameBegin();
		
		// Deploy user ships.
		deployUserShip();
		
		// This shows the ocean after the first user has deployed it ship.
		printOceanB4GameBegin();
		
		// Deploy computer ships
		deployComputerShip();
		
		// Commend game
		playersTurn();
		
		/*
		 *  The following prints the current state and the scores
		 *  of the game when either of the players win.
		 */
		printCurrentOcean();
		if (userShipLeft == 0) {
			 System.out.println("\nYour ships: " + userShipLeft + " | Computer ships: " + computerShipLeft);
			 System.out.println("The computer wins");
		 } 
		 
		 if (computerShipLeft == 0) {
			 System.out.println("\nYour ships: " + userShipLeft + " | Computer ships: " + computerShipLeft);
			 System.out.println("Hooray! You win the battle.");
		 }
		
	}

	/**
	 *  This method prints the state of the ocean at any time.
	*/
	private static void printOceanB4GameBegin() {
		System.out.println("\n  0123456789  ");
		
		for (int row = 0; row < ocean.length; row++) {
			System.out.print(row + "|");
			for (int column = 0; column < ocean[0].length; column++) {
				if (ocean[row][column] == null) {
					System.out.print(" ");
				} else {
					System.out.print("@");
				}
			}
			
			System.out.println("|" + row);
		}
		System.out.println("  0123456789  \n");
	}
	
	/**
	 * THIS WILL DEPLOY THE USER SHIP
	 * @param input
	 */
	private static void deployUserShip() {
		System.out.println("Deploy your ships:");
		for (int shipCount = 1; shipCount <= 5; shipCount++) {
			boolean check = false;
			do {
				System.out.print("Enter X coordinate for ship " + shipCount + ": ");
				int x = input.nextInt();
				System.out.print("Enter Y coordinate for ship " + shipCount + ": ");
				int y = input.nextInt();
				
				if (x > 9 || y > 9) {
					System.out.println("Invalid coordinate! Please try again.");
					check = false;
				} else {
					check = checkCoordinate(x, y);
					if (check) {
						ocean[x][y] = "1";
						continue;
					}
				}
			} while (check == false);
		}
		
		userShipLeft = MAX_NO_OF_SHIPS;
	}
	
	/**
	 * THIS WILL DEPLOY THE COMPUTER SHIP
	 */
	private static void deployComputerShip() {
		System.out.println("Computer is deploying ships");
		for (int shipCount = 1; shipCount <= 5; shipCount++) {
			boolean check = false;
			do {
				int x = (int) (Math.random() * 10);
				int y = (int) (Math.random() * 10);
				check = checkCoordinate(x, y);
				if (check) {
					ocean[x][y] = "2";
					System.out.println(shipCount + ". ship DEPLOYED");
					continue;
				}
			} while (check == false);	
		}
		
		computerShipLeft = MAX_NO_OF_SHIPS;
		System.out.println("");
	}

	// THIS METHOD CHECKS TO SEE IF THE POSITION IS EMPTY.
	private static boolean checkCoordinate(int x, int y) {
		if (ocean[x][y] == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void playersTurn() {
		 do {
			 userTurn();
			 ComputerTurn();
		 } while (userShipLeft > 0 && computerShipLeft > 0);
	}

	private static void userTurn() {
		System.out.println("YOUR TURN");
		System.out.print("Enter X coordinate: ");
		int x = input.nextInt();
		System.out.print("Enter y coordinate: ");
		int y = input.nextInt();
		
		if (x > 9 || y > 9) {
			System.out.println("Invalid Coordinate! Try again");
			userTurn();
		}
		
		if (ocean[x][y] == null) {
			ocean[x][y] = "-";
			System.out.println("Sorry, you missed!");
			printCurrentOcean();
		} else if (ocean[x][y] == "1") {
			ocean[x][y] = "x";
			userShipLeft--;
			System.out.println("Oh no, you sunk your own ship :(");
			printCurrentOcean();
		} else if (ocean[x][y] == "!") {
			System.out.println("Invalid Coordinate! Please try again.");
			userTurn();
		} else if (ocean[x][y] == "-") {
			System.out.println("Invalid Coordinate! Please try again.");
			userTurn();
		} else if (ocean[x][y] == "x") {
			System.out.println("Invalid Coordinate! Please try again.");
			userTurn();
		} else {
			ocean[x][y] = "!";
			computerShipLeft--;
			System.out.println("Boom! You sunk the ship!");
			printCurrentOcean();
		}
		System.out.println("");
	}

	private static void ComputerTurn() {
		System.out.println("COMPUTER'S TURN");
		int x = (int) (Math.random() * 10);
		int y = (int) (Math.random() * 10);
		if (ocean[x][y] == null) {
			ocean[x][y] = "-";
			System.out.println("Computer missed!");
		} else if (ocean[x][y] == "2") {
			ocean[x][y] = "x";
			System.out.println("The computer just sunk one of its own ship :(");
			computerShipLeft--;
		} else if (ocean[x][y] == "!") {
			ComputerTurn();
		} else if (ocean[x][y] == "-") {
			ComputerTurn();
		} else if (ocean[x][y] == "x") {
			ComputerTurn();
		} else {
			ocean[x][y] = "!";
			userShipLeft--;
			System.out.println("Boom! Computer sunk one of your ships!");
		}
		System.out.println("");
	}
	
	private static void printCurrentOcean() {
		System.out.println("\n  0123456789  ");
		
		for (int row = 0; row < ocean.length; row++) {
			System.out.print(row + "|");
			for (int col = 0; col < ocean[0].length; col++) {
				if (ocean[row][col] == null) {
					System.out.print(" ");
				} else if (ocean[row][col] == "1"){
					System.out.print("@");
				} else if (ocean[row][col] == "x") {
					System.out.print("x");
				} else if (ocean[row][col] == "-") {
					System.out.print(" ");
				} else if (ocean[row][col] == "!") {
					System.out.print(" ");
				}  else {
					System.out.print(" ");
				}
			}
			
			System.out.println("|" + row);
		}
		System.out.println("  0123456789  \n");
	}

}