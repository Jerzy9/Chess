package Figrures;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Pawn extends Figures{

    public Pawn(int x, int y, boolean white, boolean black) {
        super(x, y, white, black);

        if(black) {
            try {
                image = ImageIO.read(new File("PawnBlack.png"));
            } catch (IOException ex) {
                System.out.println("Black Pawn read error");
            }
        } else if(white) {
            try {
                image = ImageIO.read(new File("PawnWhite.png"));
            } catch (IOException ex) {
                System.out.println("White Pawn read error");
            }
        }
    }
    public void move(int xMouseReleased, int yMouseReleased, List<Figures> figuresList) {
        boolean possibleMove = true,possibleKill = false;
        int indexOfFigureLeft = 0,indexOfFigureRight= 0;

        for (int i = 0; i <figuresList.size() ; i++) {
            //detecting possible move
            if (xMouseReleased > figuresList.get(i).getX() && xMouseReleased < figuresList.get(i).getX() + 120 && yMouseReleased > figuresList.get(i).getY() && yMouseReleased < figuresList.get(i).getY() + 120) {
                possibleMove = false;
            }
            //detecting possible kills
            if (black && figuresList.get(i).isWhite()) {
                if (x + 120 == figuresList.get(i).getX() && y + 120 == figuresList.get(i).getY()) {
                    //right
                    indexOfFigureRight = i;
                    possibleKill = true;
                }
                if (x - 120 == figuresList.get(i).getX() && y + 120 == figuresList.get(i).getY()) {
                    //left
                    indexOfFigureLeft = i;
                    possibleKill = true;
                }
            } else if (white && figuresList.get(i).isBlack()) {
                if (x + 120 == figuresList.get(i).getX() && y - 120 == figuresList.get(i).getY()) {
                    indexOfFigureRight = i;
                    possibleKill = true;
                }
                if (x - 120 == figuresList.get(i).getX() && y - 120 == figuresList.get(i).getY()) {
                    indexOfFigureLeft = i;
                    possibleKill = true;
                }
            }
        }
        if (possibleMove) {
            if (black) {
                if (xMouseReleased > x && xMouseReleased < x + 120 && yMouseReleased > y + 120 && yMouseReleased < y + 240) {
                    //one hash upper
                    y += 120;
                } else if (y == 140 && xMouseReleased > x && xMouseReleased < x + 120 && yMouseReleased > y + 240 && yMouseReleased < y + 360) {
                    //two hash upper
                    y += 240;
                }
            } else if (white) {
                if (xMouseReleased > x && xMouseReleased < x + 120 && yMouseReleased < y && yMouseReleased > y - 120) {
                    //one hash upper
                    y -= 120;
                } else if (y == 740 && xMouseReleased > x && xMouseReleased < x + 120 && yMouseReleased < y - 120 && yMouseReleased > y - 240) {
                    //two hash upper
                    y -= 240;
                }
            }
        }
        if (possibleKill) {
            if (black) {
                if (xMouseReleased > x + 120 && xMouseReleased < x + 240 && yMouseReleased > y + 120 && yMouseReleased < y + 240) {
                    System.out.println("down, right and kill");
                    //down, right and kill
                    y += 120;
                    x += 120;
                    figuresList.remove(indexOfFigureRight);
                }
                if (xMouseReleased < x && xMouseReleased > x - 120 && yMouseReleased > y + 120 && yMouseReleased < y + 240) {
                    System.out.println("down, left and kill");
                    //down, left and kill
                    y += 120;
                    x -= 120;
                    figuresList.remove(indexOfFigureLeft);
                }
            }
            if (white) {
                if (xMouseReleased > x + 120 && xMouseReleased < x + 240 && yMouseReleased < y && yMouseReleased > y - 120) {
                    System.out.println("up, right and kill");
                    //up, right and kill
                    y -= 120;
                    x += 120;
                    figuresList.remove(indexOfFigureRight);
                }
                if (xMouseReleased < x && xMouseReleased > x - 120 && yMouseReleased < y && yMouseReleased > y - 120) {
                    System.out.println("up, left and kill");
                    //up, left and kill
                    y -= 120;
                    x -= 120;
                    figuresList.remove(indexOfFigureLeft);
                }
            }
        }
    }
    public boolean possibleShah(List<Figures> figuresList) {
        //It just checking for now, if there is king
        boolean isShah = false;
        for (int i = 0; i < figuresList.size(); i++) {
            //hashCOde BLACK KING index 0
            if (white) {
                if (x + 120 == figuresList.get(i).getX() && y - 120 == figuresList.get(i).getY() || x - 120 == figuresList.get(i).getX() && y - 120 == figuresList.get(i).getY()) {
                    if (i == 0) {
                        System.out.println("Szach pawn");
                        isShah = true;
                    }
                }
            }
            //hashCode WHITE KING index 1
            if (black) {
                if (x + 120 == figuresList.get(i).getX() && y + 120 == figuresList.get(i).getY() || x - 120 == figuresList.get(i).getX() && y + 120 == figuresList.get(i).getY()) {
                    if (i == 1) {
                        System.out.println("Szach pawn");
                        isShah = true;
                    }
                }
            }
        }
        return isShah;
    }
}
