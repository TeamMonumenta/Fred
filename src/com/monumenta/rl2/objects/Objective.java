package com.monumenta.rl2.objects;


import com.monumenta.rl2.Main;
import com.monumenta.rl2.Utils;
import com.monumenta.rl2.enums.Biome;
import net.minecraft.server.v1_13_R2.LootTables;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.util.Vector;

public class Objective extends RoomObject {

    // basic constructor with default values
    public Objective() {
        super();
    }

    // basic constructor with given RoomObject values
    public Objective(BlockFace direction, Biome biome, Vector relPos) {
        super(direction, biome, relPos);
    }

    // copy constructor
    public Objective(Objective old) {
        super(old);
    }

    public void spawnObjective() {
        try {
            Bukkit.getScheduler().callSyncMethod(Main.getInstance(), () -> {
                Location loc = this.getLocation().clone().add(0.5, 1, 0.5);
                this.getLocation().getBlock().setType(Material.AIR);
                loc.getWorld().spawn(loc, EnderCrystal.class);
                return null;
            } ).get();
            Thread.sleep(100);
        } catch (Exception e) {

        }
    }

    public void spawnChest() {
        try {
            Bukkit.getScheduler().callSyncMethod(Main.getInstance(), () -> {
                Location loc = this.getLocation();
                String table = "epic:r2/dungeons/fred/";
                if (this.getBiome() == Biome.VAULT) {
                    table += "challenge";
                } else {
                    table += "objective_" + this.getBiome().name().toLowerCase();
                }
                String waterlogged = "false";
                if (this.getBiome() == Biome.WATER) {
                    waterlogged = "true";
                }
                String cmd = "setblock " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ() +
                        " minecraft:chest[facing=" + this.getDirection().name().toLowerCase() +
                        ",waterlogged=" + waterlogged + "]{LootTable:\"" + table + "\"}";
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), cmd);
                return null;
            } ).get();
            Thread.sleep(10);
        } catch (Exception ignored) {

        }
    }
}
