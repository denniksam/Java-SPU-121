package step.learning.db;

import com.google.inject.Inject;
import step.learning.db.dao.RandomDao;
import step.learning.db.dto.RandomRecord;
import step.learning.services.random.RandomService;

public class DbApp {
    private final RandomService randomService ;
    private final RandomDao randomDao ;
    @Inject
    public DbApp(
            RandomService randomService,
            RandomDao randomDao
    ) {
        this.randomService = randomService;
        this.randomDao = randomDao;
    }

    public void demo() {
        try {
            randomDao.ensureCreated() ;
            System.out.println( "CREATE OK" ) ;

            randomDao.insertRandom() ;
            System.out.println( "INSERT OK" ) ;

            for( RandomRecord rec : randomDao.getAll() ) {
                System.out.println( rec ) ;
            }
        }
        catch( RuntimeException ex ) {
            System.err.println( ex.getMessage() ) ;
            return ;
        }

        System.out.println( "-------------------------------" ) ;
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
