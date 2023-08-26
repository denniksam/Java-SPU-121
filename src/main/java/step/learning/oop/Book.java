package step.learning.oop;

public class Book extends Literature {
    private String author ;

    public Book() {
    }

    public Book(String author, String title ) {
        this.setAuthor( author ) ;
        super.setTitle( title ) ;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String getCard() {
        return String.format(
                "Book: %s '%s'",
                this.getAuthor(),
                super.getTitle()
        );
    }
}
/* Щодо спадкування
Literature[ title  getTitle() ]

Book extends Literature
book.title  book.getTitle()
  |          |
  x    [ ----|------  author  getAuthor() ]  this
[ title  getTitle() ]  super
             |
        lit.getTitle()


        book.getTitle()
             |
          override                                 | Ілюстрація різниці
       [ getTitle()  author  getAuthor() ]  this   | this.getTitle() та
[ title  getTitle() ]  super                       | super.getTitle()
             |                                     | ! в межах одного об'єкту
        lit.getTitle()

!!!! Помилкогенність - вживання у кодах Book просто виклику getTitle()
бо насправді методів може бути два - this.getTitle() та super.getTitle()
якщо this.getTitle() не створено, то викликається super.getTitle()
АЛЕ якщо описати  this.getTitle() то виклик автоматично переключиться на нього
Висновок: дуже бажано вживати префікси this. або super.
 */
