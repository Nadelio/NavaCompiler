package src.Extensions;

import src.Debugger;
import src.NavaCompiler;
import src.NavaHook;
import javax.swing.JFrame;

public class WindowExtension extends NavaHook{

    public WindowExtension(){super("WindowExtension", new String[]{});}

    public void process(){
        Debugger.log("Window Extension Hook Process");
        int[] compilerVariables = NavaCompiler.compilerIntVariables;
        if(compilerVariables[0] == 1){
            int[] windowSize = {compilerVariables[1], compilerVariables[2]};
            Debugger.log(compilerVariables[1] + " " + compilerVariables[2]);
            JFrame frame = new JFrame("Nava Compiler");
            frame.setSize(windowSize[0], windowSize[1]);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            compilerVariables[0]++;
        } else if(compilerVariables[0] == 0){
            Debugger.log("Window is inactive");
        } else {
            Debugger.log("Window Extension has been activated");
        }
    }
}
