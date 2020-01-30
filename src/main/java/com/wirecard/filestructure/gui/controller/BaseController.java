package com.wirecard.filestructure.gui.controller;

import com.wirecard.filestructure.gui.utils.Constants;
import com.wirecard.filestructure.gui.view.BaseView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class BaseController implements ActionListener, ListSelectionListener, PropertyChangeListener {
    Object baseModel;
    BaseView activeView;
    public BaseController(){
    }

//    public BaseController(Object model){
//        this.baseModel = model;
//    }

    public void setActiveView(BaseView activeView){
        this.activeView = activeView;
    }

    public BaseView getActiveView(){
        return this.activeView;
    }

    public void actionPerformed(ActionEvent e) {
        String methodAction = e.getActionCommand();
        
        try {
            Class controller = this.getClass();
            Method method = controller.getDeclaredMethod("do"+methodAction);
            method.invoke(controller.newInstance());
        } catch (NoSuchMethodException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InvocationTargetException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        }
        //go to model and update property
    }

    public void valueChanged(ListSelectionEvent e) {
        JList list = (JList) e.getSource();
        List<Component> component = this.activeView.getAllComponents();
        String selectedMenu = Constants.MENU[list.getSelectedIndex()];

        for(int i = 0 ; i < component.size(); i++){
            String propertyName = component.get(i).getName();
            if("ContentLabel".equals(propertyName)) {
                try {
                    Method method = this.activeView.getClass()
                            .getDeclaredMethod("set"+propertyName,String.class);
                    method.invoke(this.activeView,selectedMenu);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }

    public void propertyChange(PropertyChangeEvent evt) {
        //go to model and update property
        System.out.println(evt.getPropertyName());
    }
}
