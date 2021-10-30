package model.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {
  private int id;
  private String name;
  private List<Book> books;

  public Author(int id, String name) {
    this.id = id;
    this.name = name;
  }
}
