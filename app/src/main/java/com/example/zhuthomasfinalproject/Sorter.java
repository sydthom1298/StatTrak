package com.example.zhuthomasfinalproject;

// TODO: take ArrayList of objects and sort by a field specified as a "String" by using
// getField() to get the field value.  Probably need 2 sort methods - 1 for String and 1 for int.
public class Sorter {

    private int lastSortCount;
    private long lastSortTime;
    private boolean ascending = true;
    public Sorter( boolean ascend ) {
        ascending = ascend;
    }

    /**
     * method to start mergeS which does all the work for Mergesort
     * This is done to setup timers and loop counts before starting the recursive
     * mergeS.
     * @param nums - array of numbers to be sorted
     * @param n - size of the array
     */
    public void mergeSort(int[] nums, int n){
        lastSortCount = 0; //reset loop count
        lastSortTime = System.currentTimeMillis(); //record start time
        if( ascending ) {
            mergeSAscend(nums, n); //start the sort
        } else {
            mergeSDescend(nums, n);
        }
        lastSortTime = System.currentTimeMillis() - lastSortTime; //calculate elapsed time
    }

    /**
     * method to perform Mergesort on an array of integers
     * called recursively to work on sub-arrays
     * @param nums - array of integers to be sorted
     * @param n - size of array
     */
    private void mergeSAscend(int [] nums, int n){
        int [] left; //left half of the array
        int [] right; //right half of the array
        int mid; //index of the middle element
        int nLeft, nRight; //number of elements in left and right subarray
        int i,j,k;  //indexes

        //when size is 1 on the current array, sort is done
        if(n <= 1){
            return;
        }
        //create new sub-arrays to hold each half of the current
        mid = n / 2;
        left = new int[mid];
        right = new int [n - mid];
        //fill left half
        for(i = 0; i < mid; i++){
            left[i] = nums[i];
            lastSortCount++;
        }
        //fill right half
        for(i = 0; i < n - mid; i++){
            right[i] = nums[i + mid];
            lastSortCount++;
        }
        //call mergeSort to sort each half
        nLeft = mid;
        nRight = n - mid;
        mergeSAscend(left, nLeft);
        mergeSAscend(right, nRight);

        //merge the results from processing each half
        i = 0;
        j = 0;
        k = 0;

        /*merge 2 sorted sub arrays back into main (nums) array, taking lowest values
        first (ascending) and highest values first (descending).
        k indexes combined array
        i indexes left sub-array
        j indexes right sub-array*/
        while((i < nLeft) && (j < nRight)){
            if(left[i] <= right[j]) {
                nums[k] = left[i];  //left element is smallest so copy to nums array
                i++;
                k++;
            }else{
                nums[k] = right[j]; //right element is smallest so copy to nums array
                j++;
                k++;
            }
            lastSortCount++; //track loop count
        }

        //copy anyting remaining from the left array into the nums array
        while(i < nLeft){
            nums[k] = left[i];
            i++;
            k++;
            lastSortCount++; //track loop count
        }
        //copy anything remaining from the right array into the nums array
        while(j < nRight){
            nums[k] = right[j];
            k++;
            j++;
            lastSortCount++; //track loop count
        }
    }

    /**
     * method to perform Mergesort on an array of integers
     * called recursively to work on sub-arrays
     * @param nums - array of integers to be sorted
     * @param n - size of array
     */
    private void mergeSDescend(int [] nums, int n){
        int [] left; //left half of the array
        int [] right; //right half of the array
        int mid; //index of the middle element
        int nLeft, nRight; //number of elements in left and right subarray
        int i,j,k;  //indexes

        //when size is 1 on the current array, sort is done
        if(n <= 1){
            return;
        }
        //create new sub-arrays to hold each half of the current
        mid = n / 2;
        left = new int[mid];
        right = new int [n - mid];
        //fill left half
        for(i = 0; i < mid; i++){
            left[i] = nums[i];
            lastSortCount++;
        }
        //fill right half
        for(i = 0; i < n - mid; i++){
            right[i] = nums[i + mid];
            lastSortCount++;
        }
        //call mergeSort to sort each half
        nLeft = mid;
        nRight = n - mid;
        mergeSDescend(left, nLeft);
        mergeSDescend(right, nRight);

        //merge the results from processing each half
        i = 0;
        j = 0;
        k = 0;

        /*merge 2 sorted sub arrays back into main (nums) array, taking lowest values
        first (ascending) and highest values first (descending).
        k indexes combined array
        i indexes left sub-array
        j indexes right sub-array*/
        while((i < nLeft) && (j < nRight)){
            if(left[i] >= right[j]){
                nums[k] = left[i];  //left element is smallest so copy to nums array
                i++;
                k++;
            }else{
                nums[k] = right[j]; //right element is smallest so copy to nums array
                j++;
                k++;
            }
            lastSortCount++; //track loop count
        }

        //copy anyting remaining from the left array into the nums array
        while(i < nLeft){
            nums[k] = left[i];
            i++;
            k++;
            lastSortCount++; //track loop count
        }
        //copy anything remaining from the right array into the nums array
        while(j < nRight){
            nums[k] = right[j];
            k++;
            j++;
            lastSortCount++; //track loop count
        }
    }




}
