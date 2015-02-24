/*
 * Copyright � 2014 - 2015 | Alexander01998 | All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package tk.wurst_client.module.modules;

import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import tk.wurst_client.module.Category;
import tk.wurst_client.module.Module;

public class AutoArmor extends Module
{
	private int bestHelmet;
	private int bestPlate;
	private int bestLegs;
	private int bestBoots;

	public AutoArmor()
	{
		super("AutoArmor",
			"Manages your armor automatically",
			0,
			Category.COMBAT);
	}
	
	@Override
	public void onUpdate()
	{
		if(!getToggled())
			return;
		bestHelmet = 0;
		bestPlate = 0;
		bestLegs = 0;
		bestBoots = 0;
		for(int i = 9; i < 45; i++)
		{
			ItemStack itemstack = Minecraft.getMinecraft().thePlayer.inventory.getStackInSlot(i);
			Item item = itemstack.getItem();
			if(itemstack == null || item == null)
				continue;
			if(item instanceof ItemArmor)
			{
				ItemArmor armor = (ItemArmor)item;
				if(armor.armorType == 0 && armor.damageReduceAmount > bestHelmet)
					bestHelmet = i;
				if(armor.armorType == 1 && armor.damageReduceAmount > bestPlate)
					bestPlate = i;
				if(armor.armorType == 2 && armor.damageReduceAmount > bestLegs)
					bestLegs = i;
				if(armor.armorType == 3 && armor.damageReduceAmount > bestBoots)
					bestBoots = i;
			}
		}
	}
}
