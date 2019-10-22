package JavaTest02;

public class UnderGraduate extends Student {
    private String ugClass;//本科班级
    private String manager;//班主任

    public UnderGraduate() {
    }

    public UnderGraduate(String stuName, String stuId, String stuGender, String stuBirth, String stuGrade, String stuProfessional, int credit, String ugClass, String manager) {
        super(stuName, stuId, stuGender, stuBirth, stuGrade, stuProfessional, credit);
        this.ugClass = ugClass;
        this.manager = manager;
    }

    public String getUgClass() {
        return ugClass;
    }

    public void setUgClass(String ugClass) {
        this.ugClass = ugClass;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
    public String toString(){
        return super.toString()+"\t班级："+getUgClass()+"\t班主任："+getManager();
    }
}
