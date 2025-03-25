// Platforms.java
package io.github.some_example_name;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.Gdx;

public class Platforms {

    public int x, y, width, height, dy, dx = 5;
    private ShapeRenderer shape;
    public boolean allowMovement = true;

    public Platforms(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        shape = new ShapeRenderer();
    }

    public void draw() {
        // Draw the platform
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(0.4f, 0.8f, 2.5f, 0f);
        shape.rect(x, y, width, height);
        shape.end();
    }

    public void player2PlatformCollision(Player player) {
        // Check for collision with the player
        if (player.getY() + player.getHeight() >= y && player.getY() <= y + height) {
            if (player.getX() + player.getWidth() >= x && player.getX() <= x + width) {
                if (player.getAcc() > 0) {
                    player.setY(y - player.getHeight());
                } else {
                    player.setY(y + height);
                }
                player.setDy(0);
            }
        }
    }

    public void platformMove() {
        // Move the platform to the left
        if (allowMovement) {
            x -= dx;
            if (x + width < 0) {
                x = Gdx.graphics.getWidth();
            }
        }
    }

    public static List<Platforms> generateRandomPlatforms(int startX, int startY, int maxJumpHeight) {
        List<Platforms> platforms = new ArrayList<>();
        Random random = new Random();
        int x = startX;
        int previousY = startY;
        for (int i = 0; i < 5; i++) {
            int y;
            do {
                y = 100 + random.nextInt(200) - 100; // Ensure minimum y is 100
            } while (y > previousY + maxJumpHeight || y < previousY - maxJumpHeight); // Ensure the new platform is within jump height
            int width = random.nextInt(100) + 50;
            int height = random.nextInt(20) + 10;
            platforms.add(new Platforms(x, y, width, height));
            x += 300; // Fixed horizontal distance between platforms
            previousY = y;
        }
        return platforms;
    }
}
