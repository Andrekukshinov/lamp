package kukshinov.application.lamp.controller;

import kukshinov.application.lamp.entity.Lamp;
import kukshinov.application.lamp.entity.Room;
import kukshinov.application.lamp.repository.LampRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SockController {
    @Autowired
    private LampRepo lampRepo;

    public SockController(LampRepo lampRepo) {
        this.lampRepo = lampRepo;
    }

    public SockController() {
    }

    @MessageMapping("/room/lamp/{id}")
    @SendTo("/room/{id}")
    public Lamp changeLampStatus(Room room, @DestinationVariable String id) {
        if(lampRepo.changeLampStatus(room.getLamp().getState(), room.getLamp().getId()) > 0) {
            return room.getLamp();
        }
        else return null;
    }

    public LampRepo getLampRepo() {
        return lampRepo;
    }

    public void setLampRepo(LampRepo lampRepo) {
        this.lampRepo = lampRepo;
    }

}
