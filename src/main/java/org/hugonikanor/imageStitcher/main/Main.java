package org.hugonikanor.imageStitcher.main;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
		
		if( args.length <= 0 || args[0].contains("-h") ) {
			for( String str : Files.readAllLines(
						Paths.get("src/main/resources/helpMessage.txt"))) {
				System.out.println( str );
			}
			System.exit(0);
		}

		String dir = ".";
		String regex = ".*";
		String outputFile = "outputFile";
		boolean showPreview = false;

		for( int i = 0; i < args.length; i++ ) {
			switch( args[i] ) {
			case "-d":
				dir = args[++i];
				break;
			case "-r":
				regex = args[++i];
				break;
			case "-o":
				outputFile = args[++i];
				break;
			case "-p":
				showPreview = true;
				break;
			default:
				break;
			}
		}

		FileSystemAccess fsa = new FileSystemAccess( new File(dir) );

		BufferedImage[] images = fsa.getImages(regex);
		if( images.length == 0 ) {
			System.out.println( "No images in directory matches" );
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
