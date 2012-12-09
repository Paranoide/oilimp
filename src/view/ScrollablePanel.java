package view;

import java.awt.*;
import javax.swing.*;

/**
 * A panel that implements the Scrollable interface. This class allows you to
 * customize the scrollable features by using newly provided setter methods so
 * you don't have to extend this class every time.
 *
 */
public class ScrollablePanel extends JPanel implements Scrollable
{
    public ScrollablePanel()
    {
        super();
    }
    
    public ScrollablePanel(LayoutManager manager)
    {
        super(manager);
    }
    
    
    @Override
    public Dimension getPreferredScrollableViewportSize()
    {
        return this.getPreferredSize();
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction)
    {
        return 30;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction)
    {
        return 30;
    }

    @Override
    public boolean getScrollableTracksViewportWidth()
    {
        return true;
    }

    @Override
    public boolean getScrollableTracksViewportHeight()
    {
        return false;
    }
}