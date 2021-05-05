package com.example.thetoiletpaperquest.model;
//The class represents the store, it stores the information about the toilet paper availability and whether the user has visited the store or not
public class theStore {
    private boolean hasToiletPaper;
    private boolean isExplored=false;
    private boolean isToiletPaperShowingANumber=false;
    theStore(boolean hasToiletPaper){
        this.setHasToiletPaper(hasToiletPaper);
    }

    public boolean hasToiletPaper() {
        return hasToiletPaper;
    }

    public void setHasToiletPaper(boolean hasToiletPaper) {
        this.hasToiletPaper = hasToiletPaper;
    }

    public void storeVisited() {
        isExplored = true;
    }

    public boolean isExplored() {
        return isExplored;
    }

    public boolean isToiletPaperShowingANumber() {
        return isToiletPaperShowingANumber;
    }

    public void setToiletPaperShowingANumber() {
        isToiletPaperShowingANumber = true;
    }
}
