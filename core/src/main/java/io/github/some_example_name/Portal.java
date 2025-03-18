package io.github.some_example_name;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Portal {

    // Attributes for the portal
    public Player player; // Reference to the player object
    public float x, y, height,width; // Position and size of the portal
    private ShapeRenderer shape; // ShapeRenderer for drawing the portal

    // Constructor to initialize the portal
    public Portal(float x, float y, float height, float width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        shape = new ShapeRenderer();
    }

    // Method to draw the portal
    public void draw() {

        shape.begin(ShapeRenderer.ShapeType.Filled);
        // Set the color of the portal to red
        shape.setColor(Color.RED);
        // Draw the portal as a circle
        // Begin drawing with a filled shape
        shape.ellipse(x, y, height, width);
        // End drawing
        shape.end();
    }

    // Method to flip the player's gravity when they enter the portal
    public void flipGravity(Player player) {
        // Check if the player is within the portal's bounds
        if (player.getX() + player.getWidth() >= x && player.getX() <= x + width && player.getY() + player.getHeight() >= y && player.getY() <= y + height) {
            // Flip the player's gravity by multiplying their acceleration by -1
            player.setAcc(player.getAcc() * -1);
        }
    }
}
