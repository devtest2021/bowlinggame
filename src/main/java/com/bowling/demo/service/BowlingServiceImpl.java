package com.bowling.demo.service;

import java.util.HashMap;
import java.util.UUID;

import com.bowling.demo.model.Frame;
import com.bowling.demo.model.Frames;
import com.bowling.demo.model.Score;

import org.springframework.stereotype.Component;

@Component
public class BowlingServiceImpl implements BowlingService {

    private HashMap<String, Frames> bowlingGameMap = new HashMap<>();
    Frame[] allFrames;

    @Override
    public String startNewPlayer() {
        // Generating unique playerId for the game 
        String playerId = UUID.randomUUID().toString();
        if (bowlingGameMap.containsKey(playerId)) { 
            // To avoid having same id for different players
            playerId = UUID.randomUUID().toString();
        }
        bowlingGameMap.put(playerId, new Frames());
        return playerId;
    }

    @Override
    public Boolean submitScore(Frames frames, String id) {
        
        if (!bowlingGameMap.containsKey(id)) {
            return false;
        }
        Frames bowlingFrames = bowlingGameMap.get(id);
        bowlingFrames.setAllframes(frames.getAllframes());
        return true;
    }

    @Override
    public Score getScore(String id) {
        // TODO Auto-generated method stub
        return null;
    }

}
