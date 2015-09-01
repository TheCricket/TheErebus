package erebus.tileentity;

import erebus.ModBlocks;
import erebus.entity.EntityAnimatedBambooCrate;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IChatComponent;

public class TileEntityAnimatedBambooCrate extends TileEntityBasicInventory {

	protected EntityAnimatedBambooCrate bamber;

	public TileEntityAnimatedBambooCrate(EntityAnimatedBambooCrate crate) {
		super(crate.inventory.length, "Bamber");
		inventory = crate.inventory;
		bamber = crate;
	}

	@Override
	public boolean canUpdate() {
		return false;
	}

	@Override
	public Block getBlockType() {
		return ModBlocks.bambooCrate;
	}
}