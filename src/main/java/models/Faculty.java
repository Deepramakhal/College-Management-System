package models;

public class Faculty{
	private String id;
	private String name;
	private String email;
	private String phone;
	private String course_id;
	private String password;
	public Faculty(String id,String name,String email,String phone, String course_id,String password){
		this.id=id;
		this.name=name;
		this.email=email;
		this.phone=phone;
		this.course_id=course_id;
		this.password=password;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPhone() {
		return phone;
	}
	public String getCourse_id() {
		return course_id;
	}
	public String getPassword() {
		return password;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}