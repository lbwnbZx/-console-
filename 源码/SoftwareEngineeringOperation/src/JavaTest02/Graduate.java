package JavaTest02;

public class Graduate extends Student {
    private String mentor;//研究生导师
    private String gDirection;//研究方向

    public Graduate(){}


    public Graduate(String stuName, String stuId, String stuGender, String stuBirth, String stuGrade, String stuProfessional, int credit, String mentor, String gDirection) {
        super(stuName, stuId, stuGender, stuBirth, stuGrade, stuProfessional, credit);
        this.mentor = mentor;
        this.gDirection = gDirection;
    }

    public String getMentor() {
        return mentor;
    }

    public void setMentor(String mentor) {
        this.mentor = mentor;
    }

    public String getGDirection() {
        return gDirection;
    }

    public void setGDirection(String gDirection) {
        this.gDirection = gDirection;
    }

    @Override
    public String toString() {
        return super.toString()+"\t导师："+getMentor()+"\t研究方向："+getGDirection();
    }
}
