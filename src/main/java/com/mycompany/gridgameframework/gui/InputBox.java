/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gridgameframework.gui;

import com.mycompany.gridgameframework.Game;
import com.mycompany.gridgameframework.GameController;
import com.mycompany.gridgameframework.UserInteractionObserver;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.text.AttributeSet;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Pete
 */
public class InputBox {

    private static Font DEFAULT_FONT = new Font("Courier", Font.BOLD, 15);
    protected JTextField inputField;
    protected final int limit;

    public InputBox(UserInteractionObserver obs, final int limit, final int x, final int y, String initialContent) {
        this.limit = limit;
        this.inputField = new JTextField();
        setActionListener(obs, x, y);
        setInputFieldProperties();
        inputField.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a)
                    throws BadLocationException {
                if (getLength() + str.length() <= limit) {
                    super.insertString(offs, str, a);
                }
            }
        });
        inputField.setText(initialContent);
    }

    private void setActionListener(final UserInteractionObserver obs, final int x, final int y) {
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!obs.onUserInput(x, y, e.getActionCommand())) {
                    setInputFieldAppearanceOnInvalidInput();
                }else{
                    setInputFieldAppearanceOnValidInput();
                }
            }
        });
    }
    
    protected void setInputFieldAppearanceOnInvalidInput(){
        inputField.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
    }
    
    protected void setInputFieldAppearanceOnValidInput(){ };

    protected void setInputFieldProperties() {
        inputField.setVisible(true);
        inputField.setPreferredSize(new Dimension(40, 40));
        inputField.setHorizontalAlignment(JTextField.CENTER);
        inputField.setFont(DEFAULT_FONT);
    }

    public JTextField getInputField() {
        return inputField;
    }
}
