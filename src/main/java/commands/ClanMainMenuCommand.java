package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import ru.traiwy.clanpruginv2.ClanPruginV2;

public class ClanMainMenuCommand implements CommandExecutor {
    private final ClanPruginV2 plugin;
    public ClanMainMenuCommand(ClanPruginV2 plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("В игре");
            return true;
        }
        Player player = (Player) sender;
        if(command.getName().equals("clan")){
            plugin.getInventoryClan().CreateMenuClan(player);

        }
        return true;
    }
}
