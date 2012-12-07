package view;

import javax.swing.JPanel;

/**
 *
 * @author Paranoide
 */
public abstract class OilImpMenu extends JPanel
{
    protected String currOilField = null;

    public abstract void reset();

    public abstract void reset(String oilField);

    public abstract void defaultAction();

    public void setCurrentOilField(String oilField)
    {
        this.currOilField = oilField;
    }
}
