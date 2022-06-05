package com.company;


import org.json.simple.parser.ParseException;
import tetris.Canvas;
import tetris.GameState;


import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Scanner;

public class  Main{

    

    public static void main(String[] args) throws IOException, ParseException {
        GameState gameState = GameState.getInstance();
        gameState.setRecord(gameState.getScore());
        int width ;
        Scanner myObj = new Scanner(System.in);
        while (true){

            System.out.println("Enter Size 10<size<30");
            try {
                String input = myObj.nextLine();
                 width = Integer.parseInt(input);
                if(width >= 10 && width<=30){
                    break;
                }else {
                    System.out.println("ERROR!! please enter  number in range 10_30");
                }
            }catch (Exception e){
                System.out.println("ERROR!! please enter valid number");
            }
        }


        gameState.setWidthWindow(width);




        //JLabel pic = new JLabel(new ImageIcon("12.png"));
        //pic.setBounds(0,0,1200,700);
        //pic.setVisible(true);
        //pic.setLayout(null);


        Canvas canvas = new Canvas();
        canvas.setBounds((1050-(width*15))/2,10,1200,700);
        //pic.add(canvas);


        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,700);
        frame.add(canvas);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setLayout(null);



        frame.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        canvas.rotate(-1);


                        break;
                    case KeyEvent.VK_DOWN:
                        canvas.rotate(+1);

                        break;
                    case KeyEvent.VK_LEFT:
                        canvas.move(-1);
                        

                        break;
                    case KeyEvent.VK_RIGHT:
                        canvas.move(+1);

                        break;
                    case KeyEvent.VK_SPACE:
                        try {

                            canvas.dropDown();
                        } catch (IOException | ParseException ex) {
                            ex.printStackTrace();
                        }
                        break;

                    case KeyEvent.VK_R:
                        gameState.getPieceOrigin().x=5;
                        gameState.getPieceOrigin().y =0;
                        canvas.repaint();

                }
            }

            public void keyReleased(KeyEvent e) {

            }
        });


    }

}
