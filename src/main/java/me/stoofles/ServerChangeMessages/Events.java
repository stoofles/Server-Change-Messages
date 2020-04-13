package me.stoofles.ServerChangeMessages;


import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Events implements Listener {

    @EventHandler
    public void onPostLogin(PostLoginEvent event) {
        for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
            TextComponent message = new TextComponent("+ " + event.getPlayer().getName());
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
        ProxyServer.getInstance().broadcast(message);
    }
}
