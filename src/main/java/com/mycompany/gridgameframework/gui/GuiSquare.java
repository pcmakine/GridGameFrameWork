/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gridgameframework.gui;

import com.mycompany.gridgameframework.BoardComponent;
import com.mycompany.gridgameframework.Square;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Pete
 */
public class GuiSquare<E> extends Square<E> {

    private JPanel panel;
    private JLabel label;
    private InputBox txt;

    public GuiSquare(BoardComponent component) throws IOException {
        super(component.getX(), component.getY());
        this.panel = new JPanel(new BorderLayout());
       // this.panel.setFocusable(true);

        setLabelOrTextField(component);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private void setLabelOrTextField(BoardComponent component) {
        if (component.getHint() == null) {
            panel.add(new InputBox(1 , component.getX(), component.getY()).getTextField());
            return;
        }
        if (component.getHint() instanceof BufferedImage) {
            this.label = new JLabel(new ImageIcon((BufferedImage) component.getHint()));
        } else if (component.getHint() instanceof Integer || component.getHint() instanceof String) {
            this.label = new JLabel("Hint: " + component.getHint());
        } else if (component.getHint() != null) {
            throw new IllegalStateException("Board component's hint must be instance of "
                    + "Integer, String or BufferedImage");
        }
        if (label != null) {
            panel.add(label);
        }
    }

    public void setPanel(JPanel content) {
        this.panel = content;
    }

    public JPanel getPanel() {
        return panel;
    }
}
