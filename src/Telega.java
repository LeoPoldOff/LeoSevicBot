import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import static java.lang.Math.toIntExact;

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
	private static Bot bot = new Bot();

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
		if (e.hasMessage() && e.getMessage().hasText()) {
			String message_text = e.getMessage().getText();
			long chat_id = e.getMessage().getChatId();
			var response = bot.respond(message_text.toLowerCase());
			if (e.getMessage().getText().equals("help")) {
				SendMessage message = new SendMessage().setChatId(chat_id).setText("You send help");
				InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
				List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

				List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
				ListIterator<String> listIter = bot.currentCommandList().listIterator();
				for (int f = 0; f < bot.currentCommandList().size(); f += 1) {
					String element = listIter.next();
					keyboardButtonsRow1.add(new InlineKeyboardButton().setText(element).setCallbackData(element));
				}

				rowList.add(keyboardButtonsRow1);
				inlineKeyboardMarkup.setKeyboard(rowList);
				message.setReplyMarkup(inlineKeyboardMarkup);
				try {
					execute(message);
				} catch (TelegramApiException q) {
					q.printStackTrace();
				}

			} else {
				SendMessage message = new SendMessage().setChatId(chat_id).setText(response.userRespond);
				try {
					execute(message);
				} catch (TelegramApiException q) {
					q.printStackTrace();
				}
			}
		} else if (e.hasCallbackQuery()) {
			String call_data = e.getCallbackQuery().getData();
			long message_id = e.getCallbackQuery().getMessage().getMessageId();
			long chatId = e.getCallbackQuery().getMessage().getChatId();

			if (bot.currentCommandList().contains(call_data)) {
				String answer = bot.respond(call_data).userRespond;
				EditMessageText new_message = new EditMessageText().setChatId(chatId)
						.setMessageId(toIntExact(message_id)).setText(answer);
				try {
					execute(new_message);
				} catch (TelegramApiException v) {
					v.printStackTrace();
				}
			}
		}
	}

	@Override
	public String getBotToken() {
		return System.getenv("boringbot_token");
	}

}
