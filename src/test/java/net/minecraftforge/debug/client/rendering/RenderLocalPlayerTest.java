/*
 * Minecraft Forge
 * Copyright (c) 2016-2020.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation version 2.1
 * of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

package net.minecraftforge.debug.client.rendering;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(RenderLocalPlayerTest.MODID)
@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class RenderLocalPlayerTest
{
    public static final String MODID = "render_local_player_test";
    static final boolean ENABLED = false;

    @SubscribeEvent
    public static void onItemRightClickEntity(final PlayerInteractEvent.EntityInteract event)
    {
        if (ENABLED && event.getItemStack().getItem() == Items.STICK)
        {
            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc.getCameraEntity() == mc.player)
            {
                mc.setCameraEntity(event.getTarget());

                event.setCancellationResult(ActionResult.SUCCESS);
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onItemRightClick(final PlayerInteractEvent.RightClickItem event)
    {
        if (ENABLED && event.getItemStack().getItem() == Items.STICK)
        {
            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc.getCameraEntity() != mc.player)
            {
                mc.setCameraEntity(mc.player);

                event.setCancellationResult(ActionResult.SUCCESS);
                event.setCanceled(true);
            }
        }
    }
}