package function;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.border.*;
import java.text.*;

public class Fonction {

/**
 *
 * @author safidy ETU1802
 */

    //methode mamerina Jpanel ray 
    public JPanel form(String mes){
        JPanel panne = new JPanel();
        panne.setLayout(new BoxLayout(panne, BoxLayout.Y_AXIS));
        
        JLabel mesput = new JLabel(mes);
        mesput.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mesput.setBackground(Color.ORANGE);
        mesput.setOpaque(true);
        mesput.setBorder(new EmptyBorder(15, 15, 15, 50));
        
        panne.add(mesput);
        
        Calendar daty = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        
        JLabel heure = new JLabel();
        heure.setText(df.format(daty.getTime()));
        
        panne.add(heure);
        
        return panne;
    }
}