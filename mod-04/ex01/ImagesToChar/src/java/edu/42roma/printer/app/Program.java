package edu._42roma.printer.app;

import edu._42roma.printer.logic.Converter;

public class Program {
	public static void main(String[] args) {
		if (args.length != 2 || args[0].length() > 1 || args[1].length() > 1) {
			System.err.println("Error with inputs");
			System.err.println("Usage: app <char white> <char black> <absolute/path>");
			System.exit(1);
		}

		char white = args[0].charAt(0);
		char black = args[1].charAt(0);

		Converter converter = new Converter(white, black);
		converter.printImageTerminal();
	}
}
