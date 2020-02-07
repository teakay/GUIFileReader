package com.wirecard.filestructure.gui.controller;

import java.awt.*;

public class DefaultController extends AbstractController {

    public static final String ELEMENT_TEXT_PROPERTY = "Text";
    public static final String ELEMENT_FONT_PROPERTY = "Font";

    public void changeElementText(String newText){
        setModelProperty(ELEMENT_TEXT_PROPERTY, newText);
    }

    public void changeElementFont(Font newFont){
        setModelProperty(ELEMENT_FONT_PROPERTY, newFont);
    }
}
