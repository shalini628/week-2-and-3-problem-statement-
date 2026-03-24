import java.util.*;
import java.util.*;

// ---------------- CLIENT CLASS ----------------
class Client {
    String name;
    int riskScore;
    double accountBalance;

    public Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    public String toString() {
        return name + "(" + riskScore + ", $" + accountBalance + ")";
    }
}

// ---------------- SORTING SYSTEM ----------------
class RiskSorter {

    // ----------- BUBBLE SORT (ASCENDING) -----------
    public static void bubbleSort(Client[] arr) {
        int n = arr.length;
        int swaps = 0;

        System.out.println("Bubble Sort (Ascending by Risk):");

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {

                    // Swap
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swaps++;

                    // Visualize swap
                    System.out.println("Swap: " + arr[j] + " <-> " + arr[j + 1]);

                    swapped = true;
                }
            }

            if (!swapped) break; // Early stop
        }

        printArray(arr);
        System.out.println("Total Swaps: " + swaps);
    }

    // ----------- INSERTION SORT (DESC + BALANCE) -----------
    public static void insertionSort(Client[] arr) {
        int n = arr.length;

        System.out.println("\nInsertion Sort (Descending Risk + Balance):");

        for (int i = 1; i < n; i++) {
            Client key = arr[i];
            int j = i - 1;

            // Sort by risk DESC, then balance DESC
            while (j >= 0 && compare(arr[j], key) < 0) {
                arr[j + 1] = arr[j]; // shift right
                j--;
            }
            arr[j + 1] = key;
        }

        printArray(arr);
    }

    // Comparator for DESC sorting
    private static int compare(Client a, Client b) {
        if (a.riskScore != b.riskScore) {
            return Integer.compare(a.riskScore, b.riskScore);
        }
        return Double.compare(a.accountBalance, b.accountBalance);
    }

    // ----------- TOP 10 HIGH RISK -----------
    public static void topHighRisk(Client[] arr) {
        System.out.println("\nTop High-Risk Clients:");

        int limit = Math.min(10, arr.length);
        for (int i = 0; i < limit; i++) {
            System.out.println(arr[i]);
        }
    }

    // ----------- PRINT HELPER -----------
    public static void printArray(Client[] arr) {
        for (Client c : arr) {
            System.out.print("[" + c + "] ");
        }
        System.out.println();
    }
}

public class ProblemStatement {
    public static void main(String[] args) {
        Client[] clients = {
                new Client("clientC", 80, 5000),
                new Client("clientA", 20, 2000),
                new Client("clientB", 50, 3000)
        };

        // Bubble Sort (ASC)
        RiskSorter.bubbleSort(clients);

        // Insertion Sort (DESC)
        RiskSorter.insertionSort(clients);

        // Top Risk Clients
        RiskSorter.topHighRisk(clients);


    }
}
