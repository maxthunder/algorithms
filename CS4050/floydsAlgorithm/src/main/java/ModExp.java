/*  This application computes 
    a^e (mod n)  (e a's multiplied together in Zn)
    efficiently (instead of doing e multiplications,
    it only takes log(e) multiplications)
*/

import java.math.BigInteger;
import java.util.Scanner;

public class ModExp
{
    private static BigInteger zero = new BigInteger("0");
    private static BigInteger one = new BigInteger("1");
    private static BigInteger two = new BigInteger("2");

    public static void main(String[] args) {
        Scanner keys = new Scanner( System.in );
        BigInteger a, e, n;


        if (args.length != 3) {
            System.out.println("This program will compute a^e in Zn");
            System.out.println("Enter a:\n");
            a = new BigInteger( keys.nextLine() );
            System.out.println("\nEnter e:\n");
            e = new BigInteger( keys.nextLine() );
            System.out.println("\nEnter n:\n");
            n = new BigInteger( keys.nextLine() );
        } else {
            a = new BigInteger(args[0]);
            e = new BigInteger(args[1]);
            n = new BigInteger(args[2]);
        }


        BigInteger result = modExp( a, e, n, true );

        System.out.println("\nanswer:\n" + result );

    }

    public static BigInteger modExp( BigInteger a, BigInteger e, BigInteger n,
                                     boolean showTrace )
    {
        BigInteger squares = a;
        BigInteger exp = e;
        BigInteger prod = one;

        if( showTrace ) System.out.println("\n");

        while( exp.compareTo( one ) >= 0 )
        {
            if( showTrace ) System.out.printf("%25d %25d ", squares, exp );
            if( exp.mod( two ).equals( one ) )
            {// update prod and display
                prod = prod.multiply( squares ).mod( n );
                if( showTrace ) System.out.printf("%25d", prod );
            }
            if( showTrace ) System.out.println();

            // compute next row of values
            squares = squares.multiply( squares ).mod( n );
            exp = exp.divide( two );
        }

        return prod;
    }

}