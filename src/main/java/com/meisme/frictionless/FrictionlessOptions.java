package com.meisme.frictionless;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class FrictionlessOptions extends Screen
{
    protected FrictionlessOptions(Text title) {
        super(title);
    }

    public static Screen createConfigScreen(Screen parentScreen) throws NoClassDefFoundError{
        try {
            AtomicReference<Float> currentValue = new AtomicReference<>(1.1f);
            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(parentScreen)
                    .setTitle(new TranslatableText("title.frictionless.options"));
            builder.setSavingRunnable(() -> {
                try {
                    FileWriter fw = new FileWriter("optionsF.txt", false);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write("friction:" + currentValue);
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            ConfigCategory general = builder.getOrCreateCategory(new TranslatableText("frictionless.category.general"));
            ConfigEntryBuilder entryBuilder = builder.entryBuilder();
            general.addEntry(entryBuilder.startFloatField(new TranslatableText("frictionless.option.optionA"), FrictionlessOptionLoader.getFrictionValue())
                    .setDefaultValue(1.1f)
                    .setTooltip(new TranslatableText("frictionless.tooltip.slipperiness"))
                    .setSaveConsumer(currentValue::set)
                    .build());
            return builder.build();
        }
        catch(NoClassDefFoundError e){
            return parentScreen;
        }
    }
}
