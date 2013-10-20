package med.africa.pojo;

public class Allergy {

	private String allergyName;
	private String description;
	
	public Allergy(String allergyName, String description) {
		this.allergyName = allergyName;
		this.description = description;
	}
	
	public Allergy(String allergyName) {
		this(allergyName, null);
	}

	public String getAllergyName() {
		return allergyName;
	}

	public void setAllergyName(String allergyName) {
		this.allergyName = allergyName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		String resultant = allergyName
				+ description != null ? (": " + description) : ("");
				
		return resultant;
	}
}
