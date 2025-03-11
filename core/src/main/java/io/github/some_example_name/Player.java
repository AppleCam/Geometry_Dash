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
    public float dy = 5;
    private ShapeRenderer shape;
    public Boolean isalive = true;
    public float acc = -10;
    public float time = 0;
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
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            for(int i = 0; i <= 10; i++){
                v = dy + (acc*time);
                time++;

            }
            dy = v;
        }
    }

    public void dispose(){

    }
}
