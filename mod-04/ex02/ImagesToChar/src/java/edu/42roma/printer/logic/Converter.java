package edu._42roma.printer.logic;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Converter {
	private final char white;
	private final char black;
	private int[][] arr2d;
	private int xMax;
	private int yMax;

	public Converter(char white, char black) {
		this.white = white;
		this.black = black;
	}

	public void printImageTerminal() {
		try {
			readBmpImage();
			for (int y = 0; y < yMax; y++) {
				for (int x = 0; x < xMax; x++) {
					if (arr2d[x][y] == 1)
						System.out.print(white);
					else
						System.out.print(black);
				}
				System.out.println();
			}
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	private void readBmpImage() throws IOException {
		BufferedImage image = ImageIO.read(Converter.class.getResource("/resources/image.bmp"));

		xMax = image.getWidth();
		yMax = image.getHeight();
		arr2d = new int[xMax][yMax];

		for (int xp = 0; xp < xMax; xp++) {
			for (int yp = 0; yp < yMax; yp++) {
				int color = image.getRGB(xp, yp);
				if (color == Color.BLACK.getRGB())
					arr2d[xp][yp] = 0;
				else
					arr2d[xp][yp] = 1;
			}
		}
	}
}
