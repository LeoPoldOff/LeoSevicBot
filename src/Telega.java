import java.util.Scanner;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class Telega extends TelegramLongPollingBot {
	private Bot bot = new Bot();

	public static void main(String[] args) {
		ApiContextInitializer.init();
		TelegramBotsApi botapi = new TelegramBotsApi();
		try {
			botapi.registerBot(new Telega());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getBotUsername() {
		return "boring_life_bot";
	}

	@Override
	public void onUpdateReceived(Update e) {
		Message msg = e.getMessage();
		String text = msg.getText();
		var response = bot.respond(text.toLowerCase());
		if (!response.userRespond.equals(""))
			sendMsg(msg, response.userRespond);
	}
		
	
	private void sendMsg(Message msg, String text) {
		SendMessage s = new SendMessage();
		s.setChatId(msg.getChatId());
		s.setText(text);
		try { 
			execute(s);
		} catch (TelegramApiException e){
			e.printStackTrace();
		}
	}

	@Override
	public String getBotToken() {
		return System.getenv("boringbot_token");
	}

}
