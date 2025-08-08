package main.lanterna;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;

public class LanternaLoginDemo2 {
    public static void main(String[] args) throws IOException {
        // 터미널 생성 없이 바로 스크린 생성
        Screen screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();

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
            // 여기서는 terminal 객체 접근 불가 → GUI 컴포넌트로만 메시지 표시 가능
            MessageDialog.showMessageDialog(gui, "Info", "Hello " + username.getText() + "!");
        }));

        window.setComponent(panel);
        gui.addWindowAndWait(window);
        screen.stopScreen();
    }
}
