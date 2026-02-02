/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author lenovo
 */
public class Shape {

    int x1;
    int x2;
    int y1;
    int y2;
    Color shapeColor;
    String shapeType;
    boolean dottedShape;
    boolean filledShape;

    public Shape(int x1, int y1, int x2, int y2, Color shapeColor, String shapeType, boolean dottedShape,boolean filledShape) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.shapeColor = shapeColor;
        this.shapeType = shapeType;
        this.dottedShape = dottedShape;
        this.filledShape = filledShape;
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(shapeColor);
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        int width = Math.abs(x2 - x1);
        int height = Math.abs(y2 - y1);
        
        if (dottedShape) {
        float[] dashPattern = {5f, 10f};
        BasicStroke dotted = new BasicStroke(
                2f,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND,
                0f,
                dashPattern,
                0f
        );
        g2.setStroke(dotted);
    } else {
        g2.setStroke(new BasicStroke(2));
    }

    switch (shapeType) {
        case "Oval":
            if (filledShape) g2.fillOval(x, y, width, height);
            else g2.drawOval(x, y, width, height);
            break;

        case "Rect":
            if (filledShape) g2.fillRect(x, y, width, height);
            else g2.drawRect(x, y, width, height);
            break;
        case "Line":
            g2.drawLine(x1, y1, x2, y2);
            break;
    }

    g2.setStroke(new BasicStroke()); 
     }
  }
