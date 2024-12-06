package models;

public class Faculty {

    private String id;
    private String name;
    private String email;
    private String phone;
    private String courseId;
    private String password;
    private String profileImage;  
    private String address;
    // Constructor with all fields
    public Faculty(String id, String name, String email, String phone, String courseId, 
                     String password, String profileImage, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.courseId = courseId;
        this.password = password;
        this.profileImage = profileImage;
		this.address = address;
    }

	// Default constructor
	public Faculty() {}
    
    // Getters and Setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}