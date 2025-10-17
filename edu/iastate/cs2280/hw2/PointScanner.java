package edu.iastate.cs2280.hw2;

/**
 * 
 * @author Camden Beightler
 *
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;


/**
 * 
 * This class sorts all the points in an array of 2D points to determine a reference point whose x and y 
 * coordinates are respectively the medians of the x and y coordinates of the original points. 
 * 
 * It records the employed sorting algorithm as well as the sorting time for comparison. 
 *
 */
public class PointScanner  
{
	private Point[] points; 
	
	private Point medianCoordinatePoint;  // point whose x and y coordinates are respectively the medians of 
	                                      // the x coordinates and y coordinates of those points in the array points[].
	private Algorithm sortingAlgorithm;    
	
		
	protected long scanTime; 	       // execution time in nanoseconds. 
	
	/**
	 * This constructor accepts an array of points and one of the four sorting algorithms as input. Copy 
	 * the points into the array points[].
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException
	{
		if (pts == null || pts.length == 0) 
		{
	        throw new IllegalArgumentException("Points array is null or empty.");
	    }
		
		points = new Point[pts.length];
		
		for(int i = 0; i < pts.length; i++)
		{
			points[i] = new Point(pts[i]);
		}
		
		sortingAlgorithm = algo; 
	}

	
	/**
	 * This constructor reads points from a file. 
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException   if the input file contains an odd number of integers
	 */
	protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException
	{
		File file = new File(inputFileName);
		Scanner fileScanner = new Scanner(file);
		
		sortingAlgorithm = algo;  

        // Count number of integers
        int numPoints = 0;
        
        while (fileScanner.hasNextInt()) 
        {
            numPoints++;
            fileScanner.nextInt();
        }
        
        // If numPoints is odd, fails
        if (numPoints % 2 != 0) 
        {
        	fileScanner.close();
            throw new InputMismatchException("File contains an odd number of integers.");
        }
        
        // Resets the scanner to begin reading the points
        fileScanner.close();
        fileScanner = new Scanner(file);
        
        points = new Point[numPoints / 2];  // Allocate the array for points
        
        // Read each pair of integers and store them as Point objects
        for (int i = 0; i < points.length; i++) 
        {
            int x = fileScanner.nextInt();
            int y = fileScanner.nextInt();
            points[i] = new Point(x, y);
        }
        
        fileScanner.close();
	} 

	
	/**
	 * Carry out two rounds of sorting using the algorithm designated by sortingAlgorithm as follows:  
	 *    
	 *     a) Sort points[] by the x-coordinate to get the median x-coordinate. 
	 *     b) Sort points[] again by the y-coordinate to get the median y-coordinate.
	 *     c) Construct medianCoordinatePoint using the obtained median x- and y-coordinates.     
	 *  
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter, InsertionSorter, MergeSorter,
	 * or QuickSorter to carry out sorting.       
	 * @param algo
	 * @return
	 */
	public void scan()
	{ 
		AbstractSorter aSorter; 
		
		// Checks what sorting algorithm is being called
	    switch (sortingAlgorithm) 
	    {
	        case SelectionSort:
	            aSorter = new SelectionSorter(points);
	            break;
	        case InsertionSort:
	            aSorter = new InsertionSorter(points);
	            break;
	        case MergeSort:
	            aSorter = new MergeSorter(points);
	            break;
	        case QuickSort:
	            aSorter = new QuickSorter(points);
	            break;
	        default:
	            throw new IllegalArgumentException("Invalid sorting algorithm");
	    }

	    aSorter.setComparator(0);  // compare by x-coords
	    
	    long startTimeX = System.nanoTime();
	    aSorter.sort();  // Sort by x-coordinate
	    long endTimeX = System.nanoTime();
	    
	    int medianX = aSorter.getMedian().getX();  // Get median x-coord

	    aSorter.setComparator(1);  // compare by y-coords
	    
	    long startTimeY = System.nanoTime();
	    aSorter.sort();  // Sort by y-coords
	    long endTimeY = System.nanoTime();
	    
	    int medianY = aSorter.getMedian().getY();  // Get median y-coord

	    // Store median coords
	    medianCoordinatePoint = new Point(medianX, medianY);

	    // Total time calc
	    scanTime = (endTimeX - startTimeX) + (endTimeY - startTimeY); 
	}
		
	
	
	
	/**
	 * Outputs performance statistics in the format: 
	 * 
	 * <sorting algorithm> <size>  <time>
	 * 
	 * For instance, 
	 * 
	 * selection sort   1000	  9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description. 
	 */
	public String stats()
	{
		return String.format("%-15s %5d    %d", sortingAlgorithm.name(), points.length, scanTime); 
	}
	
	
	/**
	 * Write MCP after a call to scan(),  in the format "MCP: (x, y)"   The x and y coordinates of the point are displayed on the same line with exactly one blank space 
	 * in between. 
	 */
	@Override
	public String toString()
	{
		return "MCP: (" + medianCoordinatePoint.getX() + ", " + medianCoordinatePoint.getY() + ")";
	}

	
	/**
	 *  
	 * This method, called after scanning, writes point data into a file by outputFileName. The format 
	 * of data in the file is the same as printed out from toString().  The file can help you verify 
	 * the full correctness of a sorting result and debug the underlying algorithm. 
	 * 
	 * @throws FileNotFoundException
	 */
	public void writeMCPToFile() throws FileNotFoundException
	{
		String outputFileName = "MCPoutput.txt";
	    PrintWriter writer = new PrintWriter(outputFileName);
	    writer.println(toString());
	    writer.close();
	}	
}
