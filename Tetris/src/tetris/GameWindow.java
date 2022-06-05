package tetris;



import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.IOException;


public class GameWindow {

    private final Constants constants= new Constants();
    private final Pieces pieces = new Pieces();
    private final GameState gameState = GameState.getInstance();
    private final Sound sound =new Sound();


    public GameWindow()  {
     setPlace();
    }
    public void setPlace(){
        gameState.setPlace( new String[constants.numberOfRow][constants.numberOfColumn]);
        for (int i = 0; i < constants.numberOfRow; i++) {
            for (int j = 0; j < constants.numberOfColumn; j++) {
                if (j == constants.numberOfColumn-1 || i==constants.numberOfRow-1 || i==0 ){
                    gameState.getPlace()[i][j] = "full";
                }
                else {
                    gameState.getPlace()[i][j] = "empty";
                }
            }

        }
        pieces.setCurrent_and_next_Piece();

    }



    public void fall() throws IOException, ParseException {

        for (Point p : constants.Tetraminos[gameState.getCurrentPiece()][gameState.getRotation()]) {
            gameState.getPlace()[gameState.getPieceOrigin().x + p.x ][gameState.getPieceOrigin().y + p.y] = "full";

            if( gameState.getPieceOrigin().y + p.y  ==1 ) {
                sound.Sound(constants.gameOverSound);


                setPlace();
                gameState.setRecord(gameState.getScore());
                gameState.setGameStateFor_NewGame();

            }
            sound.Sound(constants.fallSound);


        }

        clearRows();

        gameState.setLevel(gameState.getLevel() + 1);
        gameState.setScore(gameState.getScore()+1);
        pieces.setCurrent_and_next_Piece();

    }
    public void deleteRow(int row) {
        sound.Sound(constants.clearSound);
        for (int j = row-1; j > 0; j--) {
            for (int i = 0; i < constants.numberOfRow; i++) {
                gameState.getPlace()[i][j+1] = gameState.getPlace()[i][j];
            }
        }
    }

    public void clearRows() {
        boolean gap;
        int numClears = 0;

        for (int j = constants.numberOfColumn-2; j > 0; j--) {
            gap = false;
            for (int i = 0; i < constants.numberOfRow; i++) {
                if (gameState.getPlace()[i][j] .equals("empty")) {
                    gap = true;
                    break;
                }
            }
            if (!gap) {
                deleteRow(j);


                j += 1;
                numClears += 1;
            }
        }

        switch (numClears) {
            case 1 -> {
                gameState.setScore(gameState.getScore() + 10);
                gameState.setLines(gameState.getLines() + 1);
            }
            case 2 -> {
                gameState.setScore(gameState.getScore() + 20);
                gameState.setLines(gameState.getLines() + 2);
            }
            case 3 -> {
                gameState.setScore(gameState.getScore() + 30);
                gameState.setLines(gameState.getLines() + 3);
            }
            case 4 -> {
                gameState.setScore(gameState.getScore() + 400);
                gameState.setLines(gameState.getLines() + 4);
            }
        }
    }


}

