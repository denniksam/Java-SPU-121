package step.learning;

import step.learning.basics.ArraysLoops;
import step.learning.basics.Collections;
import step.learning.basics.Variables;
import step.learning.oop.Library;

public class App
{
    public static void main( String[] args )
    {
        // new Variables().demo() ;
        // new ArraysLoops().demo() ;
        // new Collections().demo() ;
        new Library().demo() ;
    }
}
/* Встановлення
Win-R cmd (terminal)
> java -version
якщо працює, переходимо далі,
якщо помилка - встановлюємо JRE

Встановлюємо JDK
або) через скачаний файл
або) через інструмент "новий проєкт" в Idea

Новий проєкт
 - (ліворуч) Maven Archetype
 - назва проєкту Java-SPU-121
 - JDK - вибираємо (або скачуємо) 1.8
 - Archetype: quickstart
     org.apache.maven.archetypes:maven-archetype-quickstart
 - Advanced Settings
  = GroupId: step.learning

Після створення проєкту налаштовуємо конфігурацію запуску
(за замовчанням - Current File)
Edit Configuration -- + -- Application  -- назва: Арр, Main class -
 вибір - App
 */
/* Вступ
Java - мова програмування
 - тип: транслятор (з проміжним кодом)
    - платформа: JRE (Java Runtime Environment) [ JVM ]
    - засоби (компілятор): JDK (Development Kit)
       - вихідний код: *.java
       - проміжний код: *.class
 - покоління: 4GL
 - парадигма: ООП (+мульти)
Комплекти:
 Java SE (Standard Edition) - базовий набір - консоль, десктоп
 Java EE (Enterprise Edition) - SE + мережа (у т.ч. веб)
Версії:
 1: 1.0 - 1.8 + підтримка ... 1.8.361 ...
 11+
 Особливості - у Java повна підтримка зворотної сумісності,
 будь-який код, написаний на попередніх версіях, має виконуватись
 на наступних
Системи збирання:
 Maven, Gradle, Ant  -- структура папок проєкту, способи зазначення
 залежностей, тощо
Особливості:
 - залежність структури проєкту та файлової системи:
 - назва файлу має збігатись із назвою класу, що в ньому описаний
   = в одному файлі може бути тільки один клас (public)
   = один клас повністю визначається в одному файлі
   = для імен класів рекомендується CapitalCamelCase
   = для змінних та методів - lowerCamelCase
 - папка відповідає за пакет (package) ~ namespace
   = назва пакету має збігатись з назвою папки (та її структурою)
   = для просторів імен рекомендується snake_case
 */
