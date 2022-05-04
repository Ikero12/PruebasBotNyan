import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.spec.EmbedCreateSpec;

import java.time.Instant;
import java.util.ArrayList;

public class Msgs extends ConexionDiscord {

    public void messages(){                                                                 //Mensajes de discord simples
        ConexionDiscord msj = new ConexionDiscord();
        msj.Mensaje("Das pena bot","nya");
        msj.Mensaje("hola","ey!");
        msj.Mensaje("que tal?","nyanyanyanyanyanyanyanyanyanyanyanya");
        msj.Mensaje("stop","Stopped");
        msj.Mensaje("rly?","Im sure it is real");
        msj.Mensaje("callate","nya");
        msj.Mensaje("no insultes","nya");
        msj.Mensaje("damn","Wassup dog");


    }

    public void commandlist(){                                                                                                              //Lista de comandos
        super.commands("comandos","Para ver la funcionalidad de los comandos ponga !command + el comando deseado." +
                "\n\n1. !embed"+
                "\n2. !ping"+
                "\n3. !hola"+
                "\n4. !damn"






        );
        super.commands("embed","Este comando muestra el embed por defecto, sin información alguna.(Herramienta de desarrollador)");
        super.commands("ping", "Devuelve el mensaje de texto '!pong' ");
        super.commands("hola", "Devuelve el mensaje de texto: 'ey!'");
        super.commands("damn", "Devuelve el mensaje de texto: 'Wassup dog'");
        super.commands("images","Devuelve la lista de las imagenes subidas al drive.");
    }

}
