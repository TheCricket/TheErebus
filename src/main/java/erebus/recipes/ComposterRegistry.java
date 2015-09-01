package erebus.recipes;

import erebus.ModBlocks;
import erebus.ModItems;
import erebus.core.helper.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComposterRegistry {

	private static List<Material> compostableMaterials = Arrays.asList(Material.cactus, Material.cake, Material.coral, Material.gourd, Material.grass, Material.leaves, Material.plants, Material.sponge, Material.vine, Material.web, Material.wood);
	private static List<ItemStack> registry = new ArrayList<ItemStack>();
	private static List<ItemStack> blacklist = new ArrayList<ItemStack>();

	public static void init() {
		register(Items.stick);
		register(Items.wooden_axe);
		register(Items.oak_door);
		register(Items.dark_oak_door);
		register(Items.acacia_door);
		register(Items.birch_door);
		register(Items.jungle_door);
		register(Items.spruce_door);
		register(Items.wooden_hoe);
		register(Items.wooden_pickaxe);
		register(Items.wooden_shovel);
		register(Items.wooden_sword);
		register(Items.wheat);
		register(Items.poisonous_potato);

		blacklist.add(new ItemStack(ModBlocks.wallPlants, 1, OreDictionary.WILDCARD_VALUE));
	}

	private static void register(Item item) {
		register(new ItemStack(item, 1, OreDictionary.WILDCARD_VALUE));
	}

	private static void register(ItemStack stack) {
		registry.add(stack);
	}

	public static ItemStack isCompostable(ItemStack stack) {
		if (stack == null)
			return null;
		for (ItemStack s : blacklist)
			if (Utils.areStacksTheSame(s, stack, false))
				return null;

		if (stack.getItem() instanceof ItemFood || stack.getItem() instanceof ItemSeeds)
			return new ItemStack(ModItems.compost);
		else {
			Block block = Block.getBlockFromItem(stack.getItem());
			if (block != null && block != Blocks.air && compostableMaterials.contains(block.getMaterial()))
				return new ItemStack(ModItems.compost);
		}

		for (ItemStack reg : registry)
			if (Utils.areStacksTheSame(stack, reg, false))
				return new ItemStack(ModItems.compost);

		return null;
	}
}