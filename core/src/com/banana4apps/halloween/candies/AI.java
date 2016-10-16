package com.banana4apps.halloween.candies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class AI {

    protected Card[] player;

    HashMap<Card, ArrayList<Card>> card_can_beat = new HashMap<Card, ArrayList<Card>>();
    HashMap<Card, ArrayList<Card>> card_beaten = new HashMap<Card, ArrayList<Card>>();

    float player_scissors = 1;
    float player_rocks = 1;
    float player_papers = 1;

    protected Card[] my;
    protected int[] my_weight;

    public AI(Card[] p, Card[] m)
    {
        player = new Card[p.length + 5];

        Random rng = new Random();
        for (int i = 0; i < p.length; i++)
        {
            player[i] = p[i];
        }
        for (int i = 0; i < 5; i++)
        {
            player[p.length + i] = new Card(rng.nextInt(3), rng.nextInt(6));
        }

        my = m;
        my_weight = new int[my.length];

        card_can_beat.put(my[0], new ArrayList<Card>());
        card_can_beat.put(my[1], new ArrayList<Card>());
        card_can_beat.put(my[2], new ArrayList<Card>());
        card_can_beat.put(my[3], new ArrayList<Card>());
        card_can_beat.put(my[4], new ArrayList<Card>());
        card_can_beat.put(my[5], new ArrayList<Card>());

        card_beaten.put(p[0], new ArrayList<Card>());
        card_beaten.put(p[1], new ArrayList<Card>());
        card_beaten.put(p[2], new ArrayList<Card>());
        card_beaten.put(p[3], new ArrayList<Card>());
        card_beaten.put(p[4], new ArrayList<Card>());
        card_beaten.put(p[5], new ArrayList<Card>());
    }

    public int decideOffence()
    {
        int i = 0, j = 0;
        for (Card aMy : my) {
            int count_win = 0;

            if (!aMy.played)
            for (Card aPlayer : player)
            {
                int a = aMy.Fight(aPlayer) > 0 ? 1 : 0;
                count_win += a;
                if (aPlayer.played && a > 0)
                {
                    card_can_beat.get(aMy).add(aPlayer);
                    card_beaten.get(aPlayer).add(aMy);
                }
            }

            my_weight[i] = count_win;
            i++;
        }

        return findMax(new ArrayList<Card>());
    }

    public int decideDefence(Card played)
    {
        for (Card var: card_beaten.get(played)) {
            if (!var.played){
                for (int i = 0; i < my.length; i++)
                    if (var == my[i]) return i;
            }
        }

        for (int i = 0; i < my.length; i++)
            if (!my[i].played) return i;

        return 0;
    }

    int findMax(ArrayList<Card> exclude)
    {
        int max = 0;
        int c = 0;
        Card v = my[0];
        for (int i = 0; i < my.length; i++)
        {
            if (exclude.contains(my[i])) continue;
            if (my_weight[i] > max)
            {
                max = my_weight[i];
                c = i;
                v = my[i];
            }
        }

        for (Card played_card: card_can_beat.get(v)) {
            if (card_beaten.get(played_card).size() == 1) {
                exclude.add(v);
                return findMax(exclude);
            }
        }

        return c;
    }
}
