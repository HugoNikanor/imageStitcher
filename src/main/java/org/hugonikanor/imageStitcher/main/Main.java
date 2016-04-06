package org.hugonikanor.imageStitcher.main;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import org.hugonikanor.imageStitcher.fileReader.FileSystemAccess;
import org.hugonikanor.imageStitcher.imageCreator.ImageStitcher;

public class Main {
	public static void main( String[] args ) {
		
		if( args.length <= 0 ) {
			System.out.println( "Please enter a directory path" );
			System.exit( 0 );
		}

		FileSystemAccess fsa = new FileSystemAccess( new File(args[0]) );

		ImageStitcher is = new ImageStitcher( fsa.getImages() );

		BufferedImage stitchedImage = is.getStitchedImage();

		JFrame frame = new JFrame();
		//frame.getContentPane().setLayout(new FlowLayout());
		frame.getContentPane().add(new JScrollPane(new JLabel(new ImageIcon(stitchedImage))), BorderLayout.CENTER);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.pack();
		frame.setVisible(true);

		//fsa.writeImage( stitchedImage, "outputImage" );

	}
}
