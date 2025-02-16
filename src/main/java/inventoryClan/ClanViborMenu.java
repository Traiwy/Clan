package inventoryClan;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ru.traiwy.clanpruginv2.ClanPruginV2;

import java.util.ArrayList;
import java.util.List;

public class ClanViborMenu {

    private ClanPruginV2 clanPruginV2;
    public ClanViborMenu(ClanPruginV2 clanPruginV2){
        this.clanPruginV2 = clanPruginV2;
    }

    public void CreateChoiseInventory(Player player){
        Inventory inventory = Bukkit.createInventory(null, 45, "Выберите тип клана");
        ItemStack ironSword = new ItemStack(Material.IRON_SWORD);
        ItemStack ironHoe = new ItemStack(Material.IRON_HOE);
        ItemStack arrow = new ItemStack(Material.ARROW);

        ItemMeta metaSword = ironSword.getItemMeta();

        if (metaSword != null) {
            metaSword.displayName(Component.text("PvP клан")
                    .color(TextColor.fromHexString("#FF0000"))
                    .decoration(TextDecoration.ITALIC, false));
            List<String> loreSword = new ArrayList<>();
            loreSword.add(" ");
            loreSword.add(" §x§f§f§2§2§2§2§l§n▍§f Стоимость создания: §6500 000 монеток");
            loreSword.add(" §x§f§f§2§2§2§2§l§n▍§f Особенности этого типа клана:");
            loreSword.add(" §x§f§f§2§2§2§2§l§n▍§x§e§7§e§7§e§7  – до 8 игроков в клане (с расширением до 12)");
            loreSword.add(" §x§f§f§2§2§2§2§l§n▍§x§e§7§e§7§e§7  – клановый Джейкоб и Боссы;");
            loreSword.add(" §x§f§f§2§2§2§2§l§n▍§x§e§7§e§7§e§7  – захват Хатынок;");
            loreSword.add(" §x§f§f§2§2§2§2§l§n▍§x§e§7§e§7§e§7  – клановые эффекты для PvP;");
            loreSword.add(" §x§f§f§2§2§2§2§l§n▍§x§e§7§e§7§e§7  – клановый магазин для PvP;");
            loreSword.add(" §x§f§f§2§2§2§2§l§n▍§x§e§7§e§7§e§7  – соревнование в топе кланов;");
            loreSword.add(" §x§f§f§2§2§2§2§l§n▍§x§e§7§e§7§e§7  – клановые очки покупаются за рубины;");
            loreSword.add(" §x§f§f§2§2§2§2§l▍§x§e§7§e§7§e§7  – шальной ивент для PvP (2 раза в сутки).");
            loreSword.add("");
            loreSword.add(" §x§f§f§2§2§2§2▶️ §fНажмите, чтобы выбрать тип клана");

            metaSword.setLore(loreSword);
            ironSword.setItemMeta(metaSword);
        }

        ItemMeta metaHoe = ironHoe.getItemMeta();

        if (metaHoe != null) {
            metaHoe.displayName(Component.text("PvЕ клан")
                    .color(TextColor.fromHexString("#00E7FF"))
                    .decoration(TextDecoration.ITALIC, false));
            List<String> loreHoe = new ArrayList<>();
            loreHoe.add("");
            loreHoe.add(" §x§0§0§d§8§f§f§l§n▍§f Стоимость создания: §6750 000 монеток");
            loreHoe.add(" §x§0§0§d§8§f§f§l§n▍§f Особенности этого типа клана:");
            loreHoe.add(" §x§0§0§d§8§f§f§l§n▍§x§e§7§e§7§e§7  – до 10 игроков в клане (с расширением до 16);");
            loreHoe.add(" §x§0§0§d§8§f§f§l§n▍§x§e§7§e§7§e§7  – клановые эффекты для PvE;");
            loreHoe.add(" §x§0§0§d§8§f§f§l§n▍§x§e§7§e§7§e§7  – особые PvE эффекты для кланов;");
            loreHoe.add(" §x§0§0§d§8§f§f§l§n▍§x§e§7§e§7§e§7  – клановый магазин для PvE;");
            loreHoe.add(" §x§0§0§d§8§f§f§l§n▍§x§e§7§e§7§e§7  – клановые очки покупаются за монетки;");
            loreHoe.add(" §x§0§0§d§8§f§f§l▍§x§e§7§e§7§e§7  – шальной ивент для PvE (5 раз в сутки).");
            loreHoe.add("");
            loreHoe.add(" §x§0§0§d§8§f§f▶️ §fНажмите, чтобы выбрать тип клана");

            metaHoe.setLore(loreHoe);
            ironHoe.setItemMeta(metaHoe);
        }

        ItemMeta metaArrow = arrow.getItemMeta();
        if (metaArrow != null) {
            metaArrow.displayName(Component.text("Выход из меню")
                    .color(TextColor.fromHexString("#FF0000"))
                    .decoration(TextDecoration.ITALIC, false));
            List<String> loreArrow = new ArrayList<>();
            loreArrow.add("Выход из меню");
            loreArrow.add("Лор бумаги");

            metaArrow.setLore(loreArrow);
            arrow.setItemMeta(metaArrow);
        }

        inventory.setItem(21, ironSword);
        inventory.setItem(23, ironHoe);
        inventory.setItem(44, arrow);

        player.openInventory(inventory);
    }
}
