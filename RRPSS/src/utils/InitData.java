package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import entity.Dessert;
import entity.Drinks;
import entity.MainCourse;
import entity.MenuItem;

public class InitData {

	private static ReadWriteFile fileToDB = new ReadWriteFile();
	private static final String SEPARATOR = "|";
	
	public static ArrayList<MenuItem> readMenuItem(String filename) throws IOException {

		ArrayList<String> stringArray = (ArrayList<String>) fileToDB.read(filename);
		ArrayList<MenuItem> itemList = new ArrayList<MenuItem>() ;

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	
				String name = star.nextToken().trim();
				String description = star.nextToken().trim();
				int type = Integer.parseInt(star.nextToken());
				double price = Double.parseDouble(star.nextToken().trim()); 
				
				MenuItem item = null;
				
				switch (type) {
					case 1:
						item = new MainCourse(name, description, price);
						break;
					case 2: 
						item = new Dessert(name, description, price);
						break;
					case 3:
						item = new Drinks(name, description, price);
						break;
					default:
						System.out.println("Error");
						item = new MainCourse(name, description, price);
				}

				itemList.add(item);
				
			}
			return itemList ;
	}
	
	public static void saveMenuItem(String filename, List al) throws IOException {
		List alw = new ArrayList() ;

        for (int i = 0 ; i < al.size() ; i++) {
        		MenuItem item = (MenuItem)al.get(i);
				StringBuilder st = new StringBuilder() ;
				st.append(item.getName().trim());
				st.append(SEPARATOR);
				st.append(item.getDescription().trim());
				st.append(SEPARATOR);
				String type = item.getType();
				if (type == "Main Course") {
					st.append(1);
				}
				else if(type == "Dessert") {
					st.append(2);
				}
				else if (type == "Drinks") {
					st.append(3);
				}
				st.append(SEPARATOR);
				st.append(item.getPrice());
				alw.add(st.toString()) ;
			}
        fileToDB.write(filename,alw);
	}


//	public static void main(String[] aArgs) {
//
//		InitData txtDB = new InitData();
//		String filename = "res/MenuItems.txt";
//
//		try {
//			// read file containing Professor records.
//			ArrayList al = InitData.readMenuItem(filename);
//			for (int i = 0; i < al.size(); i++) {
//				MenuItem item = (MenuItem) al.get(i);
//				System.out.println("Name: " + item.getName());
//				System.out.println("Description: " + item.getDescription());
//				System.out.println("Type: " + item.getType());
//				System.out.println("Price: $" + item.getPrice());
//				System.out.println();
//
//			}
//			InitData.saveMenuItem(filename, al);
//		} catch (IOException e) {
//			System.out.println("IOException > " + e.getMessage());
//		}
//	}
	
}

