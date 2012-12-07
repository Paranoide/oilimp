package view;

import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.*;
import javax.swing.*;

import java.io.*;

import java.math.BigInteger;
import model.OilImp;

import static util.OilImpDesignFactory.*;

public class OilImpGUI implements Runnable
{
    
    private OilImp game;

    private JFrame mainFrame;

    private JPanel mainPanel;
    
    private int width;
    private int height;


    // HeadPanel
    private JPanel comboBoxesPanel;

    private JPanel oilFieldBoxPanel;
    private TitledBorder oilFieldBoxPanelBorder;

    private JPanel menuBoxPanel;
    private TitledBorder menuBoxPanelBorder;

    private JComboBox<String> oilFieldBox;
    private JComboBox<String> menuBox;

    // Menus
    private JPanel menuPanel;
    private CardLayout menuPanelCards;

    private FactoryMenu factoryMenu;
    private final String FACTORY_ID = "Fabrik";

    private RefineryMenu refineryMenu;
    private final String REFINERY_ID = "Raffinerie";

    private StockMenu stockMenu;
    private final String STOCK_ID = "Aktien";
    
    private UtilityMenu utilMenu;
    private final String UTIL_ID = "Verschiedenes";
    
    // Foot messages
    private JPanel logPanel;
    private TitledBorder logPanelBorder;
    private JTextArea logArea;
    private JScrollPane logAreaScroller;

    private String currOilField;

    public OilImpGUI(OilImp gameInstance)
    {
        this(gameInstance, 800, 800);
    }
    
    public OilImpGUI(OilImp gameInstance, int width, int height)
    {
        this.game = gameInstance;
        this.width = width;
        this.height = height;
    }
    
    private void init()
    {
        this.mainFrame = new JFrame("Oil Imperium");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setSize(this.width, this.height);

        this.mainPanel = new JPanel(new BorderLayout(10, 10));
        this.mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.mainFrame.add(this.mainPanel);

        this.mainFrame.setVisible(true);
        
        // Foot Log Panel
        this.logPanel = new JPanel(new GridLayout(1, 1));
        this.logPanel.setPreferredSize(new Dimension(0, 200));
        this.logPanelBorder = createBorder("Log", FONT_CALIBRI_22_B, 0);
        this.logPanel.setBorder(this.logPanelBorder);

        this.logArea = new JTextArea();
        this.logArea.setFont(FONT_CNEW_12);
        this.logAreaScroller = new JScrollPane(this.logArea);

        this.logPanel.add(this.logAreaScroller);
        this.mainPanel.add(this.logPanel, BorderLayout.SOUTH);

        this.prepareLog();
        
        // HeadPanel
        this.comboBoxesPanel = new JPanel(new GridLayout(1, 2));
        ((GridLayout)this.comboBoxesPanel.getLayout()).setVgap(10);
        this.mainPanel.add(this.comboBoxesPanel, BorderLayout.NORTH);

        this.oilFieldBoxPanel = new JPanel();
        this.oilFieldBoxPanelBorder = createBorder("Oil Field", FONT_CALIBRI_22_B, 0);
        this.oilFieldBoxPanel.setBorder(this.oilFieldBoxPanelBorder);

        this.menuBoxPanel = new JPanel();
        this.menuBoxPanelBorder = createBorder("Menu", FONT_CALIBRI_22_B, 0);
        this.menuBoxPanel.setBorder(this.menuBoxPanelBorder);

        this.comboBoxesPanel.add(this.oilFieldBoxPanel);
        this.comboBoxesPanel.add(this.menuBoxPanel);

        // ComboBox1
        this.oilFieldBox = new JComboBox<>();
        this.oilFieldBox.setFont(FONT_ARIAL_18);
        this.oilFieldBoxPanel.add(this.oilFieldBox);
        
//        final String[] oilFieldNames = {"a", "b"}; //this.game.getOilFieldNames();
//        Runnable r = new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                for (int t = 0; t < oilFieldNames.length; t++)
//                {
//                    oilFieldBox.addItem(oilFieldNames[t]);
//                }
//            }
//        };
//        EventQueue.invokeLater(r);
//
//        // ComboBox2
//        String[] menuNames = new String[]{FACTORY_ID, REFINERY_ID, STOCK_ID, UTIL_ID};
//        this.menuBox = new JComboBox<>(menuNames);
//        this.menuBox.setFont(FONT_ARIAL_18);
//        this.menuBoxPanel.add(this.menuBox);
//
//
//        // MenuMainPanel -------------------------------------------------------
//        this.menuPanelCards = new CardLayout();
//        this.menuPanel = new JPanel(this.menuPanelCards);
//        this.mainPanel.add(this.menuPanel, BorderLayout.CENTER);
//        
//        this.factoryMenu = new FactoryMenu(this.game, oilFieldNames[0]);
//        this.menuPanel.add(this.factoryMenu, FACTORY_ID);
////        this.factoryMenu.defaultAction();
//
//        this.refineryMenu = new RefineryMenu(this.game, oilFieldNames[0]);
//        this.menuPanel.add(this.refineryMenu, REFINERY_ID);
//        
//        this.stockMenu = new StockMenu(this.game, oilFieldNames[0]);
//        this.menuPanel.add(this.stockMenu, STOCK_ID);
//        
//        this.utilMenu = new UtilityMenu(game, oilFieldNames[0]);
//        this.menuPanel.add(this.utilMenu, UTIL_ID);
        
        // ---------------------------------------------------------------------


        

//        this.applyColorToAllComponents(this.mainFrame,
//                                       backgroundColor,
//                                       JButton.class,
//                                       JComboBox.class,
//                                       JTextArea.class,
//                                       JTextField.class);
        
        

        
    }

    private void setListeners()
    {
        ItemListener oilFieldListener = new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent ie)
            {
                if (ie.getStateChange() == ItemEvent.SELECTED)
                {
                    final String oilField = (String)ie.getItem();
                    new Thread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            currOilField = oilField;
                            game.changeOilField(oilField);
                            OilImpMenu oim = getCurrentMenu();
                            oim.setCurrentOilField(oilField);
                            oim.defaultAction();
                        }
                    }).start();

                }
            }
        };

        ItemListener menuListener = new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent ie)
            {
                if (ie.getStateChange() == ItemEvent.SELECTED)
                {
                    String s = (String)ie.getItem();
                    menuPanelCards.show(menuPanel, s);
                    
                    new Thread(new Runnable()
                    {
                        @Override
                        public void run() 
                        {
                            OilImpMenu oim = getCurrentMenu();
                            if (oim != null)
                            {
                                oim.setCurrentOilField(currOilField);
                                oim.defaultAction();
                            }
                        }
                    }).start();
                }
            }
        };

        this.oilFieldBox.addItemListener(oilFieldListener);
        this.menuBox.addItemListener(menuListener);
    }
    
    private OilImpMenu getCurrentMenu()
    {
        OilImpMenu oim = null;
        for (Component c: menuPanel.getComponents())
        {
            if (c.isVisible())
            {
                oim = (OilImpMenu)c;
            }
        }
        return oim;
    }

    private void prepareLog()
    {
        ((DefaultCaret)this.logArea.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        OutputStream out = new OutputStream()
        {
            private PrintStream console = System.out;
            
            private byte lastByte = 0;

            @Override
            public synchronized void write(int b) throws IOException
            {
                this.console.write(b);

                if (b >= -128 && b <= 127)
                {
                    if (this.lastByte < 0)
                    {
                        logArea.append(new String(new byte[]{this.lastByte, (byte)b}));
                        this.lastByte = 0;
                    }
                    else if(b < 0)
                    {
                        this.lastByte = (byte)b;
                    }
                    else
                    {
                        logArea.append(new String(new byte[]{(byte)b}));
                    }
                }
                else
                {
                    logArea.append(String.valueOf((char)b));
                }
            }
            
        };

        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(out));
    }
    

    private void applyColors()
    {
        this.applyColorToAllComponents(this.mainFrame,
                                       backgroundColor,
                                       JButton.class,
                                       JComboBox.class,
                                       JTextArea.class,
                                       JTextField.class);
    }
    
    private void applyColorToAllComponents(Component comp, Color color, Class ... except)
    {
        boolean changeColor = true;
        for (Class cl: except)
        {
            if (cl.isInstance(comp) || comp instanceof JButton)
            {
                changeColor = false;
                break;
            }
        }
        if (changeColor)
        {
            comp.setBackground(color);
        }

        if (comp instanceof Container)
        {
            Component[] subComps = ((Container)comp).getComponents();
            for (Component c: subComps)
            {
                this.applyColorToAllComponents((Component)c, color, except);
            }
        }
    }

    @Override
    public void run()
    {
        this.init();
    }
    
    
    
    private void setOilFieldNames(final String[] oilFieldNames)
    {
        Runnable r = new Runnable()
        {
            @Override
            public void run()
            {
                for (int t = 0; t < oilFieldNames.length; t++)
                {
                    oilFieldBox.addItem(oilFieldNames[t]);
                }
            }
        };
        EventQueue.invokeLater(r);

        this.currOilField = oilFieldNames[0];
        
        // ComboBox2
        String[] menuNames = new String[]{FACTORY_ID, REFINERY_ID, STOCK_ID, UTIL_ID};
        this.menuBox = new JComboBox<>(menuNames);
        this.menuBox.setFont(FONT_ARIAL_18);
        this.menuBoxPanel.add(this.menuBox);


        // MenuMainPanel -------------------------------------------------------
        this.menuPanelCards = new CardLayout();
        this.menuPanel = new JPanel(this.menuPanelCards);
        this.mainPanel.add(this.menuPanel, BorderLayout.CENTER);
        
        this.factoryMenu = new FactoryMenu(this.game, oilFieldNames[0]);
        this.menuPanel.add(this.factoryMenu, FACTORY_ID);
        this.factoryMenu.defaultAction();

        this.refineryMenu = new RefineryMenu(this.game, oilFieldNames[0]);
        this.menuPanel.add(this.refineryMenu, REFINERY_ID);
        
        this.stockMenu = new StockMenu(this.game, oilFieldNames[0]);
        this.menuPanel.add(this.stockMenu, STOCK_ID);
        
        this.utilMenu = new UtilityMenu(game, oilFieldNames[0]);
        this.menuPanel.add(this.utilMenu, UTIL_ID);
    }
    
    
    public static void main(String[] args)
    {
        setLAF();
        OilImp game = new OilImp();
        OilImpGUI oig = new OilImpGUI(game);
        EventQueue.invokeLater(oig);
        
        String[] names = game.getOilFieldNames();
        oig.setOilFieldNames(names);
        oig.applyColors();
        oig.setListeners();
    }


    public static void setLAF()
    {
        try
        {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException | 
               ClassNotFoundException | 
               InstantiationException |
               IllegalAccessException e)
        {
           // handle exception
        }
    }
    
    
    
}