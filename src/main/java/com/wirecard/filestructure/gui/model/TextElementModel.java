package com.wirecard.filestructure.gui.model;

import com.wirecard.filestructure.gui.controller.DefaultController;

import java.awt.*;

public class TextElementModel extends AbstractModel {
    private  String text;
    private Font font;
    private Integer x;
    private Integer y;

    public void initDefault(){
        setText("Sample Text");
        setFont(new Font("Arial",Font.BOLD,24));
    }
    public Font getFont(){
        return font;
    }
    public void setFont(Font font){
        Font oldFont = this.font;
        this.font = font;

        firePropertyChange(DefaultController.ELEMENT_FONT_PROPERTY, oldFont, font);
    }

    public void setText(String text){
        String oldText = this.text;
        this.text = text;

        firePropertyChange(DefaultController.ELEMENT_TEXT_PROPERTY, oldText, text);
    }
}
