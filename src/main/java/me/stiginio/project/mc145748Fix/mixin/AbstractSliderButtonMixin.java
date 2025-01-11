package me.stiginio.project.mc145748Fix.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.sounds.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractSliderButton.class)
public abstract class AbstractSliderButtonMixin {

    @Unique
    private static boolean dragging;

    @Inject(method = "onDrag", at = @At("TAIL"))
    protected void onDrag(double d, double e, double f, double g, CallbackInfo callbackInfo) {
        dragging = true;
    }

    /**
     * @author stiginio
     * @reason Fix for MC-145748
     */
    @Overwrite
    public void onRelease(double d, double e) {
        if(dragging)
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
        dragging = false;
    }
}
