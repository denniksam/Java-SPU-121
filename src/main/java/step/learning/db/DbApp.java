package step.learning.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DbApp {
    private Connection connection ;
    public void demo() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/java_spu121?useUnicode=true&characterEncoding=UTF-8",
                    "spu121", "pass121");
        }
        catch( Exception ex ) {
            System.err.println( ex.getMessage() ) ;
            return ;
        }
        System.out.println( "Connection OK" ) ;

        String sql = "CREATE TABLE IF NOT EXISTS randoms (" +
                "id         CHAR(36) PRIMARY KEY," +
                "rand_int   INT," +
                "rand_float FLOAT," +
                "rand_str   TEXT" +
                ") Engine = InnoDB, DEFAULT CHARSET = utf8" ;
        try( Statement statement = connection.createStatement() ) {  // ~ SqlCommand (ADO)
            statement.executeUpdate( sql ) ;
        }
        catch( Exception ex ) {
            System.err.println( ex.getMessage() ) ;
            return ;
        }
        System.out.println( "CREATE OK" ) ;
        /*
        Реалізувати сервіс генерації випадкових даних: ціле число, дробове число, рядок
        Інжектувати сервіс у DbApp
        Зформувати запит на INSERT даних у таблицю БД, підставити в нього випадкові дані
        від сервісу.
         */
    }
}
/* JDBC - технологія доступу до даних, аналог ADO.NET
1. Встановлюємо коннектор - драйвер БД (https://mvnrepository.com/artifact/com.mysql/mysql-connector-j)
2. БД та рядок підключення
    (на прикладі PlanetScale)
        jdbc:mysql://aws.connect.psdb.cloud/maindb?sslMode=VERIFY_IDENTITY
    на прикладі локальної БД
        jdbc:mysql://localhost:3306/java_spu121?useUnicode=true&characterEncoding=UTF-8

    CREATE DATABASE java_spu121 ;
    GRANT ALL PRIVILEGES ON java_spu121.* TO 'spu121'@'localhost' IDENTIFIED BY 'pass121' ;
 */
