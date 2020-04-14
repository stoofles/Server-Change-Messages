package me.stoofles.ServerChangeMessages;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import java.io.File;
import java.io.IOException;

public class ServerChangeMessages extends Plugin {
    private static ServerChangeMessages instance;
    private File file;
    private Configuration configuration;

    @Override
    public void onEnable() {
        setInstance(this);
        getProxy().getPluginManager().registerListener(this, new Events());

        file = new File(ProxyServer.getInstance().getPluginsFolder() +  "/ServerChangeMessages/config.yml");

        try {
            if(!file.exists()){
                file.createNewFile();
                configuration.set("join_message", "");
                configuration.set("leave_message", "");
                configuration.set("switch_message", "");
            }
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        getLogger().info("has loaded.");
    }
    @Override
    public void onDisable() {
        getLogger().info( "ServerChangeMessages disabled!" );
    }

    public static ServerChangeMessages getInstance() {
        return instance;
    }

    private static void setInstance(ServerChangeMessages instance) {
        ServerChangeMessages.instance = instance;
    }
}