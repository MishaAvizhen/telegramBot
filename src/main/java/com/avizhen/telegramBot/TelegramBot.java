package com.avizhen.telegramBot;

import com.avizhen.service.TgBotService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class TelegramBot extends TelegramLongPollingBot {
    private static final String TOKEN = "1706098844:AAEWPpsVoPLTkUmR2MrXCIjvR97BHGCDc-8";
    private static final String USERNAME = "misha_avizhen_tg_bot";

    private TgBotService tgBotService;


    public TelegramBot(TgBotService tgBotService) {
        this.tgBotService = tgBotService;
    }

    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage() != null && update.getMessage().hasText()) {
            String chatId = String.valueOf(update.getMessage().getChatId());
            String cityName = update.getMessage().getText();
            String info = tgBotService.showCityInfo(cityName);

            try {
                execute(new SendMessage(chatId, info));

            } catch (TelegramApiException e) {
                e.printStackTrace();

            }

        }

    }
}
