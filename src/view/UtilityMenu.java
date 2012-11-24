package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import model.OilImp;
import static util.OilImpDesignFactory.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * @author Paranoide
 */
public class UtilityMenu extends OilImpMenu implements ActionListener
{
    
    private OilImp game;
    private String currentOilField;
    
    private JPanel utilityPanel;
    private TitledBorder utilityPanelBorder;
    private AlarmPanel alarmPanel;
    private RepairPanel repairPanel;
    
    private JPanel ressPricePanel;
    private TitledBorder ressPricePanelBorder;
    private JPanel[] ressPricePanels;
    private JLabel[] ressNameLabels;
    private JLabel[] ressPriceLabels;
    
    private Thread alarmThread;
    
    public UtilityMenu(OilImp game, String currOilField)
    {
        this.game = game;
        this.currentOilField = currOilField;
        
        this.setLayout(new BorderLayout());
        this.setBorder(createBorder("Verschiedenes", BORDER_FONT, 0));
        
        this.utilityPanel = new JPanel(new GridLayout(5, 1));
        this.utilityPanelBorder = createBorder("Aktionen", BORDER_SMALL_FONT, 0);
        this.utilityPanel.setBorder(this.utilityPanelBorder);
        
        this.alarmPanel = new AlarmPanel();
        this.utilityPanel.add(this.alarmPanel);
        
        this.repairPanel = new RepairPanel();
        this.utilityPanel.add(this.repairPanel);
        
        
        this.ressPricePanel = new JPanel(new GridLayout(1, 4));
        this.ressPricePanelBorder = createBorder("Preise", BORDER_SMALL_FONT, 0);
        this.ressPricePanel.setBorder(this.ressPricePanelBorder);
        
        this.ressPricePanels = new JPanel[4];
        this.ressNameLabels = new JLabel[4];
        this.ressPriceLabels = new JLabel[4];
        
        String[] ressNames = {"Rohoel:", "Kerosin:", "Diesel:", "Benzin:"};
        
        for (int t = 0; t < 4; t++)
        {
            this.ressPricePanels[t] = new JPanel(new GridLayout(1, 2));
//            this.ressPricePanels[t].setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
            this.ressPricePanels[t].setBorder(createBorder("", BORDER_FONT, 0));
            
            this.ressNameLabels[t] = new JLabel(ressNames[t]);
            this.ressNameLabels[t].setFont(LABEL_FONT);
            
            this.ressPriceLabels[t] = new JLabel("---");
            this.ressPriceLabels[t].setFont(NUMBER_FONT);
            this.ressPriceLabels[t].setHorizontalAlignment(JLabel.RIGHT);
            
            this.ressPricePanels[t].add(this.ressNameLabels[t]);
            this.ressPricePanels[t].add(this.ressPriceLabels[t]);
            
            this.ressPricePanel.add(this.ressPricePanels[t]);
        }
        
        
        
        
        this.add(this.utilityPanel, BorderLayout.CENTER);
        this.add(this.ressPricePanel, BorderLayout.SOUTH);
        
        this.addListeners();
    }

    public void setPrices()
    {
        int[] prices = this.game.checkPrices();
        for (int t = 0; t < 4; t++)
        {
            if (prices != null)
            {
                this.ressPriceLabels[t].setText(prices[t] + " ");
            }
            else
            {
                this.ressPriceLabels[t].setText("N/A");
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
        this.setPrices();
    }

    @Override
    public void setCurrentOilField(String oilField)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    public void toggleAlarmThread()
    {
        if (this.alarmThread != null && this.alarmThread.isAlive())
        {
            System.out.println("Stopping alarm thread...");
            this.alarmPanel.threadButton.setText("Start thread");
            this.alarmPanel.threadButton.setBackground(Color.GREEN);
            this.alarmPanel.threadButton.setForeground(Color.BLACK);
            this.alarmThread.interrupt();
            this.alarmThread = null;
        }
        else
        {
            System.out.println("Starting alarm thread...");
            this.alarmPanel.threadButton.setText("Stop thread");
            this.alarmPanel.threadButton.setBackground(Color.RED);
            this.alarmPanel.threadButton.setForeground(Color.WHITE);
            this.alarmThread = new Thread(this.alarmPanel);
            this.alarmThread.start();
        }
    }
    
    
    // Listener
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getSource() == this.alarmPanel.threadButton)
        {
            this.toggleAlarmThread();
        }
    }
    
    // Private
    private void addListeners()
    {
        this.alarmPanel.threadButton.addActionListener(this);
    }
    
    
    private class AlarmPanel extends JPanel implements Runnable
    {
        
        private JLabel alarmPanelLabel;
        
        private JPanel actionPanel;
        private JTextField[] priceFields;
        private JButton threadButton;
        
        public AlarmPanel()
        {
            this.setLayout(new BorderLayout());
            this.setBorder(createBorder("Preis-Alarm", BORDER_SMALL_FONT, 1));
            
            this.alarmPanelLabel = new JLabel("Preis-Alarm");
            this.alarmPanelLabel.setFont(LABEL_FONT);
            
            this.actionPanel = new JPanel(new GridLayout(1, 5));
            
            this.priceFields = new JTextField[4];
            for (int t = 0; t < 4; t++)
            {
                this.priceFields[t] = new JTextField((190 + t * 150) + "");
                this.priceFields[t].setHorizontalAlignment(JTextField.RIGHT);
                JPanel tmpPriceFieldPanel = new JPanel(new GridLayout(1, 1));
                tmpPriceFieldPanel.add(this.priceFields[t]);
                tmpPriceFieldPanel.setBorder(BorderFactory.createEmptyBorder(8, 5, 8, 5));
                this.priceFields[t].setFont(NUMBER_FONT);
                
                this.actionPanel.add(tmpPriceFieldPanel);
            }
            this.threadButton = new JButton("Start thread");
            this.threadButton.setContentAreaFilled(false);
            this.threadButton.setOpaque(true);
            JPanel tmpButtonPanel = new JPanel(new GridLayout(1, 1));
            tmpButtonPanel.add(this.threadButton);
            tmpButtonPanel.setBorder(BorderFactory.createEmptyBorder(8, 5, 8, 5));
            this.actionPanel.add(tmpButtonPanel);
            
            this.add(this.alarmPanelLabel, BorderLayout.WEST);
            this.add(this.actionPanel,     BorderLayout.CENTER);
        }
        
        public int[] getPriceBounds()
        {
            int[] bounds = new int[4];
            for (int t = 0; t < this.priceFields.length; t++)
            {
                try
                {
                    bounds[t] = new Integer(this.priceFields[t].getText());
                }
                catch (NumberFormatException nfe)
                {
                    bounds[t] = 0;
                }
            }
            return bounds;
        }
        
        @Override
        public void run()
        {
            boolean interrupted = false;
            
            while (!interrupted && !Thread.interrupted())
            {
                try
                {
                    Thread.sleep(60*1000);
                    checkPrices();
                }
                catch (InterruptedException ie)
                {
                    interrupted = true;
                }
                
                
            }
        }
        
        private void checkPrices()
        {
            int[] prices = game.checkPrices();
            int[] bounds = getPriceBounds();
            String[] names = {"Rohoel", "Kerosin", "Diesel", "Benzin"};
            
            for (int t = 0; t < prices.length; t++)
            {
                if (bounds[t] != 0)
                {
                    if (prices[t] > bounds[t])
                    {
                        String msg = names[t].toUpperCase() + " ist verkaufsbereit!\n" + 
                                     prices[t] + " > " + bounds[t];
                        String title = names[t] + "!";
                        int type = JOptionPane.INFORMATION_MESSAGE;
                        JOptionPane.showMessageDialog(null, msg, title, type);
                    }
                }
            }
        }
    }
    
    private class RepairPanel extends JPanel implements ActionListener
    {
        private JLabel repairPanelLabel;
        private JButton repairAllButton;
        
        public RepairPanel()
        {
            this.setLayout(new BorderLayout());
            this.setBorder(createBorder("Repair equiment", BORDER_SMALL_FONT, 1));
            
            this.repairPanelLabel = new JLabel("Repair equipment");
            this.repairPanelLabel.setFont(LABEL_FONT);
            JPanel tmpPanel = new JPanel(new GridLayout(1, 1));
            tmpPanel.setBorder(BorderFactory.createEmptyBorder(8, 5, 8, 5));
            tmpPanel.add(this.repairPanelLabel);
                        
            this.add(tmpPanel, BorderLayout.WEST);
            
            
            this.repairAllButton = new JButton("Repair all");
            this.repairAllButton.setFont(BUTTON_FONT);
            this.repairAllButton.addActionListener(this);
            tmpPanel = new JPanel(new GridLayout(1, 1));
            tmpPanel.setBorder(BorderFactory.createEmptyBorder(8, 5, 8, 5));
            tmpPanel.add(this.repairAllButton);
            
            this.add(tmpPanel, BorderLayout.EAST);
        }
        
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            if (ae.getSource() == this.repairAllButton)
            {
                new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        repairAllButton.setEnabled(false);
                        game.repairAllEQ();
                        repairAllButton.setEnabled(true);
                    }
                }).start();
            }
        }
    }
    
}
