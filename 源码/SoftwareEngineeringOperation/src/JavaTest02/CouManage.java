package JavaTest02;
//课程管理
import java.sql.*;
import java.util.Scanner;

public class CouManage {
    public static Connection con = DbConn.getConnection();//连接数据库
    public static String sql = null;
    public static PreparedStatement ps = null;


    public static void AddCourse(){
        System.out.println("请输入要添加课程的信息：");
        System.out.println("信息格式为:\t课程号_课程名_授课老师_课程简介_学分");
        Scanner scanner=new Scanner(System.in);//c001_计算机导论_冯诺依曼_计算机基础课程_3
        String couInformation =scanner.nextLine();
        String couInfo[] = couInformation.split("_");

        if (couInfo.length < 5) {
            System.out.println("输入数据不合法!!!");
        }

        try {
            sql = "INSERT INTO course VALUES(?,?,?,?,?);";
            ps = con.prepareStatement(sql);
            ps.setString(1, couInfo[0]);
            ps.setString(2, couInfo[1]);
            ps.setString(3, couInfo[2]);
            ps.setString(4, couInfo[3]);
            ps.setInt(5, Integer.parseInt(couInfo[4]));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("添加课程信息成功！！！");
    }




    public static void AlterCourse() throws SQLException {
        String id;//存储要修改课程信息的课程号
        int temp =-1;//要修改属性的编号
        System.out.println("可修改属性包括：课程名称，授课老师，课程简介，学分");
        System.out.println("如 想要修改课程号为c001课程的授课老师为洛必达，请输入：c004_授课老师_洛必达");
        System.out.println("请输入需要修改课程的课程号_属性_修改后的值:");
        Scanner scanner=new Scanner(System.in);//c004_授课老师_雷军
        String couInformation =scanner.nextLine();
        String couInfo[] = couInformation.split("_");
        String[] cAttribute = {"课程名称","授课老师","课程简介","学分"};
        String[] cAttributeValue = {"c_name","c_teacher","c_introduce","c_credit"};
        if (!(couInfo.length == 3) && temp != -1) {
            System.out.println("输入数据不合法, 添加GG!!!");
            return;
        }

        for(int i = 0; i< cAttribute.length; i++){
            if(couInfo[1].equals(cAttribute[i])) {
                temp = i;
            }
        }
        /*sql="update course set  ? = ? where c_id=?;";
        ps=con.prepareStatement(sql);
        ps.setString(1,cAttributeValue[temp]);
        ps.setString(2,couInfo[2]);
        ps.setString(3,couInfo[0]);
        ps.executeUpdate();*/

        Statement stmt=con.createStatement();//实例化statement对象
        ResultSet rs=stmt.executeQuery("select * from course where c_id='"+couInfo[0]+"';");//下达select命令查询数据库，会把结果存到rs中供我们使用
        if(rs.next()){
            String updateCou=null;

            if(couInfo[1].equals("c_credit")){//要修改学分时，学分是int类型
                //int credit=Integer.parseInt(couInfo[2]);
                updateCou="update course set" +cAttributeValue[temp]+"="+couInfo[2]+" where c_id  ="+couInfo[0]+";";
            }else{
                updateCou= "update course set "+cAttributeValue[temp]+"='"+couInfo[2]+"' where c_id='"+couInfo[0]+"';";
            }
            con.createStatement().execute(updateCou);
        }else{
            System.out.println("修改失败，数据库中没有此学号！！！");
        }



        System.out.println("课程信息修改成功！！！");
    }
}
