package edu._42roma.repositories;

import edu._42roma.models.Product;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ProductsRepositoryJdbcImplTest extends Assertions {
	final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
			new Product(0L, "abacaxi", 12),
			new Product(1L, "banana", 8),
			new Product(2L, "orange", 10),
			new Product(3L, "apple", 6),
			new Product(4L, "grapes", 15));
	final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(1L, "banana", 8);
	final Product EXPECTED_UPDATED_PRODUCT = new Product(3L, "orange", 20);

	EmbeddedDatabase dataSource;
	ProductsRepository productsRepository;

	@BeforeEach
	public void init() throws SQLException {
		this.dataSource = new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.HSQL)
				.addScript("schema.sql")
				.addScript("data.sql")
				.build();
		try {
			productsRepository = new ProductsRepositoryJdbcImpl(dataSource.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFindAll() {
		System.out.println(productsRepository.findAll());
		assertEquals(EXPECTED_FIND_ALL_PRODUCTS, productsRepository.findAll());
	}

	@Test
	public void testFindById() throws Exception {
		Long id = 1L;
		assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, productsRepository.findById(id).get());
	}

	@Test
	public void checkFindByIdException() {
		assertThrows(RuntimeException.class, () -> productsRepository.findById(25L));
	}

	@Test
	public void testUpdate() throws SQLException {
		Product expectedProduct = productsRepository.findById(EXPECTED_UPDATED_PRODUCT.getId()).orElse(null);
		expectedProduct.setPrice(EXPECTED_UPDATED_PRODUCT.getPrice());
		expectedProduct.setName(EXPECTED_UPDATED_PRODUCT.getName());
		productsRepository.update(expectedProduct);
		Product result = productsRepository.findById(expectedProduct.getId()).orElse(null);
		System.out.println(result + "\n" + EXPECTED_UPDATED_PRODUCT);
		assertNotNull(result);
		assertEquals(result, EXPECTED_UPDATED_PRODUCT);
	}

	@Test
	public void testSave() {
		Integer countBefore = productsRepository.findAll().size();
		productsRepository.save(new Product(6L, "newProduct", 1210));
		System.out.println(productsRepository.findAll());
		assertEquals(countBefore, productsRepository.findAll().size() - 1);
	}

	@Test
	public void testDelete() {
		Integer countBefore = productsRepository.findAll().size();
		productsRepository.delete(6L);
		System.out.println(productsRepository.findAll());

		assertEquals(countBefore, productsRepository.findAll().size());
	}

	@AfterEach
	public void end() {
		dataSource.shutdown();
	}
}
