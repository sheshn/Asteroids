package com.shesh.game;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class Input extends InputAdapter {

    public static final int LEFT = Keys.LEFT;
    public static final int UP = Keys.UP;
    public static final int RIGHT = Keys.RIGHT;
    public static final int DOWN = Keys.DOWN;

    public static final int SPACE = Keys.SPACE;
    public static final int ENTER = Keys.ENTER;
    public static final int P = Keys.P;
    public static final int ESC = Keys.ESCAPE;

    public static final int LEFT_MOUSE = Buttons.LEFT;
    public static final int RIGHT_MOUSE = Buttons.RIGHT;

    private static boolean[] keys = new boolean[256];
    private static boolean[] previousKeys = new boolean[256];

    public void update() {
        for (int i = 0; i < keys.length; i++) {
            previousKeys[i] = keys[i];
        }
    }


    public boolean isKeyDown(int keycode) {
        return keys[keycode];
    }

    public boolean isKeyPressed(int keycode) {
        return keys[keycode] && !previousKeys[keycode];
    }

    @Override
    public boolean keyDown(int keycode) {
        keys[keycode] = true;
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        keys[keycode] = false;
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//        if (!Gdx.app.getType().equals(ApplicationType.Android)) {
//            return false;
//        }
//
//        if (screenX < Gdx.graphics.getWidth() / 2 - 100 && screenY > Gdx.graphics.getHeight()/2) {
//            keys[Keys.UP] = true;
//        }
//
//        if (screenX > Gdx.graphics.getWidth() / 2 + 100 && screenY > Gdx.graphics.getHeight()/2) {
//            keys[Keys.Z] = true;
//        }
//
//        return false;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
//        if (!Gdx.app.getType().equals(ApplicationType.Android)) {
//            return false;
//        }
//
//        if (keys[Keys.X]) {
//            keys[Keys.X] = false;
//        }
//
//        if (screenX < Gdx.graphics.getWidth() / 2 - 100 && screenY > Gdx.graphics.getHeight()/2) {
//            keys[Keys.UP] = false;
//        }
//
//        if (screenX > Gdx.graphics.getWidth() / 2 + 100 && screenY > Gdx.graphics.getHeight()/2) {
//            keys[Keys.Z] = false;
//        }
//
//        return false;
        return false;
    }
}
//package com.shesh.engine;
//
//import java.awt.event.*;
//
//public class Input extends KeyAdapter implements MouseMotionListener, MouseListener
//{
//    private static boolean[] keys = new boolean[256];
//    private static boolean[] prevKeys = new boolean[256];
//
//    private static boolean[] mouseButtons = new boolean[3];
//    private static boolean[] prevMouseButtons = new boolean[3];
//
//    public static final int LEFT = Keys.LEFT;
//    public static final int UP = Keys.UP;
//    public static final int RIGHT = Keys.RIGHT;
//    public static final int DOWN = Keys.DOWN;
//
//    public static final int SPACE = Keys.SPACE;
//    public static final int ENTER = Keys.ENTER;
//    public static final int P = Keys.P;
//    public static final int ESC = Keys.ESCAPE;
//
//    public static final int LEFT_MOUSE = MouseEvent.BUTTON1;
//    public static final int RIGHT_MOUSE = MouseEvent.BUTTON2;
//
//    private static int mouseX;
//    private static int mouseY;
//
//    public static void update()
//    {
//        for (int i = 0; i < keys.length; i++)
//            prevKeys[i] = keys[i];
//
//        for (int i = 0; i < mouseButtons.length; i++)
//            prevMouseButtons[i] = mouseButtons[i];
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e)
//    {
//        keys[e.getKeyCode()] = true;
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e)
//    {
//        keys[e.getKeyCode()] = false;
//    }
//
//    public static boolean isKeyDown(int keyCode)
//    {
//        return keys[keyCode];
//    }
//
//    public static boolean isKeyPressed(int keycode)
//    {
//        return keys[keycode] && !prevKeys[keycode];
//    }
//
//    public static boolean isMouseDown(int button)
//    {
//        return mouseButtons[button];
//    }
//
//    public static boolean isMouseClicked(int button)
//    {
//        return mouseButtons[button] && !prevMouseButtons[button];
//    }
//
//    @Override
//    public void mouseDragged(MouseEvent e)
//    {
//
//    }
//
//    @Override
//    public void mouseMoved(MouseEvent e)
//    {
//        mouseX = (int) e.getPoint().getX();
//        mouseY = (int) e.getPoint().getY();
//    }
//
//    @Override
//    public void mouseClicked(MouseEvent e)
//    {
//
//    }
//
//    @Override
//    public void mousePressed(MouseEvent e)
//    {
//        mouseButtons[e.getButton()] = true;
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e)
//    {
//        mouseButtons[e.getButton()] = false;
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent e)
//    {
//
//    }
//
//    @Override
//    public void mouseExited(MouseEvent e)
//    {
//
//    }
//
//    public static int getMouseX()
//    {
//        return mouseX;
//    }
//
//    public static int getMouseY()
//    {
//        return mouseY;
//    }
//}