package step.learning.basics;

import java.util.*;

public class Collections {
    public void demo() {
        // listDemo() ;
        mapDemo() ;
    }
    private void mapDemo() {
        Map<String, String> headers ;
        // headers = new LinkedHashMap<>();   // зі збереженням порядку додавання елементів
        headers =  new HashMap<>() ;  // без збереження порядку
        headers.put( "Content-Type", "text/html" ) ;
        headers.put( "Content-Length", "100500" ) ;
        headers.put( "Connection", "close" ) ;
        headers.put( "Host", "localhost" ) ;
        headers.put( "Authorization", "basic admin:password" ) ;
        for( String key : headers.keySet() ) {
            System.out.println( String.format(
                    " %s: %s",
                    key, headers.get( key )
            ));
        }
    }
    private void listDemo() {
        List<String> list1 =          // List - interface для колекцій-переліків
                new ArrayList<>() ;   // ArrayList - реалізація (з массивом)
        List<String> list2 =
                new LinkedList<>() ;  // Реалізація - з зв'язуванням
        list2.add( "String 1" ) ;
        list2.add( "String 2" ) ;
        list2.add( "String 3" ) ;
        list2.add( 2, "String 2.5" ) ;
        for( String str : list2 ) {
            System.out.println( str ) ;
        }
        String[] arr = list2.toArray(new String[0]);
        // List<int> list3;  error -- Type argument cannot be of primitive type
        List<Integer> list3;  // OK
    }
}
/* Колекції
Поділяються на переліки (list), множини (set) та асоціативні (map)
! Особливість Java у тому, що немає повного контролю Generic типів,
тобто колекція сприймається як колекція<Object>
 - немає гарантії перетворення типу    (List<String>) json
    [насправді гарантується (List<?>) ]
 - при перетворенні колекції у масив слід зазначати зразок його типу
    [  list2.toArray(new String[0])  ]
 - інтенсивна робота з колекціями не є бажаною, рекомендовано їх використовувати
    для прийому даних, після чого перевести дані у масив і вже з ним
    продовжувати активну роботу
 - ArrayList можна вважати певним компромісом, оскільки в ньому вживається
    масив, тобто після отримання всіх даних перетворення не є необхідним,
    але саме отримання даних може бути сповільнене, якщо в ньому багато
    операцій вставляння елементів (не у кінець масиву), вилучення елементів
 */
/*
Д.З. Реалізувати гру "хрестики-нолики"
поле реалізувати у вигляді подвійного масиву
хід користувача приймати або) номером клітинки або) координатами
   A  B  C
1  X                  X 2 3
2     0               4 0 6
3        X            7 8 X

Ваш хід: B1          Ваш хід: 2

Ускладнення: обирати розмір поля (три-в-ряд на полі більшого розміру)
Вести статистику гри (всього, виграшів, програшів -- рахунок)
Вибір опонента
Складність гри (випадкові ходи ПК чи осмислені)
Кольорове оформлення (приклад додається)



public class ConsoleColors {
    public static final String RESET = "\033[0m";  // Text Reset

    // Regular Colors
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE

    // Bold
    public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
    public static final String WHITE_BOLD = "\033[1;37m";  // WHITE

    // Underline
    public static final String BLACK_UNDERLINED = "\033[4;30m";  // BLACK
    public static final String RED_UNDERLINED = "\033[4;31m";    // RED
    public static final String GREEN_UNDERLINED = "\033[4;32m";  // GREEN
    public static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
    public static final String BLUE_UNDERLINED = "\033[4;34m";   // BLUE
    public static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
    public static final String CYAN_UNDERLINED = "\033[4;36m";   // CYAN
    public static final String WHITE_UNDERLINED = "\033[4;37m";  // WHITE

    // Background
    public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
    public static final String RED_BACKGROUND = "\033[41m";    // RED
    public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
    public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
    public static final String BLUE_BACKGROUND = "\033[44m";   // BLUE
    public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
    public static final String CYAN_BACKGROUND = "\033[46m";   // CYAN
    public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE

    // High Intensity
    public static final String BLACK_BRIGHT = "\033[0;90m";  // BLACK
    public static final String RED_BRIGHT = "\033[0;91m";    // RED
    public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
    public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
    public static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE
    public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
    public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN
    public static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE

    // Bold High Intensity
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

    // High Intensity backgrounds
    public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
    public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
    public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
    public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
    public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
    public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
    public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";  // CYAN
    public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   // WHITE
}

System.out.println(ConsoleColors.GREEN + "Equals" + ConsoleColors.RESET);

System.out.println(ConsoleColors.RED + "Not equals" + ConsoleColors.RESET);

 */