package com.kjmaster.caveroot.gen;

import com.kjmaster.caveroot.config.ModConfig;
import com.kjmaster.caveroot.proxy.CommonProxy;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RootGenerator implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
       for (int dimID : ModConfig.dimIDs) {
           if (dimID == world.provider.getDimension()) {
               this.runRootGenerator(world, random, chunkX, chunkZ);
           }
       }
    }

    private void runRootGenerator(World world, Random random, int chunkX, int chunkZ) {

        int x = chunkX * 16 + random.nextInt(16) + 8;
        int z = chunkZ * 16 + random.nextInt(16) + 8;
        int xy = x >> 4;
        int zy = z >> 4;
        int height = world.getChunkFromChunkCoords(xy, zy).getHeight(new BlockPos(x & 15, 0, z & 15));
        for (int i = 0; i <= 70; i++) {
            int testy = height - i;
            int blockUnder = testy - 1;
            if (world.getBlockState(new BlockPos(x, blockUnder, z)).getBlock().equals(Blocks.STONE)
                    && world.getBlockState(new BlockPos(x, testy, z)).getBlock().equals(Blocks.AIR) && testy < 60) {
                float randFloat = random.nextFloat();
                if (randFloat < (float)ModConfig.spawnProb) {
                    world.setBlockState(new BlockPos(x, testy, z), CommonProxy.caveRootBlock.getDefaultState());
                }
            }
        }
    }
}
