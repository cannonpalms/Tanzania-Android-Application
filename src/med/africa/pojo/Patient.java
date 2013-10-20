package med.africa.pojo;

import java.util.HashSet;
import java.util.Set;

public final class Patient {

	private final String refNumber;
	private final String birthDate;
	private final Gender gender;
	private final String firstName;
	private final String lastName;
	private final Set<String> treatmentCodes;
	private final Set<String> conditionCodes;
	private final Set<Allergy> allergies;
	private final HIVStatus hivStatus;

	protected Patient(String refNumber, String birthDate, Gender gender,
			String firstName, String lastName, HIVStatus hivStatus) {
		this.treatmentCodes = new HashSet<String>();
		this.conditionCodes = new HashSet<String>();
		this.allergies = new HashSet<Allergy>();
		
		this.refNumber = refNumber;
		this.birthDate = birthDate;
		this.gender = gender;
		this.firstName = firstName;
		this.lastName = lastName;
		this.hivStatus = hivStatus;
	}

	public String getRefNumber() {
		return refNumber;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public Gender getGender() {
		return gender;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public Set<String> getTreatmentCodes() {
		return treatmentCodes;
	}

	public Set<String> getConditionCodes() {
		return conditionCodes;
	}

	public Set<Allergy> getAllergies() {
		return allergies;
	}
	
	public HIVStatus getHivStatus() {
		return hivStatus;
	}
	
	public void setHIVStatus(boolean status) {
		hivStatus.setStatus(status);
	}
	
	public void addTreatmentCode(String code) {
		treatmentCodes.add(code);
	}
	
	public void addConditionCode(String code) {
		conditionCodes.add(code);
	}
	
	public void addAllergy(Allergy allergy) {
		allergies.add(allergy);
	}
	
	public void removeTreatmentCode(String code) {
		treatmentCodes.remove(code);
	}
	
	public void removeConditionCode(String code) {
		conditionCodes.remove(code);
	}
	
	public void removeAllergy(String code) {
		allergies.remove(code);
	}
	
	public String getTreatmentCodeStr() {
		if (treatmentCodes.size() == 0) return "";
		
		StringBuilder codeList = new StringBuilder();
		for (String code : treatmentCodes) {
			codeList.append(code);
			codeList.append(", ");
		}
		
		int extraStart = codeList.lastIndexOf(",");
		int extraEnd = codeList.length();
		
		codeList.replace(extraStart, extraEnd, "");
		
		return codeList.toString();
	}
	
	public String getConditionCodeStr() {
		if (conditionCodes.size() == 0) return "";
		
		StringBuilder codeList = new StringBuilder();
		for (String code : conditionCodes) {
			codeList.append(code);
			codeList.append(", ");
		}
		
		int extraStart = codeList.lastIndexOf(",");
		int extraEnd = codeList.length();
		
		codeList.replace(extraStart, extraEnd, "");
		
		return codeList.toString();
	}
	
	public String getAllergiesStr() {
		if (allergies.size() == 0) return "";
		
		StringBuilder codeList = new StringBuilder();
		for (Allergy code : allergies) {
			codeList.append(code);
			codeList.append(", ");
		}
		
		int extraStart = codeList.lastIndexOf(",");
		int extraEnd = codeList.length();
		
		codeList.replace(extraStart, extraEnd, "");
		return codeList.toString();
	}

	
	@Override
	public String toString() {
		return lastName + ", " + firstName + "\t" + refNumber;
	}
}
