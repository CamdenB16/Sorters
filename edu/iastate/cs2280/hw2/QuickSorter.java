package edu.iastate.cs2280.hw2;

/**
 *  
 * @author Camden Beightler
 *
 */

/**
 * 
 * This class implements the version of the quicksort algorithm presented in the lecture.   
 *
 */

public class QuickSorter extends AbstractSorter
{
	
	// Other private instance variables if you need ... 
		
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *   
	 * @param pts   input array of integers
	 */
	public QuickSorter(Point[] pts)
	{
        super(pts); 
        algorithm = "QuickSort"; 
	}
		

	/**
	 * Carry out quicksort on the array points[] of the AbstractSorter class.  
	 * 
	 */
	@Override 
	public void sort()
	{
		quickSortRec(0, points.length - 1);
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last. 
	 * 
	 * @param first  starting index of the subarray
	 * @param last   ending index of the subarray
	 */
	private void quickSortRec(int first, int last)
	{
        if (first < last) 
        {
            // Partition the array and get the splitting index value
            int indexValue = partition(first, last);
            
            // Recursively sort
            quickSortRec(first, indexValue - 1);
            quickSortRec(indexValue + 1, last);
        }
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first
	 * @param last
	 * @return
	 */
	private int partition(int first, int last)
	{
        Point indexValue = points[last];
        int i = first - 1;  // Smaller element index

        // Go through subarray and compare each element with the index value
        for (int j = first; j < last; j++) 
        {
            if (pointComparator.compare(points[j], indexValue) <= 0) 
            {
                i++;
                swap(i, j);
            }
        }

        // Sets the index value in the correct spot
        swap(i + 1, last);
        return i + 1;  // Return index value
	}	
}
