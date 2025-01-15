package models;

public class Subjects {
    String subject;
    String subject_code;
    public Subjects(String subject,String subject_code) {
        this.subject = subject;
        this.subject_code = subject_code;
    }
    

    public String getSubject() {
        return subject;
    }
    public String getSubject_code() {
        return subject_code;
    }
}