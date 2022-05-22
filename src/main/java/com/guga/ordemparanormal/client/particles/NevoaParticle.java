package com.guga.ordemparanormal.client.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class NevoaParticle extends TextureSheetParticle {
	private double xRand;
	private double zRand;

	protected NevoaParticle(ClientLevel level, double xCoord, double yCoord, double zCoord, SpriteSet spriteSet,
			double xd, double yd, double zd) {
		super(level, xCoord, yCoord, zCoord, xd, yd, zd);

		this.friction = 0.8F;
		this.xd = xd;
		this.yd = yd;
		this.zd = zd;
		this.quadSize = 3.0F;
		this.lifetime = 100;
		this.alpha = 0.0F;
		this.setSpriteFromAge(spriteSet);

		this.rCol = 1f;
		this.gCol = 1f;
		this.bCol = 1f;
		Random random = new Random();
		double max = 0.05D;
		double min = -0.05D;
		xRand = random.nextDouble(max - min) + min;
		zRand = random.nextDouble(max - min) + min;
	}

	@Override
	public void tick() {
		super.tick();
		
		if (Math.abs(xRand) > Math.abs(zRand)) {
			zRand = 0;
		} else {
			xRand = 0;
		}
		this.move(xRand, 0.0D, zRand);
		
		if (this.age - 30 <= 0 && this.alpha < 1.0F) {
			this.alpha += 0.015F;
		} else if (this.age++ < this.lifetime && !(this.alpha <= 0.0F)) {
	         if (this.age >= this.lifetime - 60 && this.alpha > 0.001F) {
	            this.alpha -= 0.015F;
	         }
	      } else {
	         this.remove();
	      }
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
	}

	@OnlyIn(Dist.CLIENT)
	public static class Provider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet sprites;

		public Provider(SpriteSet spriteSet) {
			this.sprites = spriteSet;
		}

		public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z,
				double dx, double dy, double dz) {
			return new NevoaParticle(level, x, y, z, this.sprites, dx, dy, dz);
		}
	}
}
