package org.hugonikanor.imageStitcher.main;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import org.hugonikanor.imageStitcher.fileReader.FileSystemAccess;
import org.hugonikanor.imageStitcher.imageCreator.ImageStitcher;

public class Main {

	/**
	 * see resources/helpMessage.txt, or run the program with the '-h' flag,
	 * to see avalible args
	 */
	public static void main( String[] args ) throws IOException {

		String dir = "./";
		String regex = ".*";
		String outputFile = "outputFile";
		boolean showPreview = false;

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

		//System.out.printf("d[%s]%nr[%s]%no[%s]%n",dir,regex,outputFile);

		FileSystemAccess fsa = new FileSystemAccess( new File(dir) );

		BufferedImage[] images = fsa.getImages(regex);
		if( images.length == 0 ) {
			System.out.printf( 
					"No images in '%s' which match the regex '%s' found.%n",
					dir, regex );
			System.exit( 0 );
		}

		ImageStitcher is = new ImageStitcher(images);

		BufferedImage stitchedImage = is.getStitchedImage();

		if( showPreview ) {
			JFrame frame = new JFrame();
			frame.getContentPane().add(new JScrollPane(new JLabel(new ImageIcon(stitchedImage))), BorderLayout.CENTER);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack();
			frame.setVisible(true);
		}

		fsa.writeImage(stitchedImage, outputFile);

	}
}
