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
public class WarehouseMenu extends OilImpMenu implements ActionListener
{
    private OilImp game;
    private OilImpGUI parent;
    
    private JPanel mainPanel;
    private JPanel headerPanel;
    private JPanel scrollPanel;
    private JButton getInfoButton;
    
    public WarehouseMenu(OilImp game, String startOilField, OilImpGUI parent)
    {
        this.game = game;
        this.currOilField = startOilField;
        this.parent = parent;
        
        this.setLayout(new BorderLayout(5, 5));
        
        this.mainPanel = new JPanel(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);
        
        String[] headers = {"EQ", "Status", "Preis", "Reparieren"};
        this.headerPanel = new JPanel(new GridLayout(1, headers.length));
        JLabel[] headerLabels = new JLabel[headers.length];
        for (int t = 0; t < headers.length; t++)
        {
            headerLabels[t] = new JLabel(headers[t]);
            headerLabels[t].setFont(HEADLINE_FONT);
            this.headerPanel.add(headerLabels[t]);
        }
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        this.scrollPanel = new JPanel();
        this.scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));
        
        this.getInfoButton = new JButton("Get warehouses"); 
        this.getInfoButton.addActionListener(this);
        
        this.reset();
    }
    
    public void getWarehouseInformation()
    {
        this.clearPanel();
        
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                Map<String, List<Equipment>> eqs = game.getWarehouses();

                JScrollPane jsp = new JScrollPane(scrollPanel);
                mainPanel.add(jsp, BorderLayout.CENTER);

                createEQBoxes(eqs);
            }
        }).start();
    }
    
    private void createEQBoxes(final Map<String, List<Equipment>> eqs)
    {
        Runnable r = new Runnable()
        {
            @Override
            public void run()
            {
                
                mainPanel.add(headerPanel, BorderLayout.NORTH);
                mainPanel.setLayout(new BorderLayout());
                
                String[] fieldNames = game.getOilFieldNames();
                
                for (int t = 0; t < fieldNames.length; t++)
                {
                    String f = fieldNames[t];
                    if (eqs.containsKey(f))
                    {
                        EQBox eqb = new EQBox(f, eqs.get(f));
                        scrollPanel.add(eqb);
                    }
                }
                mainPanel.add(scrollPanel, BorderLayout.CENTER);
                
                invalidate();
                updateUI();
                
                if (parent != null)
                {
                    parent.applyColors();
                }
            }
        };
        EventQueue.invokeLater(r);
    }
    
    private void clearPanel()
    {
        Runnable r = new Runnable()
        {
            @Override
            public void run()
            {
                scrollPanel.removeAll();
                scrollPanel.invalidate();
                scrollPanel.updateUI();
                mainPanel.removeAll();
                mainPanel.invalidate();
                mainPanel.updateUI();
            }
        };
        EventQueue.invokeLater(r);
    }

    @Override
    public void reset()
    {
        this.clearPanel();
        Runnable r = new Runnable()
        {
            @Override
            public void run()
            {
                mainPanel.add(getInfoButton, BorderLayout.CENTER);
            }
        };
        EventQueue.invokeLater(r);
    }

    @Override
    public void reset(String oilField)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void defaultAction()
    {
        this.reset();
    }
    
    // LISTENER
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getSource() == this.getInfoButton)
        {
            this.getWarehouseInformation();
        }
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
        
        WarehouseMenu wm = new WarehouseMenu(game, "Tsrif", null);
        
        f.add(wm);
        f.setSize(500, 400);
        f.setVisible(true);
    }
}
