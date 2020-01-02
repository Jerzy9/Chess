package Main;


import java.awt.*;

public class Board {
    private Color color1 = new Color(50,105,50,200);
    private Color color2 = new Color(255,244,168,180);

    public Board() {

    }
    public void draw(Graphics g) {
        g.setColor(color1);
        g.fillRect(0,0,960+40,960+40);
        g.setColor(color2);
        for (int i = 0; i < 960/120/2; i++) {
            for (int j = 0; j < 960/120/2; j++) {
                g.fillRect(i*240+20,j*240+20,120,120);
                g.fillRect((i*240)+120+20,(j*240)+120+20,120,120);
            }
        }
        g.drawLine(20,20,980,20);
        g.drawLine(20,980,980,980);
        g.drawLine(20,20,20,980);
        g.drawLine(980,20,980,980);

    }

}
