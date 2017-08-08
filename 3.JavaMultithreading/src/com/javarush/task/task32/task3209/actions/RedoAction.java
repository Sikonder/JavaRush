package com.javarush.task.task32.task3209.actions;

import com.javarush.task.task32.task3209.View;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Sikonder on 18.07.2017.
 */
public class RedoAction extends AbstractAction {
    private View view;
    @Override
    public void actionPerformed(ActionEvent e) {
        view.redo();
    }

    public RedoAction(View view){
        this.view = view;
    }
}
