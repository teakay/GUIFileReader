package com.wirecard.filestructure.gui.view;

import com.wirecard.filestructure.gui.controller.DefaultController;

import javax.swing.*;
import java.beans.PropertyChangeEvent;

public abstract class AbstractViewPanel extends JPanel {

    private static final long serialVersionUID = -7421931849472129096L;

    /**
     * Called by the controller when it needs to pass along a property change
     * from a model.
     *
     * @param evt The property change event from the model
     */

    public abstract void modelPropertyChange(PropertyChangeEvent evt);


}
