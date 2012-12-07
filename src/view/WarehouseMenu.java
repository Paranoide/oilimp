package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import model.OilImp;
import model.Equipment;
import java.util.List;
import static util.OilImpDesignFactory.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Map;

/**
 *
 * @author Paranoide
 */
public class WarehouseMenu extends OilImpMenu
{
    private OilImp game;
    
    public WarehouseMenu(OilImp game, String startOilField)
    {
        this.game = game;
        this.currOilField = startOilField; 
        
        Map<String, List<Equipment>> eqs = game.getWarehouses();
        
//        this.setLayout(new GridLayout(eqs.size(), 1));
        this.setLayout(new BorderLayout(5, 5));
        
//        JPanel scrollPanel = new JPanel(new GridLayout(0, 1));
        JPanel scrollPanel = new JPanel();
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));
        JScrollPane jsp = new JScrollPane(scrollPanel);
        this.add(jsp, BorderLayout.CENTER);
        
        Dimension d = this.getPreferredSize();
        d = new Dimension(d.width, d.height + 100);
        this.setPreferredSize(d);
        
        String[] fieldNames = this.game.getOilFieldNames();
        for (int t = 0; t < fieldNames.length; t++)
        {
            String f = fieldNames[t];
            if (eqs.containsKey(f))
            {
                EQBox eqb = new EQBox(f, eqs.get(f));
                scrollPanel.add(eqb);
            }
        }
        
    }
    
    

    @Override
    public void reset()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void reset(String oilField)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void defaultAction()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    private class EQBox extends JPanel
    {
        public EQBox(String oilFieldName, List<Equipment> eq)
        {
            this(oilFieldName, eq.toArray(new Equipment[eq.size()]));
        }
        
        public EQBox(String oilFieldName, Equipment[] eq)
        {
            int eqCount = eq.length;
            
            this.setLayout(new GridLayout(eqCount, 4));
            
            for (int t = 0; t < eqCount; t++)
            {
                JLabel nameLabel   = new JLabel(eq[t].getEQName().getName());
                nameLabel.setFont(LABEL_FONT);
                
                JLabel statusLabel = new JLabel(eq[t].getStatus() + "");
                statusLabel.setFont(LABEL_FONT);
                
                JLabel priceLabel  = new JLabel(eq[t].getPrice() + "");
                priceLabel.setFont(LABEL_FONT);
                
                JButton repairButton = new JButton("Repair");
                repairButton.setFont(BUTTON_FONT);
                
                this.add(nameLabel);
                this.add(statusLabel);
                this.add(priceLabel);
                this.add(repairButton);
                
            }
            
            this.setBorder(createBorder(oilFieldName, BORDER_SMALL_FONT, 1));
        }
    }
    
    public static void main(String[] args)
    {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new GridLayout(1, 1));
        
        OilImp game = new OilImp();
        game.changeOilField("Tsrif");
        
        WarehouseMenu wm = new WarehouseMenu(game, "Tsrif");
        
        f.add(wm);
        f.setVisible(true);
    }
}
