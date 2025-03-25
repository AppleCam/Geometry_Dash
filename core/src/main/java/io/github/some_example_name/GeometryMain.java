package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import jdk.tools.jlink.internal.Platform;

public class GeometryMain extends ApplicationAdapter {
    private Player player;
    private Platforms platform;
    private Platforms platform2;
    private Platforms platform3;
    private Portal portal;


    @Override
    public void create() {
        // Initialize player, platforms, and portal objects
        player = new Player(110,100,40,40);
        platform = new Platforms(110,90,200,10);
        platform2 = new Platforms(450,175,222,10);
        platform3 = new Platforms(800,500,222,10);
        portal = new Portal(700,275,10,100);
    }

    @Override
    public void render() {
        // Clear the screen with a specific color
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);


        // Draw and move the player
        player.draw();
        player.move();

        // Draw, check collision, and move the first platform
        platform.draw();
        platform.player2PlatformCollision(player);
        //platform.platformMove();

        // Draw, check collision, and move the second platform
        platform2.draw();
        platform2.player2PlatformCollision(player);
        //platform2.platformMove();

        //Draw, check collision, and move the third platform
        platform3.draw();
        platform3.player2PlatformCollision(player);

        // Draw the portal
        portal.draw();
        portal.flipGravity(player);
    }

    @Override
    public void dispose() {
        // Dispose of the player object
        player.dispose();
    }
}
