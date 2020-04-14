package me.stoofles.ServerChangeMessages;
import net.md_5.bungee.api.ProxyServer;
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

        if(!getDataFolder().exists()){
            getDataFolder().mkdir();
        }

        file = new File(getDataFolder(), "config.yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
                configuration.set("join_message", "+ {username}");
                configuration.set("leave_message", "- {username}");
                configuration.set("switch_message", "{username} > {destination_server}");
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
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