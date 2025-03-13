package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Platforms {

    // Define platform attributes
    public int x, y, width, height, dy, dx = 5;
    private ShapeRenderer shape;
    public boolean allowMovement = true;

    // Constructor to initialize platform properties
    public Platforms(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        shape = new ShapeRenderer();
    }

    // Method to draw the platform
    public void draw() {
        // Begin drawing with a filled rectangle
        shape.begin(ShapeRenderer.ShapeType.Filled);
        // Set the color of the platform
        shape.setColor(0.4f, 0.8f, 2.5f, 0f);
        // Draw the platform rectangle
        shape.rect(x, y, width, height);
        // End drawing
        shape.end();
    }

    // Method to check collision between player and platform
    public void player2PlatformCollision(Player player) {
        // Check if the player's bottom is above the platform's top and the player's top is below the platform's bottom
        if (player.getY() + player.getHeight() >= y && player.getY() <= y + height) {
            // Check if the player's right is to the right of the platform's left and the player's left is to the left of the platform's right
            if (player.getX() + player.getWidth() >= x && player.getX() <= x + width) {
                // Player is colliding with the platform
                // Set the player's Y position to the top of the platform
                player.setY(y + height);

                // Move the player slightly to the left to prevent getting stuck
                player.setX(player.getX() - dx);
            }
        }
    }

    // Method to move the platform horizontally
    public void platformMove() {
        // Check if movement is allowed
        if (allowMovement == true) {
            // Move the platform to the left
            x -= dx;
            // If the platform goes off-screen on the left, reset its position to the right edge
            if (x + width == 0) {
                x = Gdx.graphics.getWidth();
            }
        }
    }

    // Getters and setters for the platform's X position
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
