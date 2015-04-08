/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gridgameframework.gui;

import com.mycompany.gridgameframework.BoardComponent;
import com.mycompany.gridgameframework.Square;
import com.mycompany.gridgameframework.UserInteractionObserver;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Pete
 */
public class GuiSquare<E> {

    protected JPanel contentPanel;
    protected JLabel hintLabel;
    protected InputBox contentText;

    public GuiSquare(UserInteractionObserver obs, BoardComponent component) throws IOException {
        this.contentPanel = new JPanel(new BorderLayout());
        createLabelOrInputBox(obs, component);
        contentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    
    protected void setContentPanelProperties(){
        this.contentPanel = new JPanel(new BorderLayout());
    }

    private void createLabelOrInputBox(UserInteractionObserver obs, BoardComponent component) {
        if (component.getHint() == null) {
            contentPanel.add(new InputBox(obs, 1 , component.getX(), component.getY(), component.getContent()).getInputField());
            return;
        }
        if (component.getHint() instanceof BufferedImage) {
            this.hintLabel = new JLabel(new ImageIcon((BufferedImage) component.getHint()));
        } else if (component.getHint() instanceof Integer || component.getHint() instanceof String) {
            this.hintLabel = new JLabel("Hint: " + component.getHint());
        } else if (component.getHint() != null) {
            throw new IllegalStateException("Board component's hint must be instance of "
                    + "Integer, String or BufferedImage");
        }
        if (hintLabel != null) {
            contentPanel.add(hintLabel);
        }
    }
    
    protected void addComponents(){
        addComponent(hintLabel);
        addComponent(contentText.getInputField());
    }
    
    private void addComponent(JComponent component){
        if(component != null){
            contentPanel.add(hintLabel);
        }
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }


}
