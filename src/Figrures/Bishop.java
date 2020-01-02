package Figrures;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Bishop extends Figures{

    public Bishop(int x, int y, boolean white,boolean black) {
        super(x, y, white, black);

        if(black) {
            try {
                image = ImageIO.read(new File("BishopBlack.png"));
            } catch (IOException ex) {
                System.out.println("Black Bishop read error");
            }
        } else if(white) {
            try {
                image = ImageIO.read(new File("BishopWhite.png"));
            } catch (IOException ex) {
                System.out.println("White Bishop read error");
            }
        }
    }
    public void move(int xMouseReleased, int yMouseReleased, List<Figures> figuresList) {
        int xMRField=xMouseReleased/120*120+20,yMRField = yMouseReleased/120*120+20, xFake = x, yFake = y, indexOfKilledFigure = 0;
        boolean possibleMove = true,possibleKill = false;

        while (xFake!=xMRField || yFake!=yMRField) {
            if (xFake<xMRField && yFake <yMRField) {
                xFake+=120;
                yFake+=120;
            } else if (xFake<xMRField && yFake>yMRField) {
                xFake+=120;
                yFake-=120;
            } else if (xFake>xMRField && yFake<yMRField) {
                xFake-=120;
                yFake+=120;
            } else if (xFake>xMRField && yFake>yMRField) {
                xFake-=120;
                yFake-=120;
            } else
                break;

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
        if(possibleMove && xFake ==xMRField && yFake == yMRField) {
            x = xFake;
            y = yFake;
        } else if (possibleKill && figuresList.get(indexOfKilledFigure).getX()==xMRField && figuresList.get(indexOfKilledFigure).getY()==yMRField) {
            figuresList.remove(indexOfKilledFigure);
            x = xFake;
            y= yFake;
        }
    }
    public boolean possibleShah(List<Figures> figuresList) {
        int xKing,yKing,xF = x,yF = y,indexOfKing;
        boolean szachDetected = false,figureOnTheWay = false,rightDown = false,rightUp = false,leftDown = false,leftUp = false;
        //white
        if (white) {
            xKing = figuresList.get(0).getX();
            yKing = figuresList.get(0).getY();
            indexOfKing = 0;
        } else {
         //black
            xKing = figuresList.get(1).getX();
            yKing = figuresList.get(1).getY();
            indexOfKing = 1;
        }
        if (xKing>xF && yKing>yF) rightDown = true;
        else if (xKing>xF && yKing<yF) rightUp = true;
        else if (xKing<xF && yKing>yF) leftDown = true;
        else if (xKing<xF && yKing<yF) leftUp = true;
        while (xF<980 && xF > 20 || yF < 980 && yF > 20) {
            if (xF==xKing && yF == yKing) {
                szachDetected = true;
                break;
            } else if (rightDown) {
                xF+=120;
                yF+=120;
            } else if (rightUp) {
                xF+=120;
                yF-=120;
            } else if (leftDown) {
                xF-=120;
                yF+=120;
            } else if (leftUp) {
                xF-=120;
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
            System.out.println("szachuuunio bishop");
            szachDetected = false;
            return true;
        } else {
            return false;
        }
    }
}
