package edu._42roma.repositories;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import edu._42roma.models.Product;

public interface ProductsRepository {
    List<Product> findAll();
    Optional<Product> findById(Long id) throws SQLException;
    void update(Product product);
    void save(Product product);
    void delete(Long id);
}
