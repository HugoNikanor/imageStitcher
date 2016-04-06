package org.hugonikanor.imageStitcher.fileReader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
	public List<BufferedImage> getImages() {

		File[] unfilteredFiles = dir.listFiles();
		List<File> filteredFiles = new ArrayList<>();
		for( File f : unfilteredFiles ) {
			if( !f.isDirectory() ) {
				filteredFiles.add( f );
			}
		}

		List<BufferedImage> images =
			new FileReader( (File[]) filteredFiles.toArray() ).readImages();

		return images;
	}

	/**
	 * @return the dir
	 */
	public File getDir() {
		return dir;
	}
}
