package net.ckdev.autoaccept.mixin;

import net.ckdev.autoaccept.AutoAccept;
import net.minecraft.client.multiplayer.p2p.FriendJoinHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.UUID;

@Mixin(FriendJoinHandler.class)
public class FriendJoinHandlerMixin {
	@Inject(method = "handleJoinRequest", at = @At("HEAD"), cancellable = true)
	private void autoAcceptJoinRequest(UUID fromPmid, String sessionId, CallbackInfo ci) {
		if (!AutoAccept.autoAcceptEnabled) return;

		((FriendJoinHandlerAccessor) this).getIncomingJoinRequests().put(fromPmid, sessionId);
		((FriendJoinHandler)(Object)this).acceptIncomingJoinRequest(fromPmid);

		ci.cancel();
	}
}