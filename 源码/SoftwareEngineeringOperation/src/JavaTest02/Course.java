package JavaTest02;

public class Course {
    private String couId;//课程号
    private String couName;//课程名称
    private String couTeacher;//上课老师
    private String couIntroduction;//课程简介
    private int couCredits;//学分



    public String getCouId() {
        return couId;
    }

    public void setCouId(String couId) {
        this.couId = couId;
    }

    public String getCouName() {
        return couName;
    }

    public void setCouName(String couName) {
        this.couName = couName;
    }

    public String getCouTeacher() {
        return couTeacher;
    }

    public void setCouTeacher(String couTeacher) {
        this.couTeacher = couTeacher;
    }

    public String getCouIntroduction() {
        return couIntroduction;
    }

    public void setCouIntroduction(String couIntroduction) {
        this.couIntroduction = couIntroduction;
    }

    public int getCouCredits() {
        return couCredits;
    }

    public void setCouCredits(int couCredits) {
        this.couCredits = couCredits;
    }
}
