package med.africa.pojo;


public class PatientBuilder {

	private String refNumber;
	private String birthDate;
	private Gender gender;
	private String firstName;
	private String lastName;
	private HIVStatus hivStatus;
	
	public PatientBuilder setRefNumber(String refNumber) {
		this.refNumber = refNumber;
		return this;
	}
	
	public PatientBuilder setBirthDate(String birthDate) {
		this.birthDate = birthDate;
		return this;
	}
	
	public PatientBuilder setGender(Gender gender) {
		this.gender = gender;
		return this;
	}
	
	public PatientBuilder setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}
	
	public PatientBuilder setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}
	
	public PatientBuilder setHivStatus(HIVStatus hivStatus) {
		this.hivStatus = hivStatus;
		return this;
	}
	
	public Patient build() {
		checkFields();
		Patient patient = new Patient(refNumber, birthDate, gender, firstName, lastName, hivStatus);
		return patient;
	}
	
	private void checkFields() {
		if (refNumber == null
				|| birthDate == null
				|| gender == null
				|| firstName == null
				|| lastName == null
				|| hivStatus == null) {
			throw new NullPointerException("All fields not initialized.");
		}
				
	}
	
}
