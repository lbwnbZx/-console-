package JavaTest02;



public class Student {
    private String stuName;//学生姓名
    private String stuId;//学号
    private String stuGender;//性别
    private String stuBirth;//出生日期
    private String stuGrade;//年级
    private String stuProfessional;//专业
    private int credit;//学分

    public Student() {
    }

    public Student(String stuName, String stuId, String stuGender, String stuBirth, String stuGrade, String stuProfessional,int credit) {
        this.stuName = stuName;
        this.stuId = stuId;
        this.stuGender = stuGender;
        this.stuBirth = stuBirth;
        this.stuGrade = stuGrade;
        this.stuProfessional = stuProfessional;
        this.credit = credit;
    }


    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuGender() {
        return stuGender;
    }

    public void setStuGender(String stuGender) {
        this.stuGender = stuGender;
    }

    public String getStuBirth() {
        return stuBirth;
    }

    public void setStuBirth(String stuBirth) {
        this.stuBirth = stuBirth;
    }

    public String getStuGrade() {
        return stuGrade;
    }

    public void setStuGrade(String stuGrade) {
        this.stuGrade = stuGrade;
    }

    public String getStuProfessional() {
        return stuProfessional;
    }

    public void setStuProfessional(String stuProfessional) {
        this.stuProfessional = stuProfessional;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String toString(){
        return "学生姓名："+getStuName()+"\t学号:"+getStuId()+"\t性别："+getStuGender()+"\t出生日期："+getStuBirth()+"\t年级："+getStuGrade()+"\t专业："+getStuProfessional();
    }
}
