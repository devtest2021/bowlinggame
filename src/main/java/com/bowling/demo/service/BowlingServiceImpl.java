package com.bowling.demo.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

import com.bowling.demo.model.Frame;
import com.bowling.demo.model.Frames;
import com.bowling.demo.model.Score;

import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class BowlingServiceImpl implements BowlingService {

    private static final Logger log = LoggerFactory.getLogger(BowlingServiceImpl.class);

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
        
        if (!bowlingGameMap.containsKey(id)) {
            return null;
        }
        Frames frames = bowlingGameMap.get(id);
        allFrames = frames.getAllframes();
        int totalScore = 0;
        int frameScores[] = new int[10];

        Arrays.fill(frameScores, 0);
        for (int i = 0; i < allFrames.length; i++) {
            Frame frame = frames.getFrame(i);
            int frameScore = getFrameScore(i);
            if (frame.isScored()) {
                totalScore += frameScore;
                frameScores[i] = totalScore;
            }
            log.debug("totalScore : " + totalScore + "\n frameScores : " + frameScores[i]);
        }
        return new Score(totalScore, frameScores);
    }

    public int getFrameScore(int frameNo) {
        Frame frame = allFrames[frameNo];
        if (frame == null) {
            return 0;
        }
        
        if (frame.isStrike()) {
            if (strikeBonus(frameNo) == -1) { // Waiting for next frame(s) to be scored
                frame.setScored(false);
                return 0;
            }
            frame.setScored(true);
            return 10 + strikeBonus(frameNo);
        }
        if (frame.isSpare()) {
            if (spareBonus(frameNo) == -1) { // waiting for next frame to be scored
                frame.setScored(false);
                return 0;
            }
            frame.setScored(true);
            return 10 + spareBonus(frameNo);
        }
        frame.setScored(true);
        return frame.getFirstTry() + frame.getSecondTry();
    }

    private int spareBonus(int frameNo) {
        if (frameNo == 10) {
            Frame lastFrame = allFrames[frameNo];
            return lastFrame.getThirdTry();
        }
        Frame nextFrame = allFrames[frameNo + 1];
        return nextFrame == null ? -1 : nextFrame.getFirstTry();
    }

    private int strikeBonus(int frameNo) {
        if (frameNo == 9) {
            Frame lastFrame = allFrames[frameNo];
            return lastFrame.getSecondTry() + lastFrame.getThirdTry();
        }
        Frame nextFrame = allFrames[frameNo + 1];
        if (nextFrame == null) {
            return -1;
        }
        if (nextFrame.isStrike()) {
            if (frameNo == 9) {
                // next frame is the last frame which is special
                return 10 + nextFrame.getSecondTry(); 
            }
            Frame nextNextFrame = allFrames[frameNo + 1];
            return nextNextFrame == null ? -1 : 10 + nextNextFrame.getFirstTry();
        }
        return nextFrame.getFirstTry() + nextFrame.getSecondTry();
    }
}
