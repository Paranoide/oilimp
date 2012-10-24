package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import model.OilImp;
import static util.OilImpDesignFactory.*;

/**
 *
 * @author Paranoide
 */
public class UtilityMenu extends OilImpMenu
{
    
    private OilImp game;
    private String currentOilField;
    
    private JPanel utilityPanel;
    private TitledBorder utilityPanelBorder;
    
    private JPanel ressPricePanel;
    private TitledBorder ressPricePanelBorder;
    private JPanel[] ressPricePanels;
    private JLabel[] ressNameLabels;
    private JLabel[] ressPriceLabels;
    
    
    public UtilityMenu(OilImp game, String currOilField)
    {
        this.game = game;
        this.currentOilField = currOilField;
        
        this.setLayout(new BorderLayout());
        this.setBorder(createBorder("Verschiedenes", BORDER_FONT, 0));
        
        this.utilityPanel = new JPanel(new GridLayout(5, 1));
        this.utilityPanelBorder = createBorder("Aktionen", BORDER_SMALL_FONT, 0);
        this.utilityPanel.setBorder(this.utilityPanelBorder);
        
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
    }

    public void setPrices()
    {
        int[] prices = this.game.checkPrices();
        for (int t = 0; t < 4; t++)
        {
            this.ressPriceLabels[t].setText(prices[t] + " ");
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
    
}
