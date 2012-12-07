package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;

import static util.OilImpDesignFactory.*;

import model.OilImp;
import model.FactorySettings;
/**
 *
 * @author Paranoide
 */
public class FactoryMenu extends OilImpMenu
{

    private OilImp game;

    private TitledBorder factoryPanelBorder;

    private JPanel informationPanel;
    private TitledBorder informationPanelBorder;
    
    private JPanel eqLabelPanel;
    private Border eqLabelPanelBorder;
    private JPanel statusLabelPanel;
    private Border statusLabelPanelBorder;

    private JLabel[] eqLabels;
    private JLabel[] statusLabels;

    private JPanel produceButtonsPanel;
    private Border produceButtonsPanelBorder;
    private JButton[] produceButtons;

    private JPanel checkBoxPanel;
    private TitledBorder checkBoxPanelBorder;
    private JCheckBox[] autoCheckBoxes;

    private JPanel miscButtonsPanel;
    private Border miscButtonsPanelBorder;
    private JButton[] miscButtons;

    private JTextField refreshRateField;
    
    private Thread autoThread;
    private long refreshRate = 6*60000 + 10000;
    private boolean threadIsRunning = false;
    private final FactorySettings factoryInfo;

    public FactoryMenu(OilImp game, String startOilField)
    {
        super();

        this.setLayout(new BorderLayout(10, 10));

        this.game = game;

        this.factoryPanelBorder = createBorder("Factory", FONT_CALIBRI_22_B, 0);
        this.setBorder(this.factoryPanelBorder);

        // InformationPanel ----------------------------------------------------
        this.informationPanel = new JPanel(new BorderLayout(5, 5));
        this.informationPanelBorder = createBorder("Info", FONT_ROMAN_14, 0);
        this.informationPanel.setBorder(this.informationPanelBorder);

          // EQ Labels
        this.eqLabelPanel = new JPanel(new GridLayout(9 + 1, 1, 5, 5));
        this.eqLabelPanelBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        this.eqLabelPanel.setBorder(this.eqLabelPanelBorder);
        this.eqLabelPanel.setPreferredSize(new Dimension(140, 0));
        this.informationPanel.add(this.eqLabelPanel, BorderLayout.WEST);

          // Status Labels
        this.statusLabelPanel = new JPanel(new GridLayout(9 + 1, 1, 5, 5));
        this.statusLabelPanelBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        this.statusLabelPanel.setBorder(this.statusLabelPanelBorder);
        this.informationPanel.add(this.statusLabelPanel, BorderLayout.CENTER);
        
          // Buttons
        this.produceButtonsPanel = new JPanel(new GridLayout(9 + 1, 1, 5, 5));
        this.produceButtonsPanelBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        this.produceButtonsPanel.setBorder(this.produceButtonsPanelBorder);
        this.informationPanel.add(this.produceButtonsPanel, BorderLayout.EAST);
        // ---------------------------------------------------------------------

        // Headlines
        JLabel eqHeadline = new JLabel("Equipment", JLabel.CENTER);
        eqHeadline.setFont(FONT_COMIC_18_BI);
        this.eqLabelPanel.add(eqHeadline);
        JLabel statusHeadline = new JLabel("Status", JLabel.CENTER);
        statusHeadline.setFont(FONT_COMIC_18_BI);
        this.statusLabelPanel.add(statusHeadline);
        JLabel btnHeadline = new JLabel("Action", JLabel.CENTER);
        btnHeadline.setFont(FONT_COMIC_18_BI);
        this.produceButtonsPanel.add(btnHeadline);


        this.eqLabels = new JLabel[9];
        this.statusLabels = new JLabel[9];
        this.produceButtons = new JButton[9];
        for (int t = 0; t < 9; t++)
        {
            this.eqLabels[t] = new JLabel("eqLabel" + t);
            this.eqLabels[t].setFont(FONT_ARIAL_18);
            this.eqLabelPanel.add(this.eqLabels[t]);

            this.statusLabels[t] = new JLabel("statusLabel" + t);
            this.statusLabels[t].setFont(FONT_ARIAL_18);
            this.statusLabelPanel.add(this.statusLabels[t]);

//                this.buttonPanel.add(Box.createHorizontalBox());
            this.produceButtons[t] = new JButton("Produce");
            this.produceButtons[t].setFont(FONT_ARIAL_18);
            this.produceButtonsPanel.add(this.produceButtons[t]);
//                this.buttonPanel.add(Box.createHorizontalBox());
        }

        this.add(this.informationPanel, BorderLayout.CENTER);


        // CheckBoxPanel
        this.checkBoxPanel = new JPanel(new GridLayout(9 + 1, 1));
        this.checkBoxPanelBorder = createBorder("Auto?", FONT_ROMAN_14, 0);
        this.checkBoxPanel.setBorder(this.checkBoxPanelBorder);
        this.autoCheckBoxes = new JCheckBox[9];
        
        JPanel tmpTFPanel = new JPanel(new BorderLayout());
        this.refreshRateField = new JTextField("6");
        this.refreshRateField.setHorizontalAlignment(JTextField.RIGHT);
        this.refreshRateField.setFont(LABEL_FONT);
        tmpTFPanel.add(this.refreshRateField, BorderLayout.CENTER);
        JLabel minuteLabel = new JLabel("m");
        minuteLabel.setFont(LABEL_FONT);
        tmpTFPanel.add(minuteLabel, BorderLayout.EAST);
        this.checkBoxPanel.add(tmpTFPanel);
        for (int t = 0; t < 9; t++)
        {
            JPanel tmpCBPanel = new JPanel(new GridLayout(1, 3));
            tmpCBPanel.add(Box.createHorizontalBox());
            this.autoCheckBoxes[t] = new JCheckBox();
            tmpCBPanel.add(this.autoCheckBoxes[t]);
            tmpCBPanel.add(Box.createHorizontalBox());
            
            this.checkBoxPanel.add(tmpCBPanel);
        }

        this.add(this.checkBoxPanel, BorderLayout.EAST);


        this.miscButtonsPanel = new JPanel(new GridLayout(1, 1));
        this.miscButtonsPanelBorder = createBorder("Actions", BORDER_SMALL_FONT, 0);
        this.miscButtonsPanel.setBorder(this.miscButtonsPanelBorder);
        this.miscButtons = new JButton[1];

        for (int t = 0; t < 1; t++)
        {
            this.miscButtons[t] = new JButton("Start thread");
            this.miscButtons[t].setBackground(Color.GREEN);
            this.miscButtons[t].setForeground(Color.BLACK);
            this.miscButtons[t].setContentAreaFilled(false);
            this.miscButtons[t].setOpaque(true);
            this.miscButtonsPanel.add(this.miscButtons[t]);
        }
        this.add(this.miscButtonsPanel, BorderLayout.SOUTH);



        this.factoryInfo = new FactorySettings();
        this.autoThread = new Thread(new AutoThread());
        this.currOilField = startOilField;

        this.setListeners();

    }

    private void setListeners()
    {
        ActionListener factoryListener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                
                if (ae.getSource() == refreshRateField)
                {
                    try
                    {
                        int newRate = new Integer(refreshRateField.getText());
                        refreshRate = newRate * 60 * 1000;
                    }
                    catch (NumberFormatException nfe)
                    {
                        String msg = "Please enter an integer";
                        String title = "Wrong format";
                        JOptionPane.showMessageDialog(null,
                                                      msg,
                                                      title,
                                                      JOptionPane.ERROR_MESSAGE);
                        refreshRateField.requestFocusInWindow();
                        refreshRateField.selectAll();
                    }
                }
                else
                {
                    for (int t = 0; t < produceButtons.length; t++)
                    {
                        final int t2 = t;
                        if (ae.getSource() == produceButtons[t])
                        {
                            produce(t2, currOilField);
                        }
                    }

                    for (int t = 0; t < autoCheckBoxes.length; t++)
                    {
                        JCheckBox cb = autoCheckBoxes[t];
                        if (cb == ae.getSource())
                        {
                            factoryInfo.setAuto(currOilField, t, cb.isSelected());
                        }
                    }

                    for (int t = 0; t < miscButtons.length; t++)
                    {
                        if (miscButtons[t] == ae.getSource())
                        {
                            if (t == 0)
                            {
                                setAutoThreadWorking(!threadIsRunning);
                            }
                        }
                    }
                }
            }
        };

        refreshRateField.addActionListener(factoryListener);
        for (int t = 0; t < produceButtons.length; t++)
        {
            produceButtons[t].addActionListener(factoryListener);
        }

        for (int t = 0; t < autoCheckBoxes.length; t++)
        {
            autoCheckBoxes[t].addActionListener(factoryListener);
        }
        
        for (int t = 0; t < miscButtons.length; t++)
        {
            miscButtons[t].addActionListener(factoryListener);
        }
    }

    private void getFactoryStatus()
    {
        this.getFactoryStatus(this.currOilField);
    }

    private void getFactoryStatus(final String oilField)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                synchronized (factoryInfo)
                {
                    if (!game.getCurrentOilField().equals(oilField))
                    {
                        game.changeOilField(oilField);
                    }

                    setFactoryInformation(oilField);
                }
            }
        }).start();
        
        
    }
    
    /**
     * Holt die FactoryInformation und die FactorySettings fuer ein Oelfeld
     * und aktualisiert die  FactorySettings. Ist das Oelfeld das aktuelle Feld,
     * so werden die Informationen in die Labels eingetragen und die aktuell
     * ausgewaehlten  CheckBoxen entsprechend aktiviert bzw. deaktiviert.
     * @param oilField Das Oelfeld, fuer das die Informationen angezeigt und
     *                  aktualisiert werden sollen.
     */
    private void setFactoryInformation(String oilField)
    {
        // Gespeicherte Daten eintragen
        synchronized (factoryInfo)
        {
            this.currOilField = oilField;
            
            String[] knownFields = this.factoryInfo.getOilFields();
            boolean known = false;
            for (int t = 0; t < knownFields.length && !known; t++)
            {
                if (knownFields[t].equals(oilField))
                {
                    known = true;
                }
            }
            System.out.println("Known: " + known);
            if (known)
            {
                Boolean[] autos = this.factoryInfo.getAuto(oilField);
                String[] status = this.factoryInfo.getStatus(oilField);
                for (int t = 0; t < 9; t++)
                {
                    this.autoCheckBoxes[t].setSelected(autos[t].booleanValue());
                    this.statusLabels[t].setText(status[t]);
                }
            }
            else
            {
                for (int t = 0; t < 9; t++)
                {
                    this.autoCheckBoxes[t].setSelected(false);
                    this.statusLabels[t].setText("---");
                }
            }
        
            // Neue Daten holen und eintragen
            final String[][] status = game.getFactory();
            for (int t = 0; t < status.length; t++)
            {
                final int t2 = t;
                if (oilField.equals(currOilField))
                {
                    Runnable r = new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            eqLabels[t2].setText(status[t2][0]);
                            statusLabels[t2].setText(status[t2][1]);
                        }
                    };
                    EventQueue.invokeLater(r);
                }
                factoryInfo.setStatus(oilField, t, status[t][1]);
            }
        }
    }

    private void produce(final int t, final String oilField)
    {
        Runnable r = new Runnable()
        {
            @Override
            public void run()
            {
                game.changeOilField(oilField);
                game.produceInFactory(t);
                defaultAction();
            }
        };
        new Thread(r).start();
    }

    /**
     * Ueberprueft, ob der AutoThread zurzeit laeuft oder nicht.
     * @return <code>true</code>, genau dann wenn der AutoThread zurzeit
     *          laeuft, sonst <code>false</code>
     */
    public boolean isAutoThreadWorking()
    {
        return this.threadIsRunning;
    }

    /**
     * Startet oder beendet den Thread
     *
     * @param working <code>true</code> zum Starten, <code>false</code> zum Beenden
     */
    public void setAutoThreadWorking(boolean working)
    {
        if (!this.autoThread.isAlive() && working)
        {
            this.autoThread = new Thread(new AutoThread());
            this.autoThread.start();
            this.threadIsRunning = true;
            System.out.println("Starting thread");
            this.miscButtons[0].setText("Stop thread");
            this.miscButtons[0].setBackground(Color.RED);
            this.miscButtons[0].setForeground(Color.WHITE);
            this.refreshRateField.setEnabled(false);
        }
        else if (this.autoThread.isAlive() && !working)
        {
            this.autoThread.interrupt();
            this.threadIsRunning = false;
            System.out.println("Ending thread");
            this.miscButtons[0].setText("Start thread");
            this.miscButtons[0].setBackground(Color.GREEN);
            this.miscButtons[0].setForeground(Color.BLACK);
            this.refreshRateField.setEnabled(true);
        }
        else
        {
            System.err.println("Error while changing thread status");
        }
    }

    public void setAuto(String oilField, int eqNr, boolean on)
    {
        this.factoryInfo.setAuto(oilField, eqNr, on);
    }

    @Override
    public void defaultAction()
    {
        getFactoryStatus();
    }

    @Override
    public void reset()
    {
        for (int t = 0; t < 9; t++)
        {
            this.eqLabels[t].setText("---");
            this.statusLabels[t].setText("---");
        }
    }

    @Override
    public void reset(String oilField)
    {
        
    }

    private class AutoThread implements Runnable
    {
        @Override
        public void run()
        {
//            refreshRate = 2000;
            boolean interrupted = false;
            int times = 0;
            while (!interrupted && times < 30)
            {
                times++;
                try
                {
                    String[] registeredOilFields = factoryInfo.getOilFields();
                    System.out.println("GO");

                    for (String oilField: registeredOilFields)
                    {
                        getFactoryStatus(oilField);


                        Boolean[] auto = factoryInfo.getAuto(oilField);
                        String[] status = factoryInfo.getStatus(oilField);
System.out.println("+++++++++++++++++++++++++++++");
System.out.println(currOilField);
System.out.println(oilField);
System.out.println(java.util.Arrays.toString(status));
System.out.println(java.util.Arrays.toString(auto));
System.out.println("+++++++++++++++++++++++++++++");
                        

                        for (int a = 0; a < auto.length; a++)
                        {
                            if (auto[a] && status[a].equals("bereit"))
                            {
                                produce(a, oilField);
                            }
                        }

                        getFactoryStatus(currOilField);

                    }

                    Thread.sleep(refreshRate);
                    if (Thread.interrupted())
                    {
                        interrupted = true;
                    }
                }
                catch (InterruptedException ie)
                {
                    interrupted = true;
                }
            }
            threadIsRunning = false;
        }
    }
}
