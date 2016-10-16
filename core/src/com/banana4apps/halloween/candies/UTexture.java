package com.banana4apps.halloween.candies;

import com.badlogic.gdx.graphics.Texture;

public class UTexture extends Texture {

    public UTexture(String internalPath) {
        super(internalPath);
    }

    public float getRatio()
    {
        return (float)getHeight()/getWidth();
    }

}
