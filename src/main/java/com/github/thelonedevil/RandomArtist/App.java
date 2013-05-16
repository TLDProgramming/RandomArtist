package com.github.thelonedevil.RandomArtist;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.yaml.snakeyaml.Yaml;

public class App {
	static List<String> artists = new ArrayList<String>();
	static String artist;
	static File ayaml = new File("artists.yml");
	static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {
		yamlLoad();
		yamlSave();
		new1();
		random();
		System.out.println(artist);
		s.close();
	}

	@SuppressWarnings("unchecked")
	static void yamlLoad() {
		if (ayaml.exists()) {
			try {
				InputStream input = new FileInputStream(ayaml);
				Yaml yaml = new Yaml();
				Object object = yaml.load(input);
				artists = (List<String>) object;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (ClassCastException e1) {
				e1.printStackTrace();
				System.out.println("Error with yaml file");
			}
		}
	}

	static void yamlSave() {
		Yaml yaml = new Yaml();
		String output = yaml.dump(artists);
		try {
			ayaml.createNewFile();
			PrintStream out = new PrintStream("artists.yml");
			out.print(output);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void random() {
		artist = artists.get(new Random().nextInt(artists.size()));
	}

	static void add1() {
		System.out.println("Name of the artist?");
		String add2 = s.nextLine();
		artists.add(add2);
		System.out.println("Do you want to add any more artists? true/false");

	}

	static void command() {
		for (boolean i = true; i = true;) {
			add1();
			i = Boolean.parseBoolean(s.nextLine());
			;
		}
		yamlSave();
	}
	
	static void new1(){
		System.out.println("Do you want to add Artists? true/false");
			boolean i = Boolean.parseBoolean(s.nextLine());
			if (i) {
				command();
			}
	}

}
