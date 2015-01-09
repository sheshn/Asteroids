package com.shesh.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.shesh.game.object.Asteroid;
import com.shesh.game.object.Bullet;
import com.shesh.game.object.Explosion;
import com.shesh.game.object.Ship;

import java.util.ArrayList;
import java.util.List;

public class Level
{
    private static final int MAX_LIVES = 3;
    private static final float LEVEL_START_DELAY = 1.8f;
    private static final float DEATH_DELAY = 1.5f;
    private static final float GAME_OVER_DELAY = 1.8f;

    private static final int STARTING_ASTEROIDS = 2;

    private int score;
    private int lives;
    private int levelNumber;
    private StatusBar statusBar;

    private Ship ship;
    private List<Bullet> playerBullets;
    private List<Asteroid> asteroids;
    private List<Explosion> explosions;

    private boolean startSpawn = false;
    private float levelStartDelay = 0;
    private float gameOverDelay = 0;
    private float deathDelay = 0;

    private boolean gameStarted;

    public Level()
    {
        gameStarted = false;
        asteroids = new ArrayList<Asteroid>();

        spawnAsteroids(STARTING_ASTEROIDS);
    }

    public void update(float delta)
    {
        if (gameStarted) {
            if (!ship.isDead())
                ship.update(delta);

            for (int i = 0; i < playerBullets.size(); i++) {
                Bullet b = playerBullets.get(i);
                b.update(delta);

                if (b.isRemovable())
                    playerBullets.remove(b);
            }

            for (int i = 0; i < explosions.size(); i++) {
                Explosion e = explosions.get(i);
                e.update(delta);

                if (e.isRemovable())
                    explosions.remove(e);
            }

            if (asteroids.isEmpty() && !startSpawn) {
                levelStartDelay = LEVEL_START_DELAY;
                startSpawn = true;
            }

            if (levelStartDelay == 0 && startSpawn) {
                levelNumber++;
                spawnAsteroids(levelNumber + STARTING_ASTEROIDS - 1);
                startSpawn = false;
            }

            checkCollisions();
            updateTimers(delta);
        }

        for (int i = 0; i < asteroids.size(); i++) {
            Asteroid a = asteroids.get(i);
            a.update(delta);

            if (a.isRemovable())
                asteroids.remove(a);
        }
    }

    public void render(ShapeRenderer renderer)
    {
        for (Asteroid a : asteroids)
            a.render(renderer);

        if (gameStarted) {
            if (!ship.isDead())
                ship.render(renderer);

            for (Bullet b : playerBullets)
                b.render(renderer);

            for (Explosion e : explosions)
                e.render(renderer);

            statusBar.render(renderer);
        }
    }

    private void checkCollisions()
    {
        for (int i = 0; i < asteroids.size(); i++)
        {
            Asteroid a = asteroids.get(i);

            if (!ship.isInvincible() && !ship.isDead())
            {
                if (a.checkCollision(ship))
                {
                    a.handleCollision(this, ship);
                    ship.handleCollision(this, a);

                    lives--;

                    if (lives < 0)
                        gameOverDelay = GAME_OVER_DELAY;
                    else
                        deathDelay = DEATH_DELAY;
                }
            }

            for (int j = 0; j < playerBullets.size(); j++)
            {
                Bullet b = playerBullets.get(j);

                if (a.checkCollision(b))
                {
                    a.handleCollision(this, b);
                    b.handleCollision(this, a);
                }
            }
        }
    }

    private void spawnAsteroids(int amount)
    {
        for (int i = 0; i < amount; i++)
            asteroids.add(new Asteroid());
    }

    private void updateTimers(float delta)
    {
        levelStartDelay = levelStartDelay > 0 ? levelStartDelay - delta : 0;
        deathDelay = deathDelay > 0 ? deathDelay - delta : 0;
        gameOverDelay = gameOverDelay > 0 ? gameOverDelay - delta : 0;

        if (deathDelay <= 0 && ship.isDead() && lives >= 0)
            ship.reset();

    }

    public void startGame() {
        lives = MAX_LIVES;
        levelNumber = 1;
        statusBar = new StatusBar(this);

        playerBullets = new ArrayList<Bullet>();
        explosions = new ArrayList<Explosion>();

        ship = new Ship(playerBullets);

        gameStarted = true;
    }
    
    public StatusBar getStatusBar() { return statusBar; }

    public List<Asteroid> getAsteroids()
    {
        return asteroids;
    }

    public List<Explosion> getExplosions()
    {
        return explosions;
    }

    public void addScore(int amount)
    {
        score += amount;
    }

    public int getScore()
    {
        return score;
    }

    public int getLives()
    {
        return lives;
    }

    public int getLevelNumber()
    {
        return levelNumber;
    }

    public boolean isGameOver()
    {
        return gameOverDelay <= 0 && lives < 0;
    }
}
