package model.util;

import java.util.ArrayList;
import java.util.List;
import model.entity.Author;

public class AuthorUtil {
  private AuthorUtil() {}

  public static Author checkAuthorExist(int authorId, String name, List<Author> authors) {
    var mayBeAuthor = authors.stream().filter(author -> author.getId() == authorId).findFirst();

    if (mayBeAuthor.isPresent()) {
      return mayBeAuthor.get();
    } else {
      var author = Author.builder()
          .id(authorId)
          .name(name)
          .books(new ArrayList<>())
          .build();

      authors.add(author);

      return author;
    }
  }
}
