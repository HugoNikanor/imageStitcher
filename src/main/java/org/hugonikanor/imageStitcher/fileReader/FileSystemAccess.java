package org.hugonikanor.imageStitcher.fileReader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

public class FileSystemAccess {

	private File dir;

	public FileSystemAccess( File dir ) {
		this.dir = dir;
	}

	/**
	 * @param regex
	 * 	A regex which all files in the dir must match
	 */
	public BufferedImage[] getImages( String regex ) {
		Pattern p = Pattern.compile( regex );

		File[] unfilteredFiles = dir.listFiles();

		// TODO possibly have check if regex is "match all" (.*)
		List<File> filteredFiles = new ArrayList<>();
		for( File f : unfilteredFiles ) {
			Matcher m = p.matcher( f.getName() );
			if( !f.isDirectory() && m.matches() ) {
				filteredFiles.add( f );
			}
		}

		// Note that this is not the desired order for numbers
		filteredFiles.sort( null );

		List<BufferedImage> images =
			new FileReader( filteredFiles.toArray(new File[filteredFiles.size()]) ).readImages();

		return images.toArray(new BufferedImage[images.size()]);
	}

	public void writeImage( BufferedImage image, String filename ) 
		throws IOException {
		if( !filename.substring(filename.length() - 4).equals(".png") ) {
			filename += ".png";
		}
		File outputFile = new File( filename ); 

		String message;

		if( ImageIO.write( image, "png", outputFile ) ) {
			message = "File written to disk";
		} else {
			message = "Java ImageIO.write failed when writing to disk";
		}
		System.out.printf( "%n%s:\t[%s]%n", message, outputFile );
	}

	/**
	 * @return the dir
	 */
	public File getDir() {
		return dir;
	}
}
