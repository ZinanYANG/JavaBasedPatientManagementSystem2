/**
 * 
 */
package edu.depaul;

import java.util.Scanner;

/**
 * @author RicSalvator
 *
 */
public class RegularPatient extends Patient implements ITreatable{

	private String mainSymptom;
	public RegularPatient(int id, String fName, String lName, int age, String mainSymptom) {
		super(id, fName, lName, age);
		this.mainSymptom = mainSymptom;
		
	}
	



	@Override
	public String determineTreatment() {
		String res;
		if (mainSymptom.equalsIgnoreCase("coughing") || mainSymptom.equalsIgnoreCase("runny nose")
				|| mainSymptom.equalsIgnoreCase("stuffy nose")) {
			res = "Amoxicillin";
		} else if (mainSymptom.equalsIgnoreCase("hypertension")) {
			res = "ACE inhibitors";
		} else {
			res = "IV fluids";
		}
		return res;
	}

}
