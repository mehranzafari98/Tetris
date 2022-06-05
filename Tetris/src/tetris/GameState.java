package tetris;


import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;



public class GameState {
    private  Point  pieceOrigin ;
    private Point   nextPieceOrigin ;
    private int rotation;

    private JSONObject record ;
    private long lines ;
    private  int currentPiece;
    private int  nextPiece ;
    private static GameState gameState;
    private  long score;
    private  long level;
    private int  widthWindow ;

    private  String  [][] place;




    public static GameState getInstance()  {
        if(gameState ==  null ){
            gameState = new GameState();


        }
        return gameState;
    }





    public Point getPieceOrigin() {
        return pieceOrigin;
    }


    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public int getCurrentPiece() {
        return currentPiece;
    }

    public void setCurrentPiece(int currentPiece) {
        this.currentPiece = currentPiece;
    }





    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }


    public int getNextPiece() {
        return nextPiece;
    }

    public void setNextPiece(int nextPiece) {
        this.nextPiece = nextPiece;
    }

    public void setNextPieceOrigin(Point nextPieceOrigin) {
        this.nextPieceOrigin = nextPieceOrigin;
    }

    public void setPieceOrigin(Point pieceOrigin) {
        this.pieceOrigin = pieceOrigin;
    }

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }

    public long getLines() {
        return lines;
    }

    public void setLines(long lines) {
        this.lines = lines;
    }


    public int getWidthWindow() {
        return widthWindow;
    }

    public void setWidthWindow(int widthWindow) {
        this.widthWindow = widthWindow;
    }
    public String[][] getPlace() {
        return place;
    }

    public void setPlace(String[][] place) {
        this.place = place;
    }
    public JSONObject getRecord() {
        return record;
    }

    public void setRecord(JSONObject record) {
        this.record = record;
    }

    public void setGameStateFor_NewGame(){
        setScore(0);
        setLines(0);
        setLevel(0);
    }
    public void setRecord(long score) throws IOException, ParseException {
        try {
            Object obj = new JSONParser().parse(new FileReader("Record.json"));
            JSONObject jo = (JSONObject) obj;
            setRecord(jo);

            for(int i=1;i<11;i++){

                if( (long)jo.get("Record"+i) < score ){
                    for(int j=10;i<j;j--){
                        int l = j-1;

                        jo.replace("Record" + j, jo.get("Record"+ l) );
                    }


                    jo.replace("Record" + i, score);

                    break;
                }

            }
            PrintWriter pw = new PrintWriter("Record.json");
            pw.write(jo.toString());

            pw.flush();
            pw.close();


        }catch (FileNotFoundException ex){
            CreateFileRecord();

        }
    }

    public void CreateFileRecord() throws IOException, ParseException {
        JSONObject jsObject = new JSONObject();
        for(int i=1;i<11;i++){
            jsObject.put("Record"+i,0);

        }

        PrintWriter pw = new PrintWriter("Record.json");
        pw.write(jsObject.toString());

        pw.flush();
        pw.close();
        setRecord(getScore());



    }

}




