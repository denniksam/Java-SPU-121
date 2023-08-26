package step.learning.ioc;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class IocApp {
    @Inject @Named("Hash-128")
    private HashService hashService ;

    private final HashService hashService2 ;
    @Inject
    public IocApp( @Named("Hash-160") HashService hashService2 ) {
        this.hashService2 = hashService2;
    }

    public void demo() {
        String str = "123";
        System.out.println( "Hash of " + str + " = " + hashService.hash(str) ) ;
        System.out.println( "Hash2 of " + str + " = " + hashService2.hash(str) ) ;
        System.out.println( hashService.hashCode() + " " + hashService2.hashCode() ) ;
    }
}
/*
Особливості інжекції
- перед точкою інжекції слід зазначати анотацію @Inject (com.google.inject.Inject)
- інжекція у приватні поля - працює
- якщо клас залежності "видимий" у проєкті, то інжектор його знаходить автоматично
- за замовчанням залежності впроваджуються як Transient, тобто новий об'єкт у
   кожній точці інжекції. Альтернативний варіант - Singleton, коли всі точки інжекції
   отримають один об'єкт зазначається анотацією до класу-залежності (сервісу)
- інжекція через конструктор дозволяє утворювати константні посилання на служби,
   що є кращим підходом. Для інжекції через конструктор анотація @Inject вживається
   перед конструктором, усі його параметри є інжектованими
- DIP рекомендує створювати інтерфейси і зазначати залежності від них, це спрощує
   зміну служб без втручання у код точок інжекції. Але при цьому вимагається
   "зв'язування" (binding) інтерфейсів та класів, що їх реалізують. Це здійснюється
   у конфігураційних модулях (класах)
- За потреби кількох реалізацій одного типу (інтерфейсу) вживаються іменовані
   залежності. Точки інжекції анотуються @Named("Hash-160"), а інструкції зв'язування
   доповнюються посиланням на іменування
            bind( HashService.class )
                .annotatedWith( Names.named( "Hash-160" ) )
                .to( ShaHashService.class ) ;
 */
