package com.bowling.demo.controller;
import com.bowling.demo.Utils.BowlingUtils;
import com.bowling.demo.model.Frames;
import com.bowling.demo.model.Response;
import com.bowling.demo.model.Score;
import com.bowling.demo.service.BowlingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping(path = "/bowling")
public class BowlingController {

    private static final Logger log = LoggerFactory.getLogger(BowlingController.class);

    @Autowired
    private BowlingService bowlingService;

    @GetMapping(value = "/new")
    public ResponseEntity<Response<String>> startNewPlayer() {
        String playerId = bowlingService.startNewPlayer();
        return ResponseEntity.status(HttpStatus.OK).body(new Response<String>(playerId));

    }

    @PostMapping(value = "/{playerid}/score", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<String>> submitScore(@RequestBody Frames allframes,
            @PathVariable(value = "playerid") String playerId) {

        log.debug("playerId  : " + playerId);
        log.info("frames size : " + allframes.getAllframes().length);

        if (bowlingService.submitScore(allframes, playerId)) {
            return ResponseEntity.status(HttpStatus.OK).body(new Response<String>(BowlingUtils.MSG_SUCCESS));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
        body(new Response<String>("There is no player with given id."));
    }

    @GetMapping(value = "/{id}/score", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getPlayerScore(@PathVariable(value = "id") String id) {

        Score bowlingScore = bowlingService.getScore(id);
        if (bowlingScore == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<String>("There is no player with given id."));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Response<Score>(bowlingScore));
    }

}
