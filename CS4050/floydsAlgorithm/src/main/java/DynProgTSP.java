/*  solve smallish TSP's (don't have any restrictions on edges)
    using dynamic programming
*/

import java.util.Scanner;

public class DynProgTSP
{
    public static int n;
    public static int[] pow2;
    public static double[][] weights;
    public static double[][] chart;
    public static int[][] bestChoice;
    public static int firstChoice;

    public static void main(String[] args)
    {
        Scanner keys = new Scanner( System.in );
        System.out.print("enter # of points in the TSP instance: ");
        n = keys.nextInt();  keys.nextLine();

        weights = new double[ n ][ n ];

        pow2 = new int[ n+1 ];
        int p = 1;
        for( int k=0; k<=n; k++ )
        {
            pow2[k] = p;
            System.out.println(" 2^ " + k + " = " + pow2[k] );
            p *= 2;
        }

        chart = new double[ pow2[n-1] ][ n-1 ];
        bestChoice = new int[ pow2[n-1] ][ n-1 ];

        System.out.print("Do you want to enter the weights or the points (enter w or p)? ");
        String answer = keys.nextLine();

        if( answer.equals( "w" ) )
        {
            for( int r=0; r<n; r++ )
                for( int c=0; c<n; c++ )
                {
                    System.out.print("enter edge weight from " + (r+1) + " to " + (c+1) + ": ");
                    weights[r][c] = keys.nextDouble();
                }
        }
        else
        {
            double[][] points = new double[n][2];
            for( int k=0; k<n; k++ )
            {
                System.out.print("enter rowNum and colNum coords for point " + (k+1) + ": ");
                points[k][0] = keys.nextDouble();
                points[k][1] = keys.nextDouble();
            }

            // compute Euclidean weights:
            for( int r=0; r<n; r++ )
                for( int c=0; c<n; c++ )
                    weights[ r ][ c ] = Math.hypot( points[r][0]-points[c][0],
                            points[r][1]-points[c][1] );
        }

        System.out.println("\n\n");

        // solve the TSP
        int all = pow2[ n-1 ] - 1;

        int bestFirst = -1;
        double bestFirstWeight=0;

        for( int k=2; k<=n; k++ )
        {
            System.out.println("best from 1 to " + k + " through all others back to 1 is " +
                    (weights[ 0 ][k-1] + d(all-pow2[k-2],k) ) );
            if( bestFirst==-1 || weights[0][k-1]+d(all-pow2[k-2],k) < bestFirstWeight )
            {
                bestFirst = k;
                bestFirstWeight = weights[0][k-1]+d(all-pow2[k-2],k);
            }
        }
        System.out.println("\n");

        int setWidth = (showSet( all )).length();

        System.out.printf( "%" + setWidth + "s", "" );
        for( int c=0; c<chart[0].length; c++ )
            System.out.printf("    %2s    ", c+2 );
        System.out.println();

        for( int r=0; r<chart.length; r++ )
        {
            System.out.printf( "%" + setWidth + "s", showSet( r ) );
            for( int c=0; c<chart[0].length; c++ )
            {
                if( chart[r][c] > 0 )
                {
                    System.out.printf("%6.2f", chart[r][c] );
                    System.out.printf("(%2d)", bestChoice[r][c] );
                }
                else
                    System.out.print(" ---------");
            }
            System.out.println();

        }

        // build and display optimal tour
        getTour( bestFirst );
    }

    // compute d by on-demand recursive filling in of the
    // dynamic programming chart, where
    //  d( A, j ) = weight of lightest path from j touring through A and back to 1
    //  where j is in 2..n
    public static double d( int a, int j )
    {
        double bestValue = 0;
        int bestPoint = -1;

        if( chart[ a ][ j-2 ] == 0 )
        {// doing this sub-problem for the first time so compute and store in chart

            if( a==0 )
            {// lightest path from j through {} to 1 is just edge from j to 1
                chart[ a ][ j-2 ] = weights[ j-1 ][ 0 ];
            }
            else
            {// compute value recursively from simpler cells

                for( int k=2; k<=n; k++ )
                {// try going from j to k, then touring A-{k} and back to 1, for all k in A except j
                    if( k!=j &&
                            (pow2[k-2] & a) > 0
                            )
                    {
                        double djk = d( a-pow2[k-2], k ) + weights[ j-1 ][ k-1 ];
                        if( djk < bestValue || bestPoint==-1 )
                        {
                            bestValue = djk;
                            bestPoint = k;
                        }
                    }
                }

                chart[ a ][ j-2 ] = bestValue;
                bestChoice[ a ][ j-2 ] = bestPoint;

            }// compute value recursively

        }// doing this subproblem for first time

        return chart[ a ][ j-2 ];
    }

    private static String showSet( int a )
    {
        String s = "{";
        for( int k=2; k<=n; k++ )
            if( (a & pow2[k-2]) > 0 )
                s += k + ",";

        if( a > 0 )
            s = s.substring( 0, s.length()-1 ) + "}";
        else
            s += "}";


        return s;
    }

    // given first vertex to visit from 1, generate entire
    // tour, with separate calculation of weight
    private static void getTour( int first )
    {
        int all = pow2[ n-1 ] - 1;  // all is {2,...,n}
        int a = all - pow2[ first-2 ];  // a is {2,...,n} - {first}

        int current = first;
        int prev = 1;
        int edges = 1;   // first edge is special, from 1 to first
        double weight = 0;

        System.out.println("\nOptimal tour:\n");

        while( edges <= n )
        {// find next edge
            // report current edge
            System.out.println( prev + " --> " + current + "    " + weights[prev-1][current-1] );
            weight += weights[prev-1][current-1];

            if( current != 1 )
            {
                // advance to next edge
                prev = current;
                if( a > 0 )
                {
                    current = bestChoice[ a ][ current-2 ];
                    a -= pow2[ current-2 ];
                }
                else
                {
                    current = 1;
                }
            }

            edges++;
        }

        System.out.println("\nTotal tour weight is " + weight );

    }

}