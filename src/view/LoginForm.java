package view;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

import static util.OilImpDesignFactory.*;

/**
 *
 * @author Paranoide
 */
public class LoginForm extends JFrame
{

    private JPanel mainPanel;
    private TitledBorder mainPanelBorder;

    private JLabel headline;

    private JPanel contentPanel;
    private JLabel usernameLbl;
    private JLabel passwdLbl;
    private JTextField usernameTxt;
    private JPasswordField passwdTxt;

    private JButton loginBtn;

    private String passwdPlain = "";


    public LoginForm()
    {
        this.setSize(500, 220);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        this.mainPanel = new JPanel(new BorderLayout(5, 5));
        this.mainPanelBorder = createBorder("Login", BORDER_FONT, 0);
        this.mainPanel.setBorder(this.mainPanelBorder);

        this.headline = new JLabel("Please enter your name and password:");
        this.mainPanel.add(this.headline, BorderLayout.NORTH);

        this.contentPanel = new JPanel(new GridLayout(2, 2, 5, 5));


        this.usernameLbl = new JLabel("Username:");
        this.usernameLbl.setFont(LABEL_FONT);

        this.passwdLbl = new JLabel("Password:");
        this.passwdLbl.setFont(LABEL_FONT);

        this.usernameTxt = new JTextField("");
        this.usernameTxt.setFont(LABEL_FONT);

        this.passwdTxt = new JPasswordField("");
        this.passwdTxt.setFont(LABEL_FONT);


        this.contentPanel.add(this.surroundWithEmptyBorder(this.usernameLbl));
        this.contentPanel.add(this.surroundWithEmptyBorder(this.usernameTxt));
        this.contentPanel.add(this.surroundWithEmptyBorder(this.passwdLbl));
        this.contentPanel.add(this.surroundWithEmptyBorder(this.passwdTxt));


        this.loginBtn = new JButton("Login");
        this.mainPanel.add(this.loginBtn, BorderLayout.SOUTH);


        this.mainPanel.add(this.contentPanel, BorderLayout.CENTER);

        this.add(this.mainPanel);

    }

    private JPanel surroundWithEmptyBorder(Component comp)
    {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setLayout(new GridLayout(1,1));
        panel.add(comp);
        return panel;
    }

    public String[] getData()
    {
        return new String[]{this.usernameTxt.getText(),
                            this.passwdTxt.getText()};
    }

    public void addLoginListener(ActionListener al)
    {
        this.loginBtn.addActionListener(al);
    }

    public static void main(String[] args)
    {
        LoginForm lf = new LoginForm();
        lf.setVisible(true);
    }
}
