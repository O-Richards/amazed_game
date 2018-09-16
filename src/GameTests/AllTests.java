package GameTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ArrowMobileEntityTest.class, BoulderMobileEntityTest.class, EnemyMobileEntityTest.class,
		EntityTest.class, InvincibilityBonusActionTest.class, InvincibilityEntityTest.class, KeyUsableEntityTest.class,
		MobileEntityTest.class, PlayerMobileEntityTest.class, PlayerMovementTest.class,
		ShootingArrowMobileEntityTest.class, SingleWinConditionTest.class, SwitchTileTest.class,
		SwordUsableEntityTest.class, TileTest.class, TreasureEntityTest.class, UsableEntityTest.class,
		WallTileTest.class, WinSystemTest.class })
public class AllTests {

}
