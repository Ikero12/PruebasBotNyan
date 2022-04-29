import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class Main {

    public static void main(String[] args) {
       ConexionDiscord nyanbot = new ConexionDiscord();
       Msgs mensajes = new Msgs();
       mensajes.messages();
        nyanbot.msgEmbed();

        try {
            nyanbot.driveImg();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
        try {
            nyanbot.driveimagen();
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        nyanbot.disconnect();
























































































        /*final String token = "OTU0MjkyMDEyNjU0NDAzNjA0.YjQ_sQ.eDhsNaiLK6-xzhoDeLP6QcCnRt4";
        final DiscordClient client = DiscordClient.create(token);
        final GatewayDiscordClient gateway = client.login().block();*/


       /* gateway.on(MessageCreateEvent.class).subscribe(event -> {
            final Message message = event.getMessage();
            if ("!ping".equals(message.getContent())) {
                final MessageChannel channel = message.getChannel().block();
                channel.createMessage("Pong!").block();
            }
        });

        gateway.on(MessageCreateEvent.class).subscribe(event -> {
            final Message message = event.getMessage();
            if ("Hola".equals(message.getContent())) {
                final MessageChannel channel = message.getChannel().block();
                channel.createMessage("Nya").block();
            }
        });*/

        //gateway.onDisconnect().block();


    }
    }

