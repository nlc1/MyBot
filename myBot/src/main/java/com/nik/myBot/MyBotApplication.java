package com.nik.myBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class MyBotApplication {

	public static void main(String[] args) {
		// Запуск Spring Boot приложения
		SpringApplication.run(MyBotApplication.class, args);

		// Попытка создать экземпляр TelegramBotsApi и зарегистрировать нашего бота
		try {
			TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
			botsApi.registerBot(new MyBot());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}
