package com.banana4apps.halloween.candies.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.banana4apps.halloween.candies.TextureFiles;

import java.util.Random;

public class GameScreen extends Screen {


    int Player_Score = 0;
    int Enemy_Score = 0;

    BitmapFont font;

    com.banana4apps.halloween.candies.AI Ai;

    com.banana4apps.halloween.candies.Card[] cards_stack;

    com.banana4apps.halloween.candies.Card[] enemy_hand;
    com.banana4apps.halloween.candies.Card[] enemy_table;
    com.banana4apps.halloween.candies.Card[] player_hand;

    int player_hand_target = 0;
    Rectangle[] player_positions;

    com.banana4apps.halloween.candies.Card[] player_table;


    int height = Gdx.graphics.getHeight();
    int width = Gdx.graphics.getWidth();
    private int played_cards;

    ScreenManager manager;

    static void shuffleArray(com.banana4apps.halloween.candies.Card[] ar) {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            com.banana4apps.halloween.candies.Card a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    //Распределяем карты
    void distributeCard(){

        Random rnd = new Random();
        int inequaity = 3 + rnd.nextInt(2) + rnd.nextInt(2) + rnd.nextInt(2);
        int ap = rnd.nextInt(3);

        com.banana4apps.halloween.candies.Card.CardType type = com.banana4apps.halloween.candies.Card.CardType.ROCK;
        if (ap == 0) type = com.banana4apps.halloween.candies.Card.CardType.ROCK;
        if (ap == 1) type = com.banana4apps.halloween.candies.Card.CardType.PAPER;
        if (ap == 2) type = com.banana4apps.halloween.candies.Card.CardType.SCISSORS;
        int c = 0, p = 0, a = 0;

        for (int i = 0; i < 18; i++) {
            if (inequaity > 0) {
                if (cards_stack[i].Type == type) {
                    enemy_hand[c] = cards_stack[i];
                    c++;
                    inequaity--;
                } else {
                    if (p == 6) {
                        if (6 - a > c) {
                            enemy_hand[5-a] = cards_stack[i];
                            a++;
                        }
                        continue;
                    }
                    player_hand[p] = cards_stack[i];
                    p++;
                }
                continue;
            }

            if (c < 6)
            {
                if (i % 2 == 0) {
                    enemy_hand[c] = cards_stack[i];
                    c++;
                }
                else {
                    if (p == 6)
                    {
                        enemy_hand[c] = cards_stack[i];
                        c++;
                        continue;
                    }
                    player_hand[p] = cards_stack[i];
                    p++;
                }
            } else if (p < 6)
            {
                player_hand[p] = cards_stack[i];
                p++;
            }


        }
    }

    public GameScreen(ScreenManager m) {

        manager = m;

        com.banana4apps.halloween.candies.Card.Width = width/12;
        com.banana4apps.halloween.candies.Card.Height = (int) (com.banana4apps.halloween.candies.Card.Width* TextureFiles.Card_Back.getRatio());

        //Создаём массив карт
        cards_stack = new com.banana4apps.halloween.candies.Card[18];
        for (int level = 0; level < 6; level++)
            for (int type = 0; type < 3; type++) {
                cards_stack[level + type * 6] = new com.banana4apps.halloween.candies.Card(type, level);
                cards_stack[level + type * 6].setPosition(-com.banana4apps.halloween.candies.Card.Width, -com.banana4apps.halloween.candies.Card.Height, false);
            }
        shuffleArray(cards_stack);

        enemy_hand = new com.banana4apps.halloween.candies.Card[6];
        player_hand = new com.banana4apps.halloween.candies.Card[6];
        enemy_table = new com.banana4apps.halloween.candies.Card[4];
        player_table = new com.banana4apps.halloween.candies.Card[4];

        float drop_x = (width - (com.banana4apps.halloween.candies.Card.Width*1.1f + 60)*4)/2;
        player_positions = new Rectangle[4];
        for (int i = 0; i < 4; i++)
            player_positions[i] = new Rectangle(drop_x + 30 + i*(com.banana4apps.halloween.candies.Card.Width*1.1f + 60) , height/2 - 5 - com.banana4apps.halloween.candies.Card.Height*1.1f,
                    com.banana4apps.halloween.candies.Card.Width*1.1f, com.banana4apps.halloween.candies.Card.Height*1.1f);


        distributeCard();


        font = new BitmapFont(Gdx.files.internal("font.fnt"));
//        font.getData().setScale(1);

        Ai = new com.banana4apps.halloween.candies.AI(player_hand, enemy_hand);
        GiveCards();
    }

    private boolean givingCards;
    private boolean collectingCards;

    boolean moveDeck = false;
    boolean splitCards = false;
    boolean removeDeck = false;
    boolean cardsSet = false;

    float dt_x, dt_y;
    private int current_card = 0;

    boolean countingCards;

    com.banana4apps.halloween.candies.Card cur_card;
    int current_card_i;

    public void GiveCards(){
        dt_y = height/2 - com.banana4apps.halloween.candies.Card.Height/2;
        dt_x = width;

        givingCards = true;

        splitCards = false;
        removeDeck = false;
        cardsSet = false;
        moveDeck = true;

        played_cards = 0;
        current_card = 0;
        cur_card = null;

        Ai = new com.banana4apps.halloween.candies.AI(player_hand, enemy_hand);
    }
    public void CollectCards(){
        moveDeck = true;
        collectingCards = true;
        dt_x = -com.banana4apps.halloween.candies.Card.Width;
    }
    public void CountCards(){
        countingCards = true;
        current_card_i = 0;
    }

    private void collectCards(){
        if (moveDeck)
        {
            dt_x += 10;
                for (int i = 0; i<6; i++)
                {
                    if (player_hand[i].x < dt_x) {
                    player_hand[i].setTargetPosition(dt_x + 2f, dt_y);
                    player_hand[i].update(); }

                    if (enemy_hand[i].x < dt_x) {
                    enemy_hand[i].setTargetPosition(dt_x, dt_y);
                    enemy_hand[i].update(); }
                }

            if (dt_x > width*1.5f)
            {
                if (endGame)
                {
                    if (win) {
                        ScreenManager.showGame = false;
                        manager.changeScreen(manager.winScreen);
                        manager.winScreen.show(manager, Player_Score + Enemy_Score);
                    } else
                    {
                        manager.changeScreen(manager.housesScreen);
                        manager.gameScreen = new GameScreen(manager);
                        manager.showGame = false;
                    }
                        return;
                }

                cards_stack = new com.banana4apps.halloween.candies.Card[18];
                for (int level = 0; level < 6; level++)
                    for (int type = 0; type < 3; type++) {
                        cards_stack[level + type * 6] = new com.banana4apps.halloween.candies.Card(type, level);
                        cards_stack[level + type * 6].setPosition(-com.banana4apps.halloween.candies.Card.Width, -com.banana4apps.halloween.candies.Card.Height, false);
                    }
                shuffleArray(cards_stack);

                enemy_hand = new com.banana4apps.halloween.candies.Card[6];
                player_hand = new com.banana4apps.halloween.candies.Card[6];
                enemy_table = new com.banana4apps.halloween.candies.Card[4];
                player_table = new com.banana4apps.halloween.candies.Card[4];

                distributeCard();
                collectingCards = false;
                GiveCards();
            }
            return;
        }
    }
    private void giveCards() {

        if (moveDeck)
        {
            dt_x += ((width/2 - com.banana4apps.halloween.candies.Card.Width/2) - dt_x)/10;
            if (Math.abs((width/2 - com.banana4apps.halloween.candies.Card.Width/2) - dt_x) < 1)
            {
                dt_x = width/2 - com.banana4apps.halloween.candies.Card.Width/2;
                moveDeck = false;
                splitCards = true;
            }
            return;
        }


        if (splitCards)
        {
            if (cardsSet)
                if (!enemy_hand[current_card].update())
                    if (!player_hand[current_card].update()) {
                        player_hand[current_card].fl = false;
                        current_card++;
                    }

            if (!cardsSet)
            {
                for (int i = 0; i < 6; i++)
                {
                    player_hand[i].setPosition(dt_x, dt_y, true);
                    enemy_hand[i].setPosition(dt_x, dt_y, false);
                    int cards_x = (width - 6*(com.banana4apps.halloween.candies.Card.Width + 5))/2;
                    player_hand[i].setTargetPosition(cards_x + i*(com.banana4apps.halloween.candies.Card.Width + 5), 0);
                    enemy_hand[i].setTargetPosition( cards_x + i*(com.banana4apps.halloween.candies.Card.Width + 5), height - com.banana4apps.halloween.candies.Card.Height);
                }
                cardsSet = true;
            }


            if (current_card == 6)
            {
                splitCards = false;
                removeDeck = true;
            }
            return;
        }


        if (removeDeck)
        {
            dt_x--;
            dt_x -= ((width/2 - com.banana4apps.halloween.candies.Card.Width/2) - dt_x)/10;
            if (dt_x < -com.banana4apps.halloween.candies.Card.Width)
            {
                givingCards = false;
                removeDeck = false;
            }
            return;
        }
    }
    private void countCards(){
        com.banana4apps.halloween.candies.Card p = player_table[current_card_i];
        com.banana4apps.halloween.candies.Card e = enemy_table[current_card_i];

        if (p.Fight(e) < 0) {
            e = player_table[current_card_i];
            p = enemy_table[current_card_i];
        }

        p.setTargetPosition(e.x, e.y);
        if (e.turn()) {
            if (player_table[current_card_i] == p) {
                Player_Score++;
            }
            else Enemy_Score++;
            current_card_i++;
        }

        if (Player_Score == 5) WinGame();
        if (Enemy_Score == 5) LoseGame();

        if (current_card_i > 3) {
            countingCards = false;
            CollectCards();
        }
    }

    boolean endGame = false;
    boolean win = false;
    public void WinGame()
    {
        countingCards = false;
        CollectCards();
        win = true;
        endGame = true;
    }

    public void LoseGame()
    {
        countingCards = false;
        CollectCards();
        win = false;
        endGame = true;
    }

    public void update(OrthographicCamera camera) {

        if (givingCards) {
            giveCards();
            return;
        }

        if (collectingCards) {
            collectCards();
            return;
        }

        if (countingCards) {
            countCards();
            return;
        }

        boolean touched = (Gdx.input.isTouched());
        if (touched) {
            Vector3 touchPoint = new Vector3();
            touchPoint = camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (cur_card == null)
                for (int i = 0; i < 6; i++)
                    if (!player_hand[i].played && player_hand[i].box.contains(new Vector2(touchPoint.x, touchPoint.y))) {
                        cur_card = player_hand[i];
                        current_card_i = i;
                        cur_card.drag = true;
                    }

            if (cur_card != null && cur_card.drag) {
                float tx = 0, ty = 0;
                player_hand_target = -1;

                for (int i = 0; i < 4; i++)
                    if (player_positions[i].contains(touchPoint.x, touchPoint.y) && player_table[i] == null)
                        if (enemy_table[i] == null || played_cards >= 2) {
                            tx = player_positions[i].x + player_positions[i].width * 0.05f;
                            ty = player_positions[i].y + player_positions[i].height * 0.05f;
                            player_hand_target = i;
                        }


                if (tx == 0)
                    cur_card.setTargetPosition(touchPoint.x- com.banana4apps.halloween.candies.Card.Width/2, touchPoint.y- com.banana4apps.halloween.candies.Card.Height/2);
                else
                    cur_card.setTargetPosition(tx, ty);

                cur_card.update();
            }
        }

        // Card released
        else if (cur_card != null) {
            // Animate card
            int cards_x = (width - 6 * (com.banana4apps.halloween.candies.Card.Width + 5)) / 2;
            cur_card.setTargetPosition(cards_x + current_card_i * (com.banana4apps.halloween.candies.Card.Width + 5), 0);

            if (player_hand_target != -1)
                cur_card.setTargetPosition(
                        player_positions[player_hand_target].x + player_positions[player_hand_target].width*0.05f,
                        player_positions[player_hand_target].y + player_positions[player_hand_target].height*0.05f);

            if (!cur_card.update()) {
                if (player_hand_target != -1){
                    player_table[player_hand_target] = cur_card;
                    cur_card.played = true;
                    played_cards++;
                    int c = 0;
                    if (played_cards < 3)
                    {
                        c = Ai.decideOffence();
                        PlayCard(c);
                    }
                    else {
                        PlayDCard();
                    }
                }
                cur_card.drag = false;
                cur_card = null;
            }
        }

        if (e_card != null)
            if (!e_card.update())
            {
                enemy_table[e_target] = e_card;
                e_card.played = true;
                e_card = null;

                if (played_cards == 4)
                    endGame();
            }

    }

    private void endGame() {
        CountCards();
    }

    private void PlayDCard() {
        int i = 0;
        for (i = 0; i < 4; i++)
            if (player_table[i] != null)
                if (enemy_table[i] == null) break;

        int c = Ai.decideDefence(player_table[i]);
        e_card = enemy_hand[c];
        e_card.flip();

        float drop_x = (width - (com.banana4apps.halloween.candies.Card.Width*1.1f + 60)*4)/2;
        e_card.setTargetPosition(drop_x + 30 + i*(com.banana4apps.halloween.candies.Card.Width*1.1f + 60), height/2 + 5);
        e_target = i;
    }

    com.banana4apps.halloween.candies.Card e_card; int e_target = 0;
    private void PlayCard(int c) {
        e_card = enemy_hand[c];
        e_card.flip();

        int i = 0;
        for (i = 0; i < 4; i++)
            if (player_table[i] == null)
                if (enemy_table[i] == null) break;

        float drop_x = (width - (com.banana4apps.halloween.candies.Card.Width*1.1f + 60)*4)/2;
        e_card.setTargetPosition(drop_x + 30 + i*(com.banana4apps.halloween.candies.Card.Width*1.1f + 60) + player_positions[0].width * 0.05f,
                height/2 + 5 + player_positions[0].width * 0.05f);
        e_target = i;
    }


    public void draw(SpriteBatch batch)
    {
        batch.draw(TextureFiles.Table, 0, 0, width, width*TextureFiles.Table.getRatio());

        float drop_x = (width - (com.banana4apps.halloween.candies.Card.Width*1.1f + 60)*4)/2;
        batch.draw(TextureFiles.Card_drop, drop_x + 30,                            height/2 + 5, com.banana4apps.halloween.candies.Card.Width*1.1f, com.banana4apps.halloween.candies.Card.Height*1.1f);
        batch.draw(TextureFiles.Card_drop, drop_x + 30 + 1*(com.banana4apps.halloween.candies.Card.Width*1.1f + 60), height/2 + 5, com.banana4apps.halloween.candies.Card.Width*1.1f, com.banana4apps.halloween.candies.Card.Height*1.1f);
        batch.draw(TextureFiles.Card_drop, drop_x + 30 + 2 * (com.banana4apps.halloween.candies.Card.Width * 1.1f + 60), height / 2 + 5, com.banana4apps.halloween.candies.Card.Width * 1.1f, com.banana4apps.halloween.candies.Card.Height * 1.1f);
        batch.draw(TextureFiles.Card_drop, drop_x + 30 + 3 * (com.banana4apps.halloween.candies.Card.Width * 1.1f + 60), height / 2 + 5, com.banana4apps.halloween.candies.Card.Width * 1.1f, com.banana4apps.halloween.candies.Card.Height * 1.1f);

        batch.draw(TextureFiles.Card_drop, drop_x + 30, height / 2 - 5 - com.banana4apps.halloween.candies.Card.Height * 1.1f, com.banana4apps.halloween.candies.Card.Width * 1.1f, com.banana4apps.halloween.candies.Card.Height * 1.1f);
        batch.draw(TextureFiles.Card_drop, drop_x + 30 + 1 * (com.banana4apps.halloween.candies.Card.Width * 1.1f + 60), height / 2 - 5 - com.banana4apps.halloween.candies.Card.Height * 1.1f, com.banana4apps.halloween.candies.Card.Width * 1.1f, com.banana4apps.halloween.candies.Card.Height * 1.1f);
        batch.draw(TextureFiles.Card_drop, drop_x + 30 + 2 * (com.banana4apps.halloween.candies.Card.Width * 1.1f + 60), height / 2 - 5 - com.banana4apps.halloween.candies.Card.Height * 1.1f, com.banana4apps.halloween.candies.Card.Width * 1.1f, com.banana4apps.halloween.candies.Card.Height * 1.1f);
        batch.draw(TextureFiles.Card_drop, drop_x + 30 + 3 * (com.banana4apps.halloween.candies.Card.Width * 1.1f + 60), height / 2 - 5 - com.banana4apps.halloween.candies.Card.Height * 1.1f, com.banana4apps.halloween.candies.Card.Width * 1.1f, com.banana4apps.halloween.candies.Card.Height * 1.1f);


        // Draw Deck
        batch.draw(TextureFiles.Card_Back, dt_x, dt_y, com.banana4apps.halloween.candies.Card.Width, com.banana4apps.halloween.candies.Card.Height);
        for (int i = 5; i >= 0; i--)
        {
            player_hand[i].draw(batch);
            enemy_hand[i].draw(batch);
        }

        if (collectingCards)
            batch.draw(TextureFiles.Card_Back, dt_x, dt_y, com.banana4apps.halloween.candies.Card.Width, com.banana4apps.halloween.candies.Card.Height);

        font.draw(batch, "Wins: " + Player_Score, 100, 100);
        font.draw(batch, "Wins: " + Enemy_Score, 100, height-100);

    }

}
