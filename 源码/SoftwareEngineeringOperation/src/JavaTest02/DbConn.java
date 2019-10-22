package JavaTest02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*连接数据库*/


public class DbConn {
    //声明connection对象
    public static  Connection con=null;
    //jdbc驱动，固定写法
    public static final String driver="com.mysql.cj.jdbc.Driver";
    //这里的数据库名称为zx
    public static final String url="jdbc:mysql://localhost:3306/test02?&useSSL=false&serverTimezone=UTC";
    public static final String user="root";
    public static final String  password="123456";

    static{
          try {
              //注册JDBC驱动程序
              Class.forName(driver);
              //getConnection()方法，连接MySQL数据库
              con = DriverManager.getConnection(url, user, password);
              /*if (!con.isClosed()) {
                  System.out.println("数据库连接成功");
              }*/
          } catch (ClassNotFoundException e) {
              System.out.println("数据库驱动没有安装");

          } catch (SQLException e) {
              e.printStackTrace();
              System.out.println("数据库连接失败");
          }
    }

    public static Connection getConnection() {
        return con;
    }



}





