package org.hugonikanor.imageStitcher.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputHandler {

	private String dir;
	private String regex;
	private String outputFile;
	private boolean showPreview;

	public InputHandler( String[] args ) throws IOException {
		dir = "./";
		regex = ".*";
		outputFile = "outputFile";
		showPreview = false;

		List<String> filteredArgs = new ArrayList<>();
		Pattern p = Pattern.compile("--.*");

		for( String str : args ) {
			Matcher m = p.matcher( str );
			if( m.matches() ) {
				System.out.println( str );
				filteredArgs.addAll( Arrays.asList(str.split("=", 2)) );
			} else {
				filteredArgs.add( str );
			}
		}
		Iterator<String> it = filteredArgs.iterator();

		while( it.hasNext() ) {
			switch( it.next() ) {
			case "-h":
			case "--help":
				for( String str : Files.readAllLines(
							Paths.get("src/main/resources/helpMessage.txt"))) {
					System.out.println( str );
				}
				System.exit(0);
				break;
			case "-d":
			case "--dir":
				dir = it.next();
				break;
			case "-r":
			case "--regex":
				regex = it.next();
				break;
			case "-o":
			case "--output":
				outputFile = it.next();
				break;
			case "-p":
			case "--preview":
				showPreview = true;
				break;
			default:
				break;
			}
		}
	}

	/**
	 * @return the dir
	 */
	public String getDir() {
		return dir;
	}

	/**
	 * @return the regex
	 */
	public String getRegex() {
		return regex;
	}

	/**
	 * @return the outputFile
	 */
	public String getOutputFile() {
		return outputFile;
	}

	/**
	 * @return the showPreview
	 */
	public boolean isShowPreview() {
		return showPreview;
	}
}
