import java.util.List;
import model.dao.AuthorDao;
import model.dao.BookDao;
import model.dao.impl.AuthorDaoImpl;
import model.dao.impl.BookDaoImpl;
import model.entity.Author;
import model.entity.Book;

public class MainApplication {

  public static void main(String[] args) {
    BookDao bookDao = new BookDaoImpl();

    List<Book> books = bookDao.getAllBooksWithAuthors();

    Book book = bookDao.getBook(25);

    AuthorDao authorDao = new AuthorDaoImpl();

    List<Author> authors = authorDao.getAllAuthorsWithBook();

    System.out.println("Finished");

//    var author = Author.builder().id(1).build();
//    var insertBook = Book.builder().year(2010).title("My new title").author(author).build();
//
//    bookDao.create(insertBook);

//    Properties prop = new Properties();
//
//    try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
//      prop.load(input);
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//
//    Connection connection = DriverManager.getConnection(prop.getProperty("db.url"), prop.getProperty("db.user"),
//        prop.getProperty("db.password"));
//
//    connection.setAutoCommit(false);
//
//    try {
//      String sql = "update account set summ = ? where name=?";
//      PreparedStatement preparedStatement = connection.prepareStatement(sql);
//      preparedStatement.setDouble(1, 0);
//      preparedStatement.setString(2, "Ivan");
//
//      preparedStatement.execute();
//
//      preparedStatement.setDouble(1, 100);
//      preparedStatement.setString(2, "Petya");
//
//      preparedStatement.execute();
//      Statement statement = connection.createStatement();
//
//      statement.addBatch("update account set summ = 0 where name=\"Ivan\"");
//      statement.addBatch("update account set summ = 100 where name=\"Petya\"");
//
//      statement.executeBatch();

//      connection.commit();
//      preparedStatement.close();
//    } catch (Exception e) {
//      connection.rollback();
//    }
//
//    connection.close();

  }
}
