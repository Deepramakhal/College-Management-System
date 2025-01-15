package models;

public class Student {

    private int id;
    private String name;
    private String email;
    private String phone;
    private String guardianName;
    private String gender;
    private String dob;
    private String batchId;
    private int currentSem;
    private String profileImage;  // Assuming profileImage is a path or URL to the image file.
    private String address;

    // Constructor with all fields
    public Student(int id, String name, String email, String phone, String guardianName, 
                     String gender, String dob, String batchId, int currentSem, String profileImage, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.guardianName = guardianName;
        this.gender = gender;
        this.dob = dob;
        this.batchId = batchId;
        this.currentSem = currentSem;
        this.profileImage = profileImage;
        this.address = address;
    }
    public Student() {}
    
    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public int getCurrentSem() {
        return currentSem;
    }

    public void setCurrentSem(int currentSem) {
        this.currentSem = currentSem;
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