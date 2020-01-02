package Figrures;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class Figures {
    BufferedImage image;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isShah() {
        return shah;
    }

    public void setShah(boolean shah) {
        this.shah = shah;
    }

    int x,y,width=120,height=120;
    boolean black;
    boolean white;
    private boolean shah = false;

    public Figures(int x, int y, boolean white,boolean black) {
        this.x = x;
        this.y = y;
        this.white = white;
        this.black = black;
    }
    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.drawImage(image, x,y,width,height,null);
    }

    public boolean isBlack() {
        return black;
    }

    public boolean isWhite() {
        return white;
    }

    public void move(int xMouseReleased, int yMouseReleased, List<Figures> figuresList) {
    }
    public boolean possibleShah(List<Figures> figuresList) {
        return false;
    }
}
