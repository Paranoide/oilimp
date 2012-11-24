package model;

/**
 *
 * @author Paranoide
 */
public class RefineryInformation
{
    
    public static final double[] RESS_FACTORS = {0.75, 0.60, 0.50};
    public static final int[] RESS_MA         = {40, 27, 37};
    
    private int currentRohoel;
    private int currentKerosin;
    private int currentDiesel;
    private int currentBenzin;
    private int currentWorkers;
    
    private int maxKerosin;
    private int maxDiesel;
    private int maxBenzin;
    private int maxWorkers;
    
    private int timeLeftK;
    private int timeLeftD;
    private int timeLeftB;
    
    
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
        
        this.timeLeftK = 0;
        this.timeLeftD = 0;
        this.timeLeftB = 0;
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
    
    public int getMaxRohoel()
    {
        return 250000;
    }

    /**
     * Gibt die Zeit in Sekunden zurueck, die das Kerosin noch in Produktion
     * verbleibt. Ist kein Kerosin in Produktion, so wird -1 zurueckgegeben.
     * 
     * @return Verbleibende Produktionszeit fuer Kerosin. Ist -1, wenn kein
     *          Kerosin in Produktion ist
     */
    public int getTimeLeftK()
    {
        return timeLeftK;
    }

    public void setTimeLeftK(int timeLeftK)
    {
        this.timeLeftK = timeLeftK;
    }

    /**
     * Gibt die Zeit in Sekunden zurueck, die das Diesel noch in Produktion
     * verbleibt. Ist kein Diesel in Produktion, so wird -1 zurueckgegeben.
     * 
     * @return Verbleibende Produktionszeit fuer Diesel. Ist -1, wenn kein
     *          Diesel in Produktion ist
     */
    public int getTimeLeftD()
    {
        return timeLeftD;
    }

    public void setTimeLeftD(int timeLeftD)
    {
        this.timeLeftD = timeLeftD;
    }

    /**
     * Gibt die Zeit in Sekunden zurueck, die das Benzin noch in Produktion
     * verbleibt. Ist kein Benzin in Produktion, so wird -1 zurueckgegeben.
     * 
     * @return Verbleibende Produktionszeit fuer Benzin. Ist -1, wenn kein
     *          Benzin in Produktion ist
     */
    public int getTimeLeftB()
    {
        return timeLeftB;
    }

    public void setTimeLeftB(int timeLeftB)
    {
        this.timeLeftB = timeLeftB;
    }
}
