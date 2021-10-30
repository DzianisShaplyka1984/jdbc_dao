package model.dao;

import java.util.List;

public interface DefaultDao<T> {
  void create(T item);

  void update(T item);

  void delete(T item);

  List<T> getAll();
}
