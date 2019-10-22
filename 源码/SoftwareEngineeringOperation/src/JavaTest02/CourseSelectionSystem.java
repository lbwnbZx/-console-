package JavaTest02;


import java.sql.SQLException;
import java.util.Scanner;

public class CourseSelectionSystem {


    public  static void main(String[] args) throws SQLException {

        System.out.println("-欢迎来到学生选课系统-");
        while(true){
            System.out.println("——————————————————————————————————————————————————————————————");
            System.out.println( "请选择你的操作！！！\n"+
                                "1.添加本科生信息\t"+"2.添加研究生信息\t"+ "3.修改学生信息\n"+
                                "4.添加课程信息\t"+"5.修改课程信息\n"+
                                "6.本科生选课\t"+"7.研究生选课\n"+
                                "8.本科生消课\t"+"9.研究生消课\n"+
                                "a.查看学生选课情况\t"+"b.查看课程上课人数\n"+
                                "c.统计学生选课数量\t"+"d.统计上课人数\n"+
                                "#.退出");
            Scanner scanner=new Scanner(System.in);
            char temp = scanner.next().charAt(0);
            if(temp=='#'){
                System.out.println("退出系统成功!!!");
                break;
            }
            switch (temp){
                case '1':{//添加本科生信息
                    String stuType = "本科生";
                    StuManage.addStu(stuType);
                    break;
                }
                case '2':{//添加研究生信息
                    String stuType = "研究生";
                    StuManage.addStu(stuType);
                    break;
                }
                case '3':{//修改学生信息
                    StuManage.AlterStu();
                    break;
                }
                case '4':{//添加课程信息
                    CouManage.AddCourse();
                    break;
                }
                case '5':{//修改课程信息
                    CouManage.AlterCourse();
                    break;
                }
                case '6':{//设置本科生选修某课程
                    SelectCouManage.ugSelectCou();
                    break;
                }
                case'7':{//研究生选课
                    SelectCouManage.gSelectCou();
                    break;
                }
                case'8':{//本科生消课
                    SelectCouManage.ugDeleteCou();
                    break;
                }
                case'9':{//研究生消课
                    SelectCouManage.gDeleteCou();
                    break;
                }
                case 'a':{//输入学号统计该学生选修课程
                    DataStatistics.SelectStudent();
                    break;
                }
                case 'b':{//输入课程号统计选修该课程的学生
                    DataStatistics.SelectCourse();
                    break;
                }
                case 'c':{//统计学生选课数量
                    DataStatistics.StuStatistics();
                    break;
                }
                case 'd':{//统计上课人数
                    DataStatistics.CouStatistics();
                    break;
                }
                default:{
                    System.out.println("输入错误，请重新输入");
                }


            }//switch

        }//while


    }//main


}//class
