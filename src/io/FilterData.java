package io;

public class FilterData {
    private SortData sort;
    private ContainData contains;

    /**
     * Getter
     * **/
    public SortData getSort() {
        return sort;
    }

    /**
     * Setter
     * **/
    public void setSort(final SortData sort) {
        this.sort = sort;
    }

    /**
     * Getter
     * **/
    public ContainData getContains() {
        return contains;
    }

    /**
     * Setter
     * **/
    public void setContains(final ContainData contains) {
        this.contains = contains;
    }
}
