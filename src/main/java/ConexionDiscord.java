import discord4j.common.util.Snowflake;
import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.Channel;
import discord4j.core.object.entity.channel.Channel.Type;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.object.entity.channel.TextChannel;
import discord4j.core.retriever.EntityRetrievalStrategy;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.core.spec.MessageCreateMono;
import discord4j.core.spec.MessageCreateSpec;
import discord4j.core.spec.legacy.LegacyMessageCreateSpec;
import discord4j.rest.entity.RestChannel;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Optional;
import java.util.function.Consumer;

public class ConexionDiscord {
    static final String token = "OTU0MjkyMDEyNjU0NDAzNjA0.YjQ_sQ.eDhsNaiLK6-xzhoDeLP6QcCnRt4";
    static final DiscordClient client = DiscordClient.create(token);
    static final GatewayDiscordClient gateway = client.login().block();

    public ConexionDiscord() {
    }

    public void Mensaje(String mensajerecibido, String mensajeenviado){

        gateway.on(MessageCreateEvent.class).subscribe(event -> {
            final Message message = event.getMessage();
            if (mensajerecibido.equalsIgnoreCase(message.getContent())) {
                final MessageChannel channel = message.getChannel().block();
                channel.createMessage(mensajeenviado).block();
            }
        });
    }

  public void msgEmbed() {
      String IMAGE_URL = "https://c.tenor.com/v9sdELSzVw4AAAAC/nyan-cat-kawaii.gif";
      String AUTOR_URL = "https://c.tenor.com/v9sdELSzVw4AAAAC/nyan-cat-kawaii.gif";
      String ANY_URL = "https://www.youtube.com/watch?v=1_VCp8Y8lU0";
      gateway.on(MessageCreateEvent.class).subscribe(event -> {
          final Message message = event.getMessage();
          if ("!embed".equalsIgnoreCase(message.getContent())) {
              final MessageChannel channel = message.getChannel().block();
              //final MessageChannel channel = message.getLastMessage().block();


              EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
              builder.author("setAuthor", ANY_URL, AUTOR_URL);
              builder.image(IMAGE_URL);
              builder.title("Buen pana");
              builder.url(ANY_URL);
              builder.description("\n" +
                      "big D: is setImage\n" +
                      "small D: is setThumbnail\n" +
                      "<-- setColor");
              builder.addField("addField", "inline = true", true);
              builder.addField("addFIeld", "inline = true", true);
              builder.addField("addFile", "inline = false", false);
              builder.thumbnail(IMAGE_URL);
              builder.footer("setFooter --> setTimestamp", IMAGE_URL);
              builder.timestamp(Instant.now());
              channel.createMessage(builder.build()).block();
              //channel.createMessage().block();
          }
      });




  }
    public void disconnect(){
        gateway.onDisconnect().block();
    }
}
