// GeometryMain.java
package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class GeometryMain extends ApplicationAdapter {
    private Player player;
    private List<Platforms> platforms;
    private Portal portal;

    @Override
    public void create() {
        // Initialize player with starting position and size
        player = new Player(110, 100, 40, 40);
        int maxJumpHeight = 150; // Set this to the player's maximum jump height
        // Generate initial set of platforms
        platforms = Platforms.generateRandomPlatforms(0, 0, maxJumpHeight);
        // Generate initial portal based on the last platform's position
        portal = Portal.generateRandomPortal(platforms.get(platforms.size() - 1).x, platforms.get(platforms.size() - 1).y);
    }

    @Override
    public void render() {
        // Clear the screen with a specific color
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        // Draw and move the player
        player.draw();
        player.move();

        // Iterate through platforms and update their state
        Iterator<Platforms> iterator = platforms.iterator();
        while (iterator.hasNext()) {
            Platforms platform = iterator.next();
            platform.draw();
            platform.player2PlatformCollision(player);
            platform.platformMove();

            // Remove platform if it leaves the screen
            if (platform.x + platform.width < 0) {
                iterator.remove();
            }
        }

        // Draw the portal and check for gravity flip
        portal.draw();
        portal.flipGravity(player);

        // Generate new platforms and portal if the last platform leaves the screen
        if (platforms.isEmpty() || platforms.get(platforms.size() - 1).x + platforms.get(platforms.size() - 1).width < 0) {
            int maxJumpHeight = 150; // Set this to the player's maximum jump height
            List<Platforms> newPlatforms = Platforms.generateRandomPlatforms(platforms.isEmpty() ? 0 : platforms.get(platforms.size() - 1).x, platforms.isEmpty() ? 0 : platforms.get(platforms.size() - 1).y, maxJumpHeight);
            platforms.addAll(newPlatforms);
            portal = Portal.generateRandomPortal(newPlatforms.get(newPlatforms.size() - 1).x, newPlatforms.get(newPlatforms.size() - 1).y);
        }
    }

    @Override
    public void dispose() {
        // Dispose of player resources
        player.dispose();
    }
}
