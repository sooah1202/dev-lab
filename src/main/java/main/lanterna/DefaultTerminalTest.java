package main.lanterna;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class DefaultTerminalTest {
	public static void main(String[] args) {
		try(Terminal terminal = new DefaultTerminalFactory().createTerminal()) {
			terminal.enterPrivateMode();
			terminal.setForegroundColor(TextColor.ANSI.CYAN);
			terminal.enableSGR(SGR.BOLD);

			String hello = "Hello Lanterna! Press ESC to exit.";
			for(char c : hello.toCharArray()) {
				terminal.putCharacter(c);
			}
			terminal.flush();

			while (true) {
				KeyStroke key = terminal.readInput();
				if(key.getKeyType() == KeyType.Escape) {
					break;
				} else if (key.getKeyType() == KeyType.Character) {
					terminal.putCharacter(key.getCharacter());
					terminal.flush();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
