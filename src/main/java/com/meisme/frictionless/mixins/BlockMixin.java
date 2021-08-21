package com.meisme.frictionless.mixins;

import com.meisme.frictionless.FrictionlessOptions;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;


@Mixin(Block.class)
public abstract class BlockMixin {
    /**
     * @author meisme
     */
    @Overwrite()
    public float getSlipperiness() {
        return FrictionlessOptions.getFrictionValue();
    }
}
