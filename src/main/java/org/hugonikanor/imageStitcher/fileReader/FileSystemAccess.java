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
	public BufferedImage[] getImages( String regex ) throws IOException {
		Pattern p = Pattern.compile( regex );

		File[] unfilteredFiles = dir.listFiles();
		if( unfilteredFiles == null ) {
			return new BufferedImage[0];
		}

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

	/**
	 * Note that this also writes a message to stdout depending on if the
	 * writing is succsessfull
	 *
	 * @param image
	 *     The image which should be written
	 * @param filename
	 *     what the file should be named, 'png' at end optional
	 * @return
	 *     if the file writing was successfull
	 */
	public boolean writeImage( BufferedImage image, String filename ) 
		throws IOException {
		if( !filename.substring(filename.length() - 4).equals(".png") ) {
			filename += ".png";
		}
		File outputFile = new File( filename ); 

		String message;

		boolean writeSuccess = ImageIO.write( image, "png", outputFile );
		if( writeSuccess ) {
			message = "File written to disk";
		} else {
			message = "Java ImageIO.write failed when writing to disk";
		}
		System.out.printf( "%n%s:\t[%s]%n", message, outputFile );

		return writeSuccess;
	}

	/**
	 * @return the dir
	 */
	public File getDir() {
		return dir;
	}
}
