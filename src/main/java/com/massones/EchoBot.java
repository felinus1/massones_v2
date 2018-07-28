package com.massones;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

//@author Félix Yubero
//Clase para la lógica de nuestro Bot, TelegramLongPollingBot pregunta a Telegram por nuevas actualizaciones
public class EchoBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(final Update update) {
        // Esta función se invocará cuando nuestro bot reciba un mensaje
        System.out.println(update.getMessage().getFrom().getUserName());
        
        String nombreUsuario = update.getMessage().getFrom().getUserName();
        String nombre = update.getMessage().getFrom().getFirstName();
        String apellido = update.getMessage().getFrom().getLastName();
        Integer idUsuario = update.getMessage().getFrom().getId();
        
        // Se obtiene el mensaje escrito por el usuario
        final String messageTextReceived = update.getMessage().getText();
        
        System.out.println(messageTextReceived);

        // Se obtiene el id de chat del usuario
        final long chatId = update.getMessage().getChatId();
        // Se crea un objeto mensaje
        SendMessage message = new SendMessage().setChatId(chatId);
       /* if(nombreUsuario != null) {
            message = message.setText("Ha hablado " + nombreUsuario);
        } else {
            message = message.setText("Ha hablado " + Integer.toString(idUsuario));
        }*/
        
        if(messageTextReceived.equalsIgnoreCase("Fer")) {
            //String mensaje = message.toString().concat("\n Siempre va a lo suyo, él es así");
            message =  message.setText("\n Siempre va a lo suyo, él es así");
        } else if(messageTextReceived.equalsIgnoreCase("Jose")) {
            message = message.setText("Jose es el más fichas del mundo");
        } 
        else if(messageTextReceived.equalsIgnoreCase("agresivo"))
        {
           message = message.setText("Buena definición para Sergio"); 
        }
        else {
            message = message.setText(messageTextReceived);
        }
        
        if(messageTextReceived.equalsIgnoreCase("pole")) {
            message = message.setText("El usuario ha hecho la pole");
        }

        try {
            // Se envía el mensaje
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        // Se devuelve el nombre que dimos al bot al crearlo con el BotFather
        return "massones";
    }

    @Override
    public String getBotToken() {
        // Se devuelve el token que nos generó el BotFather de nuestro bot
        return "696054031:AAE9_Kgq2HXQifzi4lp6hA66qC1FXKI9spY";
    }
}
