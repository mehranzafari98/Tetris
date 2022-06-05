package tetris;


import java.awt.*;
import java.util.Random;


public class Pieces{



    private final GameState gameState = GameState.getInstance();

    public Pieces()  {
    }

    public void setCurrent_and_next_Piece() {
        gameState.setPieceOrigin(new Point(6,0));
        gameState.setNextPieceOrigin(new Point(14,10 ));
        gameState.setRotation(0);
        Random random = new Random(System.nanoTime());
        gameState.setCurrentPiece(gameState.getNextPiece());
        gameState.setNextPiece(random.nextInt(7));
    }


}
