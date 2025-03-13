package io.github.some_example_name;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Portal {

    public Player player;
    public float x,y,circumfrence;
    private ShapeRenderer shape;

    public Portal(float x,float y,float circumfrence){
        this.x=x;
        this.y=y;
        this.circumfrence=circumfrence;
        shape = new ShapeRenderer();
    }

    public void draw(){
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.RED);
        shape.circle(x,y,circumfrence);
        shape.end();
    }

    public void flipGravity(Player player){}

}
