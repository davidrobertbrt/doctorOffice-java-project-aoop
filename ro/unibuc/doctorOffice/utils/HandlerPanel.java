package ro.unibuc.doctorOffice.utils;

public class HandlerPanel
{
    public static boolean signalExit = false;
    private static Panel crPanel;

    public HandlerPanel()
    {
        this.crPanel = new MainPanel();
    }

    public static void setPanel(Panel p)
    {
        crPanel = p;
    }

    public void execute()
    {
        crPanel.execute();
    }
}
