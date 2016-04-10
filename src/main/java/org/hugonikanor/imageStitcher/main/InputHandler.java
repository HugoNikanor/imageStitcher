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
		Pattern longP = Pattern.compile("^--.*");
		Pattern shortP = Pattern.compile("^-(.*)");

		for( String arg : args ) {

			Matcher longM = longP.matcher( arg );
			Matcher shortM = shortP.matcher( arg );

			if( longM.matches() ) {
				System.out.println( arg );
				filteredArgs.addAll( Arrays.asList(arg.split("=", 2)) );
			} else if( shortM.matches() ) {
				String[] flags = shortM.group(1).split("");
				for( String str : flags ) {
					filteredArgs.add( new String("-").concat(str) );
				}
			} else {
				filteredArgs.add( arg );
			}

		}

		Iterator<String> it = filteredArgs.iterator();

		while( it.hasNext() ) {
			String val = it.next();
			switch( val ) {
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
			case "-h":
			case "--help":
				this.printHelp();
				System.exit(0);
				break;
			default:
				this.printHelp();
				System.err.printf( "%nInvalid flag '%s'%n", val );
				System.exit(1);
				break;
			}
		}
	}

	private void printHelp() {
		try {
			for( String str : Files.readAllLines(
						Paths.get("src/main/resources/helpMessage.txt"))) {
				System.out.println( str );
			}
		} catch( IOException e ) {
			System.err.println("Coludn't acces help, tough luck");
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
