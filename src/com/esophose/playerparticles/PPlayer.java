/**
 * Copyright Esophose 2016
 * While using any of the code provided by this plugin
 * you must not claim it as your own. This plugin may
 * be modified and installed on a server, but may not
 * be distributed to any person by any means.
 */

package com.esophose.playerparticles;

import java.util.UUID;

import org.bukkit.Material;

import com.esophose.playerparticles.library.ParticleEffect;
import com.esophose.playerparticles.library.ParticleEffect.BlockData;
import com.esophose.playerparticles.library.ParticleEffect.ItemData;
import com.esophose.playerparticles.library.ParticleEffect.NoteColor;
import com.esophose.playerparticles.library.ParticleEffect.OrdinaryColor;
import com.esophose.playerparticles.library.ParticleEffect.ParticleColor;
import com.esophose.playerparticles.library.ParticleEffect.ParticleData;
import com.esophose.playerparticles.library.ParticleEffect.ParticleProperty;
import com.esophose.playerparticles.styles.DefaultStyles;
import com.esophose.playerparticles.styles.api.ParticleStyle;

public class PPlayer {

	/**
	 * The UUID of the player
	 */
	private final UUID playerUUID;

	/**
	 * The effect and style the player is using
	 */
	private ParticleEffect particleEffect;
	private ParticleStyle particleStyle;

	/**
	 * All data used to display custom particles
	 */
	private ItemData particleItemData;
	private BlockData particleBlockData;
	private OrdinaryColor particleColorData;
	private NoteColor particleNoteColorData;

	/**
	 * Constructs a new PPlayer 
	 * 
	 * @param uuid The player UUID
	 * @param effect The player's effect
	 * @param style The player's style
	 * @param itemData The player's item data
	 * @param blockData The player's block data
	 * @param colorData The player's color data
	 * @param noteColorData The player's note color data
	 */
	public PPlayer(UUID uuid, ParticleEffect effect, ParticleStyle style, ItemData itemData, BlockData blockData, OrdinaryColor colorData, NoteColor noteColorData) {
		this.playerUUID = uuid;
		this.particleEffect = effect;
		this.particleStyle = style;
		this.particleItemData = itemData;
		this.particleBlockData = blockData;
		this.particleColorData = colorData;
		this.particleNoteColorData = noteColorData;
	}
	
	/**
	 * Gets the player's UUID
	 * 
	 * @return The player's UUID
	 */
	public UUID getUniqueId() {
		return this.playerUUID;
	}

	/**
	 * Gets the player's particle effect
	 * 
	 * @return The player's particle effect
	 */
	public ParticleEffect getParticleEffect() {
		return this.particleEffect;
	}

	/**
	 * Gets the player's particle style
	 * 
	 * @return The player's particle style
	 */
	public ParticleStyle getParticleStyle() {
		return this.particleStyle;
	}

	/**
	 * Gets the player's item data
	 * 
	 * @return The player's item data
	 */
	public ItemData getItemData() {
		return this.particleItemData;
	}

	/**
	 * Gets the player's block data
	 * 
	 * @return The player's block data
	 */
	public BlockData getBlockData() {
		return this.particleBlockData;
	}

	/**
	 * Gets the player's color data
	 * 
	 * @return The player's color data
	 */
	public OrdinaryColor getColorData() {
		return this.particleColorData;
	}

	/**
	 * Gets the player's note color data
	 * 
	 * @return The player's note color data
	 */
	public NoteColor getNoteColorData() {
		return this.particleNoteColorData;
	}

	/**
	 * Sets the player's particle effect
	 * 
	 * @param effect The player's new particle effect
	 */
	public void setParticleEffect(ParticleEffect effect) {
		this.particleEffect = effect;
	}

	/**
	 * Sets the player's particle style
	 * 
	 * @param effect The player's new particle style
	 */
	public void setParticleStyle(ParticleStyle style) {
		this.particleStyle = style;
	}
	
	/**
	 * Sets the player's item data
	 * 
	 * @param effect The player's new item data
	 */
	public void setItemData(ItemData itemData) {
		this.particleItemData = itemData;
	}
	
	/**
	 * Sets the player's  block data
	 * 
	 * @param effect The player's new block data
	 */
	public void setBlockData(BlockData blockData) {
		this.particleBlockData = blockData;
	}
	
	/**
	 * Sets the player's color data
	 * 
	 * @param effect The player's new color data
	 */
	public void setColorData(OrdinaryColor colorData) {
		this.particleColorData = colorData;
	}
	
	/**
	 * Sets the player's note color data
	 * 
	 * @param effect The player's new note color data
	 */
	public void setNoteColorData(NoteColor noteColorData) {
		this.particleNoteColorData = noteColorData;
	}

	/**
	 * Gets the data the current particle effect will spawn with
	 * 
	 * @return The ParticleData the current particle effect requires
	 */
	public ParticleData getParticleSpawnData() {
		if (particleEffect.hasProperty(ParticleProperty.REQUIRES_DATA)) {
			if (particleEffect == ParticleEffect.BLOCK_CRACK || particleEffect == ParticleEffect.BLOCK_DUST || particleEffect == ParticleEffect.FALLING_DUST) {
				return particleBlockData;
			} else if (particleEffect == ParticleEffect.ITEM_CRACK) {
				return particleItemData;
			}
		}
		return null;
	}

	/**
	 * Gets the color the current particle effect will spawn with
	 * 
	 * @return Gets the ParticleColor the current particle effect will spawn with
	 */
	public ParticleColor getParticleSpawnColor() {
		if (particleEffect.hasProperty(ParticleProperty.COLORABLE)) {
			if (particleEffect == ParticleEffect.NOTE) {
				if (particleNoteColorData.getValueX() * 24 == 99) {
					return ParticleCreator.getRainbowNoteParticleColor();
				}
				return particleNoteColorData;
			} else {
				if (particleColorData.getRed() == 999 && particleColorData.getGreen() == 999 && particleColorData.getBlue() == 999) {
					return ParticleCreator.getRainbowParticleColor();
				} else {
					return particleColorData;
				}
			}
		}
		return null;
	}

	/**
	 * Gets a default PPlayer
	 * Used for when a new PPlayer is being created
	 * 
	 * @param playerUUID The player's UUID
	 * @return A default PPlayer
	 */
	public static PPlayer getNewPPlayer(UUID playerUUID) {
		ParticleEffect particleEffect = ParticleEffect.NONE;
		ParticleStyle particleStyle = DefaultStyles.NONE;
		ItemData particleItemData = new ItemData(Material.IRON_SPADE, (byte) 0);
		BlockData particleBlockData = new BlockData(Material.STONE, (byte) 0);
		OrdinaryColor particleColorData = new OrdinaryColor(0, 0, 0);
		NoteColor particleNoteColorData = new NoteColor(0);

		return new PPlayer(playerUUID, particleEffect, particleStyle, particleItemData, particleBlockData, particleColorData, particleNoteColorData);
	}

}