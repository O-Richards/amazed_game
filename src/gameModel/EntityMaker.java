package gameModel;

import gameModel.bonusMovement.HoverBonusMovement;
import gameModel.bonusMovement.InvincibilityBonusAction;
import gameModel.entity.BasicEntity;
import gameModel.entity.Entity;
import gameModel.entity.VisType;
import gameModel.mobileEntity.CowardEnemyMovement;
import gameModel.mobileEntity.EnemyMovement;
import gameModel.mobileEntity.EntityTrackingMovement;
import gameModel.mobileEntity.HoundEnemyMovement;
import gameModel.mobileEntity.HunterEnemyMovement;
import gameModel.mobileEntity.MobileEntity;
import gameModel.mobileEntity.PlayerMobileEntity;
import gameModel.mobileEntity.StrategistEnemyMovement;
import gameModel.usable.ArrowUsable;
import gameModel.usable.BombUsable;
import gameModel.usable.KeyUsable;
import gameModel.usable.SwordUsable;
import gameModel.usable.TreasureUsage;
import gameModel.usable.Usable;
import gameModel.usable.UseAction;
import gameModel.winCondition.WinCondition;
import gameModel.winCondition.WinSystem;
import gameModel.winCondition.WinType;

public class EntityMaker {
	private WinSystem winSystem;
	private EntityMover entityMover;
	private MobileEntity hunter = null;
	
	public EntityMaker(WinSystem winSystem, EntityMover entityMover) {
		this.winSystem = winSystem;
		this.entityMover = entityMover;
	}
	public PlayerMobileEntity makePlayer(Coord c) {
		return new PlayerMobileEntity.PlayerMobileEntityBuilder("P", c, entityMover)
				.build();
	}
	public Entity makeKey(Coord c) {
		return new BasicEntity.BasicEntityBuilder(VisType.KEY, c)
				.withEntityMover(entityMover)
				.withUsage(new KeyUsable())
				.build();
	}
	
	public Entity makeArrow(Coord c) {
		return new BasicEntity.BasicEntityBuilder(VisType.ARROW, c)
			.withUsage(new ArrowUsable(entityMover))
			.build();
	}
	
	public Entity makeTreasure(Coord c) {
		WinCondition treasureWin = this.winSystem.newWinCondition(WinType.TREASURE);
		return new BasicEntity.BasicEntityBuilder(VisType.TREASURE, c)
				.withEntityMover(entityMover)
				.withWinCondition(treasureWin)
				.withUsage(new TreasureUsage(treasureWin))
				.build();
	}
	
	public Entity makeInvincibilityPotion(Coord c) {
		Usable invincibilityUse = new Usable() {
			@Override
			public void applyToPlayer(PlayerMobileEntity player) {
				player.setMovement(new InvincibilityBonusAction(player.getMovement(), entityMover));
			}

			@Override
			public boolean use(UseAction action) {
				return false;
			}

			@Override
			public UseAction getUseAction() {
				return UseAction.INVINCIBILITY;
			}
			
			@Override
			public boolean canBePickedUpWith(Usable u) {
				if (u.getUseAction() == this.getUseAction()) {
					return false;
				}
				return true;
			}
			
		};
		
		return new BasicEntity.BasicEntityBuilder(VisType.INVINCIBILITY_POTION, c)
				.withUsage(invincibilityUse)
				.withEntityMover(entityMover)
				.build();
	}
	
	public Entity makeHoverPotion(Coord c) {
		Usable hoverPotionUse = new Usable() {
			@Override
			public boolean use(UseAction action) {
				return false;
			}

			@Override
			public void applyToPlayer(PlayerMobileEntity player) {
				//Applying decorator pattern to the player's movements
				//i.e. we wrap the player's movement with a hover bonus
				player.setMovement(new HoverBonusMovement(player.getMovement()));
			}

			@Override
			public UseAction getUseAction() {
				return UseAction.HOVER;
			}

			@Override
			public boolean canBePickedUpWith(Usable u) {
				if (u.getUseAction() == this.getUseAction()) {
					return false;
				}
				return true;
			}
		};
		
		return new BasicEntity.BasicEntityBuilder(VisType.HOVER_POTION, c)
				.withEntityMover(entityMover)
				.withUsage(hoverPotionUse)
				.build();
	}
	
	public Entity makeSword(Coord c) {
		return new BasicEntity.BasicEntityBuilder(VisType.SWORD, c)
				.withEntityMover(entityMover)
				.withUsage(new SwordUsable(entityMover))
				.build();
	}
	
	public MobileEntity makeBoulder(Coord c) {
		Entity basicEntity = new BasicEntity.BasicEntityBuilder(VisType.BOULDER, c)
				.withEntityMover(entityMover)
				.build();
		return new MobileEntity.MobileEntityBuilder(basicEntity)
				.withMovement(new EntityTrackingMovement())
				.withIsMoving(false)
				.withPushable(true)
				.withCanTriggerSwitches(true)
				.build();
	}
	
	public Entity makeBomb(Coord c) {
		return new BasicEntity.BasicEntityBuilder(VisType.BOMB, c)
				.withEntityMover(entityMover)
				.withUsage(new BombUsable(entityMover))
				.build();
	}
	
	public MobileEntity makeHunter(Coord c, MobileEntity player, double randMoveRate) {
		Entity basicEntity = new BasicEntity.BasicEntityBuilder(VisType.HUNTER, c)
				.withEntityMover(entityMover)
				.withAlive(true)
				.build();
		
		this.hunter = new MobileEntity.MobileEntityBuilder(basicEntity)
				.withCanPush(false)
				.withIsMoving(true)
				.withKilledBy(KillAction.WEAPON)
				.withKilledBy(KillAction.INVINCIBLE)
				.withMovement(new HunterEnemyMovement(randMoveRate, basicEntity, player, entityMover))
				.build();
		
		return this.hunter;
	}
	
	public MobileEntity makeHound(Coord c, MobileEntity player, double randMoveRate) throws EntityCreationException {
		if (this.hunter == null) {
			throw new EntityCreationException("A hunter must exist before a hound can be made");
		}
		Entity basicEntity = new BasicEntity.BasicEntityBuilder(VisType.HOUND, c)
				.withEntityMover(entityMover)
				.withAlive(true)
				.build();
		
		return new MobileEntity.MobileEntityBuilder(basicEntity)
				.withCanPush(false)
				.withIsMoving(true)
				.withKilledBy(KillAction.WEAPON)
				.withKilledBy(KillAction.INVINCIBLE)
				.withMovement(new HoundEnemyMovement(randMoveRate, basicEntity, player, this.hunter, entityMover))
				.build();
	}
	
	public MobileEntity makeCoward(Coord c, MobileEntity player, double randMoveRate) {
		Entity basicEntity = new BasicEntity.BasicEntityBuilder(VisType.COWARD, c)
				.withEntityMover(entityMover)
				.withAlive(true)
				.build();
		
		return new MobileEntity.MobileEntityBuilder(basicEntity)
				.withCanPush(false)
				.withIsMoving(true)
				.withKilledBy(KillAction.WEAPON)
				.withKilledBy(KillAction.INVINCIBLE)
				.withMovement(new CowardEnemyMovement(randMoveRate, basicEntity, player, entityMover))
				.build();
	}
	
	public MobileEntity makeStrategist(Coord c, MobileEntity player, double randMoveRate) {
		Entity basicEntity = new BasicEntity.BasicEntityBuilder(VisType.STRATEGIST, c)
				.withEntityMover(entityMover)
				.withAlive(true)
				.build();
		
		return new MobileEntity.MobileEntityBuilder(basicEntity)
				.withCanPush(false)
				.withIsMoving(true)
				.withKilledBy(KillAction.WEAPON)
				.withKilledBy(KillAction.INVINCIBLE)
				.withMovement(new StrategistEnemyMovement(randMoveRate, basicEntity, player, entityMover))
				.build();
	}
}
