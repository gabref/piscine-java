package edu._42roma.app;

import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;
import java.util.StringJoiner;

import edu._42roma.classes.Car;
import edu._42roma.classes.User;

public class Program {
	private static Class<?> clazz;
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		try {
			showClasses();
			String className = getClassName();
			clazz = Class.forName("edu._42roma.classes." + className);

			printFieldsAndMethods();
			createObject();

			scanner.close();
		} catch (Exception e) {
			System.err.println("An error occurred");
			System.err.println("-> " + e.getClass() + " - " + e.getMessage());
		}
	}

	private static void showClasses() throws ClassNotFoundException {
		Class<?> classUser = Class.forName("edu._42roma.classes.User");
		Class<?> classCar = Class.forName("edu._42roma.classes.Car");

		String userClassName = classUser.getSimpleName();
		String carClassName = classCar.getSimpleName();

		System.out.println("Classes:");
		System.out.println(userClassName);
		System.out.println(carClassName);
	}

	private static void printHr() {
		System.out.println("-----------------");
	}

	private static String getClassName() {
		printHr();
		System.out.println("Enter class name:");
		return scanner.next();
	}

	private static void printFieldsAndMethods() {
		printHr();
		System.out.println("fields :");
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			String type = field.getAnnotatedType().toString();
			if (type.contains("java.lang."))
				type = type.substring(10);
			System.out.println("\t" + type + " " + field.getName());
		}

		System.out.println("methods :");
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			String type = method.getAnnotatedReturnType().toString();
			if (type.contains("java.lang."))
				type = type.substring(10);
			System.out.println("\t" + type + " " + method.getName());
		}
	}

	private static void createObject() throws Exception {
		printHr();
		System.out.println("Let's create an object.");
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field.getName() + ":");
			field.setAccessible(true);
			if (field.getType() == Integer.class) {
				field.set(clazz, scanner.nextInt());
			} else if (field.getType() == String.class) {
				String input = scanner.nextLine();
				System.out.println("Type: " + input.getClass());
				field.set(clazz, input);
			} else if (field.getType() == Double.class) {
				field.set(clazz, scanner.nextDouble());
			} else if (field.getType() == Boolean.class) {
				field.set(clazz, scanner.nextBoolean());
			}
		}
		System.out.println("Object created: " + clazz);
	}
}
