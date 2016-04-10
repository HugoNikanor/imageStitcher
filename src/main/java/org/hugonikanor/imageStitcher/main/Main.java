package org.hugonikanor.imageStitcher.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.hugonikanor.imageStitcher.fileReader.FileSystemAccess;
import org.hugonikanor.imageStitcher.graphics.Frame;
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
					"Either no images in matching '%s' was found in '%s'%n"+
					"Or '%s' is not a valid directory%n",
					ih.getRegex(), ih.getDir(), ih.getDir() );
			System.exit( 0 );
		}

		ImageStitcher is = new ImageStitcher(images);
		BufferedImage stitchedImage = is.getStitchedImage();

		if( ih.isShowPreview() ) {
			new Frame( stitchedImage, (e) -> {
				try {
					fsa.writeImage(stitchedImage, ih.getOutputFile());
				} catch( IOException err ) {
					err.printStackTrace();
				}
				System.exit(0);
			});
		} else {
			fsa.writeImage(stitchedImage, ih.getOutputFile());
		}
	}
}
