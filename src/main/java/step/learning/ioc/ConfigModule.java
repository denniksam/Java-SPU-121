package step.learning.ioc;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import step.learning.services.random.RandomService;
import step.learning.services.random.RandomServiceV1;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConfigModule extends AbstractModule {
    private Connection localConnection ;   // поле для збереження підключення для метода-провайдера

    @Override
    protected void configure() {
        // bind( HashService.class ).to( ShaHashService.class ) ;
        bind( HashService.class )
                .annotatedWith( Names.named( "Hash-128" ) )
                .to( Md5HashService.class ) ;
        bind( HashService.class )
                .annotatedWith( Names.named( "Hash-160" ) )
                .to( ShaHashService.class ) ;
        bind( RandomService.class )
                .to( RandomServiceV1.class ) ;
    }

    @Provides @Named("Local")
    private Connection getLocalConnection() {
        /* Методи-провайдери -- розширення засобів інжекції на випадки,
           коли треба більше контролю за об'єктами
           - у служби параметризовані конструктори і треба ними керувати
           - треба більше контролю за життєвим циклом служб
           Методи "орієнтуються" на тип повернення (тут - Connection), тобто
           постачають об'єкти в точки інжекції заданого типу.
           За необхідності кількох однотипних провайдерів - іменування
         */
        if( localConnection == null ) {  // перше звернення - відкриваємо підключення
            try {
                Class.forName( "com.mysql.cj.jdbc.Driver" ) ;
                localConnection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/java_spu121?useUnicode=true&characterEncoding=UTF-8",
                        "spu121", "pass121" ) ;
            }
            catch( Exception ex ) {
                System.err.println( ex.getMessage() ) ;
                return null ;
            }
        }
        return localConnection ;
    }
}

/* Inversion of Control (IoC) - інверсія управління
Існує декілька близьких терміни
DI  - Dependency Injection
DIP - Dependency Inversion Principle (літера D у SOLID)
IoC - Inversion of Control

Інверсія управління (чим?) життєвим циклом об'єктів - виділення певного
"центру управління" який буде "постачати" об'єкти у програму.
Замість інструкцій
 obj = new Class()  які гарантовано створюють нові об'єкти
переходимо до інструкцій
 obj = IoC.getInstance(Class)  які можуть вживати раніше створені об'єкти / або створювати нові


DI - інжекція залежностей - спосіб (засіб) передачі залежних об'єктів у
інші об'єкти. Розрізняють інжекцію у поля, у конструктор, у методи
     Dependency {}    -------   Class { Dependency _dependency ; }
  без інжекції   Class { Dependency _dependency = IoC.getInstance(Dependency) ; }
  з інжекцією (у поле)   Class { @Inject Dependency _dependency ; }


DIP - Dependency Inversion Principle (літера D у SOLID)
Принцип рекомендує відмовитись від типізації залежностей як класів:
  Dependency {}    -------   Class { Dependency _dependency ; }
і перейти до інтерфейсів:
  IService      Dependency : IService {}       Class { IService _dependency ; }
Це значно спрощує перехід до нових реалізацій залежностей
  DependencyNew : IService {} залишається сумісною з Class { IService _dependency ; }
  (але не буде сумісною з Class { Dependency _dependency ; } )


У Java є декілька поширених способів інверсії управління
javax - "з коробки" - найпростіші схеми
Spring - потужний комплекс, не лише призначений для ІоС
Guice - рішення від Google, спеціалізоване для ІоС
та інші

На прикладі Guice
1. Встановлюємо
    - знаходимо пакет у відповідності до типу проєкту (Maven -- https://mvnrepository.com/artifact/com.google.inject/guice)
    - додаємо залежність у конфігурацію проєкту (pom.xml)
    - оновлюємо (завантажуємо) залежності
2. Створюємо клас-конфігуратор (ConfigModule) як нащадок AbstractModule (com.google.inject.AbstractModule)
     переозначаємо у ньому метод-конфігуратор ( configure() )
3. Змінюємо структуру проєкту. Замість створення основного класу
        new Library().demo() ;
   утворюємо інжектор та "пропускаємо" через нього клас, утворюючи об'єкт (Resolve)
        Injector injector = Guice.createInjector( new ConfigModule() ) ;
        injector.getInstance( Library.class ).demo() ;

 */
