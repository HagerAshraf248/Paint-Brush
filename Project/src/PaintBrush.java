/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.*;
import java.awt.event.*;
import java.util.Stack;
import java.util.Vector;
import javax.swing.*;

public class PaintBrush extends JPanel {

    private Color currentColor = Color.BLACK;     // Variable to store the current selected color
    private String currentShape = " ";
    private boolean dottedStatus = false, filledStatus = false;
    private int x1, y1, x2, y2;
    Vector<Object> shapeList = new Vector<>();
    Stack<Vector<Object>> shapeStack = new Stack<>();
    FreeHand currentFreeHand = null;

    public PaintBrush() {
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                x1 = e.getX();
                y1 = e.getY();
                saveState();
                if (currentShape.equals("Free"))
                    currentFreeHand = new FreeHand(currentColor,dottedStatus,true);
                else
                    currentFreeHand = new FreeHand(Color.WHITE,dottedStatus,true);
                //currentFreeHand = new FreeHand(currentShape.equals("Free") ? currentColor : Color.WHITE, dottedStatus,t);
                currentFreeHand.addPoint(e.getPoint());
                shapeList.add(currentFreeHand);
            }

            public void mouseReleased(MouseEvent e) {
                x2 = e.getX();
                y2 = e.getY();
                if (!currentShape.equals("Free") && !currentShape.equals("Eraser") && !currentShape.equals("Clear All"))
                shapeList.add(new Shape(x1, y1, x2, y2, currentColor, currentShape, dottedStatus, filledStatus));
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                x2 = e.getX();
                y2 = e.getY();

                if ((currentShape.equals("Free") || currentShape.equals("Eraser")) && currentFreeHand != null) {
                    currentFreeHand.addPoint(e.getPoint());
                  }

                repaint();
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Object obj : shapeList) {
            if (obj instanceof Shape) {
                ((Shape) obj).draw(g);
            } else if (obj instanceof FreeHand) {
                ((FreeHand) obj).draw(g);
            }
        }
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(currentColor);
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        int width = Math.abs(x2 - x1);
        int height = Math.abs(y2 - y1);

        if (dottedStatus) {
            float[] dashPattern = {5f, 10f};    // dot length, space length
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

        switch (currentShape) {
            case "Oval":
                if (filledStatus) {
                    g2.fillOval(x, y, width, height);
                } else {
                    g2.drawOval(x, y, width, height);
                }
                break;

            case "Rect":
                if (filledStatus) {
                    g2.fillRect(x, y, width, height);
                } else {
                    g2.drawRect(x, y, width, height);
                }
                break;
            case "Line":
                g2.drawLine(x1, y1, x2, y2);
                break;
        }
    }
    public void setCurrentColor(Color c){ currentColor = c; }
    public void setCurrentShape(String s){ currentShape = s; }
    public void setDotted(boolean d){ dottedStatus = d; }
    public void setFilled(boolean f){ filledStatus = f; }
    public void clearAll(){ shapeList.clear(); currentShape=""; repaint(); }
    public void saveState() {
        Vector<Object> copy = new Vector<>(shapeList);
        shapeStack.push(copy);
    }
    public void undo() {
        if (!shapeStack.isEmpty()) {
            shapeList = shapeStack.pop();
            currentShape="";
            repaint();
        }
    }
}
