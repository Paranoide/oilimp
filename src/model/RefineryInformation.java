package model;

/**
 *
 * @author Paranoide
 */
public class RefineryInformation
{
    
    private int currentRohoel;
    private int currentKerosin;
    private int currentDiesel;
    private int currentBenzin;
    private int currentWorkers;
    
    private int maxKerosin;
    private int maxDiesel;
    private int maxBenzin;
    private int maxWorkers;
    
    
    public RefineryInformation(int currR, int currK, int currD, int currB, int currW,
                                          int maxK,  int maxD,  int maxB,  int maxW)
    {
        this.currentRohoel = currR;
        this.currentKerosin = currK;
        this.currentDiesel = currD;
        this.currentBenzin = currB;
        this.currentWorkers = currW;
        
        this.maxKerosin = maxK;
        this.maxDiesel = maxD;
        this.maxBenzin = maxB;
        this.maxWorkers = maxW;
    }
    
    @Override
    public String toString()
    {
        return "Rohoel: " + this.currentRohoel + "/" + "250000" + "\n" +
               "Kerosin: " + this.currentKerosin + "/" + this.maxKerosin +  "\n" +
               "Diesel: " + this.currentDiesel + "/" + this.maxDiesel +  "\n" +
               "Benzin: " + this.currentBenzin + "/" + this.maxBenzin +  "\n" +
               "Arbeiter: " + this.currentWorkers + "/" + this.maxWorkers;
    }

    public int getCurrentRohoel()
    {
        return currentRohoel;
    }

    public void setCurrentRohoel(int currentRohoel)
    {
        this.currentRohoel = currentRohoel;
    }

    public int getCurrentKerosin()
    {
        return currentKerosin;
    }

    public void setCurrentKerosin(int currentKerosin)
    {
        this.currentKerosin = currentKerosin;
    }

    public int getCurrentDiesel()
    {
        return currentDiesel;
    }

    public void setCurrentDiesel(int currentDiesel)
    {
        this.currentDiesel = currentDiesel;
    }

    public int getCurrentBenzin()
    {
        return currentBenzin;
    }

    public void setCurrentBenzin(int currentBenzin)
    {
        this.currentBenzin = currentBenzin;
    }

    public int getCurrentWorkers()
    {
        return currentWorkers;
    }

    public void setCurrentWorkers(int currentWorkers)
    {
        this.currentWorkers = currentWorkers;
    }

    public int getMaxKerosin()
    {
        return maxKerosin;
    }

    public void setMaxKerosin(int maxKerosin)
    {
        this.maxKerosin = maxKerosin;
    }

    public int getMaxDiesel()
    {
        return maxDiesel;
    }

    public void setMaxDiesel(int maxDiesel)
    {
        this.maxDiesel = maxDiesel;
    }

    public int getMaxBenzin()
    {
        return maxBenzin;
    }

    public void setMaxBenzin(int maxBenzin)
    {
        this.maxBenzin = maxBenzin;
    }

    public int getMaxWorkers()
    {
        return maxWorkers;
    }

    public void setMaxWorkers(int maxWorkers)
    {
        this.maxWorkers = maxWorkers;
    }
}
