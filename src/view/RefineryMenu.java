package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;

import static util.OilImpDesignFactory.*;

import model.OilImp;
import model.FactoryInformation;

/**
 *
 * @author Paranoide
 */
public class RefineryMenu extends OilImpMenu
{
    
    OilImp game;
    
    String currentOilField;
    
    JPanel infoPanel;
    TitledBorder infoPanelBorder;
    JPanel ressNamePanel;
    JLabel[] ressNameLabels;
    TitledBorder ressNamePanelBorder;
    JPanel currentRessPanel;
    JLabel[] currentRessLabels;
    TitledBorder currentRessPanelBorder;
    JPanel afterwardsRessPanel;
    JLabel[] afterwardsRessLabels;
    TitledBorder afterwardsRessPanelBorder;
    JPanel capacityPanel;
    JLabel[] capacityLabels;
    TitledBorder capacityPanelBorder;
    
    
    JPanel actionPanel;
    
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
        this.currentRessLabels[0] = new JLabel("100");
        this.currentRessLabels[1] = new JLabel("53343");
        this.currentRessLabels[2] = new JLabel("20000");
        this.currentRessLabels[3] = new JLabel("15000");
        this.currentRessLabels[4] = new JLabel("50232");
        for (int t = 0; t < this.currentRessLabels.length; t++)
        {
            this.currentRessLabels[t].setFont(LABEL_FONT);
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
            this.afterwardsRessLabels[t].setFont(LABEL_FONT);
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
            this.capacityLabels[t].setFont(LABEL_FONT);
            this.capacityPanel.add(this.capacityLabels[t]);
        }
        
        this.infoPanel.add(this.ressNamePanel);
        this.infoPanel.add(this.currentRessPanel);
        this.infoPanel.add(this.afterwardsRessPanel);
        this.infoPanel.add(this.capacityPanel);
        
        
        
        this.actionPanel = new JPanel(new GridLayout(1, 1));
        this.actionPanel.add(new Button("OK"));
        
        
        
        this.add(this.infoPanel);
        this.add(this.actionPanel);
    }
    
    

    @Override
    public void reset()
    {
        
    }

    @Override
    public void reset(String oilField)
    {
        
    }

    @Override
    public void defaultAction()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setCurrentOilField(String oilField)
    {
        this.currentOilField = oilField;
    }
    
}
