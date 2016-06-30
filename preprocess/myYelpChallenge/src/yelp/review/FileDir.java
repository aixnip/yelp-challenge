/**
 * 
 */
package yelp.review;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author siliu
 *
 */
public class FileDir {

	// create file
	public static void createFile(String filename, String content) {

		// TODO Auto-generated constructor stub
		File file = new File(filename);
		if (!file.exists()) {
			try {
				/*
				 * if (file.createNewFile()) { System.out.println(filename +
				 * " is created!"); } else { System.out.println(
				 * "Failed to create file!"); }
				 */
				file.createNewFile();

				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);

				bw.write(content);
				bw.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.out.println(filename + " create fail!");
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.out.println(filename + " cannot be written!");
			}
		}
	}

}
