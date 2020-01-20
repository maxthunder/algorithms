package sorting;

public enum SortingAlgorithm {
    QUICK_SORT("sorting.QuickSort", "Θ(n^2)", "Θ(n log n)", "Θ(n log n)"),
    MERGE_SORT("sorting.MergeSort (Top-Down)", "Θ(n log n)", "Θ(n log n)", "Θ(n log n)"),
    BUBBLE_SORT("sorting.BubbleSort (Swaps)", "Θ(n^2)", "Θ(n^2)", "Θ(1)"),
    SELECTION_SORT("sorting.SelectionSort (Swaps)", "Θ(n^2)", "Θ(n^2)", "Θ(n^2)"),
    INSERTION_SORT("sorting.InsertionSort", "Θ(n^2)", "Θ(n^2)", "Θ(n)");
    private String name;
    private String worstCase;
    private String avgCase;
    private String bestCase;
    SortingAlgorithm(String name, String worstCase, String avgCase, String bestCase) {
        this.name = name;
        this.worstCase = worstCase;
        this.avgCase = avgCase;
        this.bestCase = bestCase;
    }
    public String getName() { return name; }
    public String getWorstCase() {
        return worstCase;
    }
    public String getAvgCase() {
        return avgCase;
    }
    public String getBestCase() {
        return bestCase;
    }
    public String toString() {
        return name.toUpperCase() + ": " + "[" + worstCase + ", " + avgCase + ", " + bestCase + "]";
    }
}
