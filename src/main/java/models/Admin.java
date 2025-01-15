package models;

public class Admin {
    
    private String id;
    private String name;
    private String email;
    private String phone;
    private String adminType;
    private String profileImage;
    private String address;
    
    
    public Admin(String id, String name, String email, String phone, String adminType, String profileImage, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.adminType = adminType;
        this.profileImage = profileImage;
		this.address = address;
    }

    // Default constructor
    public Admin() {
    }

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

    public String getAdminType() {
        return adminType;
    }

    public void setAdminType(String adminType) {
        this.adminType = adminType;
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