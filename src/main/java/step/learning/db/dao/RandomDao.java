package step.learning.db.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import step.learning.db.dto.RandomRecord;
import step.learning.services.random.RandomService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/* DAO - Data Access Object - Об'єкт доступу до даних -
сукупність методів, які працюють з БД та DTO
 */
@Singleton
public class RandomDao {
    private final Connection connection ;   // інжектуємо підключення
    private final Logger logger ;  // Guice автоматично реєструє логер
    private final RandomService randomService ;

    @Inject
    public RandomDao(@Named("Local") Connection connection, Logger logger, RandomService randomService) {
        this.connection = connection ;
        this.logger = logger;
        this.randomService = randomService;
    }

    public void ensureCreated() {
        String sql = "CREATE TABLE IF NOT EXISTS randoms (" +
                "id         CHAR(36) PRIMARY KEY," +
                "rand_int   INT," +
                "rand_float FLOAT," +
                "rand_str   TEXT" +
                ") Engine = InnoDB, DEFAULT CHARSET = utf8" ;
        try( Statement statement = connection.createStatement() ) {  // ~ SqlCommand (ADO)
            statement.executeUpdate( sql ) ;
        }
        catch( SQLException ex ) {
            logger.log( Level.SEVERE, ex.getMessage() ) ;
            throw new RuntimeException( "CREATE fails. Details in logs." ) ;
        }
    }

    public List<RandomRecord> getAll() {
        String sql = "SELECT * FROM randoms" ;
        try( Statement statement = connection.createStatement() ) {
            ResultSet res = statement.executeQuery( sql ) ;
            /*  JDBC                           DBMS
                 sql ------------------------> execute ->
                 res <------------------------ iterator
                 res.next() -----------------> iterator.next() -> (перший рядок)
                    (1)-id  <----------------- [id, int, float, str]
                    (2)-int ...
                 res.getString(1) == id (першого рядку)

                 res.next() -----------------> iterator.next() -> (другий рядок)
                    (1)-id  <----------------- [id, int, float, str]
                    (2)-int ...(заміна раніше одержаних даних)
                 res.getString(1) == id (другого рядку)
             */
            List<RandomRecord> ret = new ArrayList<>() ;
            while( res.next() ) {
                ret.add( new RandomRecord( res ) ) ;
            }
            return ret ;
        }
        catch( SQLException ex ) {
            logger.log( Level.SEVERE, ex.getMessage() ) ;
            throw new RuntimeException( "GET fails. Details in logs." ) ;
        }
    }

    public void insertRandom() {
        // Підготовлений (параметризований) запит - SQL окремо, дані окремо
        String sql = "INSERT INTO randoms (id, rand_int, rand_float, rand_str) VALUES (?, ?, ?, ?)" ;
        try( PreparedStatement preparedStatement = connection.prepareStatement( sql ) ) {
            // заповнення даних - за номерами параметрів (плейсхолдерів - ?)
            preparedStatement.setString( 1, UUID.randomUUID().toString() ) ;
            // на місце 1-го знака ? буде підставлено id як String
            // !! звертаємо увагу - у JDBC відлік починається з 1 !!

            preparedStatement.setInt(    2, randomService.getInt() ) ; // rand_int
            preparedStatement.setDouble( 3, randomService.getDouble() ) ; // rand_float
            preparedStatement.setString( 4, randomService.getString() ) ; // rand_str

            // після встановлення всіх параметрів запит виконується типовим способом
            preparedStatement.executeUpdate() ;
            // підготовлені запити можна виконувати декілька разів з різними параметрами -
            // один раз підготувати, а циклічно - встановлювати параметри та виконувати
        }
        catch( SQLException ex ) {
            logger.log( Level.SEVERE, ex.getMessage() ) ;
            throw new RuntimeException( "INSERT fails. Details in logs." ) ;
        }
    }

    public void insert( RandomRecord record ) {
        /*
        Д.З. Реалізувати метод insert( RandomRecord record ) у класі
        RandomDao, забезпечити його перевірку зміною у DbApp (замість
        insertRandom використати insert)
        Додати та випробувати методи RandomDao:
        void delete( RandomRecord record )
        void update( RandomRecord record ) - оновлюються дані по record.id
        bool check( RandomRecord record ) - перевірити, чи є такий запис у БД
         */
    }
}
