package Figrures;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class King extends Figures{
    private boolean shah = false;

    public boolean isShah() {
        return shah;
    }

    public void setShah(boolean shah) {
        this.shah = shah;
    }

    public King(int x, int y, boolean white,boolean black) {
        super(x, y, white, black);;

        if(black) {
            try {
                image = ImageIO.read(new File("KingBlack.png"));
            } catch (IOException ex) {
                System.out.println("Black King read error");
            }
        } else if(white) {
            try {
                image = ImageIO.read(new File("KingWhite.png"));
            } catch (IOException ex) {
                System.out.println("White King read error");
            }
        }
    }
    public void move(int xMouseReleased, int yMouseReleased, List<Figures> figuresList) {
        int xMRField=xMouseReleased/120*120+20,yMRField = yMouseReleased/120*120+20, xFake = x, yFake = y, indexOfKilledFigure = 0,xEarly = x,yEarly = y;
        boolean possibleMove = true,possibleKill = false;

        if (xMRField==xFake) {
            if (yMRField > yFake) yFake += 120;
            else yFake -= 120;
        } else if (yMRField==yFake) {
            if (xMRField > xFake) xFake += 120;
            else xFake -= 120;
        } else if (xFake < xMRField && yFake < yMRField) {
            xFake += 120;
            yFake += 120;
        } else if (xFake < xMRField && yFake > yMRField) {
            xFake += 120;
            yFake -= 120;
        } else if (xFake > xMRField && yFake < yMRField) {
            xFake -= 120;
            yFake += 120;
        } else if (xFake > xMRField && yFake > yMRField) {
            xFake -= 120;
            yFake -= 120;
        }

        for (int i = 0; i <figuresList.size() ; i++) {
            //I want to avoid duplicate same fraze for black and white, so its checking if !=black
            if (xFake==figuresList.get(i).getX() && yFake == figuresList.get(i).getY()) {
                possibleMove = false;
                if (figuresList.get(i).isBlack() != black ) {
                    possibleKill = true;
                    indexOfKilledFigure = i;
                }
                break;
            }
        }

        if(possibleMove && xFake ==xMRField && yFake == yMRField) {
            x = xFake;
            y = yFake;
        } else if (possibleKill && figuresList.get(indexOfKilledFigure).getX()==xMRField && figuresList.get(indexOfKilledFigure).getY()==yMRField) {
            figuresList.remove(indexOfKilledFigure);
            x = xFake;
            y= yFake;
        }

        for (int i = 0; i < figuresList.size(); i++) {
            if(figuresList.get(i).possibleShah(figuresList)) {
                x = xEarly;
                y = yEarly;
            }
        }
    }

}
