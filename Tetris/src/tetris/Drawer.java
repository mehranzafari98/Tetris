package tetris;

import java.awt.*;


public class Drawer {
    private final Constants constants = new Constants();
    private final GameState gameState = GameState.getInstance();


    protected void drawPiece(Graphics g) {
        g.setColor(Color.cyan);
        for (Point p : constants.Tetraminos[gameState.getCurrentPiece()][gameState.getRotation()]) {
            g.fillRect((p.x + gameState.getPieceOrigin().x) * gameState.getWidthWindow(),
                    (p.y + gameState.getPieceOrigin().y) * gameState.getWidthWindow(),
                    gameState.getWidthWindow()-1, gameState.getWidthWindow()-1);
        }

    }
    protected void drawNextPiece(Graphics g) {

        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.blue);
        g2d.fillRect(gameState.getWidthWindow()*constants.numberOfRow+20,210,120,150);
        g.setColor(Color.red);
        for (Point p : constants.Tetraminos[gameState.getNextPiece()][0]) {
            g.fillRect((p.x ) * 20+gameState.getWidthWindow()*constants.numberOfRow +30,
                    (p.y ) * 20+280,
                    19, 19);
        }
        g2d.setColor(Color.white );
        g2d.drawString("NEXT",gameState.getWidthWindow()*constants.numberOfRow+40,225);
    }

    protected void drawLevel(Graphics g) {

        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.blue);
        g2d.fillRect(gameState.getWidthWindow()*constants.numberOfRow+20,370,120,70);
        g2d.setColor(Color.white );
        g2d.drawString("LEVEL\n",gameState.getWidthWindow()*constants.numberOfRow+40,390);
        g2d.drawString(""+gameState.getLevel(),gameState.getWidthWindow()*constants.numberOfRow+50,410);

    }
    protected void drawScore(Graphics g) {

        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.blue);
        g2d.fillRect(gameState.getWidthWindow()*constants.numberOfRow+20,0,120,200);
        g2d.setColor(Color.white );
        g2d.drawString("SCORE\n",gameState.getWidthWindow()*constants.numberOfRow+40,20);
        g2d.drawString(""+gameState.getScore(),gameState.getWidthWindow()*constants.numberOfRow+40,40);
        for(int i =1 ; i<11;i++){

            g2d.drawString("Record"+i+": "+gameState.getRecord().get("Record"+i),gameState.getWidthWindow()*constants.numberOfRow+30,48+i*15);

        }

    }
    protected void drawLines(Graphics g){

        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.blue);
        g2d.fillRect(gameState.getWidthWindow()*constants.numberOfRow+20,460,120,50);
        g2d.setColor(Color.white );
        g2d.drawString("LINES--"+ gameState.getLines(),gameState.getWidthWindow()*constants.numberOfRow+40,490);

    }

    protected void drawBorderInformation(Graphics g){

        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.black);
        g2d.fillRect(gameState.getWidthWindow()*constants.numberOfRow+10,0,140,520);

    }



}
