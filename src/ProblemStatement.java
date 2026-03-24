import java.util.*;
import java.util.*;
class Log {
    String accountId;

    public Log(String accountId) {
        this.accountId = accountId;
    }

    public String toString() {
        return accountId;
    }
}

// ---------------- SEARCH SYSTEM ----------------
class SearchSystem {

    // ----------- LINEAR SEARCH (FIRST & LAST) -----------
    public static void linearSearch(Log[] logs, String target) {
        int first = -1, last = -1;
        int comparisons = 0;

        for (int i = 0; i < logs.length; i++) {
            comparisons++;

            if (logs[i].accountId.equals(target)) {
                if (first == -1) first = i;
                last = i;
            }
        }

        System.out.println("Linear Search:");
        if (first != -1) {
            System.out.println("First occurrence: " + first);
            System.out.println("Last occurrence: " + last);
        } else {
            System.out.println("Not found");
        }
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Time Complexity: O(n)\n");
    }

    // ----------- BINARY SEARCH (FIND ONE) -----------
    public static int binarySearch(Log[] logs, String target, Counter counter) {
        int low = 0, high = logs.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            counter.count++;

            int cmp = logs[mid].accountId.compareTo(target);

            if (cmp == 0) return mid;
            else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }

        return -1;
    }

    // ----------- COUNT OCCURRENCES (LEFT + RIGHT) -----------
    public static int countOccurrences(Log[] logs, String target, Counter counter) {
        int index = binarySearch(logs, target, counter);

        if (index == -1) return 0;

        int count = 1;

        // Left side
        int left = index - 1;
        while (left >= 0) {
            counter.count++;
            if (logs[left].accountId.equals(target)) {
                count++;
                left--;
            } else break;
        }

        // Right side
        int right = index + 1;
        while (right < logs.length) {
            counter.count++;
            if (logs[right].accountId.equals(target)) {
                count++;
                right++;
            } else break;
        }

        return count;
    }

    // ----------- SORT LOGS (REQUIRED FOR BINARY) -----------
    public static void sortLogs(Log[] logs) {
        Arrays.sort(logs, Comparator.comparing(l -> l.accountId));
    }

    // ----------- PRINT --------
    public static void print(Log[] logs) {
        for (Log l : logs) {
            System.out.print(l + " ");
        }
        System.out.println();
    }
}

// ---------------- COUNTER CLASS ----------------
class Counter {
    int count = 0;
}

public class ProblemStatement {
    public static void main(String[] args) {
        Log[] logs = {
                new Log("accB"),
                new Log("accA"),
                new Log("accB"),
                new Log("accC")
        };

        String target = "accB";

        // -------- LINEAR SEARCH --------
        SearchSystem.linearSearch(logs, target);

        // -------- SORT FOR BINARY --------
        SearchSystem.sortLogs(logs);
        System.out.println("Sorted Logs:");
        SearchSystem.print(logs);

        // -------- BINARY SEARCH + COUNT --------
        Counter counter = new Counter();

        int index = SearchSystem.binarySearch(logs, target, counter);
        int occurrences = SearchSystem.countOccurrences(logs, target, counter);

        System.out.println("\nBinary Search:");
        if (index != -1) {
            System.out.println("Found at index: " + index);
            System.out.println("Total occurrences: " + occurrences);
        } else {
            System.out.println("Not found");
        }

        System.out.println("Comparisons: " + counter.count);
        System.out.println("Time Complexity: O(log n)");
    }
}
