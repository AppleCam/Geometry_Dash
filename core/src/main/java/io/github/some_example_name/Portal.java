package io.github.some_example_name;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.Gdx;
import java.util.Random;

public class Portal {

    public float posX, posY, MinorRadius, MajorRadius;
    private ShapeRenderer shapeRenderer;

    public Portal(float posX, float posY, float MinorRadius, float MajorRadius) {
        this.posX = posX;
        this.posY = posY;
        this.MinorRadius = MinorRadius;
        this.MajorRadius = MajorRadius;
        shapeRenderer = new ShapeRenderer();
    }

    public void draw() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.ellipse(posX, posY, MinorRadius, MajorRadius);
        shapeRenderer.end();
    }

    public boolean checkCollision(Player player) {
        float previousPlayerX = player.getX() - player.getDx() * Gdx.graphics.getDeltaTime();
        float previousPlayerY = player.getY() - player.getDy() * Gdx.graphics.getDeltaTime();
        return lineIntersectsEllipse(previousPlayerX, previousPlayerY, player.getX(), player.getY(), posX, posY, MinorRadius, MajorRadius);
    }

    private boolean lineIntersectsEllipse(float x1, float y1, float x2, float y2, float ellipseX, float ellipseY, float ellipseWidth, float ellipseHeight) {
        float centerX = ellipseX + ellipseWidth / 2;
        float centerY = ellipseY + ellipseHeight / 2;
        float radiusX = ellipseWidth / 2;
        float radiusY = ellipseHeight / 2;

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

    public void flipGravity(Player player) {
        if (checkCollision(player)) {
            player.setAcc(player.getAcc() * -1);
        }
    }

    public static Portal generateRandomPortal(int previousX, int previousY) {
        Random random = new Random();
        float posX = previousX + 300; // Fixed horizontal distance
        float posY = previousY + random.nextInt(200) - 100; // Allow some vertical variation
        float MinorRadius = random.nextInt(20) + 10;
        float MajorRadius = random.nextInt(100) + 50;
        return new Portal(posX, posY, MinorRadius, MajorRadius);
    }
}
