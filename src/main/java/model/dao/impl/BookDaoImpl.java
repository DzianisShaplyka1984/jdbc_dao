package model.dao.impl;

import static model.util.AuthorUtil.checkAuthorExist;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.DataSourceManager;
import model.dao.BookDao;
import model.entity.Author;
import model.entity.Book;

public class BookDaoImpl implements BookDao {

  public void create(Book book) {
    try (var connection = DataSourceManager.getInstance().getConnection();
         var statement = connection.prepareStatement("insert into book(title, year, author_id) values(?, ?, ?)")) {

      statement.setString(1, book.getTitle());
      statement.setInt(2, book.getYear());

      Integer authorId = null;

      if (book.getAuthor() != null) {
        authorId = book.getAuthor().getId();
      }

      statement.setInt(3, authorId);

      statement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void update(Book book) {

  }

  public void delete(Book book) {
    try (var connection = DataSourceManager.getInstance().getConnection();
        var statement = connection.prepareStatement("delete from book where id=?")) {

      statement.setInt(1, book.getId());

      statement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public List<Book> getAll() {
    List<Book> books = new ArrayList<>();

    try (var connection = DataSourceManager.getInstance().getConnection();
        var statement = connection.prepareStatement("select * from book")) {
      var rs = statement.executeQuery();

      while (rs.next()) {
        var book = Book.builder()
            .title(rs.getString("title"))
            .year(rs.getInt("year"))
            .id(rs.getInt("id"))
            .build();

        books.add(book);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return books;
  }

  public List<Book> getAllBooksWithAuthors() {
    List<Book> books = new ArrayList<>();

    try (var connection = DataSourceManager.getInstance().getConnection();
        var statement = connection.prepareStatement("SELECT b.id as book_id, title, year, a.id as author_id, name FROM book b "
            + "left join author a on b.author_id = a.id")) {
      var rs = statement.executeQuery();

      List<Author> authors = new ArrayList<>();

      while (rs.next()) {
        var book = Book.builder()
            .title(rs.getString("title"))
            .year(rs.getInt("year"))
            .id(rs.getInt("book_id"))
            .author(checkAuthorExist(rs.getInt("author_id"), rs.getString("name"), authors))
            .build();

        books.add(book);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return books;
  }

  public Book getBook(int id) {
    Book book = null;

    try (var connection = DataSourceManager.getInstance().getConnection();
        var statement = connection.prepareStatement("select * from book where id=?")) {

      statement.setInt(1, id);
      var rs = statement.executeQuery();

      if (rs.next()) {
        book = Book.builder().id(rs.getInt("id"))
            .title(rs.getString("title"))
            .year(rs.getInt("year"))
            .build();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return book;
  }

  public List<Book> getBooks(String title) {
    return null;
  }
}
