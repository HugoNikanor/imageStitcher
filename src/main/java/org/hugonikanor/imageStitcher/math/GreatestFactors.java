package org.hugonikanor.imageStitcher.math;

import java.util.Arrays;

public class GreatestFactors {

	/**
	 * Returs the two largest factors closest in size which multiply 
	 * to become the initial value, the smalest value will always be first.
	 * @param n
	 * 	The number you want factors from
	 * @return
	 * 	int[2] with the two factors closest in size, with the smalest first  
	 */
	public static int[] Get( int n ) {
		int[] ret = { 1, n };

		for( double i = Math.floor(n/2); i > 0; i++ ) {
			if( n/i == Math.floor(n/i) ) {
				ret[0] = (int) i;
				ret[1] = (int) (n/i);
				break;
			}
		}

		Arrays.sort( ret );

		return ret;
	}
}
