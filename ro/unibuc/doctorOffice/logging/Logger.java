package ro.unibuc.doctorOffice.logging;

public interface Logger
{
    void logInfo(String message);
    void logWarn(String message);
    void logError(String message);

}
