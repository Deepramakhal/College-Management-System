package models;

public class Batch {
	private String batchId;
	private String startingYear;
	private String endingYear;
	public Batch(String batchId, String startingYear, String endingYear) {
		super();
		this.batchId = batchId;
		this.startingYear = startingYear;
		this.endingYear = endingYear;
	}
	public String getBatchId() {
		return batchId;
	}
	public String getStartingYear() {
		return startingYear;
	}
	public String getEndingYear() {
		return endingYear;
	}
}
	
	
