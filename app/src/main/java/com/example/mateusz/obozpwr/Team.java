package com.example.mateusz.obozpwr;

import java.util.Comparator;

/**
 * Created by Mateusz on 30.08.2018.
 */

public class Team {
    private long pkt;
    private int image;

    public Team(long pkt, int image) {
        this.pkt = pkt;
        this.image = image;
    }

    public long getPkt() {
        return this.pkt;
    }

    public int getImage() {
        return this.image;
    }

    public void setPkt(long pkt) {
        this.pkt = pkt;
    }

    public void setImage(int image) {
        this.image = image;
    }

    class PointComparator implements Comparator<Team> {
        @Override
        public int compare(Team team1, Team team2) {
            long team1pkt = team1.getPkt();
            long team2pkt = team2.getPkt();

            if (team1pkt > team2pkt) {
                return 1;
            } else if (team1pkt < team2pkt) {
                return -1;
            } else {
                return 0;
            }
        }
    }

}
