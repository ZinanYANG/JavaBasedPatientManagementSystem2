/**
 * 
 */
package edu.depaul;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author RicSalvator
 *
 */
public class MainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PatientManager patientManager = new PatientManager();
		int number = 0;
		// Create a Scanner object
		Scanner scanner = new Scanner(System.in);

		while (true) {
			patientManager.displayMenu();
			try {
				System.out.print("Please enter a number from 1 to 6 for display: ");
				number = scanner.nextInt();
				scanner.nextLine();

				if (number == 6)
					break; // Exit
				
//				read output before continuing
				executeMenuOption(number, patientManager, scanner);
				pauseForUser();
                

			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a number.");
				scanner.nextLine(); 
			}
		}

		System.out.println("Exit.");
		scanner.close();
	}
	
	private static void executeMenuOption(int number, PatientManager patientManager, Scanner scanner) {
		
		switch (number) {
            case 1 -> patientManager.admitPatient(scanner);
            case 2 -> patientManager.printPatientInfo();
            case 3 -> patientManager.submitPCRResult(scanner);
            case 4 -> patientManager.doRounds(scanner);
            case 5 -> patientManager.dischargePatients();
            default -> System.out.println("Invalid choice. Please choose a number between 1 and 6.");
		}
    }
	
	private static void pauseForUser() {
        System.out.println("Press Enter key to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }
}
