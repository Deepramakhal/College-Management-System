package models;


public class Course {
	private String id;
	private String name;
	private String field;
	private String hod_id;
	public Course(String id,String name,String field,String hod_id) {
		this.id=id;
		this.name=name;
		this.field=field;
		this.hod_id=hod_id;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getField() {
		return field;
	}
	public String getHod_id() {
		return hod_id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setField(String field) {
		this.field = field;
	}
	public void setHod_id(String hod_id) {
		this.hod_id = hod_id;
	}
}
	