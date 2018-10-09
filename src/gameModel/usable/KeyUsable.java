package gameModel.usable;

import gameModel.mobileEntity.PlayerMobileEntity;

public class KeyUsable implements Usable {

private static int keyCodeGenerator = 1;
	private int keyCode;
	
	private static synchronized int generateKeyCode() {
		return keyCodeGenerator++;
	}
	

	public KeyUsable() {
		this.keyCode = KeyUsable.generateKeyCode();
	}
	
	@Override
	public void applyToPlayer(PlayerMobileEntity player) {
		player.setKeyCode(keyCode);
	}

	@Override
	public boolean use(UseAction action) {
		return false;
	}


	@Override
	public UseAction getUseAction() {
		return UseAction.KEY;
	}
}
