import java.util.*;

/**
 * Created by dhyey on 24-09-2015.
 */
public class array_interview {
    /*1.   How to find missing number in integer array of 1 to 100? (solution)
           This is one of the most simple array problem you will see, mostly asked in
           telephonic round of Interview. You have given an integer array which contains
           numbers from 1 to 100 but one number is missing, you need to write a Java program
           to find that missing number in array. You cannot use any open source library or
           Java API method which solves this problem. One trick to solve this problem is
           calculate sum of all numbers in array and compare with expected sum, the
           difference would be the missing number.
    */
    public static int findMissing(int[] ar){
        int n = ar.length + 1;
        int sum = 0;
        for(int i=0; i<ar.length; i++){
            sum += ar[i];
        }
        int expected_sum = n*(n+1)/2;
        return expected_sum-sum;

    }
    //can also use BitSet for the same problem as follows
    /*
    BitSet bs = new BitSet(ar.length);
    for(int i=0; i<ar.length; i++){
        bs.set(ar[i]-1);
    }
    return bs.nextClearBit(0)+1;
     */

    /*
    2. How to find duplicate number on Integer array in Java? (solution)
        An array contains n numbers ranging from 0 to n-2. There is exactly
        one number is repeated in the array. You need to write a program to
        find that duplicate number. For example, if an array with length 6
        contains numbers {0, 3, 1, 2, 3}, then duplicated number is 3.
        Actually this problem is very similar to previous one and you can
        apply the same trick of comparing actual sum of array to expected
        sum of series to find out that duplicate. This is generally asked as
        follow-up question of previous problem.
     */
    public static int findDuplicate(int[] ar){
        int n = ar.length;
        BitSet bs = new BitSet(n-2);
        for(int num:ar){
            if(bs.get(num))
                return num;
            else
                bs.set(num);
        }
        return -1;
    }

    /*
    3. How to check if array contains a number in Java? (solution)
        Another interesting array problem, because array doesn't provide
        any builtin method to check if any number exists or not. This
        problem is essentially how to search an element in array. There
        are two options sequential search or binary search. You should
        ask interviewer about whether array is sorted or not, if array
        is sorted then you can use binary search to check if given number
        is present in array or not. Complexity of binary search is O(logN).
        BTW, if interviewer say that array is not sorted then you can still
        sort and perform binary search otherwise you can use sequential search.
        Time complexity of sequential search in array is O(n).
     */
    public static boolean search(int[] ar, int num){
        //linear search if array is not sorted
        for(int elements: ar){
            if(elements == num)
                return true;
        }
        return false;

        //binary search
        //return binary_search(ar, 0, ar.length, num);
    }

    public static boolean binary_search(int[] ar, int l, int r, int num){
        if(l<=r){
            int mid = l+r / 2;
            if(ar[mid] == num)
                return true;
            else if(ar[mid] < num)
                return binary_search(ar, mid+1,r,num);
            else
                return binary_search(ar, l, mid-1, num);
        }
        return false;
    }

    /*4. How to find largest and smallest number in unsorted array? (solution)
        This is a rather simple array interview question. You have given an
        unsorted integer array and you need to find the largest and smallest
        element in the array. Of course you can sort the array and then pick
        the top and bottom element but that would cost you O(NLogN) because
        of sorting, getting element in array with index is O(1) operation.
    */
    public static int findMax(int[] ar){
        if(ar.length==0)    return Integer.MIN_VALUE;
        int max = Integer.MIN_VALUE;
        for(int num:ar){
            if(num>max)
                max = num;
        }
        return max;
    }

    public static int findMin(int[] ar){
        if(ar.length==0)    return Integer.MAX_VALUE;
        int min = Integer.MAX_VALUE;
        for(int num:ar){
            if(num < min)
                min = num;
        }
        return min;
    }


    /*5. How to find all pairs on integer array whose sum is equal to given number? (solution)
        This is an intermediate level of array coding question, its neither too easy nor too difficult.
        You have given an integer array and a number, you need to write a program to find all elements
        in array whose sum is equal to the given number. Remember, array may contain both positive and
        negative numbers, so your solution should consider that. Don't forget to write unit test though,
        even if interviewer is not asked for it, that would separate you from lot of developers. Unit
        testing is always expected from a professional developer.
    */
    //either you can use two loops, or sort, or use hashtable
    public static void getPairsWithSum(int[] ar, int sum){
        Hashtable ht = new Hashtable(ar.length);
        String s = "";
        for(int num:ar){
            int other = sum - num;
            if(ht.containsKey(other)){
                s = s + num + "   " + other + "\n";
            }
            ht.put(num,true);
        }
        System.out.println(s);
    }
    /*6.  How to find repeated numbers in an array if it contains multiple duplicate? (solution)
        This is actually the follow-up question of problem 2, how to find duplicate number on integer
        array. In that case, array contains only one duplicate but what if it contains multiple duplicates?
        Suppose, an array contains n numbers ranging from 0 to n-1 and there are 5 duplicates on it, how do
        you find it?
     */
    //this would work even if we dont know any property of the array
    public static void printMultipleDuplicates(int[] ar){
        Map<Integer, Integer> m = new LinkedHashMap<Integer, Integer>();
        String s="";
        for(int num:ar) {
            if (m.containsKey(num)) {
                int count = m.get(num);
                if(count==1)    s = s + num + "  ";
                m.put(num, count + 1);
            } else {
                m.put(num, 1);
            }
        }
        System.out.println(s);
    }

    /*
    7. Write a program to remove duplicates from array in Java? (solution)
        This is another follow-up question from problem 2 and 6. You have given
        an array which contains duplicates, could be one or more. You need to write
        a program to remove all duplicates from array in Java. For example if given
        array is {1, 2, 1, 2, 3, 4, 5} then your program should return an array which
        contains just {1, 2, 3, 4, 5}. This array question is also comes at intermediate
        category because there is no way to delete an element from array. If substituting
        with another value is not an option then you need to create another array to
        mimic deletion.
     */
    //using extra space
    public static int[] removeDuplicates(int[] ar){
        int[] ans = new int[ar.length];
        Arrays.sort(ar);
        int j=0;
        for(int i=0; i<ar.length-1; i++){
            if(ar[i]!=ar[i+1]){
                ans[j++] = ar[i];
            }
        }
        if(ar[ar.length-1]!=ans[j-1]){
            ans[j] = ar[ar.length-1];
        }
        return ans;
    }

    /*
    8. How to sort an array in place using QuickSort algorithm? (solution)
       You will often see sorting problems on array related questions, because
       sorting mostly happen on array data structure. You need to write a program
       to implement in place quick sort algorithm in Java. You can implement either
       recursive or iterative quick sort, its your choice but you cannot use additional
       buffer, array or list, you must sort array in place.
     */
    public static int[] quickSort(int[] ar, int l, int r){
        if(l<r){
            int p = partition(ar, l, r);
            quickSort(ar, l, p-1);
            quickSort(ar, p+1, r);
        }
        else{
            return ar;
        }
        return ar;
    }

    public static int partition(int[] ar, int l, int r){
        int i = l+1;
        int j = l+1;
        int pivot = ar[l];
        while(j<=r){
            if(ar[j] < pivot){
                int temp = ar[i];
                ar[i] = ar[j];
                ar[j] = temp;
                i++;
            }
            j++;
        }
        ar[l] = ar[i-1];
        ar[i-1] = pivot;
        return i-1;
    }

    /*
    9.  Write a program to find intersection of two sorted array in Java? (solution)
        Another interesting array interview question, where you need to treat array as Set.
        Your task is to write a function in your favorite language e.g. Java, Python, C or C++
        to return intersection of two sorted array. For example, if the two sorted arrays as input
        are {21, 34, 41, 22, 35} and {61, 34, 45, 21, 11}, it should return an intersection array
        with numbers {34, 21}, For the sake of this problem you can assume that numbers in each integer array are unique.
     */
    public static void findIntersection(int[] ar1, int[] ar2){
        Hashtable ht = new Hashtable(ar1.length);
        for(int num: ar1){
            if(!ht.containsKey(num)) {
                ht.put(num, true);
            }
        }
        String s = "";
        for(int num:ar2){
            if(ht.containsKey(num)){
                s = s + num + "  ";
            }
        }
        System.out.println("Intersection is "+s);
    }

    /*
    10. There is an array with every element repeated twice except one. Find that element? (solution)
    This is an interesting array coding problem, just opposite of question related to finding duplicates
    in array. Here you need to find the unique number which is not repeated twice. For example if given
    array is {1, 1, 2, 2, 3, 4, 4, 5, 5} then your program should return 3. Also, don't forget to write
    couple of unit test for your solution.
     */
    public static int findNonRepeated(int[] ar){
        int nonrepeat = 0, j=0;
        for(int i=0; i<ar.length; i++){
            for(j=i+1; j<ar.length; j++){
                if(ar[i]==ar[j])    break;
            }
            if(j==ar.length){
                nonrepeat = ar[i];
            }
        }
        return nonrepeat;
    }

    /*
    16. How to find top two numbers from an integer array? (solution)
    This is another one of the easy array questions you will find on
    telephonic round of Interviews, but its also little bit tricky.
    You are asked to find top two numbers not just the top or highest numbers?
    Can you think of how you would do it without sorting? before looking at solution.
     */
    public static void topTwoMax(int[] ar){
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        for(int num:ar){
            if(num > max1){
                max2 = max1;
                max1 = num;
            }
            else if(num > max2){
                max2 = num;
            }
        }
        System.out.println(max1+"  "+max2);
    }

    /*
    18. How to rearrange array in alternating positive and negative number? (solution)
Given an array of positive and negative numbers, arrange them in an alternate fashion such that every positive number is followed by negative and vice-versa maintaining the order of appearance.
Number of positive and negative numbers need not be equal. If there are more positive numbers they appear at the end of the array. If there are more negative numbers, they too appear in the end of the array. This is also a difficult array problem to solve and you need lot of practice to solve this kind of problems in real interviews, especially when you see it first time. If you have time constraint then always attempt these kind of questions once you are done with easier ones.

Example:

Input: {1, 2, 3, -4, -1, 4}
Output: {-4, 1, -1, 2, 3, 4}

Input: {-5, -2, 5, 2, 4, 7, 1, 8, 0, -8}
output: {-5, 5, -2, 2, -8, 4, 7, 1, 8, 0}
     */




}
