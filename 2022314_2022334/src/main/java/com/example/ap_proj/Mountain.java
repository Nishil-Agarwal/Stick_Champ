package com.example.ap_proj;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Mountain {
    private int height = 115;
    private Rectangle mountain;

    private Redtarget target;

    public Mountain(int xCoord,int width) {
        mountain = new Rectangle(xCoord, 210, width, height);
        this.mountain.setFill(Color.SADDLEBROWN);
    }

    public Rectangle getmountain() {
        return this.mountain;
    }

    public void createtarget(){}
    public void gettarget(){}

    public class Redtarget{
        private final int HEIGHT=3;
        private final int WIDTH=5;
        private Rectangle target;

        public Redtarget(int xcoord){          //xcoord to be of center of mountain
            this.target = new Rectangle(xcoord-2.5,207,WIDTH,HEIGHT);     //2.5 may cause issue due to decimal
            this.target.setFill(Color.RED);
        }
    }
}