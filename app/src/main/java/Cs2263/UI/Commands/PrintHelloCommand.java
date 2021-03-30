package Cs2263.UI.Commands;

public class PrintHelloCommand implements UICommand {


    @Override
    public void execute() {
        System.out.println("Hello World!");
    }
}
