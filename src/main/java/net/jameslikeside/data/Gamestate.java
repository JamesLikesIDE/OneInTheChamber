package net.jameslikeside.data;

public enum Gamestate {

    // This class stores all the GameStates

    LOBBY, STARTING, INGAME, ENDED;

    private static Gamestate currentstate;

    public static void setGamestate(Gamestate state){
        currentstate = state;
    }

    public static Gamestate getCurrentGamestate(){
        return currentstate;
    }

}
