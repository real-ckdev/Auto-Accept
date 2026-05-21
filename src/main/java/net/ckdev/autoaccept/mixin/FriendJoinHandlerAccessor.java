package net.ckdev.autoaccept.mixin;

import net.minecraft.client.multiplayer.p2p.FriendJoinHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Mixin(FriendJoinHandler.class)
public interface FriendJoinHandlerAccessor {
    @Accessor("incomingJoinRequests")
    ConcurrentHashMap<UUID, String> getIncomingJoinRequests();
}