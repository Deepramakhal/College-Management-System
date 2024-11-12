package models;

public class Batch{
	private String id;
	private String name;
	private String course_id;
	public Batch(String id,String name,String course_id){
		this.id=id;
		this.name=name;
		this.course_id=course_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
}