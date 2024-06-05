package com.nik.myBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MyBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "Zaymooobot";
    }

    @Override
    public String getBotToken() {
        return "7314496491:AAE8Y5BheE57das7xShAbbbpRyDSnkr3akM";
    }

    private static final String VISITORS_FILE = "D:\\Project\\myBot\\myBot\\src\\main\\java\\com\\nik\\myBot\\visitors.txt";

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            String userName = update.getMessage().getFrom().getUserName();

            logVisitor(userName);  // Логируем посетителя

            if (messageText.startsWith("/")) {
                handleCommand(messageText, chatId);
            } else {
                handleCountrySelection(messageText, chatId);
            }
        }
    }

    private void handleCommand(String messageText, long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);

        switch (messageText.split(" ")[0]) {
            case "/start":
                message.setText("Привет, я Бот 🤖 который поможет получить Займ 💸\nНе хватает финансов? Я выручу!\nВыберите страну для займа👇🏻");
                message.setReplyMarkup(getCountrySelectionKeyboard());
                break;
            case "/help":
                message.setText("/start - Начало работы\n/help - Список команд\n/more - Получить еще один вариант");
                break;
            case "/more":
                handleMoreCommand(message);
                break;
            default:
                message.setText("Неизвестная команда. Используйте /help для списка доступных команд.");
                break;
        }

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void handleCountrySelection(String selectedCountry, long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.enableHtml(true);

        switch (selectedCountry) {
            case "🇷🇺РОССИЯ🇷🇺":
                message.setText("💳 Займы для Граждан Российской Федерации 🇷🇺:\n\n" +
                        "Займер - Акция «Первый займ под 0%»\n" +
                        "➡️ <a href=\"https://123.com\">https://123.com</a>\u200B\n\n" +
                        "MoneyMan - Первый займ под 0%\n" +
                        "➡️ <a href=\"https://123.com\">https://123.com</a>\n\n" +
                        "Lime-zaim - 20.000₽ под 0% на 15 дней\n" +
                        "➡️ <a href=\"https://123.com\">https://123.com</a>\n\n" +
                        "OneClickMoney - Первый займ до 30.000₽\n" +
                        "➡️ <a href=\"https://123.com\">https://123.com</a>\n\n" +
                        "КнопкаДеньги - 30.000₽ под 0%\n" +
                        "➡️ <a href=\"https://123.com\">https://123.com</a>\n\n" +
                        "Надо Денег - первый займ от 1000 до 30000 руб без переплат\n" +
                        "➡️ <a href=\"https://123.com\">https://123.com</a>\n\n" +
                        "ГринМани - До 30.000₽ на первый займ под 0%\n" +
                        "➡️ <a href=\"https://123.com\">https://123.com</a>\n\n" +
                        "PayPs - 10.000₽ под 0%\n" +
                        "➡️ <a href=\"https://123.com\">https://123.com</a>\n\n" +
                        "БелкаКредит - Займ под 0%\n" +
                        "➡️ <a href=\"https://123.com\">https://123.com</a>\n\n" +
                        "Е-Капуста - первый займ до 50 000 руб.\n" +
                        "➡️ <a href=\"https://123.com\">https://123.com</a>"
                );
                break;
            case "🇰🇿КАЗАХСТАН🇰🇿":
                message.setText("💳 Займы для Граждан Казахстана 🇰🇿:\n\n" +
                        "Займер - Автоматическое одобрение 70 000 тенге\n" +
                        "➡️ <a href=\"https://123.com\">https://123.com</a>\n\n" +
                        "Koke kz - До 145.000 тенге без переплат\n" +
                        "➡️ <a href=\"https://123.com\">https://123.com</a>\n\n" +
                        "TengoKZ - Первый займ под 0.01%\n" +
                        "➡️ <a href=\"https://123.com\">https://123.com</a>\n\n" +
                        "Hava - Первый займ под 0.01%\n" +
                        "➡️ <a href=\"https://123.com\">https://123.com</a>\n\n" +
                        "Tomi - Микрокредит под 0% до 145.000 тенге\n" +
                        "➡️ <a href=\"https://123.com\">https://123.com</a>\n\n" +
                        "OneCredit - до 170.000 тенге без %\n" +
                        "➡️ <a href=\"https://123.com\">https://123.com</a>"
                );
                break;
            default:
                message.setText("Неизвестная команда. Пожалуйста, выберите страну используя клавиатуру.");
                break;
        }

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private ReplyKeyboardMarkup getCountrySelectionKeyboard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add(new KeyboardButton("🇷🇺РОССИЯ🇷🇺"));
        keyboard.add(keyboardRow1);

        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow2.add(new KeyboardButton("🇰🇿КАЗАХСТАН🇰🇿"));
        keyboard.add(keyboardRow2);

        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;
    }

    private void handleMoreCommand(SendMessage message) {
        message.setText("Функция /more пока не реализована.");
    }

    private void logVisitor(String userName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(VISITORS_FILE, true))) {
            String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.write("User: " + userName + " Time: " + currentTime);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


