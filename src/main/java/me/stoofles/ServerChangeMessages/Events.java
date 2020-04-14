package me.stoofles.ServerChangeMessages;


import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.md_5.bungee.event.EventHandler;
import java.io.File;
import java.io.IOException;


public class Events implements Listener {

    @EventHandler
    public void onPostLogin(PostLoginEvent event) {
        TextComponent message = new TextComponent("+ " + event.getPlayer().getName());
        for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
            player.sendMessage(message);
        }
    }

    @EventHandler
    public void onPostSwitch(ServerSwitchEvent serverSwitchEvent) {
        TextComponent message = new TextComponent(serverSwitchEvent.getPlayer() + " > " + serverSwitchEvent.getPlayer().getServer().getInfo().getName());
        for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
            if(!(serverSwitchEvent.getFrom() == null)){
                player.sendMessage(message);
            }
        }
    }

    @EventHandler
    public void onPostLeave(PlayerDisconnectEvent event){
        TextComponent message = new TextComponent("- " + event.getPlayer());
        for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
            player.sendMessage(message);
        }
    }
}
