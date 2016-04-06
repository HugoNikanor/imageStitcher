package org.hugonikanor.imageStitcher.fileReader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class FileSystemAccess {

	private File dir;

	public FileSystemAccess( File dir ) {

		this.dir = dir;

		// This may be null
		// it also isn't sorted in any way
		dir.listFiles();

	}

	/**
	 * TODO this should take a filter
	 */
	public BufferedImage[] getImages() {

		File[] unfilteredFiles = dir.listFiles();
		List<File> filteredFiles = new ArrayList<>();
		for( File f : unfilteredFiles ) {
			if( !f.isDirectory() ) {
				filteredFiles.add( f );
			}
		}

		List<BufferedImage> images =
			new FileReader( filteredFiles.toArray(new File[filteredFiles.size()]) ).readImages();

		return images.toArray(new BufferedImage[images.size()]);
	}

	public void writeImage( BufferedImage image, String filename ) 
		throws IOException {
		ImageIO.write( image, "png", new File(filename.concat(".png")) );
	}

	/**
	 * @return the dir
	 */
	public File getDir() {
		return dir;
	}
}
