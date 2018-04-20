package com.kjmaster.caveroot.gen;

import com.kjmaster.caveroot.config.ModConfig;
import com.kjmaster.caveroot.proxy.CommonProxy;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
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
       for (int dimID : ModConfig.caveRootCategory.dimIDs) {
           if (dimID == world.provider.getDimension() && !ModConfig.caveRootCategory.disabled) {
               this.runGenerator(world, random, chunkX, chunkZ, CommonProxy.caveRootBlock.getDefaultState(), ModConfig.caveRootCategory.spawnProb);
           }
       }
       for (int dimID : ModConfig.torchFungiCategory.dimIDs) {
           if (dimID == world.provider.getDimension() && !ModConfig.torchFungiCategory.disabled) {
               this.runGenerator(world, random, chunkX, chunkZ, CommonProxy.torchFungiBlock.getDefaultState(), ModConfig.torchFungiCategory.spawnProb);
           }
       }
       for (int dimID : ModConfig.driedRootCategory.dimIDs) {
           if (dimID == world.provider.getDimension() && !ModConfig.driedRootCategory.disabled) {
               this.runDriedRootGenerator(world, random, chunkX, chunkZ, CommonProxy.driedRootBlock.getDefaultState());
           }
       }
    }

    private void runGenerator(World world, Random random, int chunkX, int chunkZ, IBlockState state, double spawnProb) {

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
                if (randFloat < (float)spawnProb) {
                    world.setBlockState(new BlockPos(x, testy, z), state);
                }
            }
        }
    }

    private void runDriedRootGenerator(World world, Random random, int chunkX, int chunkZ, IBlockState state) {
        int x = chunkX * 16 + random.nextInt(16) + 8;
        int z = chunkZ * 16 + random.nextInt(16) + 8;
        int xy = x >> 4;
        int zy = z >> 4;
        int height = world.getChunkFromChunkCoords(xy, zy).getHeight(new BlockPos(x & 15, 0, z & 15));
        for (int i = 0; i <= 70; i++) {
            int testy = height - i;
            int blockUnder = testy - 1;
            if (world.getBlockState(new BlockPos(x, blockUnder, z)).getBlock().equals(Blocks.AIR)
                    && world.getBlockState(new BlockPos(x, testy, z)).getBlock().equals(Blocks.AIR)
                    && world.getBlockState(new BlockPos(x, testy + 1, z)).getMaterial().equals(Material.ROCK)&& testy < 60) {
                float randFloat = random.nextFloat();
                if (randFloat < (float)ModConfig.driedRootCategory.spawnProb) {
                    world.setBlockState(new BlockPos(x, testy, z), state);
                }
            }
        }
    }
}
