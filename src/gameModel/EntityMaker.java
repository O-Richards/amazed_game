package gameModel;

import gameModel.bonusMovement.HoverBonusMovement;
import gameModel.bonusMovement.InvincibilityBonusAction;
import gameModel.entity.BasicEntity;
import gameModel.entity.Entity;
import gameModel.entity.VisType;
import gameModel.mobileEntity.EnemyMovement;
import gameModel.mobileEntity.EntityTrackingMovement;
import gameModel.mobileEntity.MobileEntity;
import gameModel.mobileEntity.PlayerMobileEntity;
import gameModel.usable.ArrowUsable;
import gameModel.usable.BombUsable;
import gameModel.usable.KeyUsable;
import gameModel.usable.TreasureUsage;
import gameModel.usable.Usable;
import gameModel.usable.UseAction;
import gameModel.winCondition.WinCondition;
import gameModel.winCondition.WinSystem;
import gameModel.winCondition.WinType;

public class EntityMaker {
	private WinSystem winSystem;
	private EntityMover entityMover;
	
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
		};
		
		return new BasicEntity.BasicEntityBuilder(VisType.HOVER_POTION, c)
				.withEntityMover(entityMover)
				.withUsage(hoverPotionUse)
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
	
	public MobileEntity makeEnemy(Coord c, Entity target, double randMoveRate) {
		Entity basicEntity = new BasicEntity.BasicEntityBuilder(VisType.HUNTER, c)
				.withEntityMover(entityMover)
				.withAlive(true)
				.build();
		
		return new MobileEntity.MobileEntityBuilder(basicEntity)
				.withCanPush(false)
				.withIsMoving(true)
				.withKillAction(KillAction.ENEMY)
				.withKilledBy(KillAction.WEAPON)
				.withMovement(new EnemyMovement(randMoveRate, basicEntity, target, entityMover))
				.build();
	}
}
