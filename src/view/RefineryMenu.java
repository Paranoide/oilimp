package view;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import javax.swing.border.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import static util.OilImpDesignFactory.*;

import model.OilImp;
import model.FactorySettings;
import model.RefineryInformation;

import static model.RefineryInformation.*;

/**
 *
 * @author Paranoide
 */
public class RefineryMenu extends OilImpMenu implements ActionListener,
                                                        MouseListener,
                                                        DocumentListener
{
    private static final String SHOW_AMOUNT = "showamount";
    private static final String SHOW_TIMELEFT = "showtimeleft";
    
    
    private OilImp game;
    
    private JPanel infoPanel;
    private TitledBorder infoPanelBorder;
    private JPanel ressNamePanel;
    private JLabel[] ressNameLabels;
    private TitledBorder ressNamePanelBorder;
    private JPanel currentRessPanel;
    private JLabel[] currentRessLabels;
    private TitledBorder currentRessPanelBorder;
    private JPanel afterwardsRessPanel;
    private JLabel[] afterwardsRessLabels;
    private TitledBorder afterwardsRessPanelBorder;
    private JPanel capacityPanel;
    private JLabel[] capacityLabels;
    private TitledBorder capacityPanelBorder;
    
    
    
    private JPanel actionPanel;
    private TitledBorder actionPanelBorder;
    
    private JPanel consumePanel;
    private TitledBorder consumePanelBorder;
    private JLabel[] consumeNameLabels;
    private JTextField[] consumeValueFields;
    
    
    
    private JPanel producePanel;
    private TitledBorder producePanelBorder;
    
    private JPanel choosePanel;
    private JRadioButton[] chooseRBs;
    private ButtonGroup rbGroup;
    private JLabel[] ressLabels;
    
    private JPanel[] ressAmountPanels;
    private JTextField[] ressAmount;
    private JLabel[] ressTimeLeftLabels;
    private CardLayout[] ressAmountTimeLeftLayouts;
    
    private JButton[] maxButtons;
    
    
    private JPanel produceButtonPanel;
    private JButton produceButton;
    private JButton produceAllMaxButton;
    
    
    private RefineryInformation ri = null;
    
    
    public RefineryMenu(OilImp game, String startOilField)
    {
        this.game = game;
        this.currOilField = startOilField;
        
        this.setLayout(new GridLayout(2, 1));
        
        this.infoPanel           = new JPanel(new GridLayout(1, 4));
        this.infoPanelBorder = createBorder("Information", BORDER_FONT, 0);
        this.infoPanel.setBorder(this.infoPanelBorder);
        
        this.ressNamePanel      = new JPanel(new GridLayout(5, 1));
        this.ressNamePanelBorder = createBorder("Ressource", BORDER_SMALL_FONT, 0);
        this.ressNamePanel.setBorder(this.ressNamePanelBorder);
        
        this.currentRessPanel    = new JPanel(new GridLayout(5, 1));
        this.currentRessPanelBorder = createBorder("Jetzt", BORDER_SMALL_FONT, 0);
        this.currentRessPanel.setBorder(this.currentRessPanelBorder);
        
        this.afterwardsRessPanel = new JPanel(new GridLayout(5, 1));
        this.afterwardsRessPanelBorder = createBorder("Danach", BORDER_SMALL_FONT, 0);
        this.afterwardsRessPanel.setBorder(this.afterwardsRessPanelBorder);
        
        this.capacityPanel       = new JPanel(new GridLayout(5, 1));
        this.capacityPanelBorder = createBorder("Kapazitaet", BORDER_SMALL_FONT, 0);
        this.capacityPanel.setBorder(this.capacityPanelBorder);
        
        this.ressNameLabels = new JLabel[5];
        this.ressNameLabels[0] = new JLabel("Freie MAs");
        this.ressNameLabels[1] = new JLabel("Rohoel");
        this.ressNameLabels[2] = new JLabel("Kerosin");
        this.ressNameLabels[3] = new JLabel("Diesel");
        this.ressNameLabels[4] = new JLabel("Benzin");
        for (int t = 0; t < this.ressNameLabels.length; t++)
        {
            this.ressNameLabels[t].setFont(HEADLINE_FONT);
            this.ressNamePanel.add(this.ressNameLabels[t]);
        }
        
        this.currentRessLabels = new JLabel[5];
        this.currentRessLabels[0] = new JLabel(formatBigNumber(100));
        this.currentRessLabels[1] = new JLabel(formatBigNumber(53343));
        this.currentRessLabels[2] = new JLabel(formatBigNumber(20000));
        this.currentRessLabels[3] = new JLabel(formatBigNumber(15000));
        this.currentRessLabels[4] = new JLabel(formatBigNumber(50232));
        for (int t = 0; t < this.currentRessLabels.length; t++)
        {
            this.currentRessLabels[t].setFont(NUMBER_FONT);
            this.currentRessLabels[t].setHorizontalAlignment(JLabel.RIGHT);
            this.currentRessPanel.add(this.currentRessLabels[t]);
        }
        
        this.afterwardsRessLabels = new JLabel[5];
        this.afterwardsRessLabels[0] = new JLabel("70");
        this.afterwardsRessLabels[1] = new JLabel("33343");
        this.afterwardsRessLabels[2] = new JLabel("24000");
        this.afterwardsRessLabels[3] = new JLabel("15000");
        this.afterwardsRessLabels[4] = new JLabel("50232");
        for (int t = 0; t < this.afterwardsRessLabels.length; t++)
        {
            this.afterwardsRessLabels[t].setFont(NUMBER_FONT);
            this.afterwardsRessLabels[t].setHorizontalAlignment(JLabel.RIGHT);
            this.afterwardsRessPanel.add(this.afterwardsRessLabels[t]);
        }
        
        this.capacityLabels = new JLabel[5];
        this.capacityLabels[0] = new JLabel("70");
        this.capacityLabels[1] = new JLabel("33343");
        this.capacityLabels[2] = new JLabel("24000");
        this.capacityLabels[3] = new JLabel("15000");
        this.capacityLabels[4] = new JLabel("50232");
        for (int t = 0; t < this.capacityLabels.length; t++)
        {
            this.capacityLabels[t].setFont(NUMBER_FONT);
            this.capacityLabels[t].setHorizontalAlignment(JLabel.RIGHT);
            this.capacityPanel.add(this.capacityLabels[t]);
        }
        
        this.infoPanel.add(this.ressNamePanel);
        this.infoPanel.add(this.currentRessPanel);
        this.infoPanel.add(this.afterwardsRessPanel);
        this.infoPanel.add(this.capacityPanel);
        
        
        
        this.actionPanel = new JPanel(new GridLayout(1, 2));
        this.actionPanelBorder = createBorder("Aktion", BORDER_FONT, 0);
        this.actionPanel.setBorder(this.actionPanelBorder);
        
        this.consumePanel = new JPanel(new GridLayout(3, 2));
        this.consumePanelBorder = createBorder("Verbrauch", BORDER_SMALL_FONT, 0);
        this.consumePanel.setBorder(this.consumePanelBorder);
        this.actionPanel.add(this.consumePanel);
        
        this.producePanel = new JPanel(new BorderLayout());
        this.producePanelBorder = createBorder("Herstellen", BORDER_SMALL_FONT, 0);
        this.producePanel.setBorder(this.producePanelBorder);
        this.actionPanel.add(this.producePanel);
        
        this.consumeNameLabels  = new JLabel[3];
        this.consumeValueFields = new JTextField[3];
        
        this.consumeNameLabels[0] = new JLabel("Rohoel:");
        this.consumeNameLabels[1] = new JLabel("MAs:");
        this.consumeNameLabels[2] = new JLabel("Kosten:");
        
        
        this.consumeValueFields[0] = new JTextField(formatBigNumber(1234));
        this.consumeValueFields[1] = new JTextField(formatBigNumber(10));
        this.consumeValueFields[2] = new JTextField("$" + formatBigNumber(500));
        
        for (int t = 0; t < 3; t++)
        {
            this.consumeNameLabels[t].setFont(HEADLINE_FONT);
            this.consumeNameLabels[t].setHorizontalAlignment(JLabel.LEFT);
            this.consumePanel.add(this.consumeNameLabels[t]);
            this.consumeValueFields[t].setFont(NUMBER_FONT);
            this.consumeValueFields[t].setHorizontalAlignment(JLabel.RIGHT);
            this.consumeValueFields[t].setEnabled(false);
            this.consumePanel.add(this.consumeValueFields[t]);
        }
        this.consumeValueFields[1].setEnabled(true);
        
        
        this.choosePanel = new JPanel(new BorderLayout());
        
        this.ressLabels = new JLabel[3];
        this.ressLabels[0] = new JLabel("Kerosin");
        this.ressLabels[1] = new JLabel("Diesel");
        this.ressLabels[2] = new JLabel("Benzin");
        
        JPanel rbPanel = new JPanel(new GridLayout(3, 1));
        JPanel labelAndFieldPanel = new JPanel(new GridLayout(3, 3));
        this.choosePanel.add(rbPanel, BorderLayout.WEST);
        this.choosePanel.add(labelAndFieldPanel, BorderLayout.CENTER);
        
        this.chooseRBs = new JRadioButton[3];
        
        this.rbGroup = new ButtonGroup();
        this.ressAmount = new JTextField[3];
        this.ressAmountPanels = new JPanel[3];
        this.ressTimeLeftLabels = new JLabel[3];
        this.ressAmountTimeLeftLayouts = new CardLayout[3];
        this.maxButtons = new JButton[3];
        
        for (int t = 0; t < 3; t++)
        {
            this.chooseRBs[t] = new JRadioButton();
            rbGroup.add(this.chooseRBs[t]);
            this.chooseRBs[t].setHorizontalAlignment(JRadioButton.CENTER);
            this.chooseRBs[t].addActionListener(this);
            rbPanel.add(this.chooseRBs[t]);
            
            this.ressLabels[t].setFont(LABEL_FONT);
            this.ressLabels[t].setHorizontalAlignment(JLabel.LEFT);
            this.ressLabels[t].setEnabled(false);
            this.ressLabels[t].addMouseListener(this);
            labelAndFieldPanel.add(this.ressLabels[t]);
            
            this.ressAmount[t] = new JTextField();
            this.ressAmount[t].setFont(NUMBER_FONT);
            this.ressAmount[t].setHorizontalAlignment(JTextField.RIGHT);
            this.ressAmount[t].setEnabled(false);
            this.ressAmount[t].addMouseListener(this);
            this.ressAmount[t].getDocument().addDocumentListener(this);
            this.ressAmount[t].setBorder(BorderFactory.createEtchedBorder());
            JPanel tmpRessAmoutPanel = new JPanel(new GridLayout(1, 1));
            tmpRessAmoutPanel.setBorder(BorderFactory.createEmptyBorder(8, 5, 8, 5));
            this.ressAmountTimeLeftLayouts[t] = new CardLayout();
            this.ressAmountPanels[t] = new JPanel(this.ressAmountTimeLeftLayouts[t]);
            this.ressAmountPanels[t].add(this.ressAmount[t], SHOW_AMOUNT);
            tmpRessAmoutPanel.add(this.ressAmountPanels[t]);
            labelAndFieldPanel.add(tmpRessAmoutPanel);
            
            this.ressTimeLeftLabels[t] = new JLabel("");
            this.ressAmountPanels[t].add(this.ressTimeLeftLabels[t], SHOW_TIMELEFT);
            
            this.maxButtons[t] = new JButton("Max");
            this.maxButtons[t].setFont(BUTTON_FONT);
            this.maxButtons[t].setEnabled(false);
            this.maxButtons[t].addActionListener(this);
            this.maxButtons[t].addMouseListener(this);
            JPanel tmpMaxButtonPanel = new JPanel(new GridLayout(1, 1));
            tmpMaxButtonPanel.setBorder(BorderFactory.createEmptyBorder(8, 5, 8, 5));
            tmpMaxButtonPanel.add(this.maxButtons[t]);
            labelAndFieldPanel.add(tmpMaxButtonPanel);
        }
        
        this.produceButtonPanel = new JPanel(new GridLayout(1, 2));
        this.produceButton = new JButton("Herstellen");
        this.produceButton.setFont(BUTTON_FONT);
        this.produceButton.addActionListener(this);
        this.produceButtonPanel.add(this.produceButton);
        this.produceAllMaxButton = new JButton("Alles auf max");
        this.produceAllMaxButton.setFont(BUTTON_FONT);
        this.produceAllMaxButton.addActionListener(this);
        this.produceButtonPanel.add(this.produceAllMaxButton);
        
        this.producePanel.add(this.choosePanel, BorderLayout.CENTER);
        this.producePanel.add(this.produceButtonPanel, BorderLayout.SOUTH);
        
        
        this.add(this.infoPanel);
        this.add(this.actionPanel);
        
        this.reset();
        
    }
    
    
    
    public void getRefineryInformation()
    {
        Thread t = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                switchActiveAmountField(-1);
                
                ri = game.getRefinery();
                currentRessLabels[0].setText(ri.getCurrentWorkers() + "");
                currentRessLabels[1].setText(ri.getCurrentRohoel() + "");
                currentRessLabels[2].setText(ri.getCurrentKerosin() + "");
                currentRessLabels[3].setText(ri.getCurrentDiesel() + "");
                currentRessLabels[4].setText(ri.getCurrentBenzin() + "");

                afterwardsRessLabels[0].setText(ri.getCurrentWorkers() + "");
                afterwardsRessLabels[1].setText(ri.getCurrentRohoel() + "");
                afterwardsRessLabels[2].setText(ri.getCurrentKerosin() + "");
                afterwardsRessLabels[3].setText(ri.getCurrentDiesel() + "");
                afterwardsRessLabels[4].setText(ri.getCurrentBenzin() + "");

                capacityLabels[0].setText(ri.getMaxWorkers() + "");
                capacityLabels[1].setText(ri.getMaxRohoel() + "");
                capacityLabels[2].setText(ri.getMaxKerosin() + "");
                capacityLabels[3].setText(ri.getMaxDiesel() + "");
                capacityLabels[4].setText(ri.getMaxBenzin() + "");

                int timeLeftK = ri.getTimeLeftK();
                int timeLeftD = ri.getTimeLeftD();
                int timeLeftB = ri.getTimeLeftB();

                int[] timeLefts = {timeLeftK, timeLeftD, timeLeftB};

                System.out.println(Arrays.toString(timeLefts));

                for (int t = 0; t < 3; t++)
                {
                    if (timeLefts[t] > 0)
                    {
                        String s = getTimestamp(timeLefts[t] * 1000);
                        String[] ts = s.split(" ");
                        ressTimeLeftLabels[t].setText("<html>" + ts[0] + "<br/>" + ts[1] + "</html>");
                        ressAmountTimeLeftLayouts[t].show(ressAmountPanels[t], SHOW_TIMELEFT);
                    }
                    else
                    {
                        ressAmount[t].setText("");
                        ressAmountTimeLeftLayouts[t].show(ressAmountPanels[t], SHOW_AMOUNT);
                    }
                }
            }
        });
        
        EventQueue.invokeLater(t);
        
    }
    
    public void produce()
    {
        OilImp.Ressource[] resses = {OilImp.Ressource.KEROSIN,
                                     OilImp.Ressource.DIESEL,
                                     OilImp.Ressource.BENZIN};
        OilImp.Ressource ress = null;
        int ressAmountInt = 0;
        int workers = 0;
        for (int t = 0; t < 3; t++)
        {
            if (this.ressAmount[t].isEnabled())
            {
                ress = resses[t];
                ressAmountInt = new Integer(consumeValueFields[0].getText());
                workers = new Integer(this.consumeValueFields[1].getText());
            }
        }

        this.game.produceInRefinery(ress, ressAmountInt, workers);
    }
    

    @Override
    public void reset()
    {
        for (int t = 0; t < this.currentRessLabels.length; t++)
        {
            this.currentRessLabels[t].setText("---");
        }
        
        for (int t = 0; t < this.afterwardsRessLabels.length; t++)
        {
            this.afterwardsRessLabels[t].setText("---");
        }
        
        for (int t = 0; t < this.capacityLabels.length; t++)
        {
            this.capacityLabels[t].setText("---");
        }
    }

    @Override
    public void reset(String oilField)
    {
        
    }

    @Override
    public void defaultAction()
    {
        this.getRefineryInformation();
    }
    
    // Listener methods
    
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        // Radio Buttons
        for (int t = 0; t < 3; t++)
        {
            if (ae.getSource() == this.chooseRBs[t])
            {
                this.switchActiveAmountField(t);
                break;
            }
        }
        
        // Max-Buttons
        for (int t = 0; t < 3; t++)
        {
            if (ae.getSource() == this.maxButtons[t])
            {
                this.setMaximum(t);
            }
        }
        
        // Produce button
        if (ae.getSource() == this.produceButton)
        {
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    produce();
                    getRefineryInformation();
                }
            }).start();
        }
        
        // Produce all max button
        if (ae.getSource() == this.produceAllMaxButton)
        {
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    game.fillAllRefineries();
                    getRefineryInformation();
                }
            }).start();
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e)
    {
        
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        for (int t = 0; t < 3; t++)
        {
            if (e.getSource() == this.ressLabels[t] ||
                e.getSource() == this.ressAmount[t])
            {
                this.switchActiveAmountField(t);
                break;
            }
            
            if (e.getSource() == this.maxButtons[t])
            {
                if (!((JButton)e.getSource()).isEnabled())
                {
                    this.switchActiveAmountField(t);
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        
    }

    @Override
    public void insertUpdate(DocumentEvent e)
    {
        this.changedUpdate(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e)
    {
        this.changedUpdate(e);
    }

    @Override
    public synchronized void changedUpdate(final DocumentEvent e)
    {
        Thread t = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                int nr = 0;
                for (int t = 0; t < ressAmount.length; t++)
                {
                    if (e.getDocument() == ressAmount[t].getDocument())
                    {
                        nr = t;
                        break;
                    }
                }
                
                updateValues(nr);
                
            }
        });
        
        EventQueue.invokeLater(t);
        
    }

    
    
    // Private methods
    
    private void setMaximum(int ress)
    {
                
        int currRess = new Integer(this.currentRessLabels[ress + 2].getText());
        int currRessCap = new Integer(this.capacityLabels[ress + 2].getText());
        
        int maxRessByCapacity = currRessCap - currRess;
        
        int currRohoel = new Integer(this.currentRessLabels[1].getText());
        int maxRessByRohoel = (int)(currRohoel * RESS_FACTORS[ress]);
        
        
        int maximum = Math.min(maxRessByCapacity, maxRessByRohoel);
        
        this.ressAmount[ress].setText(maximum + "");
        
//        updateValues(ress);
    }
    
    private void updateValues(int ress)
    {
        String na = ressAmount[ress].getText().trim();
        int newAmount = new Integer("0" + (na == null ? "" : na));
        
        // Verbleibende Ressourcen
        // Ress
        for (int t = 0; t < ressAmount.length; t++)
        {
            afterwardsRessLabels[t + 2].setText(currentRessLabels[t + 2].getText());
        }
        int currRess = new Integer("0" + currentRessLabels[ress + 2].getText());
        String newAmountStr = (currRess + newAmount) + "";
        afterwardsRessLabels[ress + 2].setText(newAmountStr);
        
        // MAs
        int availableMAs = new Integer(currentRessLabels[0].getText());
        consumeValueFields[1].setText(Math.min(RESS_MA[ress], availableMAs) + "");

        // Benoetigtes Rohoel
        int rohoelNeeded = (int)(newAmount / RESS_FACTORS[ress]);
        consumeValueFields[0].setText(rohoelNeeded + "");
        
        // Verbleibende Ressourcen
        // Rohoel
        int currRohoel = new Integer(currentRessLabels[1].getText());
        int afterwardsRohoel = currRohoel - rohoelNeeded;
        afterwardsRessLabels[1].setText(afterwardsRohoel + "");
        
    }
    
    private void switchActiveAmountField(int index)
    {
        int[] timeLefts = {-1, -1, -1};
        if (this.ri != null)
        {
            timeLefts[0] = ri.getTimeLeftK();
            timeLefts[1] = ri.getTimeLeftD();
            timeLefts[2] = ri.getTimeLeftB();
        }
        
        this.rbGroup.clearSelection();
        for (int t = 0; t < 3; t++)
        {
            
            this.ressAmount[t].setText("");

            this.ressLabels[t].setEnabled(false);
            this.ressAmount[t].setEnabled(false);
            this.maxButtons[t].setEnabled(false);
            
            
            if (timeLefts[t] <= 0)
            {        
                if (t == index)
                {
                    this.ressLabels[t].setEnabled(true);
                    this.ressAmount[t].setEnabled(true);
                    this.maxButtons[t].setEnabled(true);

                    this.ressAmount[t].requestFocus();
                    this.ressAmount[t].selectAll();
                    
                    this.chooseRBs[t].setSelected(true);
                }
            }
            
        }
    }
    
    private String getTimestamp(long offset)
    {
        String date = DateFormat.getDateTimeInstance().format(new Date(System.currentTimeMillis() + offset));
        return date;
    }
    
}
