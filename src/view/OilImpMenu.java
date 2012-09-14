package view;

import javax.swing.JPanel;

/**
 *
 * @author Paranoide
 */
public abstract class OilImpMenu extends JPanel
{

    public abstract void reset();

    public abstract void reset(String oilField);

    public abstract void defaultAction();

    public abstract void setCurrentOilField(String oilField);
}
