package ro.unibuc.doctorOffice.utils;
import java.util.Scanner;


public abstract class Panel
{
    protected Scanner scanner = new Scanner(System.in);

    protected abstract void execute();
}
