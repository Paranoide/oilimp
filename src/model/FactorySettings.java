package model;

import java.util.List;
import java.util.LinkedList;

/**
 *
 * @author Paranoide
 */
public class FactorySettings
{

    private List<String> oilFields;
    private List<String[]> status;
    private List<Boolean[]> auto;

    public FactorySettings()
    {
        this.oilFields = new LinkedList<>();
        this.status = new LinkedList<>();
        this.auto = new LinkedList<>();
    }

    private void addOilField(String name)
    {
        if (!this.oilFields.contains(name))
        {
            this.oilFields.add(name);
            String[] defaultStatus = new String[9];
            for (int t = 0; t < defaultStatus.length; t++)
            {
                defaultStatus[t] = "";
            }
            this.status.add(defaultStatus);

            Boolean[] defaultAuto = new Boolean[9];
            for (int t = 0; t < defaultAuto.length; t++)
            {
                defaultAuto[t] = Boolean.FALSE;
            }
            this.auto.add(defaultAuto);
        }
    }

    public synchronized String[] getOilFields()
    {
        return this.oilFields.toArray(new String[this.oilFields.size()]);
    }

    public synchronized void setStatus(String oilField, int eq, String status)
    {
        int index = this.oilFields.indexOf(oilField);
        String[] theStatus = null;

        if (index < 0)
        {
            this.addOilField(oilField);
            index = this.oilFields.size() - 1;
        }

        theStatus = this.status.get(index);
        theStatus[eq] = status;
    }

    public synchronized void setAuto(String oilField, int eq, Boolean auto)
    {
        int index = this.oilFields.indexOf(oilField);
        Boolean[] theAuto = null;
        if (index < 0)
        {
            this.addOilField(oilField);
            index = this.oilFields.size() - 1;
        }

        theAuto = this.auto.get(index);
        theAuto[eq] = auto;
    }

    public synchronized String[] getStatus(String oilField)
    {
        int index = this.oilFields.indexOf(oilField);
        String[] theStatus = null;
        if (index >= 0)
        {
            theStatus = this.status.get(index);
        }
        return theStatus;
    }

    public synchronized Boolean[] getAuto(String oilField)
    {
        int index = this.oilFields.indexOf(oilField);
        Boolean[] theAuto = null;
        if (index >= 0)
        {
            theAuto = this.auto.get(index);
        }
        return theAuto;
    }

}
