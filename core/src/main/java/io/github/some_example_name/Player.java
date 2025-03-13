package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Player {

    public float x;
    public float y;
    public int width;
    public int height;
    public float dx = 5;
    public float dy = 0; // Initial vertical velocity is 0
    private ShapeRenderer shape;
    public Boolean isalive = true;
    public float acc = -175; // Gravity
    public float time = 0;
    public float jumpVelocity = 100; // Initial upward velocity for jump
    public float v = dy + (acc*time);
    ///V = u + (a*t)

    public Player(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        shape = new ShapeRenderer();
    }

    public void draw(){
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.rect(x,y,width,height);
        shape.end();
    }

    public void move() {
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            x -= dx;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            x += dx;
        }

        // Apply gravity continuously
        dy += acc * Gdx.graphics.getDeltaTime();

        // Jump
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if (y >= 0) { // Make sure the player is on the ground before jumping
                dy = jumpVelocity; // Set the initial upward velocity
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

    public void dispose(){

    }
}
