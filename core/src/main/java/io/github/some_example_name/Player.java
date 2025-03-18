package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Player {

    // Player attributes
    public float x;
    public float y;
    public int width;
    public int height;
    public float dx = 5;
    public float dy = 0; // Initial vertical velocity is 0
    private ShapeRenderer shape;
    public Boolean isalive = true;
    public float acc = -600; // Increased gravity
    public float time = 0;
    public float jumpVelocity = 300; // Adjusted jump velocity
    public float v = dy + (acc * time); // Calculate initial velocity (not used directly)
    ///V = u + (a*t)

    // Constructor to initialize player properties
    public Player(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        shape = new ShapeRenderer();
    }

    // Method to draw the player
    public void draw() {
        // Begin drawing with a filled rectangle
        shape.begin(ShapeRenderer.ShapeType.Filled);
        // Draw the player rectangle
        shape.rect(x, y, width, height);
        // End drawing
        shape.end();
    }

    // Method to handle player movement and physics
    public void move() {
        // Handle left/right movement
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            x -= dx;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            x += dx;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.R)){
            y = 110;
        }

        // Apply gravity continuously
        dy += acc * Gdx.graphics.getDeltaTime();

        // Handle jumping
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            // Only jump if the player is on the ground
            if (y >= 0) {
                if (acc > 0) {
                    dy = -jumpVelocity; // Set the initial downward velocity for flipped gravity
                } else {
                    dy = jumpVelocity; // Set the initial upward velocity for normal gravity
                }
                time = 0; // Reset the time
            }
        }

        // Update position based on velocity
        y += dy * Gdx.graphics.getDeltaTime();

        // Keep the player within the screen bounds (adjust as needed)
        if (y < 0) {
            y = 0;
            dy = 0; // Stop the player from going below the ground
            time = 0; // Reset the time
        }
    }

    // Getters and setters for player attributes
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getDx() {
        return dx;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public float getDy() {
        return dy;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public ShapeRenderer getShape() {
        return shape;
    }

    public void setShape(ShapeRenderer shape) {
        this.shape = shape;
    }

    public Boolean getIsalive() {
        return isalive;
    }

    public void setIsalive(Boolean isalive) {
        this.isalive = isalive;
    }

    public float getAcc() {
        return acc;
    }

    public void setAcc(float acc) {
        this.acc = acc;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public float getJumpVelocity() {
        return jumpVelocity;
    }

    public void setJumpVelocity(float jumpVelocity) {
        this.jumpVelocity = jumpVelocity;
    }

    public float getV() {
        return v;
    }

    public void setV(float v) {
        this.v = v;
    }

    public void dispose() {
        // Dispose of the ShapeRenderer
        shape.dispose();
    }
}
