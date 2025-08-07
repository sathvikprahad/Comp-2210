import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Sathvik Prahadeeswaran (srp0061@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  January 24, 2023
*
*/
public final class Selector {

   /**
    * Can't instantiate this class.
    *
    * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
    *
    */
   private Selector() { }


   /**
    * Selects the minimum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int min(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("Error");
      }
      
      int min = a[0];
      for (int i : a) {
         if (i < min) {
            min = i;
         }
      }
      return min;
   }


   /**
    * Selects the maximum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int max(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("Error");
      }
      
      int max = a[0];
      for (int i : a) {
         if (i > max) {
            max = i;
         }
      }
      return max;
   }


   /**
    * Selects the kth minimum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth minimum value. Note that there is no kth
    * minimum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmin(int[] a, int k) {
      if (a == null || a.length == 0 || a.length < k || k < 1) {
         throw new IllegalArgumentException("Error");
      }
      
      //Creates a new array to be sorted   
      int[] cloneArray = Arrays.copyOf(a, a.length);
      Arrays.sort(cloneArray);
      
      int kmin = 0; //Value to be returned
      int difValues = 1; //Tracks # of distinct values in the array
      int compare = cloneArray[0]; //Holds  a val, compared with difValues
      
      //Check to see if k = 1, if yes return
      //**Need to debug out of bounds errors when k = 1
      if (k == 1) {
         kmin = cloneArray[0];
         return kmin;
      }
      
      for (int i=0; i < cloneArray.length; i++) {
         if (cloneArray[i] != compare) {
            difValues++;
            if (difValues == k) { //If difValues matches k, we found kmin
               kmin = cloneArray[i];
            }
         }
         compare = cloneArray[i]; //Compare holds the next value in the array
      }
      
      if (difValues < k) {
         throw new IllegalArgumentException("Not enough different values");
      }
      
      return kmin;
   }


   /** 
    * Selects the kth maximum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth maximum value. Note that there is no kth
    * maximum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   
   public static int kmax(int[] a, int k) {
      if (a == null || a.length == 0 || a.length < k || k < 1) {
         throw new IllegalArgumentException("Error");
      }
         //Creates a new array to be sorted
      int[] cloneArr = Arrays.copyOf(a, a.length);
      Arrays.sort(cloneArr);
         
      Arrays.sort(cloneArr);
      int distinct = 1;
      int temp = cloneArr[cloneArr.length - 1];
      int kmax = 0;
         
         //Check to see if k = 1, if yes return
      //**Need to debug out of bounds errors when k = 1
      if (k == 1) {
         kmax = cloneArr[cloneArr.length - 1];
         return kmax;
      }
         //Checks array for distinct elements, if distinct = k, that is kmin.
      for (int i = cloneArr.length - 1; i >= 0; i--) {
         if (cloneArr[i] != temp) {
            distinct++;
            if (distinct == k) {
               kmax = cloneArr[i];
            }
         }
         temp = cloneArr[i];
      }
         
      if (distinct < k) {
         throw new IllegalArgumentException("Error");
      }      
      return kmax;
   }


   /**
    * Returns an array containing all the values in a in the
    * range [low..high]; that is, all the values that are greater
    * than or equal to low and less than or equal to high,
    * including duplicate values. The length of the returned array
    * is the same as the number of values in the range [low..high].
    * If there are no qualifying values, this method returns a
    * zero-length array. Note that low and high do not have
    * to be actual values in a. This method throws an
    * IllegalArgumentException if a is null or has zero length.
    * The array a is not changed by this method.
    */
   public static int[] range(int[] a, int low, int high) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("Error");
      }
      //Determine how big the range of the array should be   
      int size = 0;
      for (int i : a) {
         if (low <= i && high >= i) {
            size++;  
         }
      } 
      //Create the array
      int[] rangeArr = new int[size];
       
      if (size == 0) {
         return rangeArr;
      }
      else {
         size = 0;
      }
       
      for (int i : a) {
         if (low <= i && high >= i) {
            rangeArr[size] = i;
            size++;
         }
      }
      return rangeArr;
   }


   /**
    * Returns the smallest value in a that is greater than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int ceiling(int[] a, int key) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("Error");
      }
      
      //Validation Check
      int count = 0;
      for (int i : a) {
         if (i >=  key) {
            count++;
         }
      }
      
      if (count == 0) {
         throw new IllegalArgumentException("Error");
      }
      int max = Integer.MAX_VALUE; //Need to store a value higher than any for now
      int ceiling = 0;
      int position = 0;
      
      count = -1;
      
      //Finds the closest #, then sets ceiling = to 1 spot below that number
      for (int i : a) {
         count++;
         if (i >= key) {
            ceiling = i - key;
            if (ceiling < max) {
               max = ceiling;
               position = count;
            }
         }
      }
      return a[position];
   }


   /**
    * Returns the largest value in a that is less than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int floor(int[] a, int key) {
      if(a == null||a.length == 0){
         throw new IllegalArgumentException("Error");
      }
      
      int count = 0;
      for(int i : a){
         if(i <= key)
            count++;
      }
      if(count==0)
         throw new IllegalArgumentException();
         
      int max = Integer.MIN_VALUE;
      int ceiling = 0;
      int position = 0;
      count = -1;
      
      
      for(int i : a){
         count++;
         if(i <=key){
            ceiling = i - key;
            if(ceiling > max){
               max = ceiling;
               position = count; 
            }
         }
      }
      return a[position];
   }

}