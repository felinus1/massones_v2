/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.massones;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.PhotoSize;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 *
 * @author Felix
 */
public class PhotoBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) { //Se comprueba si el mensaje contiene un objeto de foto

        if (update.hasMessage() && update.getMessage().hasText()) { //El mensaje contiene un texto
            // Variables
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            if (message_text.equals("/start")) {
                SendMessage message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText(message_text);
                try {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (message_text.equals("/zapaEdu")) {
                SendPhoto msg = new SendPhoto()
                        .setChatId(chat_id)
                        .setPhoto("AgADBAADIq0xG-S7wVLyGWdhf6iB3R8zoBoABKho-PdFV0bCUz8BAAEC")
                        .setCaption("Zapatillas de la Roca d'or");
                try {
                    sendPhoto(msg); // Call method to send the photo
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (message_text.equals("/teclado")) {
                SendMessage message = new SendMessage() //Enviamos mensaje de teclado
                        .setChatId(chat_id)
                        .setText("Teclado personalizado");
                //Se crea un objeto para el teclado
                ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                //Creamos la lista de valores para el teclado en un array personalizado
                List<KeyboardRow> keyboard = new ArrayList<>();
                //Se cra una nueva fila
                KeyboardRow row = new KeyboardRow();
                //Se establece cada botón
                row.add("/Fer");
                row.add("/Jose");
                row.add("/laRoca");
                //añadimos la primera fila al teclado
                keyboard.add(row);
                // Create another keyboard row
                row = new KeyboardRow();
                // Set each button for the second line
                row.add("/alvaro");
                row.add("/zapaEdu");
                row.add("/ocultarTeclado");
                //Añadimos la segunda fila
                keyboard.add(row);
                // Set the keyboard to the markup
                keyboardMarkup.setKeyboard(keyboard);
                // Add it to the message
                message.setReplyMarkup(keyboardMarkup);
                try {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (message_text.equals("/ocultarTeclado")) {
                SendMessage msg = new SendMessage()
                        .setChatId(chat_id)
                        .setText("Teclado oculto");
                ReplyKeyboardRemove keyboardMarkup = new ReplyKeyboardRemove();
                msg.setReplyMarkup(keyboardMarkup);
                sendPhoto(msg); // Llama al método para enviar la foto, cuidado aquí que no hay catch
            } else if (message_text.equalsIgnoreCase("/Fer")) {
                SendMessage mensaje = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText("Siempre va a lo suyo, él es así");
                try {
                    execute(mensaje); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } 
            else if (message_text.equalsIgnoreCase("/Jose")) {
                SendMessage mensaje = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText("El más fichas del mundo");
                try {
                    execute(mensaje); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } 
            else if (message_text.equalsIgnoreCase("/laRoca")) {
                SendMessage mensaje = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText("¿Es Fer? ¿Es Edu? ES EDUARRRRRDO");
                try {
                    execute(mensaje); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } 
            else if (message_text.equalsIgnoreCase("/alvaro")) {
                SendMessage mensaje = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText("EL corrista voladorrrr");
                try {
                    execute(mensaje); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } 
            else
            {
                SendMessage message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText("Unknown command");
                try {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        } else if (update.hasMessage() && update.getMessage().hasPhoto()) { //El mensaje contiene una foto
            //Variables
            long chat_id = update.getMessage().getChatId();

            List<PhotoSize> photos = update.getMessage().getPhoto();
            String f_id = photos.stream()
                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                    .findFirst()
                    .orElse(null).getFileId();
            int f_width = photos.stream()
                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                    .findFirst()
                    .orElse(null).getWidth();
            int f_height = photos.stream()
                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                    .findFirst()
                    .orElse(null).getHeight();
            String caption = "file_id: " + f_id + "\nwidth: " + Integer.toString(f_width) + "\nheight: " + Integer.toString(f_height);
            SendPhoto msg = new SendPhoto()
                    .setChatId(chat_id)
                    .setPhoto(f_id)
                    .setCaption(caption);
            try {
                sendPhoto(msg); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if(update.hasMessage() && update.getMessage().hasVideo()) {
            
        }
    }

    @Override
    public String getBotUsername() {
        // Return bot username
        // If bot username is @MyAmazingBot, it must return 'MyAmazingBot'
        return "PhotoBot";
    }

    @Override
    public String getBotToken() {
        // Return bot token from BotFather
        return "696054031:AAE9_Kgq2HXQifzi4lp6hA66qC1FXKI9spY";
    }

    private void sendPhoto(SendMessage msg) {
        throw new UnsupportedOperationException("No "); //To change body of generated methods, choose Tools | Templates.
    }
}
