/**
 * 
 */
package edu.depaul;

/**
 * @author RicSalvator
 *
 */
public class Covid19Patient extends Patient implements ITreatable{
	private static final double CRITICAL_TEMPERATURE = 40.0;
	private static final double ELDERLY_AGE = 59;
	private static final double FEVER_TEMPERATURE = 36.5;
	private static final String MEDICINE_DEXAMETHASONE = "Dexamethasone";
    private static final String MEDICINE_PAXLOVID = "Paxlovid";
    private static final String MEDICINE_STANDARD = "fluids and Acetaminophen";
	private double temperature;

	public Covid19Patient(int id, String fName, String lName, int age, double temperature) {
		super(id, fName, lName, age);
		this.temperature = temperature;

	}

	/**
	 * @return the temperature
	 */
	public double getTemperature() {
		return temperature;
	}

	/**
	 * @param temperature the temperature to set
	 */
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}



	@Override
	public String determineTreatment() {
		if (temperature >= CRITICAL_TEMPERATURE) {
			return MEDICINE_DEXAMETHASONE;
		}

		if (getAge() >= ELDERLY_AGE && temperature >= FEVER_TEMPERATURE) {
			return  MEDICINE_PAXLOVID;
		}
		return MEDICINE_STANDARD;
	}

}
