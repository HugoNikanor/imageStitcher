package org.hugonikanor.imageStitcher.math;

import java.util.Arrays;

public class GreatestFactors {
	/**
	 * Returs the two largest factors closest in size which multiply 
	 * to become the initial value, the largest value will always be first.
	 * @param n
	 * 	The number you want factors from
	 * @return
	 * 	int[2] with the two factors closest in size, with the smalest first  
	 */
	public static int[] Get( int n ) {
		int[] ret = { n, 1 };

		for( double i = Math.floor(n/2); i > 0; i++ ) {
			if( n/i == Math.floor(n/i) ) {
				ret[0] = (int) i;
				ret[1] = (int) (n/i);
				break;
			}
		}

		// sort and reverse
		Arrays.sort( ret );
		int temp = ret[0];
		ret[0] = ret[1];
		ret[1] = temp;

		return ret;
	}
}
