package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import static util.OilImpDesignFactory.*;

import model.OilImp;
import model.FactorySettings;
import model.RefineryInformation;

/**
 *
 * @author Paranoide
 */
public class RefineryMenu extends OilImpMenu implements ActionListener,
                                                        MouseListener,
                                                        DocumentListener
{
    
    private OilImp game;
    
    private String currentOilField;
    
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
    private JLabel[] ressLabels;
    private JTextField[] ressAmount;
    private JButton[] maxButtons;
    
    private JPanel produceButtonPanel;
    private JButton produceButton;
    
    
    private double kerosinFactor = 0.75;
    private double dieselFactor  = 0.60;
    private double benzinFactor  = 0.50;
    private double[] ressFactors = {0.75, 0.60, 0.50};
    private int[] ressMAs        = {40, 27, 37};
    
    
    public RefineryMenu(OilImp game, String startOilField)
    {
        this.game = game;
        this.currentOilField = startOilField;
        
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
        
        ButtonGroup rbGroup = new ButtonGroup();
        this.ressAmount = new JTextField[3];
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
            tmpRessAmoutPanel.add(this.ressAmount[t]);
            labelAndFieldPanel.add(tmpRessAmoutPanel);
            
            this.maxButtons[t] = new JButton("Max");
            this.maxButtons[t].setFont(BUTTON_FONT);
            this.maxButtons[t].setEnabled(false);
            this.maxButtons[t].addActionListener(this);
            JPanel tmpMaxButtonPanel = new JPanel(new GridLayout(1, 1));
            tmpMaxButtonPanel.setBorder(BorderFactory.createEmptyBorder(8, 5, 8, 5));
            tmpMaxButtonPanel.add(this.maxButtons[t]);
            labelAndFieldPanel.add(tmpMaxButtonPanel);
        }
        
        this.produceButtonPanel = new JPanel(new GridLayout(1, 1));
        this.produceButton = new JButton("Herstellen");
        this.produceButton.setFont(BUTTON_FONT);
        this.produceButton.addActionListener(this);
        this.produceButtonPanel.add(this.produceButton);
        
        this.producePanel.add(this.choosePanel, BorderLayout.CENTER);
        this.producePanel.add(this.produceButtonPanel, BorderLayout.SOUTH);
        
        
        this.add(this.infoPanel);
        this.add(this.actionPanel);
        
        this.reset();
        
    }
    
    
    
    public void getRefineryInformation()
    {
        RefineryInformation ri = this.game.getRefinery();
        this.currentRessLabels[0].setText(ri.getCurrentWorkers() + "");
        this.currentRessLabels[1].setText(ri.getCurrentRohoel() + "");
        this.currentRessLabels[2].setText(ri.getCurrentKerosin() + "");
        this.currentRessLabels[3].setText(ri.getCurrentDiesel() + "");
        this.currentRessLabels[4].setText(ri.getCurrentBenzin() + "");
        
        this.afterwardsRessLabels[0].setText(ri.getCurrentWorkers() + "");
        this.afterwardsRessLabels[1].setText(ri.getCurrentRohoel() + "");
        this.afterwardsRessLabels[2].setText(ri.getCurrentKerosin() + "");
        this.afterwardsRessLabels[3].setText(ri.getCurrentDiesel() + "");
        this.afterwardsRessLabels[4].setText(ri.getCurrentBenzin() + "");
        
        this.capacityLabels[0].setText(ri.getMaxWorkers() + "");
        this.capacityLabels[1].setText(ri.getMaxRohoel() + "");
        this.capacityLabels[2].setText(ri.getMaxKerosin() + "");
        this.capacityLabels[3].setText(ri.getMaxDiesel() + "");
        this.capacityLabels[4].setText(ri.getMaxBenzin() + "");
        
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

    @Override
    public void setCurrentOilField(String oilField)
    {
        this.currentOilField = oilField;
    }
    
    // Listener methods
    
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        for (int t = 0; t < 3; t++)
        {
            if (ae.getSource() == this.chooseRBs[t])
            {
                for (int n = 0; n < 3; n++)
                {
                    this.ressLabels[n].setEnabled(false);
                    this.ressAmount[n].setEnabled(false);
                    this.maxButtons[n].setEnabled(false);
                }
                
                this.ressLabels[t].setEnabled(true);
                this.ressAmount[t].setEnabled(true);
                this.maxButtons[t].setEnabled(true);
                
                this.ressAmount[t].requestFocus();
                this.ressAmount[t].selectAll();
                
                break;
            }
        }
        
        for (int t = 0; t < 3; t++)
        {
            if (ae.getSource() == this.maxButtons[t])
            {
                this.setMaximum(t);
            }
        }
        
        if (ae.getSource() == this.produceButton)
        {
            OilImp.Ressource[] resses = {OilImp.Ressource.KEROSIN,
                                         OilImp.Ressource.DIESEL,
                                         OilImp.Ressource.BENZIN};
            OilImp.Ressource ress = null;
            int rohoelAmount = 0;
            int ressAmount = 0;
            int workers = 0;
            for (int t = 0; t < 3; t++)
            {
                if (this.ressAmount[t].isEnabled())
                {
                    ress = resses[t];
                    rohoelAmount = new Integer(this.ressAmount[t].getText());
                    ressAmount = (int)(rohoelAmount / this.ressFactors[t]);
                    workers = new Integer(this.consumeValueFields[1].getText());
                }
            }
            
            this.game.produceInRefinery(ress, ressAmount, workers);
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
            this.ressLabels[t].setEnabled(false);
            this.ressAmount[t].setEnabled(false);
            this.maxButtons[t].setEnabled(false);
            this.chooseRBs[t].setSelected(false);
            if (e.getSource() == this.ressLabels[t] ||
                e.getSource() == this.ressAmount[t])
            {
                this.ressLabels[t].setEnabled(true);
                this.ressAmount[t].setEnabled(true);
                this.maxButtons[t].setEnabled(true);
                this.chooseRBs[t].setSelected(true);
                
                this.ressAmount[t].requestFocus();
                this.ressAmount[t].selectAll();
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
    public void changedUpdate(DocumentEvent e)
    {
//         int currW = new Integer(this.currentRessLabels[0].getText());
//         int currR = new Integer(this.currentRessLabels[1].getText());
//         int currK = new Integer(this.currentRessLabels[2].getText());
//         int currD = new Integer(this.currentRessLabels[3].getText());
//         int currB = new Integer(this.currentRessLabels[4].getText());

        int nr = 0;
        int newAmount = 0;
        for (int t = 0; t < this.ressAmount.length; t++)
        {
            if (e.getDocument() == this.ressAmount[t].getDocument())
            {
                nr = t;
                newAmount = new Integer("0" + this.ressAmount[t].getText());
            }
            else
            {
                this.ressAmount[t].setText("");
            }
        }

        for (int t = 0; t < this.ressAmount.length; t++)
        {
            this.afterwardsRessLabels[t + 2].setText(this.currentRessLabels[t + 2].getText());
        }

        int currRess = new Integer("0" + this.currentRessLabels[nr + 2].getText());
        String newAmountStr = (currRess + newAmount) + "";
        this.afterwardsRessLabels[nr + 2].setText(newAmountStr);
        
        this.consumeValueFields[1].setText(this.ressMAs[nr] + "");
        int rohoelNeeded = (int)(newAmount / this.ressFactors[nr]);
        this.consumeValueFields[0].setText(rohoelNeeded + "");
    }

    
    
    // Private methods
    
    private void setMaximum(int ress)
    {
                
        int currRess = new Integer(this.currentRessLabels[ress + 2].getText());
        int currRessCap = new Integer(this.capacityLabels[ress + 2].getText());
        
        int maxRessByCapacity = currRessCap - currRess;
        
        int currRohoel = new Integer(this.currentRessLabels[1].getText());
        int maxRessByRohoel = (int)(currRohoel * this.ressFactors[ress]);
        
        
        int maximum = Math.min(maxRessByCapacity, maxRessByRohoel);
        
        this.ressAmount[ress].setText(maximum + "");
    }
}
