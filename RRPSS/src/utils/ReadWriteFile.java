package utils;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import entity.Dessert;
import entity.Drinks;
import entity.MainCourse;
import entity.MenuItem;

/**
 * The Class ReadWriteFile.
 */
public class ReadWriteFile {
	
	/**
	 *  Write fixed content to the given file.
	 *
	 * @param fileName the file name
	 * @param data the data
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void write(String fileName, List data) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(fileName));

		try {
			for (int i = 0; i < data.size(); i++) {
				out.println((String) data.get(i));
			}
		} finally {
			out.close();
		}
	}

	/**
	 *  Read the contents of the given file.
	 *
	 * @param fileName the file name
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public List read(String fileName) throws IOException {
		List data = new ArrayList();
		Scanner scanner = new Scanner(new FileInputStream(fileName));
		try {
			while (scanner.hasNextLine()) {
				data.add(scanner.nextLine());
			}
		} finally {
			scanner.close();
		}
		return data;
	}
}