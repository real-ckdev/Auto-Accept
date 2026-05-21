package net.ckdev.autoaccept.mixin;

import net.ckdev.autoaccept.AutoAccept;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.components.OptionsList;
import net.minecraft.client.gui.screens.options.OnlineOptionsScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@SuppressWarnings("unused")
@Mixin(OnlineOptionsScreen.class)
public class OnlineOptionsScreenMixin {

    @Redirect(
            method = "addOptions",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/components/OptionsList;addSmall([Lnet/minecraft/client/OptionInstance;)V",
                    ordinal = 2
            )
    )
    private void redirectPresenceRow(OptionsList list, OptionInstance<?>[] options) {
        OnlineOptionsScreenAccessor accessor = (OnlineOptionsScreenAccessor) this;

        AbstractWidget presenceWidget = accessor.getOptions().sharePresence()
                .createButton(accessor.getOptions(), 0, 0, 150);

        CycleButton<Boolean> autoAcceptButton = CycleButton.onOffBuilder(AutoAccept.autoAcceptEnabled)
                .create(0, 0, 150, 20,
                        Component.literal("Auto Accept Requests"),
                        (btn, val) -> {
                            AutoAccept.autoAcceptEnabled = val;
                            AutoAccept.saveConfig();
                        }
                );

        list.addSmall(presenceWidget, autoAcceptButton);
    }
}
