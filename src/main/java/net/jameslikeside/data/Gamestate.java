package net.jameslikeside.data;

public enum Gamestate {

    LOBBY, STARTING, INGAME, ENDED;

    private static Gamestate currentstate;

    public static void setGamestate(Gamestate state){
        currentstate = state;
    }

    public static Gamestate getCurrentGamestate(){
        return currentstate;
    }

}
