import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	private static HashMap<Long, Bot> bots = new HashMap<>();
	private static Logger log = Logger.getLogger(Telegram.class.getName());

	public static void main(String[] args) {
		ApiContextInitializer.init();
		TelegramBotsApi botapi = new TelegramBotsApi();
		try {
			botapi.registerBot(new Telegram());
		} catch (TelegramApiException e) {
			log.log(Level.SEVERE, "Exception: ", e);
		}
	}

	@Override
	public String getBotUsername() {
		return "boring_life_bot";
	}

	private Bot getBot(Update update) {
		long chatId = 0;
		if (update.hasMessage())
			chatId = update.getMessage().getChatId();
		else if (update.hasCallbackQuery())
			chatId = update.getCallbackQuery().getMessage().getChatId();

		Bot bot = bots.putIfAbsent(chatId, new Bot());

		return bot;
	}

	@Override
	public void onUpdateReceived(Update update) {
		var bot = getBot(update);
		if (update.hasMessage() && update.getMessage().hasText()) {

			String messageText = update.getMessage().getText();
			long chatId = update.getMessage().getChatId();
			var response = bot.respond(messageText, Long.toString(chatId));
			if (update.getMessage().getText().toLowerCase().equals("help")
					|| update.getMessage().getText().toLowerCase().equals("/help")) {
				SendMessage message = new SendMessage().setChatId(chatId).setText("You send help");
				InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
				List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
				for (int i = 0; i < 9; i++) {
					List<InlineKeyboardButton> button = new ArrayList<>();
					rowList.add(button);
				}
				for (int f = 0; f < bot.currentCommandList(update.getMessage().getChatId().toString()).size(); f += 1) {
					String element = bot.currentCommandList(update.getMessage().getChatId().toString()).get(f);
					rowList.get(f).add(new InlineKeyboardButton().setText(element).setCallbackData(element));
				}
				inlineKeyboardMarkup.setKeyboard(rowList);
				message.setReplyMarkup(inlineKeyboardMarkup);
				try {
					execute(message);
				} catch (TelegramApiException q) {
					log.log(Level.SEVERE, "Exception: ", q);
				}

			} else {
				SendMessage message = new SendMessage().setChatId(chatId).setText(response.userRespond);
				try {
					execute(message);
				} catch (TelegramApiException q) {
					log.log(Level.SEVERE, "Exception: ", q);
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
					log.log(Level.SEVERE, "Exception: ", v);
				}
			}
		}
	}

	@Override
	public String getBotToken() {
		return System.getenv("boringbot_token");
	}

}
