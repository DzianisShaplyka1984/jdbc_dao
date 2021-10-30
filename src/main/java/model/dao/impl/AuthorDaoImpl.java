package model.dao.impl;

import static model.util.AuthorUtil.checkAuthorExist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.DataSourceManager;
import model.dao.AuthorDao;
import model.entity.Author;
import model.entity.Book;

public class AuthorDaoImpl implements AuthorDao {

  @Override
  public void create(Author author) {

  }

  @Override
  public void update(Author author) {

  }

  @Override
  public void delete(Author author) {

  }

  @Override
  public List<Author> getAll() {
    List<Author> authors = new ArrayList<>();

    try (Connection connection = DataSourceManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from author")) {
      ResultSet rs = statement.executeQuery();

      while (rs.next()) {
        Author author = new Author(rs.getInt("id"), rs.getString("name"));

        authors.add(author);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return authors;
  }

  @Override
  public List<Author> getAllAuthorsWithBook() {
    List<Author> authors = new ArrayList<>();

    try (Connection connection = DataSourceManager.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT b.id as book_id, title, year, name, a.id FROM author a "
            + "left join book b on b.author_id = a.id;")) {
      ResultSet rs = statement.executeQuery();

      while (rs.next()) {
        var author = checkAuthorExist(rs.getInt("id"), rs.getString("name"), authors);

        var book = Book.builder().id(rs.getInt("book_id")).year(rs.getInt("year")).title(rs.getString("title")).build();
        author.getBooks().add(book);

      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return authors;
  }

  @Override
  public Author getAuthor(int id) {
    return null;
  }

  @Override
  public List<Author> getAuthor(String name) {
    return null;
  }
}
