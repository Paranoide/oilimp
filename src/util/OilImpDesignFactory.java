package util;

import java.awt.Font;
import java.awt.Color;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.border.*;

/**
 *
 * @author Paranoide
 */
public class OilImpDesignFactory
{
    public static final Font FONT_CALIBRI_22_B = new Font("Calibri", Font.BOLD, 22);
    public static final Font FONT_ROMAN_14 = new Font("Times New Roman", Font.PLAIN, 14);
    public static final Font FONT_ARIAL_18 = new Font("Arial", Font.PLAIN, 18);
    public static final Font FONT_COMIC_18_BI = new Font("Comic Sans MS", Font.ITALIC | Font.BOLD, 18);
    public static final Font FONT_CNEW_12 = new Font("Courier New", Font.PLAIN, 12);
    public static final Font FONT_OCR_18 = new Font("OCR A Extended", Font.PLAIN, 18);

    public static final Font BORDER_FONT = FONT_CALIBRI_22_B;
    public static final Font BORDER_SMALL_FONT = FONT_ROMAN_14;
    public static final Font LABEL_FONT = FONT_ARIAL_18;
    public static final Font LOG_FONT = FONT_CNEW_12;
    public static final Font HEADLINE_FONT = FONT_COMIC_18_BI;
    public static final Font NUMBER_FONT = FONT_OCR_18;

    public static final Color backgroundColor = new Color(32, 188, 255);

    public static TitledBorder createBorder(String title, Font font, int pos)
    {
        Border lb = BorderFactory.createLineBorder(Color.BLACK, 1, true);


        TitledBorder tb = BorderFactory.createTitledBorder(
                          //BorderFactory.createEmptyBorder(10,10,10,10),
                          lb,
                          title,
                          pos == 1 ? TitledBorder.LEADING : TitledBorder.CENTER,
                          TitledBorder.TOP,
                          font);

        return tb;
    }
    
    public static String formatBigNumber(double value)
    {
      String pattern = "###,###";
      DecimalFormat myFormatter = new DecimalFormat(pattern);
      String output = myFormatter.format(value);
      return output;
   }
}
