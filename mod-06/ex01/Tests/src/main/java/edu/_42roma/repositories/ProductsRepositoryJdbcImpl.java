package edu._42roma.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import edu._42roma.models.Product;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {

	private final Connection connection;

	public ProductsRepositoryJdbcImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Product> findAll() {
		List<Product> products = new LinkedList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM products");
			while (rs.next()) {
				products.add(new Product(
						rs.getLong("id"),
						rs.getString("name"),
						rs.getInt("price")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public Optional<Product> findById(Long id) throws RuntimeException {
		try {
			Optional<Product> products = Optional.empty();
			ResultSet rs = null;
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM products WHERE id = ?");
			statement.setLong(1, id);
			rs = statement.executeQuery();
			if (rs.next()) {
				products = Optional.of(new Product(
						rs.getLong("id"),
						rs.getString("name"),
						rs.getInt("price")));
				return products;
			} else {
				throw new SQLException("");
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	@Override
	public void update(Product product) {
		String sql = "UPDATE products SET " +
				"name = ?, " +
				"price = ?" +
				"WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, product.getName());
			statement.setInt(2, product.getPrice());
			statement.setLong(3, product.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void save(Product product) {
		String sql = "INSERT INTO products (name, price) VALUES (?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, product.getName());
			statement.setInt(2, product.getPrice());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM products WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
