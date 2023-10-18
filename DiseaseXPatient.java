package edu.depaul;

public class DiseaseXPatient extends Patient implements ITreatable {

	private static final double CriticalTemperature = 40.0;
	private static final double elderlyAge = 60;
	private static final double feverTemperature = 37.5;
	private static final String MedicineAntibody = "antibody";
	private static final String MedicineHeparin = "heparin";
	private static final String MedicineRegular = "Fluid";

	private double temperature;
	private boolean isPregnant;
	private boolean pcr;

	public DiseaseXPatient(int id, String fName, String lName, int age, double temperature, boolean isPregnant) {
		super(id, fName, lName, age);
		this.isPregnant = isPregnant;
		this.temperature = temperature;
		this.pcr = pcr;
	}

	/**
	 * @return the isPregnant
	 */
	public boolean isPregnant() {
		return isPregnant;
	}

	/**
	 * @param isPregnant the isPregnant to set
	 */
	public void setPregnant(boolean isPregnant) {
		this.isPregnant = isPregnant;
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

		if (!this.isPregnant) {
			return MedicineHeparin;
		}
		if (temperature >= CriticalTemperature) {
			return MedicineAntibody;
		}

		if (getAge() >= elderlyAge && temperature >= feverTemperature) {
			return MedicineRegular;
		}
		return MedicineRegular;
	}

	/**
	 * @return the pcr
	 */
	public boolean isPcr() {
		return pcr;
	}

	/**
	 * @param pcr the pcr to set
	 */
	public void setPcr(boolean pcr) {
		this.pcr = pcr;
	}

	@Override
	public String toString() {
		return " DiseaseXPatient  \n" + " ID: " + getId() + " \n" + " Name: " + getfName() + " " + getlName() + "\n "
				+ "Age: " + getAge() + "\n " + "Temperature: " + getTemperature() + "\n " + "Is Pregnant: "
				+ isPregnant() + " \n";
	}

}
