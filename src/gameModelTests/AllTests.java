package gameModelTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ArrowMobileEntityTest.class,
		BoulderMobileEntityTest.class, CoordTest.class, DoorTileTest.class, EnemyMobileEntityTest.class,
		EntityTest.class, ExitTileTest.class, HoverPotionTest.class, InvincibilityEntityTest.class,
		MobileEntityTest.class, TreasureEntityTest.class, WallTileTest.class })
public class AllTests {

}
