package JavaTest02;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;


//学生信息管理
public class StuManage {
    public static Connection con = DbConn.getConnection();//连接数据库
    public static String sql = null;
    public static PreparedStatement ps = null;

//学生信息添加
    public static boolean addStu(String StuType) {
        String stuInformation=null;
        if(StuType == "本科生"){
            System.out.println("请输入需要添加的本科生信息");
            System.out.println("本科生格式为:\t姓名_学号_性别_出生年月_年级_专业_班级_班主任");
            Scanner scanner=new Scanner(System.in);
            stuInformation=scanner.nextLine();       //  赵大_ug001_男_2019-10-19_大四_母猪产后护理_二班_托尼老师
        }else{
            System.out.println("请输入需要添加的研究生信息");
            System.out.println("研究生格式为:\t姓名_学号_性别_出生年月_年级_专业_导师_研究方向");
            Scanner scanner=new Scanner(System.in);   //  秋大_g001_男_2019-10-19_研一_母猪产后护理_武老大_产后健身
            stuInformation=scanner.nextLine();
        }


        String[] stuInfo = stuInformation.split("_");
        if (stuInfo.length < 8) {
            System.out.println("输入数据不合法!!!");
            return false;
        }

        try {
            if (StuType == "本科生") {
                sql = "INSERT INTO undergraduate VALUES(?,?,?,?,?,?,?,?,?);";//本科生
            } else {
                sql = "INSERT INTO graduate VALUES(?,?,?,?,?,?,?,?,?);";//研究生
            }
            ps = con.prepareStatement(sql);
            ps.setString(1, stuInfo[0]);
            ps.setString(2, stuInfo[1]);
            ps.setString(3, stuInfo[2]);
            ps.setString(4, stuInfo[3]);
            ps.setString(5, stuInfo[4]);
            ps.setString(6, stuInfo[5]);
            ps.setString(7, stuInfo[6]);
            ps.setString(8, stuInfo[7]);
            ps.setInt(9, 0);            //学分默认为0

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("添加信息成功");
        return true;
    }
//对学生信息修改
    public static void AlterStu() throws SQLException {
        //每个学生有唯一的 id
        String tb_name;//存储要修改的表名
        String id;//存储要修改学生信息的学号
        int temp =-1;//要修改属性的编号


        System.out.println("可改属性包括：姓名,性别,出生年月,年级,专业,班级,班主任,研究生导师,研究方向\n"+
                            "如: 想要修改一个本科生,学号为'ug001'的年级为大三, 输入格式:本科生_ug001_年级_大三\n"+
                            "请输入需要修改学生的学号_属性_修改后的值:");
        Scanner scanner=new Scanner(System.in);
        String[] strInfo = scanner.nextLine().split("_");

        /*//查找此id是否存在
        ResultSet rs=null;
        rs=con.createStatement().executeQuery("select * from undergraduate,graduate where ug_id= '"+strInfo[1]+"';");
        if(rs.next()){

        }*/

        if(strInfo[0].equals("本科生")){
            id=strInfo[1];
            String[] ugAttribute = {"姓名","性别","出生年月","年级","专业","班级","班主任"};
            String[] ugAttributeValue = {"ug_name","ug_gender","ug_birth","ug_grade","ug_profressional","ug_class","ug_manager"};
            tb_name = "undergraduate";

            for(int i = 0; i< ugAttribute.length; i++){
                if(strInfo[2].equals(ugAttribute[i]))
                    temp= i;
            }
            sql="update "+tb_name+" set "+ugAttributeValue[temp]+" = ? where ug_id=?;";
            ps=con.prepareStatement(sql);//预编译
            ps.setString(1,strInfo[3]);
            ps.setString(2,id);
            ps.executeUpdate();//执行

        }else{
            id=strInfo[1];
            String[] gAttribute = {"姓名","性别","出生年月","年级","专业","研究生导师","研究方向"};
            String[] gAttributeValue = {"g_name","g_gender","g_birth","g_grade","g_profressional","g_mentor","g_direction"};

            tb_name = "graduate";
            for(int i = 0; i< gAttribute.length; i++){
                if(strInfo[2].equals(gAttribute[i]))
                    temp= i;
            }

            sql="update "+tb_name+" set "+gAttributeValue[temp]+" = ? where g_id=?;";
            ps=con.prepareStatement(sql);//预编译
            ps.setString(1,strInfo[3]);
            ps.setString(2,id);
            ps.executeUpdate();//执行
        }


        System.out.println("信息更新成功！！！");



    }





}
