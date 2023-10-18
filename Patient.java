/**
 * 
 */
package edu.depaul;

/**
 * @author RicSalvator
 *
 */
public abstract class Patient {
	private int id;
	private String fName;
	private String lName;
	private int age;
	private boolean pcr;
	
	public Patient(int id, String fName, String lName, int age ) {
		super();
		this.id = id;
		setfName(fName);
		setlName(lName);
		setAge(age);
		
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the fName
	 */
	public String getfName() {
		return fName;
	}

	/**
	 * @param fName the fName to set
	 */
	public void setfName(String fName) {
		 if(fName == null || fName.trim().isEmpty()) {
		        throw new IllegalArgumentException("First name cannot be null or empty");
		    }
		this.fName = fName;
	}

	/**
	 * @return the lName
	 */
	public String getlName() {
		return lName;
	}

	/**
	 * @param lName the lName to set
	 */
	public void setlName(String lName) {
		 if(lName == null || lName.trim().isEmpty()) {
		        throw new IllegalArgumentException("last name cannot be null or empty");
		    }
		this.lName = lName;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		
		this.age = age;
	}
	
	

	/**
	 * @return the pcr
	 */
	public boolean getPcr() {
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
		StringBuilder patientInfo = new StringBuilder();
        patientInfo.append("id: ").append(getId()).append("\n");

        if (fName != null && lName != null) {
            patientInfo.append("Full Name: ").append(fName).append(" ").append(lName).append("\n");
        }

        patientInfo.append("Age: ").append(age).append("\n");
        patientInfo.append("PCR: ").append(pcr).append("\n");

        return patientInfo.toString();
	}
}
