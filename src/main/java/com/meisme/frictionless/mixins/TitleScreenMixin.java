package com.meisme.frictionless.mixins;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.atomic.AtomicReference;


@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen{
    protected TitleScreenMixin(Text title){
        super(title);
    }

    @Inject(at = @At("RETURN"), method = "initWidgetsNormal")
    private void addButton(int y, int spacingY, CallbackInfo ci){
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, y - spacingY, 125, 20, new TranslatableText("frictionless.button.options"), (button) -> {
            Screen parent = this;
            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(new TranslatableText("title.frictionless.options"));
            builder.setSavingRunnable(() -> {

            });
            ConfigCategory general = builder.getOrCreateCategory(new TranslatableText("frictionless.category.general"));
            ConfigEntryBuilder entryBuilder = builder.entryBuilder();
            AtomicReference<Float> currentValue = new AtomicReference<>(1.1f);

            general.addEntry(entryBuilder.startFloatField(new TranslatableText("frictionless.option.optionA"), currentValue.get())
                    .setDefaultValue(1.1f)
                    .setTooltip(new TranslatableText("Slipperiness (1.1 is no friction, 1.2+ is negative friction)"))
                    .setSaveConsumer(currentValue::set) // Recommended: Called when user save the config
                    .build()); // Builds the option entry for cloth config
            Screen screen = builder.build();
            MinecraftClient.getInstance().setScreen(screen);
        }));
    }

}
