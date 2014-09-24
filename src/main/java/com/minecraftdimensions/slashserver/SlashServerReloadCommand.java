package com.minecraftdimensions.slashserver;

import com.minecraftdimensions.slashserver.configlibrary.Config;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;

import java.io.File;

/**
 * User: Bloodsplat
 * Date: 13/11/13
 */
public class SlashServerReloadCommand extends Command {
    String configpath = File.separator + "plugins" + File.separator + "SlashServer" + File.separator + "config.yml";

    public SlashServerReloadCommand( String name, String permission, String... aliases ) {
        super( name, permission, aliases );
    }

    @Override
    public void execute( CommandSender sender, String[] args ) {

        SlashServer.time.clear();
        SlashServer.c = new Config( configpath );
        for(String data: ProxyServer.getInstance().getServers().keySet()){
            SlashServer.time.put( data, SlashServer.c.getInt( data, 0 ) );
        }
        SlashServer.ALREADY_ON_SERVER = SlashServer.color(SlashServer.c.getString( "ALREADY_ON_SERVER", "&cYou are already on that server!" ));
        SlashServer.TELEPORTING = SlashServer.color(SlashServer.c.getString( "TELEPORTING", "&2Teleporting you to the server {name}" ));
        SlashServer.ALREADY_TELEPORTING = SlashServer.color(SlashServer.c.getString( "ALREADY_TELEPORTING", "&cAlready teleporting you to a server" ));
        sender.sendMessage( "Config reloaded" );
    }
}
