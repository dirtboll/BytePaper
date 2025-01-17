package blue.lhf.bytepaper.library.syntax.event.player;

import blue.lhf.bytepaper.util.EventMapsTo;
import org.bukkit.entity.Player;
import org.bukkit.event.player.*;
import org.byteskript.skript.api.Event;
import org.byteskript.skript.api.Library;
import org.byteskript.skript.api.note.Documentation;
import org.byteskript.skript.api.note.EventValue;
import org.byteskript.skript.api.syntax.EventHolder;

@Documentation(
        name = "Player Toggle Sneak",
        description = "Run when a player toggles sneak.",
        examples = {
                """
                on player toggle sneak:
                    trigger:
                        send "You toggled sneak!" to event-player"
                """
        }
)
@EventMapsTo(PlayerToggleSneakEvent.class)
public class EventPlayerToggleSneak extends EventHolder {
    public EventPlayerToggleSneak(Library provider) {
        super(provider, "on [player] sneak [toggle]");
    }

    @Override
    public Class<? extends Event> eventClass() {
        return Data.class;
    }

    @SuppressWarnings("unused")
    public static class Data extends Event {
        protected final PlayerToggleSneakEvent event;

        public Data(PlayerToggleSneakEvent event) {
            this.event = event;
        }

        @Override
        public boolean isAsync() {
            return false;
        }

        @EventValue("player")
        public Player player() {
            return event.getPlayer();
        }

    }
}
