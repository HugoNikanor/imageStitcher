package org.hugonikanor.imageStitcher.main;

import java.awt.image.BufferedImage;
import java.io.File;

import org.hugonikanor.imageStitcher.fileReader.FileSystemAccess;
import org.hugonikanor.imageStitcher.imageCreator.ImageStitcher;

public class Main {
	public static void main( String[] args ) {
		
		if( args.length < 0 ) {
			System.out.println( "Please enter a directory path" );
		}

		FileSystemAccess fsa = new FileSystemAccess( new File(args[0]) );

		ImageStitcher is = new ImageStitcher( 
				(BufferedImage[]) fsa.getImages().toArray() );

		//BufferedImage stitchedImage = is.getStitchedImage();

		//fsa.writeImage( stitchedImage, "outputImage" );

	}
}
