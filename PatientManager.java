/**
 * 
 */
package edu.depaul;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * @author RicSalvator
 *
 */
public class PatientManager {
	private List<Patient> patients;

	Scanner scanner = new Scanner(System.in);

	public PatientManager() {
		this.patients = new ArrayList<>();
	}

	public void admitPatient(Scanner scanner2) {
//		logic to admit patient
		System.out.println("Admit a patient");

		double temperature;
		String diseaseType = getDiseaseType();
		;

		try {

			if (diseaseType.equalsIgnoreCase("DiseaseX")) {

				int id = getInput("Please enter your ID:", Integer.class);
				String fName = getInput("Please enter your first name:", String.class);
				String lName = getInput("Please enter your last name:", String.class);
				int age = getInput("Please enter your age:", Integer.class);

				temperature = getInput("Plz enter your temperature:", Double.class);
				String pregnantInput;
				while (true) {
					System.out.println("Are you pregnant? (y/n):");
					pregnantInput = scanner.nextLine().trim();
					if (pregnantInput.equalsIgnoreCase("y") || pregnantInput.equalsIgnoreCase("n")) {
						break;
					} else {
						System.out.println("Invalid input. Please try again.");
					}
				}

				boolean isPregnant = pregnantInput.equalsIgnoreCase("y");

				admitDiseaseXPatient(id, fName, lName, age, temperature, isPregnant);
				System.out.println("Successfully admitted a DiseaseX patient.");
			} else {
				String PCR;
				while (true) {
					System.out.println("plz tell me your PCR test result is negative or positive (y/n):");
					PCR = scanner.nextLine().trim();
					if (PCR.equalsIgnoreCase("y") || PCR.equalsIgnoreCase("n")) {
						break;
					} else {
						System.out.println("Invalid input. Please try again.");
					}
				}

				int id = getInput("Please enter your ID:", Integer.class);
				String fName = getInput("Please enter your first name:", String.class);
				String lName = getInput("Please enter your last name:", String.class);
				int age = getInput("Please enter your age:", Integer.class);

				if (PCR.equalsIgnoreCase("y")) {
					temperature = getInput("Please enter your temperature:", Double.class);
//				create and record a new patient
					admitCovid19Patient(id, fName, lName, age, temperature);
				} else {
					String mainSymptom = getInput("Please enter your main symptom:", String.class);
//				create and record a new patient 
					admitRegularPatient(id, fName, lName, age, mainSymptom);
				}

				System.out.println("successfully admitted.");
			}

		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Please enter the correct data type.");
			scanner.nextLine();
		}

	}

	private String getDiseaseType() {
		String diseaseType = "";
		while (true) {
			System.out.println("Please specify your disease type (COVID19/DiseaseX): ");
			diseaseType = scanner.nextLine().trim();
			if (diseaseType.equalsIgnoreCase("COVID19") || diseaseType.equalsIgnoreCase("DiseaseX")) {
				break;
			} else {
				System.out.println("Invalid input. Please try again.");
			}
		}

		return diseaseType;
	}

//	create and record covid patient 
	private void admitCovid19Patient(int id, String fName, String lName, int age, double temperature) {
		Covid19Patient positivePatient = new Covid19Patient(id, fName, lName, age, temperature);
		positivePatient.setPcr(true);
		patients.add(positivePatient);
	}

//	create and record regular patient 
	private void admitRegularPatient(int id, String fName, String lName, int age, String mainSymptom) {
		RegularPatient negativePatient = new RegularPatient(id, fName, lName, age, mainSymptom);
		negativePatient.setPcr(false);
		patients.add(negativePatient);
	}

//	create and record X patient 
	private void admitDiseaseXPatient(int id, String fName, String lName, int age, double temperature,
			boolean isPregnant) {
		DiseaseXPatient diseaseXPatient = new DiseaseXPatient(id, fName, lName, age, temperature, isPregnant);
		diseaseXPatient.setPcr(true);
		patients.add(diseaseXPatient);
	}


	public void printPatientInfo() {
		System.out.println("Print patient information");
		for (Patient patient : patients) {

			System.out.println(patient.toString());
		}
	}

	public void submitPCRResult(Scanner scanner) {
		System.out.println("Submit a PCR test result");
//		get the patient id
		System.out.println("plz tell me ur id: \n");
		int id = scanner.nextInt();
		scanner.nextLine();
//		get the PCR result 
		System.out.println("plz tell me your PCR test result is negative or positive (n/y):");
		String pcrUpdate = scanner.nextLine();

		List<Patient> toRemove = new ArrayList<>();
		for (int i = 0; i < patients.size(); i++) {
			Patient patient = patients.get(i);
			if (patient.getId() == id) {
				if (pcrUpdate.equalsIgnoreCase("y")) {
					patient.setPcr(true);
					System.out.print("PCR test result updated successfully!\n");
					if (patient instanceof RegularPatient) {
						System.out.println("plz tell me ur temperature:\n");
						double temperature = scanner.nextDouble();
						scanner.nextLine();
						Covid19Patient RegularPatientUpdate = new Covid19Patient(patient.getId(), patient.getfName(),
								patient.getlName(), patient.getAge(), temperature);
						patients.set(i, RegularPatientUpdate);

					} else if (patient instanceof DiseaseXPatient) {
						System.out.println("plz tell me ur temperature:\n");
						double temperature = scanner.nextDouble();
						scanner.nextLine();
						DiseaseXPatient RegularPatientUpdate = new DiseaseXPatient(patient.getId(), patient.getfName(),
								patient.getlName(), patient.getAge(), temperature,
								((DiseaseXPatient) patient).isPregnant());
						patients.set(i, RegularPatientUpdate);
					}

				} else {
					patient.setPcr(false);
					System.out.print("PCR test result updated successfully!\n");
					if (patient instanceof Covid19Patient) {
						toRemove.add(patient);
						System.out.println("patients discharged .");
					} else if (patient instanceof DiseaseXPatient) {
						toRemove.add(patient);
						System.out.println("patients discharged .");
					}

				}
			}
		}

		patients.removeAll(toRemove);
	}

	public void doRounds(Scanner scanner) {
		for (Patient patient : patients) {
			if (patient instanceof Covid19Patient) {
				System.out.println("plz tell me ur temperature:\n");
				double temperature = scanner.nextDouble();
				scanner.nextLine();
				((Covid19Patient) patient).setTemperature(temperature);
				Covid19Patient cp = (Covid19Patient) patient;
				String treatment = cp.determineTreatment();
				System.out.println("patient:" + patient.getId() + "\n" + "treatment:" + treatment + "\n");
			}
		}
	}

	public void dischargePatients() {
		List<Patient> toDischarge = new ArrayList<>();
		for (Patient patient : patients) {
			if (patient.getPcr() == false) {
				toDischarge.add(patient);
				System.out.println(patient.getfName() + patient.getlName() + " " + "discharged" + "\n");
			}
		}
		patients.removeAll(toDischarge);
	}

	public void displayMenu() {
		System.out.println("1. Admit a patient\n");
		System.out.println("2. Print patient information\n");
		System.out.println(
				"3. Submit a PCR test result \n (y ==> positive n ==> negative)\n (true ==> positive false == negative) \n");
		System.out.println("4. Do rounds\n");
		System.out.println("5. Discharge patient\n");
		System.out.println("6. Exit\n");
	}

//	for user input error handling
	private <T> T getInput(String prompt, Class<T> type) {
		System.out.println(prompt);

		while (true) {
			try {
				if (type == Integer.class) {
					int input = scanner.nextInt();
					scanner.nextLine(); 
					return type.cast(input);
				} else if (type == Double.class) {
					double input = scanner.nextDouble();
					scanner.nextLine(); 
					return type.cast(input);
				} else if (type == String.class) {
					String input = scanner.nextLine();

					if (input == null || input.trim().isEmpty()) {
						throw new InputMismatchException("Input cannot be null or empty");
					}
					return type.cast(input);
				}

			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please try again.");
				scanner.nextLine(); 
			}
		}
	}

}
