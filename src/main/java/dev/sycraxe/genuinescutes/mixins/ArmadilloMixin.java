package dev.sycraxe.genuinescutes.mixins;

import net.minecraft.world.entity.animal.armadillo.Armadillo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Armadillo.class)
public abstract class ArmadilloMixin {

    @Inject(method = "brushOffScute", at = @At("HEAD"), cancellable = true)
    private static void injectBrushOffScuteToFalse(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }

    @Redirect(
            method = "customServerAiStep",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/animal/armadillo/Armadillo;isAlive()Z"
            )
    )
    private boolean redirectIsAliveToFalse(Armadillo instance) {
        return false;
    }
}