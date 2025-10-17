package edu.iastate.cs2280.hw2;

/**
 *  
 * @author Camden Beightler
 *
 */

/**
 * 
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
        super(pts); 
        algorithm = "MergeSort"; 
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 */
	@Override 
	public void sort()
	{
		mergeSortRec(points);
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts)
	{
        if (pts.length < 2) {
            return;
        }
        
        int mid = pts.length / 2;  // Find the middle index to split the array

        // Create subarrays for the two halves
        Point[] left = new Point[mid];
        Point[] right = new Point[pts.length - mid];

        for (int i = 0; i < mid; i++) 
        {
            left[i] = pts[i];  // Copy first half onto the left
        }

        for (int i = mid; i < pts.length; i++)
        {
            right[i - mid] = pts[i];  // Copy second half onto the right
        }
        
        // Recursively sort both the left and right
        mergeSortRec(left);
        mergeSortRec(right);

        // Merge the final sorted left and right
        merge(pts, left, right);
	}

	
    /**
     * Merges two sorted subarrays into one sorted array.
     * 
     * @param pts   is passed into the array where merged array will go	
     * @param left  sorted left subarray
     * @param right sorted right subarray
     */
    private void merge(Point[] pts, Point[] left, Point[] right) 
    {
        int i = 0, j = 0, mergeindex = 0;

        // Merge the two subarrays back into pts[]
        while (i < left.length && j < right.length)
        {
            // pointComparator compares points
            if (pointComparator.compare(left[i], right[j]) <= 0) 
            {
                pts[mergeindex++] = left[i++];
            } 
            else 
            {
                pts[mergeindex++] = right[j++];
            }
        }

        while (i < left.length) 
        {
            pts[mergeindex++] = left[i++];
        }

        while (j < right.length) 
        {
            pts[mergeindex++] = right[j++];
        }
    }

}
