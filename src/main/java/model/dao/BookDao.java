package model.dao;

import java.util.List;
import model.entity.Book;

public interface BookDao extends DefaultDao<Book>{
  Book getBook(int id);

  List<Book> getBooks(String title);

  List<Book> getAllBooksWithAuthors();
}
