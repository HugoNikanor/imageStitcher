package org.hugonikanor.imageStitcher.fileReader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class FileReader {

	private File[] files;

	public FileReader( File[] files ) {
		this.files = files;
	}

	/**
	 * @return the images
	 */
	public List<BufferedImage> readImages() {

		List<BufferedImage> images = new ArrayList<>();

		for( File path : files ) {
			if( !path.isFile() ) {
				System.err.printf( "%s is not a file%n", path );
				continue;
			}

			try {
				images.add( ImageIO.read(path) );
				System.out.printf( "%s loaded%n", path );
			} catch( IOException e ) {
				e.printStackTrace();
			}
		}
		return images;

	}
}
