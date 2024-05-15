package edu._42roma.classes;

import java.util.StringJoiner;

public class Car {
	private Integer year;
	private Double priceUSA;
	private Double priceUE;
	private String brand;

	public Car() {
		this.year = 0;
		this.priceUE = 0.0;
		this.priceUSA = 0.0;
		this.brand = "default";
	}

	public Car(Integer year, Double priceUE, Double priceUSA, String brand) {
		this.year = year;
		this.priceUE = priceUE;
		this.priceUSA = priceUSA;;
		this.brand = brand;
	}

	public Integer yearsSinceProduction() {
		return 2024 - year;
	}

	@Override
	public String toString() {
		return new StringJoiner("Car [ ")
			.add("brand = '" + brand + "', ")
			.add("priceUE = $" + priceUE + ", ")
			.add("priceUsa = $" + priceUSA + " ")
			.add("]")
			.toString();
	}
}
