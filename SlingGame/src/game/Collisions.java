package game;

import objects.EnemyObjects;
import objects.PlayerObjects;

public class Collisions {

	int playerW;
	int playerH;

	// player to enemy collision
	public static boolean Collision(PlayerObjects playerObject, EnemyObjects enemyObject) {

		if (playerObject.getBounds().intersects(enemyObject.getBounds())) {
			return true;
		}

		return false;

	}

	// enemy to player collision
	public static boolean Collision(EnemyObjects enemyObject, PlayerObjects playerObject) {

		if (enemyObject.getBounds().intersects(playerObject.getBounds())) {
			return true;
		}

		return false;

	}

}
