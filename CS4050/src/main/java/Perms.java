/*
  demo code to generate
  all permutations by
  simulating the tree of
  possibilities with method
  calls

  (can only go up to 26 symbols)
*/

import java.util.Scanner;

public class Perms {

    private static char[] symbols;

    public static void main(String[] args) {

        int n = 0;

        if (args.length == 0) {
            System.out.print("enter n: ");
            n = new Scanner( System.in ).nextInt();
        } else if (args.length == 1 ) {
            n = new Integer(args[0]);
        } else {
            System.out.println("Only 0 or 1 arguments allowed.\n\nExiting...");
            System.exit(1);
        }


        // generate a..z for convenience
        symbols = new char[n];
        for( int k=0; k<n; k++ )
            symbols[k] = (char) ('a' + k);

        generate( n, "" );

    }

    // given number of symbols n and the
    // word generated at this conceptual node
    private static void generate( int n, String s ) {

        if( s.length() == n )
            System.out.println( s );  // have reached a leaf
        else {
            // call generate with all possible single symbol extensions to s
            for( int k=0; k<symbols.length; k++ ) {
                if( ! s.contains( "" + symbols[k] ) )
                    generate( n, s+symbols[k] );
            }
        }
    }

}