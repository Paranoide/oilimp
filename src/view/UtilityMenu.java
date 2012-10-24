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
    
    private JPanel utilityPanel;
    private TitledBorder utilityPanelBorder;
    
    private JPanel ressPricePanel;
    private JPanel[] ressPricePanels;
    private JLabel[] ressNameLabels;
    private JLabel[] ressPriceLabels;
    
    
    public UtilityMenu(OilImp game)
    {
        this.game = game;
        
        this.setLayout(new BorderLayout());
        
        this.utilityPanel = new JPanel(new GridLayout(5, 1));
        this.utilityPanelBorder = createBorder("Verschiedenes", BORDER_FONT, 0);
        this.utilityPanel.setBorder(this.utilityPanelBorder);
        
        this.ressPricePanel = new JPanel(new GridLayout(1, 4));
        this.ressPricePanels = new JPanel[4];
        this.ressNameLabels = new JLabel[4];
        this.ressPriceLabels = new JLabel[4];
        
        String[] ressNames = {"Rohoel", "Kerosin", "Diesel", "Benzin"};
        
        for (int t = 0; t < 4; t++)
        {
            this.ressPricePanels[t] = new JPanel(new GridLayout(1, 2));
            this.ressNameLabels[t] = new JLabel(ressNames[t]);
            this.ressPriceLabels[t] = new JLabel("---");
            
            this.ressPricePanels[t].add(this.ressNameLabels[t]);
            this.ressPricePanels[t].add(this.ressPriceLabels[t]);
        }
        
        
        
        
        this.add(this.utilityPanel, BorderLayout.CENTER);
        this.add(this.ressPricePanel, BorderLayout.SOUTH);
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

    @Override
    public void setCurrentOilField(String oilField)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
