package models;

public class Admin{
	private String id;
	private String name;
	private String email;
	private String phone;
	private String admin_type;
	private String password;
	
	public Admin(String id,String name,String email,String phone,String admin_type,String password){
		this.id=id;
		this.name=name;
		this.email=email;
		this.phone=phone;
		this.admin_type=admin_type;
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

	public String getAdmin_type() {
		return admin_type;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public void setAdmin_type(String admin_type) {
		this.admin_type = admin_type;
	}
}