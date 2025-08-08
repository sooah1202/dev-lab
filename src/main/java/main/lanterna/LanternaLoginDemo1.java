package main.lanterna;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

// 터미널을 직접 만들고 그 위에 스크린 생성
public class LanternaLoginDemo1 {
    public static void main(String[] args) throws IOException {
        // 터미널 직접 생성
        Terminal terminal = new DefaultTerminalFactory().createTerminal();

        // 터미널을 기반으로 스크린 생성
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();

        // GUI 구성
        MultiWindowTextGUI gui = new MultiWindowTextGUI(screen);
        BasicWindow window = new BasicWindow("Login");
        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(2));

        panel.addComponent(new Label("Username:"));
        TextBox username = new TextBox(new TerminalSize(20, 1));
        panel.addComponent(username);

        panel.addComponent(new Label("Password:"));
        TextBox password = new TextBox(new TerminalSize(20, 1)).setMask('*');
        panel.addComponent(password);

        panel.addComponent(new EmptySpace());
        panel.addComponent(new Button("Login", () -> {
            try {
                terminal.setForegroundColor(TextColor.ANSI.GREEN);
                terminal.setCursorPosition(0, 10);
                terminal.putString("Hello " + username.getText() + "! (From Terminal)");
                terminal.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));

        window.setComponent(panel);
        gui.addWindowAndWait(window);
        screen.stopScreen();
    }
}
