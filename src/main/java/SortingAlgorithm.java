public enum SortingAlgorithm {
    QUICK_SORT("QuickSort", "Θ(n^2)", "Θ(n log n)", "Θ(n log n)"),
    MERGE_SORT("MergeSort (Top-Down)", "Θ(n log n)", "Θ(n log n)", "Θ(n log n)"),
    BUBBLE_SORT("BubbleSort (Swaps)", "Θ(n^2)", "Θ(n^2)", "Θ(1)"),
    SELECTION_SORT("SelectionSort (Swaps)", "Θ(n^2)", "Θ(n^2)", "Θ(n^2)"),
    INSERTION_SORT("InsertionSort", "Θ(n^2)", "Θ(n^2)", "Θ(n)");
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
