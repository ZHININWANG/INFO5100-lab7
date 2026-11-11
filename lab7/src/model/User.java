package model;

public class User {
    private int userId;
    private String name;
    private int age;
    private String college;
    private String hobby;

    public User() {}

    public User(String name, int age, String college, String hobby) {
        this.name = name;
        this.age = age;
        this.college = college;
        this.hobby = hobby;
    }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getCollege() { return college; }
    public void setCollege(String college) { this.college = college; }
    public String getHobby() { return hobby; }
    public void setHobby(String hobby) { this.hobby = hobby; }
}
