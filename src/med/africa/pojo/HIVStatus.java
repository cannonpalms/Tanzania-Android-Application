package med.africa.pojo;

public class HIVStatus {

	private boolean hasHIV;
	
	public HIVStatus(boolean hasHIV) {
		this.hasHIV = hasHIV;
	}
	
	public boolean isPositive() {
		return hasHIV;
	}
	
	public void setStatus(boolean status) {
		this.hasHIV = status;
	}
	
	@Override
	public String toString() {
		if (hasHIV) return "Positive";
		else return "Negative";
	}
}
