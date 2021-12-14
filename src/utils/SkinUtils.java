package utils;

import mp3gui.mp3;
import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class SkinUtils { //смена скина программы
    
        public static void changeSkin(Component comp, LookAndFeel laf) { //установка скина LookAndFeel
        try {
            UIManager.setLookAndFeel(laf);
        } catch (UnsupportedLookAndFeelException ex){
            Logger.getLogger(mp3.class.getName()).log(Level.SEVERE, null, ex);
        }
            SwingUtilities.updateComponentTreeUI(comp);
            
        }
        
        public static void changeSkin(Component comp, String laf) { //установка скинов из библиотек
            try{
                try{
                UIManager.setLookAndFeel(laf);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SkinUtils.class.getName()).log(Level.SEVERE,null,ex);
            } catch (InstantiationException ex){
                Logger.getLogger(SkinUtils.class.getName()).log(Level.SEVERE,null,ex);
            } catch (IllegalAccessException ex){
                Logger.getLogger(SkinUtils.class.getName()).log(Level.SEVERE,null,ex);
            } 
           } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(SkinUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            SwingUtilities.updateComponentTreeUI(comp);
            
        }
}

