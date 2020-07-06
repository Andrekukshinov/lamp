package kukshinov.application.lamp.controller;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kukshinov.application.lamp.entity.Country;
import kukshinov.application.lamp.entity.Room;
import kukshinov.application.lamp.repository.CountryRepo;
import kukshinov.application.lamp.repository.LampRepo;
import kukshinov.application.lamp.repository.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RoomController {
    private RoomRepo roomRepo;
    private LampRepo lampRepo;

    private CountryRepo countryRepo;


    public RoomController(RoomRepo roomRepo, LampRepo lampRepo, CountryRepo countryRepo) {
        this.roomRepo = roomRepo;
        this.lampRepo = lampRepo;
        this.countryRepo = countryRepo;
    }

    public RoomController() {
    }

    private String getUserCountryName(String userIp) {
        Gson gson = new Gson();
        RestTemplate restTemplate = new RestTemplate();
        Type type = new TypeToken<Map<String, String>>() {
        }.getType();
        String cntryNameJSON = restTemplate.getForObject("http://ip-api.com/json/" + "93.170.30.6" /*userIp8*/, String.class);
        Map<String, String> result = gson.fromJson(cntryNameJSON, type);
        Optional<String> userCountry = Optional.ofNullable(result.get("country"));
        return userCountry.orElse("Not found");

    }

    @PostMapping("create")
    public Room createNewRoom(@RequestBody Room room) {
        Optional<Country> dbCountry = countryRepo.findById(room.getCountry().getId());
        if (dbCountry.isPresent()) {
            return roomRepo.save(room);
        }
        return null;
    }

    @GetMapping()
    public Iterable<Room> getAllRooms() {
        return roomRepo.findAll();
    }

    @GetMapping("/countries")
    public Iterable<Country> getAllCountries() {
        return countryRepo.findAll();
    }

    /**
     * Lets enter the room if user IP equals to country IP
     *
     * @param room_id
     * @param request
     * @return
     */
    @GetMapping("room/{room_id}")
    public Room findByCountryName(HttpServletRequest request, @PathVariable String room_id) {
        Optional<Room> optionalRoom = roomRepo.findById(Long.parseLong(room_id));
        Room room;
        if (optionalRoom.isPresent()) {
            room = optionalRoom.get();
            String userIp = request.getRemoteAddr();
            String userCountry = getUserCountryName(userIp);
            if (userCountry.equalsIgnoreCase(room.getCountry().getCountryName())) {

                return room;
            }
        }
        return null;
    }


    public RoomRepo getRoomRepo() {
        return roomRepo;
    }

    @Autowired
    public void setRoomRepo(RoomRepo roomRepo) {
        this.roomRepo = roomRepo;
    }

    public LampRepo getLampRepo() {
        return lampRepo;
    }

    @Autowired
    public void setLampRepo(LampRepo lampRepo) {
        this.lampRepo = lampRepo;
    }

    public CountryRepo getCountryRepo() {
        return countryRepo;
    }

    @Autowired
    public void setCountryRepo(CountryRepo countryRepo) {
        this.countryRepo = countryRepo;
    }
}
