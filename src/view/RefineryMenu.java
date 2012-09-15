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
    
    public RefineryMenu(OilImp game, String startOilField)
    {
        this.game = game;
        this.currentOilField = startOilField;
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
