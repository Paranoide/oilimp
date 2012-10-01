/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.EventQueue;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import model.OilImp;

import static util.OilImpDesignFactory.*;

/**
 *
 * @author Paranoide
 */
public class StockMenu extends OilImpMenu
{
    private OilImp game;
    
    private JPanel mainPanel;
    private TitledBorder mainPanelBorder;
    
    private JPanel messagePanel;
    private JLabel messageLabel;
    
    private String currentOilField;
    
    public StockMenu(OilImp game, String oilField)
    {
        this.mainPanel = new JPanel(new GridLayout(1, 1));
        this.mainPanelBorder = createBorder("Stocks", BORDER_FONT, 0);
        
        this.messagePanel = new JPanel(new GridLayout(1, 1));
        this.messageLabel = new JLabel("---");
        this.messagePanel.add(this.messageLabel);
        
        this.mainPanel.add(this.messagePanel);
        
        this.add(this.mainPanel);
        
        this.game = game;
        this.currentOilField = oilField;
    }
    
    public void getStockInformation()
    {
        Thread t = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                String answer = game.getStockInformation();
                messageLabel.setText(answer);
            }
        });
        
        EventQueue.invokeLater(t);
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
        this.getStockInformation();
    }

    @Override
    public void setCurrentOilField(String oilField)
    {
        this.currentOilField = oilField;
    }
    
}
