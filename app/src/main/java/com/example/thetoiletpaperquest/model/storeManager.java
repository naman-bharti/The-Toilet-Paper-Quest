package com.example.thetoiletpaperquest.model;
// Class represents a collection of the stores and keeps track of which store has toilet paper and which ones are revealed to the user
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class storeManager {
    private int numberOfRows;
    private int numberOfStoresWithTPs;
    private int numberOfColumns;
    private List<List> theStores=new ArrayList<List>();
    private int numberOfToiletPapersInRowsUnrevealed[];
    private int numberOfToiletPapersInColumnsUnrevealed[];
    private static storeManager instance;
    public static storeManager getInstance(int rows, int columns, int numberOfTPs){
        instance=new storeManager(rows,columns,numberOfTPs);
        return instance;
    }
    public static storeManager getInstance(){
        return instance;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.getInstance(numberOfRows,this.numberOfColumns,this.numberOfStoresWithTPs);
    }

    public int getNumberOfStoresWithTPs() {
        return numberOfStoresWithTPs;
    }

    public void setNumberOfStoresWithTPs(int numberOfStoresWithTPs) {
        this.getInstance(this.numberOfRows,this.numberOfColumns,numberOfStoresWithTPs);
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public void setNumberOfColumns(int numberOfColumns) {
        this.getInstance(this.numberOfRows,numberOfColumns,this.numberOfStoresWithTPs);
    }

    private storeManager(int rows, int columns, int numberOfStoresWithToiletPaper){
        numberOfToiletPapersInRowsUnrevealed =new int[rows];
        numberOfToiletPapersInColumnsUnrevealed =new int[columns];
        //Initializing the lists containing number of elements
        for(int i=0;i<rows;i++){
            numberOfToiletPapersInRowsUnrevealed[i]=0;
        }
        for(int i=0;i<columns;i++){
            numberOfToiletPapersInColumnsUnrevealed[i]=0;
        }
        int numberOfToiletPapersAdded=0;
        Random random=new Random();
        for(int rowIndex=0;rowIndex<rows;rowIndex++){
            List<theStore> aStoreRow= new ArrayList<theStore>();
            theStores.add(aStoreRow);
            for(int columnIndex=0;columnIndex<columns;columnIndex++){

                theStore aStore=new theStore(false);
                theStores.get(rowIndex).add(aStore);
            }
        }
        for(int i=0;i<numberOfStoresWithToiletPaper;){
            int rowIndex=Math.abs(random.nextInt()%rows);
            int columnIndex=Math.abs(random.nextInt()%columns);
            List<theStore> theRandomRowOfStores=theStores.get(rowIndex);
            if(!theRandomRowOfStores.get(columnIndex).hasToiletPaper()) {
                i++;
                numberOfToiletPapersInColumnsUnrevealed[columnIndex]++;
                numberOfToiletPapersInRowsUnrevealed[rowIndex]++;
                theRandomRowOfStores.get(columnIndex).setHasToiletPaper(true);
            }
        }
        this.numberOfColumns=columns;
        this.numberOfRows=rows;
        this.numberOfStoresWithTPs=numberOfStoresWithToiletPaper;
    }
    //Returns the output to be displayed on the screen
    //Returns -1 if toilet paper is to be displayed without any text on it
    public int getOutput(int row, int column){
        theStore aStore=(theStore)this.theStores.get(row).get(column);
        if((!aStore.isExplored())&&(!aStore.hasToiletPaper())){
            aStore.storeVisited();
            return this.numberOfToiletPapersInRowsUnrevealed[row]+this.numberOfToiletPapersInColumnsUnrevealed[column];
        }
        else if(aStore.hasToiletPaper()&&aStore.isExplored()){
            aStore.storeVisited();
            aStore.setToiletPaperShowingANumber();
            return this.numberOfToiletPapersInRowsUnrevealed[row]+this.numberOfToiletPapersInColumnsUnrevealed[column];
        }
        else if((!aStore.hasToiletPaper())&&aStore.isExplored()){
            aStore.storeVisited();
            return this.numberOfToiletPapersInRowsUnrevealed[row]+this.numberOfToiletPapersInColumnsUnrevealed[column];
        }
        else{
            aStore.storeVisited();
            numberOfToiletPapersInColumnsUnrevealed[column]--;
            numberOfToiletPapersInRowsUnrevealed[row]--;
            return -1;
        }
    }
    //Returns the updated text to be displayed and returns -1 if text is supposed to be the same
    public int getOutputWithoutVisiting(int row, int column){
        theStore aStore=(theStore)this.theStores.get(row).get(column);
        if(aStore.hasToiletPaper()&&aStore.isExplored()&&aStore.isToiletPaperShowingANumber()){
            return this.numberOfToiletPapersInRowsUnrevealed[row]+this.numberOfToiletPapersInColumnsUnrevealed[column];
        }
        else if((!aStore.hasToiletPaper())&&aStore.isExplored()){
            return this.numberOfToiletPapersInRowsUnrevealed[row]+this.numberOfToiletPapersInColumnsUnrevealed[column];
        }
        else{
            return -1;
        }
    }
    public boolean hasToiletPaper(int row, int column){
        theStore aStore= (theStore)theStores.get(row).get(column);
        return aStore.hasToiletPaper();
    }
    public boolean isExplored(int row, int column){
        theStore aStore= (theStore)theStores.get(row).get(column);
        return aStore.isExplored();
    }

}