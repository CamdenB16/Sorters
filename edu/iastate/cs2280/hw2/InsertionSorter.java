package edu.iastate.cs2280.hw2;


/**
 *  
 * @author Camden Beightler
 *
 */

/**
 * 
 * This class implements insertion sort.   
 *
 */

public class InsertionSorter extends AbstractSorter 
{
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 * 
	 * @param pts  
	 */
	public InsertionSorter(Point[] pts) 
	{
		super(pts);
		algorithm = "InsertionSort";
	}	

	
	/** 
	 * Perform insertion sort on the array points[] of the parent class AbstractSorter.  
	 */
	@Override 
	public void sort()
	{
        // Iterate over the array starting from the second element
        for (int i = 1; i < points.length; i++) 
        {
            Point currentPoint = points[i];  // The point to be inserted
            int j = i - 1;

            // Move the elements that are greater than the current point, to one position ahead of their current spot
            while (j >= 0 && pointComparator.compare(points[j], currentPoint) > 0) 
            {
                points[j + 1] = points[j];  // Shift the element to the right
                j = j - 1;
            }

            // Insert the point into the correct position
            points[j + 1] = currentPoint;
        }
	}		
}
