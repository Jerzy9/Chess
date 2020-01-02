package Figrures;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Knight extends Figures{

    public Knight(int x, int y, boolean white,boolean black) {
        super(x, y, white, black);

        if(black) {
            try {
                image = ImageIO.read(new File("KnightBlack.png"));
            } catch (IOException ex) {
                System.out.println("Black Knight read error");
            }
        } else if(white) {
            try {
                image = ImageIO.read(new File("KnightWhite.png"));
            } catch (IOException ex) {
                System.out.println("White Knight read error");
            }
        }
    }
    public void move(int xMouseReleased, int yMouseReleased, List<Figures> figuresList) {
        int xMRField=xMouseReleased/120*120+20,yMRField = yMouseReleased/120*120+20,xFake = x,yFake = y,indexOfKilledFigure = 0;
        boolean possibleMove = true,possibleKill = false;

        //split on two if, easier to read
        //four fields witch x -+120
        if (xMRField == x+120 && yMRField == y-240 || xMRField == x+120 && yMRField == y+240 || xMRField == x-120 && yMRField == y+240 || xMRField == x-120 && yMRField == y-240) {
            xFake = xMRField;
            yFake = yMRField;
        } else if (xMRField == x+240 && yMRField == y-120 || xMRField == x+240 && yMRField == y+120 || xMRField == x-240 && yMRField == y+120 || xMRField == x-240 && yMRField == y-120) {
            //four fields witch x -+240
            xFake = xMRField;
            yFake = yMRField;
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
        if(possibleMove) {
            x = xFake;
            y = yFake;
        } else if (possibleKill && figuresList.get(indexOfKilledFigure).getX()==xMRField && figuresList.get(indexOfKilledFigure).getY()==yMRField) {
            figuresList.remove(indexOfKilledFigure);
            x = xFake;
            y= yFake;
        }
    }
    public boolean possibleShah(List<Figures> figuresList) {
        //0 king black
        // 1 white king
        int xK=0,yK=0;

        if (white) {
            xK = figuresList.get(0).getX();
            yK = figuresList.get(0).getY();
        } else if (black) {
            xK = figuresList.get(1).getX();
            yK = figuresList.get(1).getY();
        }
        // 8 differend combinations
        if (xK== x+120 && yK == y-240 || xK == x+120 && yK == y+240 || xK == x-120 && yK == y+240 || xK == x-120 && yK == y-240 && xK == x+240 && yK == y-120 || xK == x+240 && yK == y+120 || xK == x-240 && yK == y+120 || xK == x-240 && yK == y-120) {
            System.out.println("szach knight");
            return true;
        } else return false;
    }
}
