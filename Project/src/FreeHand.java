import java.awt.*;
import java.util.ArrayList;
public class FreeHand {
     ArrayList<Point> points = new ArrayList<>();
    Color color;
    boolean dottedStatus,t=false;

    public FreeHand(Color color,boolean dottedStatus,boolean t) {
        this.color = color;
       this.t=t;
        this.dottedStatus=dottedStatus;
    }

    public void addPoint(Point p) {
        points.add(p);
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);

        if (dottedStatus && t) {
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
        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            g2.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }
}
