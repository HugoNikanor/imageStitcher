package org.hugonikanor.imageStitcher.main;

import org.hugonikanor.imageStitcher.math.GreatestFactors;

public class Main {
	public static void main( String[] args ) {
		for( int i = 1; i <= 20; i++ ) {
			int[] factors = GreatestFactors.Get( i );
			System.out.printf( "%02d: %02d x %d%n", i, factors[0], factors[1] );
		}
	}
}
