package step.learning.oop;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Literature> funds ;

    private void fillFunds() {
        funds = new ArrayList<>() ;
        // Book book = new Book() ;
        // book.setAuthor( "D. Knuth" ) ;
        // book.setTitle( "Art of Programming" ) ;
        Book book = new Book( "D. Knuth", "Art of Programming" ) ;
        funds.add( book ) ;
        Newspaper newspaper = new Newspaper() ;
        newspaper.setTitle( "Daily Telegraph" ) ;
        try { newspaper.setDate( "2023-08-19" ) ; }
        catch (ParseException e) { System.err.println( "Invalid date"); }
        funds.add( newspaper ) ;
        Journal journal = new Journal() ;
        journal.setTitle( "ArgC & ArgV" ) ;
        journal.setNumber( 314 ) ;
        funds.add( journal ) ;
    }

    /**
     * Виводить тільки періодичні видання
     */
    private void showPeriodic() {
        for( Literature literature : funds ) {
            if( literature instanceof Periodic ) {
                System.out.print( literature.getCard() ) ;
                System.out.println(
                        " Comes " + ((Periodic) literature).getPeriod()
                ) ;
            }
        }
    }

    public void demo() {
        System.out.println( "Library" ) ;
        fillFunds() ;
        for( Literature literature : funds ) {
            System.out.println( literature.getCard() ) ;
        }
        System.out.println( "------------ PERIODIC ----------------" ) ;
        showPeriodic() ;
    }
}
/*
Завдання:
Реалізувати метод showNonPeriodic(), який виводить тільки неперіодичні видання.

Створити інтерфейс-маркер Copyable (чи можна скопіювати на ксерографі)
 можна копіювати книги та журнали, газети завеликі і не копіюються
Реалізувати методи showCopyable() та showNonCopyable()

Додати декілька нових типів літератури:
 Буклет (назва, видавець(типографія)) - неперіодичний, копійований
 Постер (назва, кольоровість(boolean)) - неперіодичний, некопійований
 створити декілька об'єктів, додати до фондів, переконатись у роботі
 */
/*
Бібліотека: є фонди, які містять різну літературу - книги, газети, журнали, тощо.
Є спільні риси - картка для каталога - вона існує для будь-якої літератури
Є відмінні риси - у книги є автор(и), у газет - дата виходу, у журналів - номер
--------------------------
Предметна галузь: книги, газети, журнали
 створюємо об'єкти: Book{Author, Title}, Newspaper{Date, Title}, Journal{Number, Title}
 абстрагування: виділення структурно-ієрархічних "вузлів", які призначені
  для групування (поліморфізму) та спрощення (відокремлення спільних даних)
                             Literature{Title}
                           /        |         \
               Book{Author}   Newspaper{Date}   Journal{Number}
 спільна та індивідуальна функціональність
   потрібний спільний засіб (метод, властивість) для формування картки (каталогу)
    Literature{Title, getCard()}
   на рівні класу Literature метод getCard() не має сенсу реалізовувати, тому
    він залишається абстрактним. Значить кожен з об'єктів має надати свою реалізацію
 */
/*
ООП - об'єктно-орієнтована парадигма програмування
Для того щоб опанувати ООП у новій мові програмування слід розглянути:
- оголошення класів, різновиди (чи є інтерфейси, enum, тощо)
- конструктори та створення об'єктів
- спадкування та реалізація
- поліморфізм - сумісність типів даних
- перетворення типів
 */
/*
Імперативна ~алгоритмічна - повне управління виконавцем (процесором)
  поняття: інструкція (її номер/адреса, перехід до іншої інструкції)
  відгалуження: процедурний підхід, структурне програмування
  мови: машинний код, ассемблери, C/C++, Basic, Fortran,

Декларативна - встановлення певних правил, відношень, аксіом та перевірка
     на базі них різних тверджень
  поняття: домен, clause, бектрекінг
  вігалуження: логічна парадигма
  мови: Prolog

ООП - програма подається як об'єкти та їх взаємодія
  поняття: інкапсуляція, поліморфізм, наслідування (спадкування)
  відгалуження: прототипне програмування (JS)
  мови: C#, Java

Функціональна - послідовне виконання функцій над вхідними даними до досягнення
     потрібної мети.
  поняття: лямбда (функція), кортеж, голова-хвіст
  відгалужень: чисті парадигми
  мови: LISP, Python, J, Haskell
 */
