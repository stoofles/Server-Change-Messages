package me.stoofles.ServerChangeMessages;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class ServerChangeMessages extends Plugin {
    @Override
    public void onEnable() {
        getProxy().getPluginManager().registerListener(this, new Events());
        getLogger().info("ServerChangeMessages has loaded.");
    }
    @Override
    public void onDisable()
    {
        getLogger().info( "ServerChangeMessages disabled!" );
    }
}