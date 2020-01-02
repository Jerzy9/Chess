package Main;

import Figrures.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class MainPanel extends JPanel implements Runnable, MouseListener {

    final static int WIDTH = 960+40, HEIGHT = WIDTH;
    private JPanel panel;
    private Thread thread;
    // all components
    private Board board;
    private Figures pawnWhite1,pawnWhite2,pawnWhite3,pawnWhite4,pawnWhite5,pawnWhite6,pawnWhite7,pawnWhite8,pawnBlack1,pawnBlack2,pawnBlack3,pawnBlack4,pawnBlack5,pawnBlack6,pawnBlack7,pawnBlack8;
    private Figures rookWhite1,rookWhite2,rookBlack1,rookBlack2;
    private Figures knightWhite1,knightWhite2,knightBlack1,knightBlack2;
    private Figures bishopWhite1,bishopWhite2,bishopBlack1,bishopBlack2;
    private Figures queenWhite,queenBlack;
    private Figures kingWhite,kingBlack;
    private List<Figures> figuresWhiteList,figuresBlackList,figuresList;

    private boolean running = false;
    private boolean whiteTour = true;

    private int xMousePressed, yMousePressed,xMouseReleased, yMouseReleased;
    private int ticks = 0;
    private int indexOfKing = 0, indexOfFigure = 33;

    public MainPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        panel = new JPanel();
        figuresWhiteList = new ArrayList<>();
        figuresBlackList = new ArrayList<>();
        figuresList = new ArrayList<>();
        //creating all components
        creatingGameComponents();

        add(panel);
        addMouseListener(this);
        start();
    }
    public void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    public void stop() {
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.fillInStackTrace();
            System.out.println("Thread.join error");
        }
    }
    @Override
    public void run() {
        while (running) {
            repaint();
            tick();
            try {
                thread.sleep(1000/30);
            } catch (InterruptedException ex) {
                ex.fillInStackTrace();
                System.out.println("Thread.sleep error");
            }
        }
    }
    public void tick() {

        if(ticks>50) {
            //i = 2, couse on first two places are Kings
            //shah detection system
//            for (int i = 2; i < figuresList.size(); i++) {
//                figuresList.get(i).possibleShah(figuresList);
//            }
            //method();
            ticks = 0;
        }
        ticks ++;
    }
    public void paint(Graphics g) {
        //drawing all components
        board.draw(g);
        for (int i = 0; i <figuresList.size() ; i++) {
            figuresList.get(i).draw(g);
        }

    }
    public void creatingGameComponents() {
        board = new Board();
        //KINGS
        kingBlack = new King(500,20,false,true);
        kingWhite = new King(500,860,true,false);

        pawnWhite1 = new Pawn(20,740,true,false);
        pawnWhite2 = new Pawn(140,740,true,false);
        pawnWhite3 = new Pawn(260,740,true,false);
        pawnWhite4 = new Pawn(380,740,true,false);
        pawnWhite5 = new Pawn(500,740,true,false);
        pawnWhite6 = new Pawn(620,740,true,false);
        pawnWhite7 = new Pawn(740,740,true,false);
        pawnWhite8 = new Pawn(860,740,true,false);

        rookWhite1 = new Rook(20,860,true,false);
        rookWhite2 = new Rook(860,860,true,false);

        knightWhite1 = new Knight(140,860,true,false);
        knightWhite2 = new Knight(740,860,true,false);

        bishopWhite1 = new Bishop(260,860,true,false);
        bishopWhite2 = new Bishop(620,860,true,false);
        queenWhite = new Queen(380,860,true,false);

        pawnBlack1 = new Pawn(20,140,false,true);
        pawnBlack2 = new Pawn(140,140,false,true);
        pawnBlack3 = new Pawn(260,140,false,true);
        pawnBlack4 = new Pawn(380,140,false,true);
        pawnBlack5 = new Pawn(500,140,false,true);
        pawnBlack6 = new Pawn(620,140,false,true);
        pawnBlack7 = new Pawn(740,140,false,true);
        pawnBlack8 = new Pawn(860,140,false,true);

        rookBlack1 = new Rook(20,20,false,true);
        rookBlack2 = new Rook(860,20,false,true);

        knightBlack1 = new Knight(140,20,false,true);
        knightBlack2 = new Knight(740,20,false,true);

        bishopBlack1 = new Bishop(260,20,false,true);
        bishopBlack2 = new Bishop(620,20,false,true);

        queenBlack = new Queen(380,20,false,true);
        //adding KINGS
        figuresList.add(kingBlack);
        figuresList.add(kingWhite);

        figuresList.add(pawnWhite1);
        figuresList.add(pawnWhite2);
        figuresList.add(pawnWhite3);
        figuresList.add(pawnWhite4);
        figuresList.add(pawnWhite5);
        figuresList.add(pawnWhite6);
        figuresList.add(pawnWhite7);
        figuresList.add(pawnWhite8);

        figuresList.add(rookWhite1);
        figuresList.add(rookWhite2);

        figuresList.add(knightWhite1);
        figuresList.add(knightWhite2);

        figuresList.add(bishopWhite1);
        figuresList.add(bishopWhite2);

        figuresList.add(queenWhite);

        figuresList.add(pawnBlack1);
        figuresList.add(pawnBlack2);
        figuresList.add(pawnBlack3);
        figuresList.add(pawnBlack4);
        figuresList.add(pawnBlack5);
        figuresList.add(pawnBlack6);
        figuresList.add(pawnBlack7);
        figuresList.add(pawnBlack8);

        figuresList.add(rookBlack1);
        figuresList.add(rookBlack2);

        figuresList.add(knightBlack1);
        figuresList.add(knightBlack2);

        figuresList.add(bishopBlack1);
        figuresList.add(bishopBlack2);

        figuresList.add(queenBlack);

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        xMousePressed = mouseEvent.getX();
        yMousePressed = mouseEvent.getY();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        movingSystem(mouseEvent);
    }
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }
    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
    public boolean shahDetection() {
        boolean kingWhiteShah = false,kingBlackShah = false;

        for (int i = 0; i < figuresList.size(); i++) {
            if(figuresList.get(i).possibleShah(figuresList)) {
                if (figuresList.get(i).isWhite()) {
                    //white
                    kingBlackShah = true;
                    indexOfKing = 0;
                    break;
                } else {
                    //black
                    kingWhiteShah = true;
                    indexOfKing = 1;
                    break;
                }
            }
        }
        if (kingBlackShah || kingWhiteShah) {
            return true;
        } else {
            return false;
        }
    }
    public void movingSystem (MouseEvent mouseEvent) {
        xMouseReleased = mouseEvent.getX();
        yMouseReleased = mouseEvent.getY();
        //these two variables are checking changes in x and y
        int xBef, yBef;
        //detecting where mouse has been released if shah off
        for (int i = 0; i < figuresList.size(); i++) {

            if (xMousePressed > figuresList.get(i).getX() && xMousePressed < figuresList.get(i).getX() + 120 && yMousePressed > figuresList.get(i).getY() && yMousePressed < figuresList.get(i).getY() + 120) {
                // x and y before move
                xBef = figuresList.get(i).getX();
                yBef = figuresList.get(i).getY();
                indexOfFigure = i;

                //move
                if (whiteTour && figuresList.get(i).isWhite()) {
                    figuresList.get(i).move(xMouseReleased, yMouseReleased, figuresList);
                    //turn = 1;
                } else if (!whiteTour && figuresList.get(i).isBlack()) {
                    figuresList.get(i).move(xMouseReleased, yMouseReleased, figuresList);
                    //turn = 0;
                }
                System.out.println(whiteTour);

                //if x or y have changed
                if (xBef!=figuresList.get(i).getX() || yBef != figuresList.get(i).getY()) {
                    System.out.println("kekke");
                    //these two conditions are here to avoid problem with indexOfKing (collision)
                    if (shahDetection() && figuresList.get(indexOfKing).isWhite() == figuresList.get(i).isWhite()) {
                        figuresList.get(i).setX(xBef);
                        figuresList.get(i).setY(yBef);
                        //that's the difference
                        indexOfKing = 0;
                        System.out.println("back white");

                    } else if (shahDetection() && figuresList.get(indexOfKing).isBlack() == figuresList.get(i).isBlack()) {
                        figuresList.get(i).setX(xBef);
                        figuresList.get(i).setY(yBef);
                        //that's the difference
                        indexOfKing = 1;
                        System.out.println("back black");

                    }else {
                        if (whiteTour) {
                            whiteTour = false;
                        } else {
                            whiteTour = true;
                        }
                    }
                }
            }
        }
    }
}

