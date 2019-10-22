package JavaTest02;

import java.sql.*;
import java.util.Scanner;

public class SelectCouManage {

//本科生选课
    public static void ugSelectCou() throws SQLException {
        System.out.println("请输入选课信息：");
        System.out.println("信息格式为:\t学号_课程号");
        Scanner scanner=new Scanner(System.in);//ug002_c002
        String selectCouInfo[] = scanner.nextLine().split("_");
        if (selectCouInfo.length != 2) {
            System.out.println("输入数据不合法!!!");
        }

        //判断该学号是否存在
        Connection con = DbConn.getConnection();//连接数据库

        Statement stmt = con.createStatement();
        ResultSet rs = null;
        rs = stmt.executeQuery("SELECT * FROM undergraduate WHERE ug_id='"+selectCouInfo[0]+"';");
        UnderGraduate underGraduate =new UnderGraduate();
        if(rs.next()){
            underGraduate.setStuId(rs.getString("ug_id"));
            underGraduate.setCredit(rs.getInt("ug_credit"));
        }else{
            System.out.println("选课失败！！！，无此学生");
            return;
        }

        //判断该学生学分是否选够
        if(underGraduate.getCredit()>=40){
            System.out.println("该学生无需再选课程！！！");
            return;
        }

        //判断所选的课程是否存在,如果存在,获取课程对象
        Course course=new Course();
        rs = stmt.executeQuery("SELECT * FROM course WHERE c_id='"+selectCouInfo[1]+"';");
        if(rs.next()){
            course.setCouId(rs.getString("c_id"));
            course.setCouCredits(rs.getInt("c_credit"));
        }else{
            System.out.println("选课失败: 数据库中未查询到此课程!!!");
            return;
        }


        //将选课信息保存到选课表中
        PreparedStatement ps=con.prepareStatement("insert ug_select_cou values (?,?);");
        ps.setString(1,selectCouInfo[0]);
        ps.setString(2,selectCouInfo[1]);
        if(ps.execute()){
            System.out.println("选课失败,选课信息已存在!!");
            return ;
        }
        //更新学分
        String sql="update undergraduate set ug_credit='"+(underGraduate.getCredit()+course.getCouCredits())+"' where ug_id='"+selectCouInfo[0]+"';";
        con.createStatement().execute(sql);

        System.out.println("选课成功!!");


    }



//研究生选课
    public static void gSelectCou()throws SQLException{
        System.out.println("请输入选课信息：");
        System.out.println("信息格式为:\t学号_课程号");
        Scanner scanner=new Scanner(System.in);//g002_c002
        String selectCouInfo[] = scanner.nextLine().split("_");
        if (selectCouInfo.length != 2) {
            System.out.println("输入数据不合法!!!");
        }

        //判断该学号是否存在
        Connection con = DbConn.getConnection();//连接数据库

        Statement stmt = con.createStatement();
        ResultSet rs = null;
        rs = stmt.executeQuery("SELECT * FROM graduate WHERE g_id='"+selectCouInfo[0]+"';");
        Graduate graduate =new Graduate();
        if(rs.next()){
            graduate.setStuId(rs.getString("g_id"));
            graduate.setCredit(rs.getInt("g_credit"));
        }else{
            System.out.println("选课失败！！！，无此学生");
            return;
        }

        //判断该学生学分是否选够
        if(graduate.getCredit()>=40){
            System.out.println("该学生无需再选课程！！！");
            return;
        }

        //判断所选的课程是否存在,如果存在,获取课程对象
        Course course=new Course();
        rs = stmt.executeQuery("SELECT * FROM course WHERE c_id='"+selectCouInfo[1]+"';");
        if(rs.next()){
            course.setCouId(rs.getString("c_id"));
            course.setCouCredits(rs.getInt("c_credit"));
        }else{
            System.out.println("选课失败: 数据库中未查询到此课程!!!");
            return;
        }


        //将选课信息保存到选课表中
        PreparedStatement ps=con.prepareStatement("insert g_select_cou values (?,?);");
        ps.setString(1,selectCouInfo[0]);
        ps.setString(2,selectCouInfo[1]);
        if(ps.execute()){
            System.out.println("选课失败,选课信息已存在!!");
            return ;
        }
        //更新学分
        String sql="update graduate set g_credit='"+(graduate.getCredit()+course.getCouCredits())+"' where g_id='"+selectCouInfo[0]+"';";
        con.createStatement().execute(sql);

        System.out.println("选课成功!!");

    }



//本科生消课
    public static void ugDeleteCou()throws SQLException{
        System.out.println("请输入消课信息：");
        System.out.println("信息格式为:\t学号_课程号");
        Scanner scanner=new Scanner(System.in);//ug002_c002
        String deleteCouInfo[] = scanner.nextLine().split("_");
        if (deleteCouInfo.length != 2) {
            System.out.println("输入数据不合法!!!");
        }

        //判断该学号是否存在
        Connection con = DbConn.getConnection();//连接数据库

        Statement stmt = con.createStatement();
        ResultSet rs = null;

        rs = stmt.executeQuery("SELECT * FROM undergraduate WHERE ug_id='"+deleteCouInfo[0]+"';");
        UnderGraduate underGraduate =new UnderGraduate();
        if(rs.next()){
            underGraduate.setStuId(rs.getString("ug_id"));
            underGraduate.setCredit(rs.getInt("ug_credit"));
        }else{
            System.out.println("消课失败！！！，无此学生");
            return;
        }


        //判断所消的课程是否存在,如果存在,获取课程对象
        Course course=new Course();
        rs = stmt.executeQuery("SELECT * FROM course WHERE c_id='"+deleteCouInfo[1]+"';");
        if(rs.next()){
            course.setCouId(rs.getString("c_id"));
            course.setCouCredits(rs.getInt("c_credit"));
        }else{
            System.out.println("消课失败: 数据库中未查询到此课程!!!");
            return;
        }


        //将消课信息保存到选课表中
        PreparedStatement ps=con.prepareStatement("DELETE FROM ug_select_cou WHERE ugsc_stu=? AND ugsc_cou=?;");
        ps.setString(1,deleteCouInfo[0]);
        ps.setString(2,deleteCouInfo[1]);
        if(ps.execute()){
            System.out.println("选课失败,选课信息已存在!!");
            return ;
        }
        //更新学分
        String sql="UPDATE undergraduate SET ug_credit = "+(underGraduate.getCredit()-course.getCouCredits())+" WHERE ug_id='"+deleteCouInfo[0]+"';";
        con.createStatement().execute(sql);

        System.out.println("消课成功!!");


    }



//研究生消课
    public static void gDeleteCou() throws SQLException {
        System.out.println("请输入消课信息：");
        System.out.println("信息格式为:\t学号_课程号");
        Scanner scanner=new Scanner(System.in);//ug002_c002
        String deleteCouInfo[] = scanner.nextLine().split("_");
        if (deleteCouInfo.length != 2) {
            System.out.println("输入数据不合法!!!");
        }

        //判断该学号是否存在
        Connection con = DbConn.getConnection();//连接数据库

        Statement stmt = con.createStatement();
        ResultSet rs = null;

        rs = stmt.executeQuery("SELECT * FROM graduate WHERE g_id='"+deleteCouInfo[0]+"';");
        Graduate graduate =new Graduate();
        if(rs.next()){
            graduate.setStuId(rs.getString("g_id"));
            graduate.setCredit(rs.getInt("g_credit"));
        }else{
            System.out.println("消课失败！！！，无此学生");
            return;
        }


        //判断所消的课程是否存在,如果存在,获取课程对象
        Course course=new Course();
        rs = stmt.executeQuery("SELECT * FROM course WHERE c_id='"+deleteCouInfo[1]+"';");
        if(rs.next()){
            course.setCouId(rs.getString("c_id"));
            course.setCouCredits(rs.getInt("c_credit"));
        }else{
            System.out.println("消课失败: 数据库中未查询到此课程!!!");
            return;
        }


        //将消课信息保存到选课表中
        PreparedStatement ps=con.prepareStatement("DELETE FROM g_select_cou WHERE gsc_stu=? AND gsc_cou=?;");
        ps.setString(1,deleteCouInfo[0]);
        ps.setString(2,deleteCouInfo[1]);
        if(ps.execute()){
            System.out.println("选课失败,选课信息已存在!!");
            return ;
        }
        //更新学分
        String sql="UPDATE graduate SET g_credit = "+(graduate.getCredit() - course.getCouCredits())+" WHERE g_id='"+deleteCouInfo[0]+"';";
        con.createStatement().execute(sql);

        System.out.println("消课成功!!");
    }

}


