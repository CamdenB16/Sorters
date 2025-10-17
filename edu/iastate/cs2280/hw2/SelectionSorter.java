package edu.iastate.cs2280.hw2;

/**
 *  
 * @author Camden Beightler
 *
 */

/**
 * 
 * This class implements selection sort.   
 *
 */

public class SelectionSorter extends AbstractSorter
{
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts  
	 */
	public SelectionSorter(Point[] pts)  
	{
		super(pts);
		algorithm = "SelectionSort";
	}	

	
	/** 
	 * Apply selection sort on the array points[] of the parent class AbstractSorter.  
	 * 
	 */
	@Override 
	public void sort()
	{
        // Selection sort algorithm
        for (int i = 0; i < points.length - 1; i++) 
        {
            int minIndex = i;
            
            // Find the index of the minimum point from the unsorted part
            for (int j = i + 1; j < points.length; j++) 
            {
                if (pointComparator.compare(points[j], points[minIndex]) < 0) 
                {
                    minIndex = j;  // Update minIndex if a smaller element in the array is found
                }
            }    
            swap(i, minIndex);
        }
	}	
}
