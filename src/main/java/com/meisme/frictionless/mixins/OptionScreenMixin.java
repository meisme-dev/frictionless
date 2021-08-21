package com.meisme.frictionless.mixins;

import com.meisme.frictionless.FrictionlessOptions;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OptionsScreen.class)
public abstract class OptionScreenMixin extends Screen{
    protected OptionScreenMixin(Text title){
        super(title);
    }
    @Inject(at = @At("RETURN"), method = "init")
    private void addButton(CallbackInfo ci){
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, this.height / 6 + 24 - 6, 310, 20, new TranslatableText("options.frictionless"), (button) -> MinecraftClient.getInstance().setScreen(FrictionlessOptions.createConfigScreen(this))));
    }

}
