package models;

public class Student {
	public String id;
	public String name;
	public String email;
	public String phone;
	public String address;
	public String guardian_name;
	public String guardian_phone;
	public String section;
	public String password;
	public String batch_id;
	public Student(String id,String name,String email,String phone,String address,String guardian_name,String guardian_phone,String section,String password,String batch_id)
	{
		this.id=id;
		this.name=name;
		this.email=email;
		this.phone=phone;
		this.address=address;
		this.guardian_name=guardian_name;
		this.guardian_phone=guardian_phone;
		this.section=section;
		this.password=password;
		this.batch_id=batch_id;

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
	public String getAddress() {
		return address;
	}
	public String getGuardian_name() {
		return guardian_name;
	}
	public String getGuardian_phone() {
		return guardian_phone;
	}
	public String getSection() {
		return section;
	}
	public String getPassword() {
		return password;
	}
	public String getBatch_id() {
		return batch_id;
	}
	public void setBatch_id(String batch_id) {
		this.batch_id = batch_id;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public void setGuardian_phone(String guardian_phone) {
		this.guardian_phone = guardian_phone;
	}
	public void setGuardian_name(String guardian_name) {
		this.guardian_name = guardian_name;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setId(String id) {
		this.id = id;		
}
}
	
