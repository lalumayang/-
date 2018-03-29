package tw.edu.ntut.csie.game.state;

import android.util.Log;

import java.util.List;
import java.util.Map;

import tw.edu.ntut.csie.game.Game;
import tw.edu.ntut.csie.game.Pointer;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.Audio;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.engine.GameEngine;
import tw.edu.ntut.csie.game.extend.Animation;
import tw.edu.ntut.csie.game.extend.BitmapButton;
import tw.edu.ntut.csie.game.extend.ButtonEventHandler;
import tw.edu.ntut.csie.game.extend.Integer;


public class StateRun extends GameState  {
    public static final int DEFAULT_SCORE_DIGITS = 4;
    private MovingBitmap _background;
    private MovingBitmap _android;
    private MovingBitmap _cloud;
    private MovingBitmap _door;
    private MovingBitmap _message;
    private BitmapButton _homeButton;
    private BitmapButton _buyButton;
    private BitmapButton _menuButton;
    private BitmapButton _m1;
    private Animation _flower;
    private Integer _scores;
    private boolean _grab;
    private int _cx, _cy;
    private int _bx,_by=0;
    private  int ay;
    private int distance=0;
    private Audio _music;

    public StateRun(GameEngine engine) {
        super(engine);
    }

    @Override
    public void initialize(Map<String, Object> data) {
        _m1=new BitmapButton(R.drawable.m1,20,20);
        _background = new MovingBitmap(R.drawable.background);
        _bx=0;
        _by=0;
        _background.setLocation(_bx,_by);
        _message = new MovingBitmap(R.drawable.message, 130, 150);
        _android = new MovingBitmap(R.drawable.android_green);
        _android.setLocation(100, 200);

        _cloud = new MovingBitmap(R.drawable.cloud);
        _cx = 100;
        _cy = 50;
        _cloud.setLocation(_cx, _cy);

        _door = new MovingBitmap(R.drawable.door);
        _door.setLocation(300, 200);

        _scores = new Integer(DEFAULT_SCORE_DIGITS, 50, 550, 10);

        _flower = new Animation();
        _flower.setLocation(560, 310);
        _flower.addFrame(R.drawable.flower1);
        _flower.addFrame(R.drawable.flower2);
        _flower.addFrame(R.drawable.flower3);
        _flower.addFrame(R.drawable.flower4);
        _flower.addFrame(R.drawable.flower5);
        _flower.setDelay(2);

        _music = new Audio(R.raw.ntut);
        _music.setRepeating(true);
        _music.play();

        _grab = false;

        _menuButton=new BitmapButton(R.drawable.menubutton,5,5);
        _buyButton=new BitmapButton(R.drawable.buybutton,512,5);
        _homeButton=new BitmapButton(R.drawable.homebutton,577,5);



    }





    @Override
    public void move() {
        _flower.move();
        _cloud.setLocation(_cx, _cy);
    }

    @Override
    public void show() {
        // �I�s���Ǭ��K�϶���
        _background.show();

     /*   _scores.show();
        _flower.show();
        _message.show();
        _cloud.show();
        _door.show();
        _android.show();*/
        _buyButton.show();
        _homeButton.show();
        _menuButton.show();
        _m1.show();
    }

    @Override
    public void release() {
        _background.release();
        _scores.release();
        _android.release();
        _flower.release();
        _message.release();
        _cloud.release();
        _music.release();
        _door.release();
        _buyButton.release();
        _homeButton.release();
        _menuButton.release();


        _background = null;
        _scores = null;
        _android = null;
        _flower = null;
        _message = null;
        _cloud = null;
        _music = null;
        _door = null;
        _buyButton=null;
        _homeButton=null;
        _menuButton=null;
    }

    @Override
    public void keyPressed(int keyCode) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyReleased(int keyCode) {
        // TODO Auto-generated method stub
    }

    @Override
    public void orientationChanged(float pitch, float azimuth, float roll) {
        if (roll > 15 && roll < 60 && _cx > 50)
            _cx -= 2;
        if (roll < -15 && roll > -60 && _cx + _cloud.getWidth() < 500)
            _cx += 2;
    }

    @Override
    public void accelerationChanged(float dX, float dY, float dZ) {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean pointerPressed(Pointer actionPointer, List<Pointer> pointers) {
        _message.setVisible(false);
        ay=actionPointer.getY();
        return true;
    }

    @Override
    public boolean pointerMoved(Pointer actionPointer, List<Pointer> pointers) {
        distance =(actionPointer.getY()-ay);
            if(distance>0 && _by<-50) {
                if(distance>60)distance=60;
               _by=_by+distance;
                _background.setLocation(0, _by );
            }
            if(distance<0 && _by>-175){
                if(distance<-40)distance=-40;
                _by=_by+distance;
                _background.setLocation(0, _by );

            }



        return true;

    }

    public void resizeAndroidIcon() {

    }

    @Override
    public boolean pointerReleased(Pointer actionPointer, List<Pointer> pointers) {
        _grab = false;
        distance=0;
        return true;
    }

    @Override
    public void pause() {
        _music.pause();
    }

    @Override
    public void resume() {
        _music.resume();
    }
}
