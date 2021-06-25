package com.avizhen.telegramBot;

import com.avizhen.entity.City;
import com.avizhen.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class TelegramBot extends TelegramLongPollingBot {
    private static final String TOKEN = "1706098844:AAEWPpsVoPLTkUmR2MrXCIjvR97BHGCDc-8";
    private static final String USERNAME = "misha_avizhen_tg_bot";

    private final CityService cityService;

    public TelegramBot(CityService cityService) {
        this.cityService = cityService;
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
            String text = update.getMessage().getText();
            City byName = cityService.findByName(text);
            String info = byName.getInfo();

            try {
                execute(new SendMessage(chatId, info));

            } catch (TelegramApiException e) {
                e.printStackTrace();

            }

        }

    }
}
