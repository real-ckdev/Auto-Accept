package net.ckdev.autoaccept.mixin;

import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(OptionsSubScreen.class)
public interface OnlineOptionsScreenAccessor {
    @Accessor("options")
    net.minecraft.client.Options getOptions();
}