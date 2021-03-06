package exter.substratum.item;

import exter.substratum.creativetab.TabMaterials;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class ItemMortar extends Item
{
  public ItemMortar(int uses)
  {
    setUnlocalizedName("substratum.mortar");
    setCreativeTab(TabMaterials.tab);
    setMaxDamage(uses - 1);
    setMaxStackSize(1);
    setRegistryName("mortar");
    MinecraftForge.EVENT_BUS.register(this); 
  }
  
  @SuppressWarnings("deprecation")
  @SubscribeEvent
  public void onCrafting(PlayerEvent.ItemCraftedEvent event)
  {
    int i;
    int size = event.craftMatrix.getSizeInventory();
    for(i = 0; i < size; i++)
    {
      ItemStack is = event.craftMatrix.getStackInSlot(i);
      if(is != null && is.getItem() == this && is.getItemDamage() < getMaxDamage())
      {
        is.grow(1);
        // Don't damage when crafting dye powders.
        if(event.crafting != null && event.crafting.getItem() != SubstratumItems.item_dye_powder)
        {
          is.setItemDamage(is.getItemDamage() + 1);
        }
      }
    }
  }
}
