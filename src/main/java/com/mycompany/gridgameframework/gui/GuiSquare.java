/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gridgameframework.gui;

import com.mycompany.gridgameframework.BoardComponent;
import com.mycompany.gridgameframework.Square;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Pete
 */
public class GuiSquare<E> extends Square<E> {

    private JPanel panel;
    private JLabel label;

    public GuiSquare(BoardComponent component) throws IOException {
        super(component.getX(), component.getY());
        this.panel = new JPanel();

        setLabel(component);

        //  BufferedImage img = ImageIO.read(new File(imagePath));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private void setLabel(BoardComponent component) {
        if (component.getHint() == null) {
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
