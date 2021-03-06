package tw.edu.ntut.csie.game.state;

import android.util.Log;

import java.util.List;
import java.util.Map;


import tw.edu.ntut.csie.game.Game;
import tw.edu.ntut.csie.game.Pointer;
import tw.edu.ntut.csie.game.PointerEventHandler;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.Audio;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.engine.GameEngine;
import tw.edu.ntut.csie.game.extend.Animation;
import tw.edu.ntut.csie.game.extend.BitmapButton;
import tw.edu.ntut.csie.game.extend.ButtonEventHandler;
import tw.edu.ntut.csie.game.extend.Integer;


public class StateRun extends AbstractGameState  {
    public static final int DEFAULT_SCORE_DIGITS = 4;
    private MovingBitmap _background;

    private BitmapButton _homeButton;
    private BitmapButton _buyButton;
    private BitmapButton _menuButton;
    private int x,y;
    private boolean _grab;
    private int _bx,_by=0;
    private  int ay;
    private int distance=0;
    private Audio _music;

    public StateRun(GameEngine engine) {
        super(engine);
    }

    @Override
    public void initialize(Map<String, Object> data) {
        _background = new MovingBitmap(R.drawable.background);
        _bx=0;
        _by=0;
        _background.setLocation(_bx,_by);
        //_scores = new Integer(DEFAULT_SCORE_DIGITS, 50, 550, 10);
        _music = new Audio(R.raw.ntut);
        _music.setRepeating(true);
        _music.play();
        _grab = false;

        _menuButton=new BitmapButton(R.drawable.menubutton,5,3);
        _buyButton=new BitmapButton(R.drawable.buybutton,525,3);
        _homeButton=new BitmapButton(R.drawable.homebutton,580,3);


    }


    @Override
    public void move() {

    }
    @Override
    public void show() {
        // �I�s���Ǭ��K�϶���
        _background.show();
        _buyButton.show();
        _homeButton.show();
        _menuButton.show();

    }

    @Override
    public void release() {
        _background.release();
        _music.release();
        _buyButton.release();
        _homeButton.release();
        _menuButton.release();


        _background = null;

        _music = null;

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

    }

    @Override
    public void accelerationChanged(float dX, float dY, float dZ) {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean pointerPressed(Pointer actionPointer, List<Pointer> pointers) {
        ay=actionPointer.getY();
        x=actionPointer.getX();
        y=actionPointer.getY();
        if((x>=527 &&x<=574) &&(y>=8&&y<=53)){
            changeState(Game.SHOP_STATE);
        }


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
