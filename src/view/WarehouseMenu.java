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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author Paranoide
 */
public class WarehouseMenu extends OilImpMenu implements ActionListener
{
    private OilImp game;
    private OilImpGUI parent;
    
    private JPanel warehousePanel;
    private JPanel headerPanel;
    private JPanel scrollPanel;
    
    private JPanel buttonPanel;
    private JButton getInfoButton;
    
    
    
    
    public WarehouseMenu(OilImp game, String startOilField, OilImpGUI parent)
    {
        this.game = game;
        this.currOilField = startOilField;
        this.parent = parent;
        
        this.setLayout(new BorderLayout(5, 5));
        
        
        // ---------------------------------------------------------------------
        this.warehousePanel = new JPanel(new BorderLayout());
        
        String[] headers = {"EQ", "Status", "Preis", "Aktionen"};
        this.headerPanel = new JPanel(new GridLayout(1, headers.length + 1));
        this.headerPanel.setBorder(createBorder("", HEADLINE_FONT, 1));
        JLabel[] headerLabels = new JLabel[headers.length];
        for (int t = 0; t < headers.length; t++)
        {
            headerLabels[t] = new JLabel(headers[t]);
            headerLabels[t].setFont(HEADLINE_FONT);
            this.headerPanel.add(headerLabels[t]);
        }
        this.headerPanel.add(new JPanel());
        
        this.scrollPanel = new ScrollablePanel();
        this.scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));
        // ---------------------------------------------------------------------
        
        
        
        // ---------------------------------------------------------------------
        this.buttonPanel = new JPanel(new GridLayout(1, 1));
        
        this.getInfoButton = new JButton("Get warehouses"); 
        this.getInfoButton.addActionListener(this);
        this.buttonPanel.add(this.getInfoButton);
        
        // ---------------------------------------------------------------------
        
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
                // Aus Model requesten
                Map<String, List<Equipment>> eqs = game.getWarehouses();
                
//                // TESTDATEN -------
//                Map<String, List<Equipment>> eqs = new HashMap<>();
//                
//                // Erstes
//                String name = "Tsrif";
//                LinkedList<Equipment> eqList = new LinkedList<>();
//                eqList.add(new Equipment(Equipment.EQName.TANK_A, 99.3, 12345));
//                eqList.add(new Equipment(Equipment.EQName.TANK_B, 39.3, 44444));
//                eqList.add(new Equipment(Equipment.EQName.TANK_C, 50.5, 23423));
//                eqs.put(name, eqList);
//                
//                // Zweites
//                name = "Dnoces";
//                eqList = new LinkedList<>();
//                eqList.add(new Equipment(Equipment.EQName.BOHRTURM_A, 99, 50000));
//                eqList.add(new Equipment(Equipment.EQName.BOHRTURM_B, 20, 60000));
//                eqList.add(new Equipment(Equipment.EQName.BOHRTURM_C, 100, 70000));
//                eqList.add(new Equipment(Equipment.EQName.PIPELINE_B, 60.5, 111111));
//                eqs.put(name, eqList);
//                System.out.println(eqs);
                
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
                warehousePanel.setLayout(new BorderLayout());
                warehousePanel.add(headerPanel, BorderLayout.NORTH);
                
                scrollPanel = new ScrollablePanel();
                BoxLayout boxLayout = new BoxLayout(scrollPanel, BoxLayout.Y_AXIS);
                scrollPanel.setLayout(boxLayout);
                
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
                JScrollPane jsp = new JScrollPane(scrollPanel,
                                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                jsp.setViewportView(scrollPanel);
                warehousePanel.add(jsp, BorderLayout.CENTER);
                
                add(warehousePanel, BorderLayout.CENTER);
                
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
                warehousePanel.removeAll();
                warehousePanel.invalidate();
                warehousePanel.updateUI();
                removeAll();
                invalidate();
                updateUI();
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
                add(buttonPanel, BorderLayout.CENTER);
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
            
            this.setLayout(new GridLayout(eqCount, 5));
            
            for (int t = 0; t < eqCount; t++)
            {
                JLabel nameLabel   = new JLabel(eq[t].getEQName().getName());
                nameLabel.setFont(LABEL_FONT);
                this.add(nameLabel);
                
                JLabel statusLabel = new JLabel(eq[t].getStatus() + "");
                statusLabel.setFont(LABEL_FONT);
                this.add(statusLabel);
                
                JLabel priceLabel  = new JLabel(eq[t].getPrice() + "");
                priceLabel.setFont(LABEL_FONT);
                this.add(priceLabel);
                
                JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
                JButton sellButton = new JButton("Verkaufen");
                sellButton.setFont(BUTTON_FONT);
                this.add(sellButton);
                
                boolean yep = new java.util.Random().nextBoolean();
                yep = true;
                if (eq[t].getStatus() < 100 || yep)
                {
                    JButton repairButton = new JButton("Reparieren");
                    repairButton.setFont(BUTTON_FONT);
                    this.add(repairButton);
                }
                else
                {
                    this.add(new JPanel());
                }
//                this.add(buttonPanel);
                
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
