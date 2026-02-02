import javax.swing.*;
import java.awt.*;

public class ToolPanel extends JPanel {

    public ToolPanel(PaintBrush p) {
        setPreferredSize(new Dimension(130, 300));
        setMaximumSize(new Dimension(180, Integer.MAX_VALUE));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
//Color Group
        JPanel groupColor = new JPanel(new FlowLayout(FlowLayout.LEADING, 2, 2));
        groupColor.setBorder(BorderFactory.createTitledBorder("Colors"));
        Color[] colorr = { Color.RED, Color.GREEN, Color.BLUE,Color.black };

        for (Color color : colorr) {
            JButton colorBtn = new JButton();
            colorBtn.setBackground(color);
            colorBtn.setPreferredSize(new Dimension(24, 24));
            colorBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            colorBtn.addActionListener(e -> p.setCurrentColor(color));
            groupColor.add(colorBtn);
        }

        groupColor.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupColor.setMaximumSize(groupColor.getPreferredSize());
//Shape Group
        JPanel groupShape = new JPanel(new GridLayout(0, 2, 2, 2));
        groupShape.setBorder(BorderFactory.createTitledBorder("Shapes"));
        String[] shapeName = { "Oval", "Rect", "Line", "Free" };
        ButtonGroup shapeGroup = new ButtonGroup();
        for (String current : shapeName) {
            JToggleButton shapeBtn = new JToggleButton(current);
            shapeBtn.setPreferredSize(new Dimension(50, 50));
            shapeBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                shapeBtn.addActionListener(e -> p.setCurrentShape(current));

            shapeGroup.add(shapeBtn);
            groupShape.add(shapeBtn);
        }

        groupShape.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupShape.setMaximumSize(groupShape.getPreferredSize());
//Edit Group
        JPanel editGroup = new JPanel();
        editGroup.setLayout(new BoxLayout(editGroup, BoxLayout.Y_AXIS));
        editGroup.setAlignmentX(Component.CENTER_ALIGNMENT);
        editGroup.setBorder(BorderFactory.createTitledBorder("Edit Tools"));

        JToggleButton eraserBtn = new JToggleButton("Eraser");

        eraserBtn.addActionListener(e -> p.setCurrentShape("Eraser"));
        shapeGroup.add(eraserBtn);

        JButton undoBtn = new JButton("Undo");
        undoBtn.addActionListener(e ->{
         shapeGroup.clearSelection();
            p.undo();
        });

        JButton clearBtn = new JButton("Clear All");
        clearBtn.addActionListener(e -> {
            p.clearAll();
            shapeGroup.clearSelection();
        });

        Dimension btnSize = new Dimension(100, 35);
// For edit buttons
        eraserBtn.setMaximumSize(btnSize);
        undoBtn.setMaximumSize(btnSize);
        clearBtn.setMaximumSize(btnSize);

        eraserBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        undoBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        clearBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        editGroup.add(eraserBtn);
        editGroup.add(Box.createVerticalStrut(5));
        editGroup.add(undoBtn);
        editGroup.add(Box.createVerticalStrut(5));
        editGroup.add(clearBtn);

//Check Box
        JPanel CheckBox = new JPanel(new GridLayout(2, 1, 0, 0));
        CheckBox.setBorder(BorderFactory.createTitledBorder("CheckBox"));
        JCheckBox dotted = new JCheckBox("Dotted");
        dotted.addActionListener(e -> p.setDotted(dotted.isSelected()));

        JCheckBox filled = new JCheckBox("Filled");
        filled.addActionListener(e -> p.setFilled(filled.isSelected()));

        CheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        CheckBox.setMaximumSize(groupShape.getPreferredSize());

        CheckBox.add(dotted);
        CheckBox.add(filled);
        add(Box.createVerticalStrut(40));
        add(groupColor);
        add(Box.createVerticalStrut(20));
        add(groupShape);
        add(Box.createVerticalStrut(20));
        add(editGroup);
        add(Box.createVerticalStrut(20));
        add(CheckBox);
    }
}