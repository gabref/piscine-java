package edu._42roma.printer.app;

import edu._42roma.printer.logic.Converter;
// import print.color.ColoredPrinter;

public class Program {
	public static void main(String[] args) {
		if (args.length != 2 || args[0].length() > 1 || args[1].length() > 1) {
			System.err.println("Error with inputs");
			System.err.println("Usage: app <char white> <char black> <absolute/path>");
			System.exit(1);
		}

		try {
			Class.forName("print.color.ColoredPrinter");
			System.out.println("driver loaded");
		} catch (Exception e) {
			System.err.println("couldn't find driver");
			System.err.println(e);
			System.exit(1);
		}

		// ColoredPrinter cp = new ColoredPrinter.Builder(1, false)
		// 		.foreground(FColor.WHITE).background(BColor.BLUE) // setting format
		// 		.build();
		// // printing according to that format
		// cp.println(cp);
		// cp.setAttribute(Attribute.REVERSE);
		// cp.println("This is a normal message (with format reversed).");

		char white = args[0].charAt(0);
		char black = args[1].charAt(0);

		Converter converter = new Converter(white, black);
		converter.printImageTerminal();
	}
}
