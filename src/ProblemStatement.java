import java.util.*;
import java.util.*;
class Asset {
    String name;
    double returnRate;
    double volatility;

    public Asset(String name, double returnRate, double volatility) {
        this.name = name;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    public String toString() {
        return name + ":" + returnRate + "% (vol=" + volatility + ")";
    }
}

// ---------------- SORTING SYSTEM ----------------
class PortfolioSorter {

    // ----------- MERGE SORT (ASC, STABLE) -----------
    public static void mergeSort(Asset[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    private static void merge(Asset[] arr, int left, int mid, int right) {
        Asset[] temp = new Asset[right - left + 1];

        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            // Stable: <= preserves order
            if (arr[i].returnRate <= arr[j].returnRate) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        for (i = left, k = 0; i <= right; i++, k++) {
            arr[i] = temp[k];
        }
    }

    // ----------- QUICK SORT (DESC + VOL ASC) -----------
    public static void quickSort(Asset[] arr, int low, int high) {
        if (low < high) {

            // Hybrid: use insertion sort for small partitions
            if (high - low < 10) {
                insertionSort(arr, low, high);
                return;
            }

            int pivotIndex = medianOfThree(arr, low, high);
            int pi = partition(arr, low, high, pivotIndex);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // ----------- PARTITION -----------
    private static int partition(Asset[] arr, int low, int high, int pivotIndex) {
        swap(arr, pivotIndex, high);
        Asset pivot = arr[high];

        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(arr[j], pivot) > 0) { // DESC logic
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    // ----------- COMPARATOR -----------
    private static int compare(Asset a, Asset b) {
        if (a.returnRate != b.returnRate) {
            return Double.compare(a.returnRate, b.returnRate);
        }
        // volatility ASC
        return Double.compare(b.volatility, a.volatility);
    }

    // ----------- MEDIAN OF THREE PIVOT -----------
    private static int medianOfThree(Asset[] arr, int low, int high) {
        int mid = (low + high) / 2;

        Asset a = arr[low];
        Asset b = arr[mid];
        Asset c = arr[high];

        if (a.returnRate < b.returnRate) {
            if (b.returnRate < c.returnRate) return mid;
            else if (a.returnRate < c.returnRate) return high;
            else return low;
        } else {
            if (a.returnRate < c.returnRate) return low;
            else if (b.returnRate < c.returnRate) return high;
            else return mid;
        }
    }

    // ----------- RANDOM PIVOT (OPTIONAL) -----------
    private static int randomPivot(int low, int high) {
        return new Random().nextInt(high - low + 1) + low;
    }

    // ----------- INSERTION SORT (HYBRID) -----------
    private static void insertionSort(Asset[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            Asset key = arr[i];
            int j = i - 1;

            while (j >= low && compare(arr[j], key) < 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // ----------- SWAP -----------
    private static void swap(Asset[] arr, int i, int j) {
        Asset temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // ----------- PRINT -----------
    public static void print(Asset[] arr) {
        for (Asset a : arr) {
            System.out.print("[" + a + "] ");
        }
        System.out.println();
    }
}



public class ProblemStatement {
    public static void main(String[] args) {
        Asset[] assets = {
                new Asset("AAPL", 12, 0.3),
                new Asset("TSLA", 8, 0.6),
                new Asset("GOOG", 15, 0.2)
        };

        // -------- MERGE SORT --------
        PortfolioSorter.mergeSort(assets, 0, assets.length - 1);
        System.out.println("Merge Sort (Ascending, Stable):");
        PortfolioSorter.print(assets);

        // -------- QUICK SORT --------
        PortfolioSorter.quickSort(assets, 0, assets.length - 1);
        System.out.println("\nQuick Sort (Descending + Volatility ASC):");
        PortfolioSorter.print(assets);
    }
}
