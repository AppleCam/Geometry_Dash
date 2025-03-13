package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Platforms {

    public int x,y,width,height,dy,dx = 10;
    private ShapeRenderer shape;
    public boolean allowMovement = true;

    public Platforms(int x,int y,int width,int height){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        shape = new ShapeRenderer();
    }

    public void draw(){
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(0.4f,0.8f,2.5f,0f);
        shape.rect(x,y,width,height);
        shape.end();

    }

    public void player2PlatformCollision(Player player) {
        if (player.getY() + player.getHeight() >= y && player.getY() <= y + height) {
            if (player.getX() + player.getWidth() >= x && player.getX() <= x + width) {
                // Player is colliding with the platform
                player.setY(y + height);
            }
        }
    }

    public void platformMove(){
        if (allowMovement == true){
            x -= dx;
            if (x + width == 0) {
                x = Gdx.graphics.getWidth();
            }
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
