package med.africa.pojo;

public enum Gender {
	MALE, FEMALE;
	
	@Override
	public String toString() {
		if (this == MALE) return "M";
		else return "F";
	}
}
