package dev.cape.mixin.client;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import dev.cape.CapeSelector;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(PlayerListEntry.class)
public abstract class PlayerListEntryMixin {

	@Shadow @Final private Map<MinecraftProfileTexture.Type, Identifier> textures;

	@Inject(at = @At("HEAD"), method = "getCapeTexture")
	private void getCapeTexture$0(CallbackInfoReturnable<Identifier> cir) {
		if (CapeSelector.capeSelectedID != null) this.textures.put(MinecraftProfileTexture.Type.CAPE, new Identifier(CapeSelector.MOD_ID, "capes/" + CapeSelector.capeSelectedID + ".png"));
		else this.textures.put(MinecraftProfileTexture.Type.CAPE, null);
	}
}