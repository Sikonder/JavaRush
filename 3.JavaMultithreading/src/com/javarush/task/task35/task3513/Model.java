package com.javarush.task.task35.task3513;

import java.util.*;

/**
 * Created by Sikonder on 29.07.2017.
 */
public class Model {
    private final static int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    int score;
     int maxTile;
     Stack<Tile[][]> previousStates = new Stack();
     Stack<Integer> previousScores = new Stack();
     boolean isSaveNeeded = true;


    public Model() {
        resetGameTiles();
    }
    public void resetGameTiles(){
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for(int i = 0;i<FIELD_WIDTH;i++){
            for(int j = 0;j<FIELD_WIDTH;j++){
                gameTiles[i][j] = new Tile();
            }
        }
        score=0;
        maxTile=2;
        addTile();
        addTile();
    }
    private void addTile(){
        List<Tile> list = getEmptyTiles();
        if(list.size()>0){
            list.get((int)(list.size()*Math.random())).value = Math.random()<0.9 ? 2:4;
        }
    }
    private List<Tile> getEmptyTiles(){
        List<Tile> list = new ArrayList<>();
        for(int i = 0;i<FIELD_WIDTH;i++){
            for(int j = 0;j<FIELD_WIDTH;j++){
                if(gameTiles[i][j].value==0){
                    list.add(gameTiles[i][j]);
                }
            }
        }
        return list;
    }
    private boolean compressTiles(Tile[] tiles){
        boolean change = false;
        for(int k = 0;k<tiles.length-1;k++){
            for(int i = 0;i<tiles.length-1;i++){
                if(tiles[i].isEmpty()&&!tiles[i+1].isEmpty()){
                    change=true;
                    tiles[i]=tiles[i+1];
                    tiles[i+1] = new Tile();
                }
            }
        }
        return change;
    }
    private boolean mergeTiles(Tile[] tiles){
        boolean change = false;
        for(int i = 0;i<tiles.length-1;i++){
            if(tiles[i].value==tiles[i+1].value && !tiles[i].isEmpty()&&!tiles[i+1].isEmpty()){
                change=true;
                tiles[i].value = tiles[i].value*2;
                tiles[i+1] = new Tile();
                maxTile = maxTile>tiles[i].value? maxTile : tiles[i].value;
                score+=tiles[i].value;
                compressTiles(tiles);
            }
        }
        return change;
    }
    public void left(){
        if(isSaveNeeded) saveState(gameTiles);
        boolean isChanged = false;
        for(int i = 0;i<FIELD_WIDTH;i++){
            if(compressTiles(gameTiles[i])|| mergeTiles(gameTiles[i])){
                isChanged=true;
            }
        }
        if(isChanged) addTile();
        isSaveNeeded = true;
    }
    private void rotateToRight() {
        Tile tmp;
        for (int i = 0; i < FIELD_WIDTH / 2; i++) {
            for (int j = i; j < FIELD_WIDTH - 1 - i; j++) {
                tmp = gameTiles[i][j];
                gameTiles[i][j] = gameTiles[FIELD_WIDTH-j-1][i];
                gameTiles[FIELD_WIDTH-j-1][i] = gameTiles[FIELD_WIDTH-i-1][FIELD_WIDTH-j-1];
                gameTiles[FIELD_WIDTH-i-1][FIELD_WIDTH-j-1] = gameTiles[j][FIELD_WIDTH-i-1];
                gameTiles[j][FIELD_WIDTH-i-1] = tmp;
            }
        }
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }
    public boolean canMove(){
        if(!getEmptyTiles().isEmpty()) return true;

        for (int i = 0;i<gameTiles.length;i++){
            for(int j = 1;j<gameTiles.length;j++){
                if(gameTiles[i][j].value==gameTiles[i][j-1].value)
                    return true;
            }
        }
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 1; j < gameTiles.length; j++) {
                if (gameTiles[j][i].value == gameTiles[j-1][i]. value)return true;
            }
        }
        return false;
    }

    void right() {
        saveState(gameTiles);
        rotateToRight();
        rotateToRight();
        left();
        rotateToRight();
        rotateToRight();
    }
    void up() {
        saveState(gameTiles);
        rotateToRight();
        rotateToRight();
        rotateToRight();
        left();
        rotateToRight();
    }
    void down() {
        saveState(gameTiles);
        rotateToRight();
        left();
        rotateToRight();
        rotateToRight();
        rotateToRight();
    }

    private void saveState(Tile[][] tiles){
       Tile[][] toSave = new Tile[tiles.length][tiles[0].length];
       for(int i = 0;i<tiles.length;i++){
           for(int j = 0;j<tiles[0].length;j++){
               toSave[i][j] = new Tile(tiles[i][j].value);
           }
       }
       previousStates.push(toSave);
        int scoreToSave = score;
       previousScores.push(scoreToSave);
       isSaveNeeded = false;
    }
    public void rollback(){
        if(!previousStates.isEmpty()&&!previousScores.isEmpty()){
            this.score = previousScores.pop();
            this.gameTiles=previousStates.pop();
        }
    }
    public void randomMove(){
        int n = ((int) (Math.random() * 100)) % 4;
        if(n == 0) left();
        else if(n==1) right();
        else if(n==2) up();
        else  if(n==3) down();
    }
    private boolean hasBoardChanged(){
        boolean result = false;
        int sumNow=0;
        int sumPre=0;
        Tile[][] tmp=previousStates.peek();
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[0].length; j++) {
                sumNow += gameTiles[i][j].value;
                sumPre += tmp[i][j].value;
            }
        }
        return sumNow != sumPre;

    }
    private MoveEfficiency getMoveEfficiency(Move move){
        MoveEfficiency moveEfficiency;
        move.move();
        if(hasBoardChanged()) moveEfficiency = new MoveEfficiency(getEmptyTiles().size(),score,move);
        else moveEfficiency=new MoveEfficiency(-1,0,move);
        rollback();
        return moveEfficiency;

    }
    public void autoMove(){

    }
}
