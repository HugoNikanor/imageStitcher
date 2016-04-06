package org.hugonikanor.imageStitcher.imageCreator;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.hugonikanor.imageStitcher.math.GreatestFactors;

public class ImageStitcher {
	
	private BufferedImage stitchedImage;

	public ImageStitcher( BufferedImage[] images ) {
		int noImages = images.length;

		int imgWidth = images[0].getWidth();
		int imgHeight = images[0].getHeight();

		int[] factors = GreatestFactors.Get( noImages );


		stitchedImage = new BufferedImage(
				factors[1] * imgWidth,
				factors[0] * imgHeight,
				BufferedImage.TYPE_INT_ARGB );

		Graphics2D canvas = (Graphics2D) stitchedImage.getGraphics();

		//System.out.println( factors[0] );
		//System.out.println( factors[1] );

		int count = 0;
		for( int y = 0; y < factors[0]; y++ ) {
			for( int x = 0; x < factors[1]; x++ ) {
				canvas.drawImage( images[count], imgWidth * x, imgHeight * y, null );
				count++;
			}
		}
	}

	/**
	 * @return the stitchedImage
	 */
	public BufferedImage getStitchedImage() {
		return stitchedImage;
	}

}
