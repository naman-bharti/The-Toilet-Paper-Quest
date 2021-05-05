package com.example.thetoiletpaperquest.ui;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.thetoiletpaperquest.R;
import com.example.thetoiletpaperquest.model.storeManager;

// This class represents the Game Activity.
public class GameScreen extends AppCompatActivity {
    private int rows;
    private int columns;
    private int numberOfStoresWithToiletPaper;
    private int totalNumberOfToiletPapersDiscovered=0;
    private int scansUsed=0;
    storeManager theStores;
    Button storeButtons[][];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        this.rows=storeManager.getInstance().getNumberOfRows();
        this.columns=storeManager.getInstance().getNumberOfColumns();
        this.storeButtons=new Button[rows][columns];
        this.numberOfStoresWithToiletPaper=storeManager.getInstance().getNumberOfStoresWithTPs();
        storeManager.getInstance(rows,columns,numberOfStoresWithToiletPaper);
        TextView settingNumberOfMaximumTP=(TextView)findViewById(R.id.totalNumberOfTP);
        settingNumberOfMaximumTP.setText(" "+numberOfStoresWithToiletPaper+" ");
        TextView settingNumberOfScans=(TextView)findViewById(R.id.numberOfScansUsed);
        settingNumberOfScans.setText(" 0 ");
        TextView settingNumberOfTPDiscovered=(TextView)findViewById(R.id.numberOfTPsDiscovered);
        settingNumberOfTPDiscovered.setText(" 0 ");
        populateButtons();
    }

    private void populateButtons() {
        TableLayout tableOfStores=(TableLayout)findViewById( R.id.theButtons);
        theStores= storeManager.getInstance();
        for(int row=0;row<rows;row++){
            TableRow rowOfStores=new TableRow(this);
            rowOfStores.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            ));
            tableOfStores.addView(rowOfStores);
            for(int col=0;col<columns;col++){
                final int ROW_FOR_PARAMETER=row;
                final int COL_FOR_PARAMETER=col;
                Button aStore=new Button(this);
                aStore.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f
                ));
                int height=aStore.getHeight();
                int width=aStore.getWidth();
                aStore.setBackgroundResource(R.drawable.store_icon);
                aStore.setPadding(0,0,0,0);
                rowOfStores.addView(aStore);
                storeButtons[row][col]=aStore;
                aStore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            exploreTheStore(ROW_FOR_PARAMETER, COL_FOR_PARAMETER);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    private void exploreTheStore(int rowForParameter, int colForParameter) throws InterruptedException {
        boolean visitedFirstTime=!theStores.isExplored(rowForParameter,colForParameter);
        int theOutput=theStores.getOutput(rowForParameter,colForParameter);
        Button theStoreClicked=storeButtons[rowForParameter][colForParameter];
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if(theOutput==-1){
            vibrator.vibrate(600);
            this.totalNumberOfToiletPapersDiscovered++;
            theStoreClicked.setBackgroundResource(R.drawable.toilet_paper);
            TextView settingCurrentTP=(TextView)findViewById(R.id.numberOfTPsDiscovered);
            settingCurrentTP.setText(" "+totalNumberOfToiletPapersDiscovered+" ");
            if(totalNumberOfToiletPapersDiscovered==this.numberOfStoresWithToiletPaper){
                MediaPlayer audio;
                audio=MediaPlayer.create(GameScreen.this,R.raw.toilet_paper_sound);
                audio.seekTo(0);
                audio.setVolume(3,3);
                audio.start();
                gameOver();
            }
            else{
                MediaPlayer audio;
                audio=MediaPlayer.create(GameScreen.this,R.raw.toilet_paper_sound);
                audio.seekTo(0);
                audio.setVolume(3,3);
                audio.start();
            }
            updateRowsAndColumnsScanned(rowForParameter,colForParameter);
            storeButtons[rowForParameter][colForParameter].setText("");
        }
        else if(this.theStores.hasToiletPaper(rowForParameter,colForParameter)){
            vibrator.vibrate(500);
            scanEffect(rowForParameter,colForParameter);
            if(visitedFirstTime) {
                scansUsed++;
            }
            theStoreClicked.setText(""+theOutput);
            MediaPlayer audio;
            audio=MediaPlayer.create(GameScreen.this,R.raw.toiler_paper_scan);
            audio.seekTo(0);
            audio.setVolume(3,3);
            audio.start();
            updateRowsAndColumnsScanned(rowForParameter,colForParameter);
        }
        else if(theOutput==0){
            vibrator.vibrate(400);
            scanEffect(rowForParameter,colForParameter);
            MediaPlayer audio;
            audio=MediaPlayer.create(GameScreen.this,R.raw.sound_for_empty);
            audio.seekTo(0);
            audio.setVolume(3,3);
            audio.start();
            if(visitedFirstTime) {
                scansUsed++;
            }
            theStoreClicked.setText(""+theOutput);
            theStoreClicked.setBackgroundResource(R.drawable.red_circle);
            updateRowsAndColumnsScanned(rowForParameter,colForParameter);
        }
        else{
            vibrator.vibrate(300);
            scanEffect(rowForParameter,colForParameter);
            MediaPlayer audio;
            audio=MediaPlayer.create(GameScreen.this,R.raw.scan_sound);
            audio.seekTo(0);
            audio.setVolume(3,3);
            audio.start();
            if(visitedFirstTime) {
                scansUsed++;
            }
            theStoreClicked.setText(""+theOutput);
            theStoreClicked.setBackgroundResource(R.drawable.green_circle_);
            updateRowsAndColumnsScanned(rowForParameter,colForParameter);
        }
        TextView updatingScans=(TextView)findViewById(R.id.numberOfScansUsed);
        updatingScans.setText(" "+scansUsed+" ");
    }

    private void scanEffect(int rowForParameter, int colForParameter) {
        for(int i=0;i<rows;i++){
            Button btnForEffect=storeButtons[i][colForParameter];
            btnForEffect.animate().rotationBy(360);
        }
        for(int i=0;i<columns;i++){
            Button btnForEffect=storeButtons[rowForParameter][i];
            btnForEffect.animate().rotationBy(360);
        }
    }



    private void gameOver() throws InterruptedException {
        FragmentManager manager=getSupportFragmentManager();
        WinMessage dialog=new WinMessage();
        MediaPlayer audio;
        audio=MediaPlayer.create(GameScreen.this,R.raw.win_sound);
        audio.seekTo(0);
        audio.setVolume(3,3);
        audio.start();
        dialog.show(manager,"WinMessage");
    }

    private void updateRowsAndColumnsScanned(int row, int column){
        for(int i=0;i<this.columns;i++){
            int x=theStores.getOutputWithoutVisiting(row,i);
            if(theStores.isExplored(row,i)&&x!=-1){
                storeButtons[row][i].setText(""+x);
            }
        }
        for(int i=0;i<this.rows;i++){
            int x=theStores.getOutputWithoutVisiting(i,column);
            if(theStores.isExplored(i,column)&&x!=-1){
                storeButtons[i][column].setText(""+x);
            }
        }
    }
}
//Scan used not increased buttons incremented automatically