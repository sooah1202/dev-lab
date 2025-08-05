package main.lanterna;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class LanternaGuiDemo {
    public static void main(String[] args) {

        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            Screen screen = new TerminalScreen(terminal);
            screen.startScreen();

            MultiWindowTextGUI gui = new MultiWindowTextGUI(screen);

            BasicWindow basicWindow = new BasicWindow("Lanterna GUI Demo");

            TextBox textBox = new TextBox(new TerminalSize(30, 1));
            Button button = new Button("Print & Close", () -> {
                System.out.println("input : " + textBox.getText());
                basicWindow.close();
            });

            Panel panel = new Panel(new LinearLayout(Direction.VERTICAL));
            panel.addComponent(new Label("first input and push the button. : "));
            panel.addComponent(textBox);
            panel.addComponent(button);

            basicWindow.setComponent(panel);

            gui.addWindowAndWait(basicWindow);

            screen.stopScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
