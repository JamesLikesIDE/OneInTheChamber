package net.jameslikeside.data;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.List;

public class Item {

    private final ItemStack current;

    public Item(ItemStack i) {
        this.current = i;
    }

    public Item(Material m, int amount, short ID) {
        this(new ItemStack(m, amount, ID));
    }
    public Item(Material m, int amount) {
        this(new ItemStack(m, amount));
    }

    @SuppressWarnings("deprecation")
    public Item(int ID, int amount, short DoubleID) {
        this(new ItemStack(ID, amount, DoubleID));
    }

    public Item setDisplayName(String displayname) {
        ItemMeta m = current.getItemMeta();
        m.setDisplayName(displayname);
        current.setItemMeta(m);
        return this;
    }

    public Item setLore(List<String> lore) {
        ItemMeta m = current.getItemMeta();
        m.setLore(lore);
        current.setItemMeta(m);
        return this;
    }

    public Item setUnbreakable(boolean unbreakable){
        ItemMeta m = current.getItemMeta();
        m.spigot().setUnbreakable(unbreakable);
        current.setItemMeta(m);
        return this;
    }

    public Item setAmount(int amount) {
        current.setAmount(amount);
        return this;
    }

    public Item addEnchantment(Enchantment enchantment, int level, boolean bool) {
        ItemMeta m = current.getItemMeta();
        m.addEnchant(enchantment, level, bool);
        current.setItemMeta(m);
        return this;
    }

    public ItemStack build() {
        return this.current;
    }
}
