package com.meisme.frictionless.mixins;

import com.meisme.frictionless.FrictionlessOptionLoader;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;


@Mixin(Block.class)
public abstract class BlockMixin {
    /**
     * @author me is me
     * @reason Modify friction using a user controlled value
     */
    @Overwrite()
    public float getSlipperiness() {
        return FrictionlessOptionLoader.getFrictionValue();
    }
}
