import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static java.lang.Math.toIntExact;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class Telegram extends TelegramLongPollingBot {
	private static Bot bot = new Bot();

	public static void main(String[] args) {
		ApiContextInitializer.init();
		TelegramBotsApi botapi = new TelegramBotsApi();
		try {
			botapi.registerBot(new Telegram());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getBotUsername() {
		return "boring_life_bot";
	}

	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasMessage() && update.getMessage().hasText()) {
			
			String messageText = update.getMessage().getText();
			long chatId = update.getMessage().getChatId();
			var response = bot.respond(messageText, Long.toString(chatId));
			if (update.getMessage().getText().equals("help") || update.getMessage().getText().equals("Help")
					|| update.getMessage().getText().equals("/help")) {
				SendMessage message = new SendMessage().setChatId(chatId).setText("You send help");
				InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
				List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
				List<InlineKeyboardButton> keyboardButtonsRow0 = new ArrayList<>();
				List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
				List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
				List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
				List<InlineKeyboardButton> keyboardButtonsRow4 = new ArrayList<>();
				List<InlineKeyboardButton> keyboardButtonsRow5 = new ArrayList<>();
				List<InlineKeyboardButton> keyboardButtonsRow6 = new ArrayList<>();
				List<InlineKeyboardButton> keyboardButtonsRow7 = new ArrayList<>();
				List<InlineKeyboardButton> keyboardButtonsRow8 = new ArrayList<>();
				rowList.add(keyboardButtonsRow0);
				rowList.add(keyboardButtonsRow1);
				rowList.add(keyboardButtonsRow2);
				rowList.add(keyboardButtonsRow3);
				rowList.add(keyboardButtonsRow4);
				rowList.add(keyboardButtonsRow5);
				rowList.add(keyboardButtonsRow6);
				rowList.add(keyboardButtonsRow7);
				rowList.add(keyboardButtonsRow8);
				ListIterator<String> listIter = bot.currentCommandList(update.getMessage().getChatId().toString())
						.listIterator();
				for (int f = 0; f < bot.currentCommandList(update.getMessage().getChatId().toString()).size(); f += 1) {
					String element = listIter.next();
					rowList.get(f).add(new InlineKeyboardButton().setText(element).setCallbackData(element));
				}
				inlineKeyboardMarkup.setKeyboard(rowList);
				message.setReplyMarkup(inlineKeyboardMarkup);
				try {
					execute(message);
				} catch (TelegramApiException q) {
					q.printStackTrace();
				}

			} else {
				SendMessage message = new SendMessage().setChatId(chatId).setText(response.userRespond);
				try {
					execute(message);
				} catch (TelegramApiException q) {
					q.printStackTrace();
				}
			}
		} else if (update.hasCallbackQuery()) {
			
			String callData = update.getCallbackQuery().getData();
			long messageId = update.getCallbackQuery().getMessage().getMessageId();
			long chatId = update.getCallbackQuery().getMessage().getChatId();

			if (bot.currentCommandList(Long.toString(chatId)).contains(callData)) {
				String answer = bot.respond(callData, Long.toString(chatId)).userRespond;
				EditMessageText newMessage = new EditMessageText().setChatId(chatId).setMessageId(toIntExact(messageId))
						.setText(answer);
				try {
					execute(newMessage);
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
