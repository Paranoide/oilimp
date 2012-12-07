package model;

/**
 *
 * @author Paranoide
 */
public class Equipment
{
    
    public static enum EQName
    {
        BOHRTURM_A("Bohrturm A"),
        BOHRTURM_B("Bohrturm B"),
        BOHRTURM_C("Bohrturm C"),
        TANK_A("Tank A"),
        TANK_B("Tank B"),
        TANK_C("Tank C"),
        PIPELINE_A("Pipeline A"),
        PIPELINE_B("Pipeline B"),
        PIPELINE_C("Pipeline C");
        
        private String name;
        
        private EQName(String name)
        {
            this.name = name;
        }
        
        public String getName()
        {
            return this.name;
        }
    }
    
    private EQName eqName;
    private double status;
    private int price;
    
    public Equipment(String name, double status, int price)
    {
        EQName eqN = null;
        
        for (EQName e: EQName.values())
        {
            if (e.getName().equals(name))
            {
                eqN = e;
            }
        }
        
        if (eqN == null)
        {
            throw new IllegalArgumentException(name + " is not a valid EQName");
        }
        
        this.eqName = eqN;
        this.status = status;
        this.price = price;
    }
    
    public Equipment(EQName name, double status, int price)
    {
        this.eqName = name;
        this.status = status;
        this.price = price;
    }

    public EQName getEQName()
    {
        return eqName;
    }

    public void setEQName(EQName eqName)
    {
        this.eqName = eqName;
    }

    public double getStatus()
    {
        return status;
    }

    public void setStatus(double status)
    {
        this.status = status;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }
    
    @Override
    public String toString()
    {
        return this.eqName.getName() + ", " + 
               this.status + ", " + 
               this.price;
    }
    
    
}
