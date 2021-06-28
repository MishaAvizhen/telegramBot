package com.avizhen;

import com.avizhen.service.TgBotService;
import com.avizhen.telegramBot.TelegramBot;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

    }

    @Bean
    public CommandLineRunner registerTgBot(TgBotService tgBotService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {

                TelegramBot telegramBot = new TelegramBot(tgBotService);
                TelegramBotsApi telegramBotsApi;

                try {
                    telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
                    telegramBotsApi.registerBot(telegramBot);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
