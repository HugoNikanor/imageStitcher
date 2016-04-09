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
				continue;
			}

			try {
				BufferedImage img = ImageIO.read(path);
				if( img == null ) {
					System.out.printf( "Not an image: [%s]%n", path );
					continue;
				} else {
					images.add( img );
					System.out.printf( "Loaded: [%s]%n", path );
				}
			} catch( IOException e ) {
				e.printStackTrace();
			}
		}
		return images;
	}
}

