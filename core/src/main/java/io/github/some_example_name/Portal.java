package io.github.some_example_name;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.Gdx;

public class Portal {

    // Attributes for the portal
    public float posX, posY, MinorRadius, MajorRadius; // Position and size of the portal
    private ShapeRenderer shapeRenderer; // ShapeRenderer for drawing the portal

    // Constructor to initialize the portal
    public Portal(float posX, float posY, float MinorRadius, float MajorRadius) {
        this.posX = posX;
        this.posY = posY;
        this.MinorRadius = MinorRadius;
        this.MajorRadius = MajorRadius;
        shapeRenderer = new ShapeRenderer();
    }

    // Method to draw the portal
    public void draw() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.ellipse(posX, posY, MinorRadius, MajorRadius);
        shapeRenderer.end();
    }

    // Method to check collision with the player
    public boolean checkCollision(Player player) {
        // Calculate the player's previous position
        float previousPlayerX = player.getX() - player.getDx() * Gdx.graphics.getDeltaTime();
        float previousPlayerY = player.getY() - player.getDy() * Gdx.graphics.getDeltaTime();

        // Check for collisions along the path from the previous position to the current position
        return lineIntersectsEllipse(previousPlayerX, previousPlayerY, player.getX(), player.getY(), posX, posY,MinorRadius, MajorRadius);
    }

    // Helper method to check if a line intersects an ellipse
    private boolean lineIntersectsEllipse(float x1, float y1, float x2, float y2, float ellipseX, float ellipseY, float ellipseWidth, float ellipseHeight) {
        // Calculate the center of the ellipse
        float centerX = ellipseX + ellipseWidth / 2;
        float centerY = ellipseY + ellipseHeight / 2;

        // Calculate the radii of the ellipse
        float radiusX = ellipseWidth / 2;
        float radiusY = ellipseHeight / 2;

        // Check if the line intersects the ellipse
        for (float t = 0; t <= 1; t += 0.01) {
            float xt = x1 + t * (x2 - x1);
            float yt = y1 + t * (y2 - y1);
            float dx = xt - centerX;
            float dy = yt - centerY;
            if ((dx * dx) / (radiusX * radiusX) + (dy * dy) / (radiusY * radiusY) <= 1) {
                return true;
            }
        }
        return false;
    }

    // Method to flip the player's gravity if collision is detected
    public void flipGravity(Player player) {
        if (checkCollision(player)) {
            player.setAcc(player.getAcc() * -1);
        }
    }
}
