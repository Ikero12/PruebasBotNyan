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
import discord4j.rest.util.Color;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.io.IOException;
import java.time.Instant;
import java.util.Optional;
import java.util.function.Consumer;

public class ConexionDiscord {
    static final String token = "OTU0MjkyMDEyNjU0NDAzNjA0.YjQ_sQ.umdV8pwa5bjBpJHlr6jVjOTb4QM";
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


    public List<File> driveImg () throws IOException,GeneralSecurityException{
        //Variables estáticas finales
        final String CREDENTIALS_FILE_PATH = "/credentials.json";
        final String APPLICATION_NAME = "Google Drive API Java Quickstart";
        final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
        final String TOKENS_DIRECTORY_PATH = "tokens";
        final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE_METADATA_READONLY);
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, this.getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        // Print the names and IDs for up to 10 files.
        FileList result = service.files().list()
                .setQ( "mimeType = 'image/jpeg' and '1dT7al515axCO-6taVZXDFnTfVTlq-C3x' in parents ")
                .setSpaces("drive")
                .setFields("nextPageToken, files(id, name)")
                .execute();
        List<File> files = result.getFiles();

        return files;

    }

    public void driveimagen() throws GeneralSecurityException, IOException {
        List<File>files = this.driveImg();

        EmbedCreateSpec embed;
        String fileList = "";
        for (File file : files) {
            fileList=fileList+"\n"+file.getName()+file.getId();

        }

        embed = EmbedCreateSpec.builder()
                .color(Color.SEA_GREEN)
                .title("Lista de imagenes")
                .description(fileList)
                .build();


        gateway.on(MessageCreateEvent.class).subscribe(event -> {
            final Message message = event.getMessage();
            if ("!images".equalsIgnoreCase(message.getContent())) {
                final MessageChannel channel = message.getChannel().block();
                channel.createMessage(embed).block();



    }});}


    public Credential getCredentials(NetHttpTransport HTTP_TRANSPORT) throws IOException,GeneralSecurityException {
        //Variables estáticas finales
        final String CREDENTIALS_FILE_PATH = "/credentials.json";
        final String APPLICATION_NAME = "Google Drive API Java Quickstart";
        final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
        final String TOKENS_DIRECTORY_PATH = "tokens";
        final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
        //final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        // Load client secrets.
        InputStream in = DriveQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        //returns an authorized Credential object.
        return credential;
    }



    public void disconnect(){
        gateway.onDisconnect().block();
    }
}
