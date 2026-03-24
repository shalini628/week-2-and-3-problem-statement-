import java.util.*;
import java.util.*;
class Counter {
    int count = 0;
}

// ---------------- SEARCH SYSTEM ----------------
class RiskSearch {

    // ----------- LINEAR SEARCH (UNSORTED) -----------
    public static void linearSearch(int[] arr, int target) {
        int comparisons = 0;
        boolean found = false;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;

            if (arr[i] == target) {
                System.out.println("Linear: Found at index " + i);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Linear: Not found");
        }

        System.out.println("Comparisons: " + comparisons);
        System.out.println("Time Complexity: O(n)\n");
    }

    // ----------- BINARY SEARCH (EXACT MATCH) -----------
    public static int binarySearch(int[] arr, int target, Counter c) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            c.count++;

            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) low = mid + 1;
            else high = mid - 1;
        }

        return -1;
    }

    // ----------- LOWER BOUND (INSERTION POINT) -----------
    public static int lowerBound(int[] arr, int target, Counter c) {
        int low = 0, high = arr.length;

        while (low < high) {
            int mid = (low + high) / 2;
            c.count++;

            if (arr[mid] < target)
                low = mid + 1;
            else
                high = mid;
        }

        return low; // insertion index
    }

    // ----------- FLOOR & CEILING -----------
    public static void floorCeil(int[] arr, int target, Counter c) {
        int index = lowerBound(arr, target, c);

        Integer floor = null, ceil = null;

        if (index < arr.length && arr[index] == target) {
            floor = ceil = arr[index];
        } else {
            if (index - 1 >= 0) floor = arr[index - 1];
            if (index < arr.length) ceil = arr[index];
        }

        System.out.println("Floor: " + (floor != null ? floor : "None"));
        System.out.println("Ceiling: " + (ceil != null ? ceil : "None"));
    }

    // ----------- PRINT --------
    public static void print(int[] arr) {
        for (int x : arr) {
            System.out.print(x + " ");
        }
        System.out.println();
    }
}

public class ProblemStatement {
    public static void main(String[] args) {
        // Unsorted for linear search
        int[] unsorted = {50, 10, 100, 25};

        // Sorted for binary search
        int[] sorted = {10, 25, 50, 100};

        int target = 30;

        // -------- LINEAR SEARCH --------
        RiskSearch.linearSearch(unsorted, target);

        // -------- BINARY SEARCH --------
        Counter counter = new Counter();

        int index = RiskSearch.binarySearch(sorted, target, counter);

        System.out.println("Binary Search:");
        if (index != -1) {
            System.out.println("Found at index " + index);
        } else {
            System.out.println("Not found");
        }

        // -------- FLOOR & CEILING --------
        RiskSearch.floorCeil(sorted, target, counter);

        // -------- INSERTION POINT --------
        int insertionIndex = RiskSearch.lowerBound(sorted, target, counter);
        System.out.println("Insertion Index: " + insertionIndex);

        System.out.println("Total Comparisons: " + counter.count);
        System.out.println("Time Complexity: O(log n)");
    }
}
