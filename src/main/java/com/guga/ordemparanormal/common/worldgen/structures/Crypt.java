package com.guga.ordemparanormal.common.worldgen.structures;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BuiltinStructureSets;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.PostPlacementProcessor;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.Optional;

public class Crypt extends StructureFeature<JigsawConfiguration> {
    public static final Codec<JigsawConfiguration> CODEC = RecordCodecBuilder.create((p_67764_) ->
            p_67764_.group(StructureTemplatePool.CODEC.fieldOf("start_pool")
            .forGetter(JigsawConfiguration::startPool), Codec.intRange(0, 30).fieldOf("size")
            .forGetter(JigsawConfiguration::maxDepth)).apply(p_67764_, JigsawConfiguration::new));
    public Crypt() {
        super(CODEC, Crypt::createPiecesGenerator, PostPlacementProcessor.NONE);
    }
    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.UNDERGROUND_STRUCTURES;
    }
    private static boolean isFeatureChunk(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {
        ChunkPos chunkpos = context.chunkPos();

        return !context.chunkGenerator().hasFeatureChunkInRange(BuiltinStructureSets.VILLAGES, context.seed(), chunkpos.x, chunkpos.z, 10);
    }
    public static Optional<PieceGenerator<JigsawConfiguration>> createPiecesGenerator(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {
        if (!Crypt.isFeatureChunk(context)) {
            return Optional.empty();
        }

        BlockPos blockpos = context.chunkPos().getMiddleBlockPosition(0);

        return JigsawPlacement.addPieces(context, PoolElementStructurePiece::new, blockpos, false, true);
    }
}
