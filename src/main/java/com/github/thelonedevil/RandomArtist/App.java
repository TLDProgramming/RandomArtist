package com.github.thelonedevil.RandomArtist;

import java.awt.GraphicsEnvironment;
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
		boolean h = false;
		if (args.length != 0 && args[0].equalsIgnoreCase("-h")) {
			h = true;
		}

		if (!GraphicsEnvironment.isHeadless() && !h) {
			Window gui = new Window();
		} else if (GraphicsEnvironment.isHeadless() || h) {
			new1();
			System.out.println("Your random artist: " + artist);
			s.close();
		}
	}

	@SuppressWarnings("unchecked")
	static void yamlLoad() {
		if (ayaml.exists()) {
			try {
				InputStream input = new FileInputStream(ayaml);
				Yaml yaml = new Yaml();
				Object object = yaml.load(input);
				if (object != null) {
					artists = (List<String>) object;
				}
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
		if (!artists.contains(add2)) {
			artists.add(add2);
			System.out.println("You added " + add2);
			yamlSave();
		} else if (artists.contains(add2)) {
			System.out.println("This Artist is already in the list");
		}

	}

	static void command() {
		boolean i = true;
		while (i) {
			System.out.println("Do you want to add any more artists? y/n");
			String input = s.nextLine();
			if (input.equalsIgnoreCase("y")) {
				i = true;
				add1();
			} else if (input.equalsIgnoreCase("n")) {
				i = false;
			} else if (!input.equalsIgnoreCase("n") || !input.equalsIgnoreCase("y")) {
				System.out.println("Invalid response!");
				command();
			}

		}
		random();
	}

	static void new1() {
		System.out.println("Do you want to add Artists? y/n");
		String input = s.nextLine();
		if (input.equalsIgnoreCase("y")) {
			add1();
			command();
		} else if (input.equalsIgnoreCase("n")) {
			random();
		} else if (!input.equalsIgnoreCase("n") || !input.equalsIgnoreCase("y")) {
			System.out.println("Invalid response!");
			new1();
		}

	}
}
