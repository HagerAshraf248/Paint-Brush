import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        JFrame frame=new JFrame("Paint Brush");
        frame.setSize(1200,800);

        ImageIcon icon = new ImageIcon("4615511.png");
        frame.setIconImage(icon.getImage());

        PaintBrush mainPart = new PaintBrush();
        ToolPanel tools = new ToolPanel(mainPart);

        frame.setLayout(new BorderLayout());
        frame.add(tools, BorderLayout.WEST);
        frame.add(mainPart, BorderLayout.CENTER);
        frame.setVisible(true);


        }
    }
