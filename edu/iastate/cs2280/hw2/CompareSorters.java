package edu.iastate.cs2280.hw2;

/**
 *  
 * @author Camden Beightler
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Scanner; 
import java.util.Random; 


public class CompareSorters 
{
    /**
     * Repeatedly take integer sequences either randomly generated or read from files. 
     * Use them as coordinates to construct points.  Scan these points with respect to their 
     * median coordinate point four times, each time using a different sorting algorithm.  
     * 
     * @param args
     **/
    public static void main(String[] args) throws FileNotFoundException
    {                    
        Scanner scanner = new Scanner(System.in);

        PointScanner[] scanners = new PointScanner[4];
        
        Random generator = new Random();
        
        int index = 0;
        
        while(true)
        {
            index++;
            
            System.out.println("Enter 1 to scan random points. Enter 2 to read points from a file. Enter 3 to exit:");
            System.out.println("Trial " + index + ": ");
            int userInput = scanner.nextInt();
    
            Point[] points = null; // Array to hold points
    
            if (userInput == 1) 
            {
                System.out.println("Enter the amount of random points to generate: ");
                int numPoints = scanner.nextInt();
                
                points = generateRandomPoints(numPoints, generator);
                
                scanners[0] = new PointScanner(points, Algorithm.SelectionSort);
                scanners[1] = new PointScanner(points, Algorithm.InsertionSort);
                scanners[2] = new PointScanner(points, Algorithm.MergeSort);
                scanners[3] = new PointScanner(points, Algorithm.QuickSort);

                printStats(scanners);
            } 
            else if (userInput == 2) 
            {
                // User wants to read points from a file
                System.out.println("Enter the filename: ");
                String filename = scanner.next();
    
                scanners[0] = new PointScanner(filename, Algorithm.SelectionSort);
                scanners[1] = new PointScanner(filename, Algorithm.InsertionSort);
                scanners[2] = new PointScanner(filename, Algorithm.MergeSort);
                scanners[3] = new PointScanner(filename, Algorithm.QuickSort);
                
                printStats(scanners);
            } 
            else if (userInput == 3)
            {
                System.out.println("Ending program...");
                scanner.close();
                return;
            }
            else 
            {
                System.out.println("Invalid input. Please enter a 1, 2, or 3 only.\n");
            }
        }
    }
        
    
    
    /**
     * This method generates a given number of random points.
     * The coordinates of these points are pseudo-random numbers within the range 
     * [-50,50] × [-50,50]. Please refer to Section 3 on how such points can be generated.
     * 
     * Ought to be private. Made public for testing. 
     * 
     * @param numPts      number of points
     * @param rand      Random object to allow seeding of the random number generator
     * @throws IllegalArgumentException if numPts < 1
     */
    public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
    { 
        int x;
        int y;
    
        if (numPts < 1) 
        {
            throw new IllegalArgumentException("Number of points must be at least 1.");
        }

        Point[] points = new Point[numPts];
        
        for (int i = 0; i < numPts; i++) 
        {
            x = rand.nextInt(101) - 50; 
            y = rand.nextInt(101) - 50;
            points[i] = new Point(x, y);
        }

        return points;
    }

    /**
     * This method takes the stats data passed in from each scanner
     * and prints out the formatted statistics of the sort algorithms
     * 
     * @param scanners      passes in the scanner being used
     */
    private static void printStats(PointScanner[] scanners)
    {
        // Scan with each algorithm and print stats
        System.out.println("\nAlgorithm        Size    Time (ns)");
        System.out.println("---------------------------------");
               
        for (int i = 0; i < scanners.length; i++) 
        {
             scanners[i].scan(); 
             System.out.println(scanners[i].stats()); // Output stats 
        }
        
        System.out.println("---------------------------------\n");
    }
}
