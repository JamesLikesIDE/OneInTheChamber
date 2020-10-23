package net.jameslikeside;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.labymod.serverapi.bukkit.LabyModPlugin;
import org.bukkit.entity.Player;

import java.util.UUID;

public class LabyMod {

    public JsonObject addSecret(JsonObject jsonObject, String hasKey, String key, UUID secret, String domain) {
        jsonObject.addProperty(hasKey, Boolean.valueOf(true));
        jsonObject.addProperty(key, String.valueOf(secret.toString()) + ":" + domain);
        return jsonObject;
    }

    public static void sendCurrentPlayingGamemode(Player player, boolean visible, String gamemodeName) {
        JsonObject object = new JsonObject();
        object.addProperty("show_gamemode", Boolean.valueOf(visible));
        object.addProperty("gamemode_name", gamemodeName);
        LabyModPlugin.getInstance().sendServerMessage(player, "server_gamemode", (JsonElement)object);
    }

    public static void updateGameInfo(Player player, boolean hasGame, String gamemode, long startTime, long endTime) {
        JsonObject obj = new JsonObject();
        obj.addProperty("hasGame", Boolean.valueOf(hasGame));
        if (hasGame) {
            obj.addProperty("game_mode", gamemode);
            obj.addProperty("game_startTime", Long.valueOf(startTime));
            obj.addProperty("game_endTime", Long.valueOf(endTime));
        }
        LabyModPlugin.getInstance().sendServerMessage(player, "discord_rpc", (JsonElement)obj);
    }

    public static void setMiddleClickActions(Player player) {
        JsonArray array = new JsonArray();
        JsonObject entry = new JsonObject();
        entry.addProperty("displayName", "");
        entry.addProperty("type", EnumActionType.RUN_COMMAND.name());
        entry.addProperty("value", "kick {name}");
        array.add((JsonElement)entry);
        entry = new JsonObject();
        entry.addProperty("displayName", "Open shop");
        entry.addProperty("type", EnumActionType.OPEN_BROWSER.name());
        entry.addProperty("value", "https://shop.example.com");
        array.add((JsonElement)entry);
        entry = new JsonObject();
        entry.addProperty("displayName", "Copy stats profile url");
        entry.addProperty("type", EnumActionType.CLIPBOARD.name());
        entry.addProperty("value", "https://example.com/stats/{name}");
        array.add((JsonElement)entry);
        entry = new JsonObject();
        entry.addProperty("displayName", "Create report");
        entry.addProperty("type", EnumActionType.SUGGEST_COMMAND.name());
        entry.addProperty("value", "report {name} >reason<");
        array.add((JsonElement)entry);
        LabyModPlugin.getInstance().sendServerMessage(player, "user_menu_actions", (JsonElement)array);
    }

    enum EnumActionType {
        NONE, CLIPBOARD, RUN_COMMAND, SUGGEST_COMMAND, OPEN_BROWSER;
    }

    public void setSubtitle( Player receiver, UUID subtitlePlayer, String value ) {
        // List of all subtitles
        JsonArray array = new JsonArray();

        // Add subtitle
        JsonObject subtitle = new JsonObject();
        subtitle.addProperty( "uuid", subtitlePlayer.toString() );

        // Optional: Size of the subtitle
        subtitle.addProperty( "size", 1.6d ); // Range is 0.8 - 1.6 (1.6 is Minecraft default)

        // no value = remove the subtitle
        if(value != null)
            subtitle.addProperty( "value", value );

        // You can set multiple subtitles in one packet
        array.add(subtitle);

        // Send to LabyMod using the API
        LabyModPlugin.getInstance().sendServerMessage( receiver, "account_subtitle", array );
    }

}
