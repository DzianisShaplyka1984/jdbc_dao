package model.dao;

import java.util.List;
import model.entity.Author;

public interface AuthorDao extends DefaultDao<Author>{
  Author getAuthor(int id);

  List<Author> getAuthor(String name);

  List<Author> getAllAuthorsWithBook();
}
