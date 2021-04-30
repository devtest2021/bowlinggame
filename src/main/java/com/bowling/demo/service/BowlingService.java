package com.bowling.demo.service;

import com.bowling.demo.model.Frames;
import com.bowling.demo.model.Score;

public interface BowlingService {

    String startNewPlayer();

    Boolean submitScore(Frames frame, String id);
    
    Score getScore(String id);

}
