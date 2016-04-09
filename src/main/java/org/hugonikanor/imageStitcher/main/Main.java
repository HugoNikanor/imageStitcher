package org.hugonikanor.imageStitcher.main;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

		InputHandler ih = new InputHandler( args );
		FileSystemAccess fsa = new FileSystemAccess( new File(ih.getDir()) );

		BufferedImage[] images = fsa.getImages(ih.getRegex());
		if( images.length == 0 ) {
			System.out.printf( 
					"No images in '%s' which match the regex '%s' found.%n",
					ih.getDir(), ih.getRegex() );
			System.exit( 0 );
		}

		ImageStitcher is = new ImageStitcher(images);
		BufferedImage stitchedImage = is.getStitchedImage();

		if( ih.isShowPreview() ) {
			JFrame frame = new JFrame();
			frame.getContentPane().add(new JScrollPane(new JLabel(new ImageIcon(stitchedImage))), BorderLayout.CENTER);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack();
			frame.setVisible(true);
		}

		fsa.writeImage( stitchedImage, ih.getOutputFile() );
	}
}
