package Figrures;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Rook extends Figures{

    public Rook(int x, int y, boolean white, boolean black) {
        super(x, y, white, black);

        if(black) {
            try {
                image = ImageIO.read(new File("RookBlack.png"));
            } catch (IOException ex) {
                System.out.println("Black Rook read error");
            }
        } else if(white) {
            try {
                image = ImageIO.read(new File("RookWhite.png"));
            } catch (IOException ex) {
                System.out.println("White Rook read error");
            }
        }
    }
    public void move(int xMouseReleased, int yMouseReleased, List<Figures> figuresList) {
        int xMRField=xMouseReleased/120*120+20,yMRField = yMouseReleased/120*120+20, xFake = x, yFake = y, indexOfKilledFigure = 0;
        boolean possibleMove = true,possibleKill = false;

        while (xFake!=xMRField || yFake!=yMRField) {
            if (xMRField==xFake) {
                if (yMRField > yFake) yFake += 120;
                else yFake -= 120;
            } else if (yMRField==yFake) {
                if (xMRField > xFake) xFake += 120;
                else xFake -= 120;
            } else {
                break;
            }
            for (int i = 0; i <figuresList.size() ; i++) {
                //unikam powtarzania sie tego samego dla white i black, jesli są takie same to ruch, jeśli rozne to kill
                if (xFake==figuresList.get(i).getX() && yFake == figuresList.get(i).getY()) {
                    possibleMove = false;
                    if (figuresList.get(i).isBlack() != black ) {
                        possibleKill = true;
                        indexOfKilledFigure = i;
                    }
                    break;
                }
            }
            if(possibleKill) break;
        }
        if(possibleMove) {
            x = xFake;
            y = yFake;
        } else if (possibleKill && figuresList.get(indexOfKilledFigure).getX()==xMRField && figuresList.get(indexOfKilledFigure).getY()==yMRField) {
            figuresList.remove(indexOfKilledFigure);
            x = xFake;
            y= yFake;
        }
    }
    //SZACH
    public boolean possibleShah(List<Figures> figuresList) {
        int xKing, yKing, xF = x, yF = y,indexOfKing;
        boolean szachDetected = false,figureOnTheWay = false,rightDown = false,rightUp = false,leftDown = false,leftUp = false,right = false,left = false, down = false,up = false;

        if (white) {
            //white
            xKing = figuresList.get(0).getX();
            yKing = figuresList.get(0).getY();
            indexOfKing = 0;
        } else {
            //black
            xKing = figuresList.get(1).getX();
            yKing = figuresList.get(1).getY();
            indexOfKing = 1;
        }
        if (xKing>xF && yKing == yF) right = true;
        else if (xKing<xF && yKing == yF) left = true;
        else if (yKing>yF && xKing == xF) down = true;
        else if (yKing<yF && xKing == xF) up = true;
        while (xF<980 && xF > 20 || yF < 980 && yF > 20) {
            if (xF==xKing && yF == yKing) {
                szachDetected = true;
                break;
            } else if(right) {
                xF+=120;
            } else if (left) {
                xF-=120;
            } else if (down) {
                yF+=120;
            } else if (up) {
                yF-=120;
            } else break;

            for (int i = 2; i < figuresList.size(); i++) {
                if (xF == figuresList.get(i).getX() &&  yF == figuresList.get(i).getY()) {
                    figureOnTheWay = true;
                    break;
                    //if it's false, detected some figure on his way, (close this loop)
                }
            }
        }
        if (szachDetected && !figureOnTheWay) {
            System.out.println("szachuuunio rook");
            szachDetected = false;
            return true;
        } else {
            return false;
        }
    }
}
