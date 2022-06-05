package tetris;


import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class Canvas  extends JComponent {

    private final Constants constants = new Constants();
    private final GameState gameState = GameState.getInstance();
    private final GameWindow gameWindow = new GameWindow();
    private Drawer drawer;
    Sound sound = new Sound();
    public Canvas() {

        Timer timer = new Timer();
        TimerTask timerTask = new Ticker();
        timer.scheduleAtFixedRate(timerTask,1000,1000);
    }


    @Override
    public void paintComponent(Graphics g)

    {
        g.setColor(Color.red);
        g.fillRect(gameState.getWidthWindow(), gameState.getWidthWindow(), gameState.getWidthWindow()*(constants.numberOfRow-2), (gameState.getWidthWindow())*(constants.numberOfColumn-2));
        for (int i = 1; i < constants.numberOfRow-1; i++) {
            for (int j = 0; j < (constants.numberOfColumn-1) ; j++) {

                if (gameState.getPlace()[i][j].equals("empty") && j!=0) {
                    g.setColor(Color.black);
                    g.fillRect(gameState.getWidthWindow()*i, gameState.getWidthWindow()*j, gameState.getWidthWindow(), gameState.getWidthWindow());
                }else if(j!=0) {
                    g.setColor(new Color(220,120,20));
                    g.fillRect(gameState.getWidthWindow()*i , gameState.getWidthWindow()*j, gameState.getWidthWindow()-1, gameState.getWidthWindow()-1);
                }


            }
        }
        try {
            setDrawer(g);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        g.setColor(Color.black);
        g.fillRect(gameState.getWidthWindow(),0,gameState.getWidthWindow()*(constants.numberOfRow-2),gameState.getWidthWindow());
        g.setColor(Color.red);
        g.drawLine(gameState.getWidthWindow(),gameState.getWidthWindow(),gameState.getWidthWindow()*(constants.numberOfRow-1),gameState.getWidthWindow());

    }
    private boolean collidesAt(int x, int y, int rotation) {

        for (Point p : constants.Tetraminos[gameState.getCurrentPiece()][rotation]) {
            if ( !gameState.getPlace()[p.x + x][p.y + y].equals( "empty") ) {
                return false;
            }
        }
        return true;
    }



    public void move(int i) {
        sound.Sound(constants.moveAndRotationSound);
        if(gameState.getPieceOrigin().x + i>-1){
            if (collidesAt(gameState.getPieceOrigin().x + i, gameState.getPieceOrigin().y, gameState.getRotation())) {
                gameState.getPieceOrigin().x += i;
           }

        }
        repaint();
    }

    public void rotate(int i) {
        sound.Sound(constants.moveAndRotationSound);
        int newRotation = (gameState.getRotation() + i) % 4;
        if (newRotation < 0) {
            newRotation = 3;
        }
        if (collidesAt(gameState.getPieceOrigin().x, gameState.getPieceOrigin().y, newRotation)) {
            gameState.setRotation(newRotation);
        }
        repaint();
    }
    public void dropDown() throws IOException, ParseException {
        sound.Sound(constants.dropDownSound);
        if (collidesAt(gameState.getPieceOrigin().x, gameState.getPieceOrigin().y + 1, gameState.getRotation())) {
            gameState.getPieceOrigin().y += 1;
        } else {
            gameWindow.fall();
        }
        repaint();

    }
    public void setDrawer(Graphics g) throws FileNotFoundException {
        if(drawer==null)drawer = new Drawer();

        drawer.drawBorderInformation(g);
        drawer.drawPiece(g);
        drawer.drawNextPiece(g);
        drawer.drawLevel(g);
        drawer.drawScore(g);
        drawer.drawLines(g);

    }
    private class Ticker extends TimerTask {
        @Override
        public void run() {

            try {
                dropDown();
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }

            repaint();

        }
    }




}
