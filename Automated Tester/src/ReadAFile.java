import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ReadAFile {
	
	String path;
	List<String[]> res;
	
	public ReadAFile(String filePath){
		path = filePath;
		res = new ArrayList<>();
	}
	
	public void printGreetingAndTotalCount() {
		System.out.println("\n*************************************************************************");
		System.out.println("WELCOME!");
		String line = "";
		int counter = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			//s.nextLine(); //skip the first line
			try {
				while((line = br.readLine()) != null) {
					String[] values = line.split(",");
					res.add(values);
					counter++;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("\n*************************************************************************");
			System.out.println("File Scanning Done!");
			System.out.println("We have found "+ counter + " tickets in your file.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("\n*************************************************************************");
		System.out.println("Start the testing...");
		System.out.println("Testing Rule: Each ticket must follow this approval sequence");
		System.out.println("1)Dev Approval -> 2)QA Approval -> 3)BAT Approval");
		System.out.println("\n*************************************************************************");
	}
	
	public List<String[]> getResArray() {
		return res;
	}
}