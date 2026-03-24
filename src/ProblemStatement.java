import java.util.*;


class Transaction {
    String id;
    double fee;
    String timestamp; // HH:MM format

    public Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    public String toString() {
        return id + ": " + fee + " @" + timestamp;
    }
}


class TransactionSorter {


    public static void bubbleSort(List<Transaction> list) {
        int n = list.size();
        int passes = 0, swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Collections.swap(list, j, j + 1);
                    swaps++;
                    swapped = true;
                }
            }


            if (!swapped) break;
        }

        System.out.println("Bubble Sort Result:");
        printList(list);
        System.out.println("Passes: " + passes + ", Swaps: " + swaps);
    }


    public static void insertionSort(List<Transaction> list) {
        int n = list.size();

        for (int i = 1; i < n; i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            // Sort by fee first, then timestamp
            while (j >= 0 && compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j)); // shift right
                j--;
            }
            list.set(j + 1, key);
        }

        System.out.println("\nInsertion Sort Result:");
        printList(list);
    }


    private static int compare(Transaction a, Transaction b) {
        if (a.fee != b.fee) {
            return Double.compare(a.fee, b.fee);
        }
        return a.timestamp.compareTo(b.timestamp);
    }


    public static void findOutliers(List<Transaction> list) {
        System.out.println("\nHigh-fee Outliers (> $50):");
        boolean found = false;

        for (Transaction t : list) {
            if (t.fee > 50) {
                System.out.println(t);
                found = true;
            }
        }

        if (!found) {
            System.out.println("None");
        }
    }


    public static void printList(List<Transaction> list) {
        for (Transaction t : list) {
            System.out.print("[" + t + "] ");
        }
        System.out.println();
    }
}

public class ProblemStatement {
    public static void main(String[] args) {

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        int size = transactions.size();

        // Decide sorting strategy
        if (size <= 100) {
            TransactionSorter.bubbleSort(transactions);
        } else if (size <= 1000) {
            TransactionSorter.insertionSort(transactions);
        } else {
            System.out.println("Use advanced sorting (not required here)");
        }

        // Outlier detection
        TransactionSorter.findOutliers(transactions);

    }
}
