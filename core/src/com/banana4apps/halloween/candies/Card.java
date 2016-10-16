package com.banana4apps.halloween.candies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Card {

    public static int Width;
    public static int Height;
    public boolean played = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (Level != card.Level) return false;
        return Type == card.Type;

    }

    @Override
    public int hashCode() {
        int result = Type.hashCode();
        result = 31 * result + Level;
        return result;
    }

    public boolean drag = false;

    public void flip() {
        face = TextureFiles.Card_Face[Level+Type.ordinal()*6];
    }

    public enum CardType { ROCK, PAPER, SCISSORS }

    public CardType Type;
    public int Level;

    public float x, y;
    protected float t_x, t_y;
    public Rectangle box;

    protected Texture face;

    public Card(int type, int level)
    {
        Type = CardType.values()[type];
        Level = level;
        face = TextureFiles.Card_Back;
        box = new Rectangle(x, y, Width, Height);
    }

    public void setPosition(float x, float y, boolean player)
    {
        this.x = x;
        this.y = y;
        box.setPosition(x,y);
        face = TextureFiles.Card_Back;
        if (player)
            face = TextureFiles.Card_Face[Level+Type.ordinal()*6];
//        else
    }

    public void setTargetPosition(float x, float y)
    {
        this.t_x = x;
        this.t_y = y;
    }


    public boolean update() {
        float dx = t_x - x;
        float dy = t_y - y;
        if (Math.abs(Math.sqrt(dx*dx + dy*dy)) < 1)
        {
            x = t_x;
            y = t_y;
            box.setPosition(x,y);
            return false;
        }
        x += dx/3;
        y += dy/3;
        box.setPosition(x,y);
        return true;
    }

    boolean fliping = false;
    public boolean fl = false;
    float scale = 1;
    public boolean turn()
    {
        fliping = true;
        if (!fl) scale -= 0.15f;
        else scale += 0.15f;

        if (scale <= 0.09f && scale >= -0.05f) {
            if (face == TextureFiles.Card_Back)
                face = TextureFiles.Card_Face[Level+Type.ordinal()*6];
            else face = TextureFiles.Card_Back;
            fl = true;
        }

        if (scale >= 1)
        {
            scale = 1;
            fliping = false;
//            fl = false;
            return true;
        }

        return false;
    }

    public int Fight(Card card)
    {
        if (card.Type == Type)
        {
            if (Level == 0 && card.Level == 5) return 1;
            if (Level == 5 && card.Level == 0) return -1;
            return Level - card.Level;
        }

        if (   (Type == CardType.PAPER && card.Type == CardType.ROCK)
            || (Type == CardType.SCISSORS && card.Type == CardType.PAPER)
            || (Type == CardType.ROCK && card.Type == CardType.SCISSORS))
        {
            return 1;
        }

        return -1;
    }

    public void draw(SpriteBatch batch)
    {
        if (fliping)
        {
            batch.draw(face, x, y, Width/2, Height/2,
                    Width, Height, scale, 1,
                    0, 0, 0,
                    face.getWidth(), face.getHeight(), false, false);
            return;
        }

        batch.draw(face, x, y, Width, Height);
    }

}
