package model;

import java.util.ArrayList;
import java.util.List;

public class StudentAccount {
    private final String id;
    private final String fullName;
    private final String email;
    private final String phone;
    private final String level;
    private final String major;
    private final boolean scholarship;
    private final List<String> courses;

    private StudentAccount(StudentAccountBuilder builder) {
        this.id = builder.id;
        this.fullName = builder.fullName;
        this.email = builder.email;
        this.phone = builder.phone;
        this.level = builder.level;
        this.major = builder.major;
        this.scholarship = builder.scholarship;
        this.courses = builder.courses;
    }

    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getLevel() { return level; }
    public String getMajor() { return major; }
    public boolean isScholarship() { return scholarship; }
    public List<String> getCourses() { return courses; }

    public StudentAccount cloneWithNewId(String newId, String newName, String newEmail) {
        List<String> deepCopyCourses = new ArrayList<>(this.courses);
        return new StudentAccount.StudentAccountBuilder(newId, newName, newEmail)
                .level(this.level)
                .major(this.major)
                .scholarship(this.scholarship)
                .phone(this.phone)
                .courses(deepCopyCourses)
                .build();
    }

    @Override
    public String toString() {
        return "StudentAccount{id='" + id + "', name='" + fullName + "', email='" + email + 
               "', level='" + level + "', major='" + major + "'}";
    }

    public static class StudentAccountBuilder {
        private final String id;
        private final String fullName;
        private final String email;
        private String phone = null;
        private String level = "L1";
        private String major = "";
        private boolean scholarship = false;
        private List<String> courses = new ArrayList<>();

        public StudentAccountBuilder(String id, String fullName, String email) {
            if (id == null || id.trim().isEmpty()) {
                throw new IllegalArgumentException("رقم التسجيل إجباري.");
            }
            if (fullName == null || fullName.trim().isEmpty()) {
                throw new IllegalArgumentException("الاسم الكامل إجباري.");
            }
            if (email == null || !email.contains("@")) {
                throw new IllegalArgumentException("البريد الإلكتروني يجب أن يحتوي على @.");
            }
            this.id = id;
            this.fullName = fullName;
            this.email = email;
        }

        public StudentAccountBuilder phone(String phone) { this.phone = phone; return this; }
        public StudentAccountBuilder level(String level) { this.level = level; return this; }
        public StudentAccountBuilder major(String major) { this.major = major; return this; }
        public StudentAccountBuilder scholarship(boolean s) { this.scholarship = s; return this; }
        public StudentAccountBuilder courses(List<String> c) { this.courses = c; return this; }

        public StudentAccount build() {
            return new StudentAccount(this);
        }
    }
}