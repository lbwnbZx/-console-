package JavaTest02;

import java.sql.*;
import java.util.Scanner;

public class DataStatistics {

    /*输入学生学号，输出该学生的选修课程信息*/
    public static void SelectStudent() throws SQLException {
        Connection con = DbConn.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = null;

        System.out.println("输入要查询学生的学号：");
        Scanner scanner=new Scanner(System.in);
        String stuId=scanner.nextLine();
        if(stuId.charAt(0)=='u'){//判断输入的是本科生还是研究生
            rs=stmt.executeQuery("select c_id,any_value(c_name),any_value(c_teacher),any_value(c_introduce),any_value(c_credit) " +
                    " from course " +
                    " INNER JOIN ug_select_cou on c_id=ugsc_cou " +
                    " WHERE ugsc_stu='"+stuId +"'order by c_id;");


        }else{
            rs=stmt.executeQuery("select c_id,any_value(c_name),any_value(c_teacher),any_value(c_introduce),any_value(c_credit) " +
                    " from course " +
                    " INNER JOIN g_select_cou on c_id=gsc_cou " +
                    " WHERE gsc_stu='"+stuId +"'order by c_id;");


        }
        System.out.println("该学生选课情况：");
        System.out.println("—————————————————————————————————————————————————————————————");
        System.out.println("\t课程号\t课程名称\t授课老师\t课程简介\t\t学分");
        while(rs.next()){
            System.out.println("\t"+rs.getString("c_id")+"\t"+rs.getString("any_value(c_name)")+"\t"+rs.getString("any_value(c_teacher)")+"\t"+rs.getString("any_value(c_introduce)")+"\t"+rs.getString("any_value(c_credit)"));
        }
        System.out.println("—————————————————————————————————————————————————————————————");
    }




    /*输入课程号，输出选该课程的学生信息*/
    public static void SelectCourse() throws SQLException{
        Connection con = DbConn.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = null;


        System.out.println("输入要查询的课程号：");
        Scanner scanner=new Scanner(System.in);
        String couId=scanner.nextLine();
        //该课程的本科生选课情况
        rs=stmt.executeQuery("SELECT ug_id,any_value(ug_name),any_value(ug_gender),any_value(ug_profressional),any_value(ug_grade) " +
                " FROM undergraduate " +
                " INNER JOIN ug_select_cou ON ugsc_stu=ug_id  " +
                " WHERE ugsc_cou= '"+couId+"' order BY ug_id;");

        System.out.println("该课程的本科生选课情况：");
        System.out.println("—————————————————————————————————————————————————————————————————————————————————————");
        System.out.println("\t学号\t姓名\t性别\t专业\t班级");
        while(rs.next()){
            System.out.println("\t"+rs.getString("ug_id")+"\t"+rs.getString("any_value(ug_name)")+"\t"+rs.getString("any_value(ug_gender)")+"\t"+rs.getString("any_value(ug_profressional)")+"\t"+rs.getString("any_value(ug_grade)"));
        }
        System.out.println("—————————————————————————————————————————————————————————————————————————————————————");


        rs=stmt.executeQuery("SELECT g_id,any_value(g_name),any_value(g_gender),any_value(g_grade),any_value(g_professional),any_value(g_mentor),any_value(g_direction) " +
                " FROM graduate " +
                " INNER JOIN g_select_cou ON gsc_stu=g_id  " +
                " WHERE gsc_cou= '"+couId+"' order BY g_id;");

        System.out.println("该课程的研究生选课情况：");
        System.out.println("—————————————————————————————————————————————————————————————————————————————————————");
        System.out.println("\t学号\t姓名\t性别\t专业\t年级\t导师\t研究方向");
        while(rs.next()){
            System.out.println("\t"+rs.getString("g_id")+"\t"+rs.getString("any_value(g_name)")+"\t"+rs.getString("any_value(g_gender)")+"\t"+rs.getString("any_value(g_professional)")+"\t"+rs.getString("any_value(g_grade)")+"\t"+rs.getString("any_value(g_mentor)")+"\t"+rs.getString("any_value(g_direction)"));

        }
        System.out.println("—————————————————————————————————————————————————————————————————————————————————————");
    }



    /*根据学生统计选课信息  输出学号、姓名、选课数量*/
    public static void StuStatistics() throws SQLException {
        Connection con = DbConn.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = null;


       /* 统计本科生选课情况  输出学号，姓名，选修课程数量*/
                rs = stmt.executeQuery(" select ug_id,any_value(ug_name),COUNT(ug_select_cou.ugsc_stu) AS countCourse "+
                " from undergraduate "+
                "inner join ug_select_cou "+
                "on  undergraduate.ug_id=ug_select_cou.ugsc_stu "+
                "GROUP BY undergraduate.ug_id; ");
        System.out.println("\n_______本科生选课统计表______\n");
        System.out.printf("\t学号\t姓名\t选课数量\n");
        while(rs.next()){
            System.out.printf("\t"+rs.getString("ug_id")+"\t"+rs.getString("any_value(ug_name)")+"\t    "+ rs.getInt("countCourse")+"\n");
            //System.out.println("\n");
        }


        /* 统计研究生选课情况  输出学号，姓名，选修课程数量*/
        rs = stmt.executeQuery(" select g_id,any_value(g_name),COUNT(g_select_cou.gsc_stu) AS countCourse "+
                " from graduate "+
                "inner join g_select_cou "+
                "on  graduate.g_id=g_select_cou.gsc_stu "+
                "GROUP BY graduate.g_id; ");
        System.out.println("\n_______研究生选课统计表______\n");
        System.out.printf("\t学号\t姓名\t选课数量\n");
        while(rs.next()){
            System.out.printf("\t"+rs.getString("g_id")+"\t"+rs.getString("any_value(g_name)")+"\t    "+ rs.getInt("countCourse")+"\n");
            //System.out.println("\n");
        }

    }



    /*根据课程统计选课信息   输出课程号，课程名称，选修此课程的学生人数*/
    public static void CouStatistics() throws SQLException {
        Connection con = DbConn.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = null;

        rs = stmt.executeQuery(" select c_id,any_value(c_name),COUNT(ugsc_stu) AS countUndergraduate " +
                " FROM ug_select_cou\n " +
                " INNER JOIN course\n " +
                " on course.c_id=ug_select_cou.ugsc_cou " +
                " GROUP BY c_id; ");
        System.out.println("\n_______本科生选课统计表______\n");
        System.out.printf("\t课程号\t课程名称\t本科生选课人数\n");
        while(rs.next()){
            System.out.printf("\t"+rs.getString("c_id")+"\t"+rs.getString("any_value(c_name)")+"\t"+ rs.getInt("countUndergraduate")+"\n");
            //System.out.println("\n");
        }
        rs = stmt.executeQuery(" select c_id,any_value(c_name),COUNT(gsc_stu) AS countGraduate" +
                " FROM g_select_cou " +
                " INNER JOIN course " +
                " on course.c_id=g_select_cou.gsc_cou " +
                " GROUP BY c_id; ");
        System.out.println("\n_______研究生选课统计表______\n");
        System.out.printf("\t课程号\t课程名称\t研究生选课人数\n");
        while(rs.next()){
            System.out.printf("\t"+rs.getString("c_id")+"\t"+rs.getString("any_value(c_name)")+"\t"+ rs.getInt("countGraduate")+"\n");
            //System.out.println("\n");
        }
    }


}















