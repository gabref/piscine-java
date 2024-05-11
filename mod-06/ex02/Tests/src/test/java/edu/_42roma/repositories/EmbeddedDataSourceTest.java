package edu._42roma.repositories;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class EmbeddedDataSourceTest {
	EmbeddedDatabase dataSource;

	@BeforeEach
	public void init() throws SQLException {
		dataSource = new EmbeddedDatabaseBuilder()
			.setType(EmbeddedDatabaseType.HSQL)
			.addScript("schema.sql")
			.addScript("data.sql")
			.build();
	}

	@Test
	public void DatasourceConnectionTest() throws SQLException {
		assertNotNull(dataSource.getConnection());
	}

	@AfterEach
	public void destroy() {
		dataSource.shutdown();
	}
}
