package com.deepak.horsetrack.service;

import com.deepak.horsetrack.model.Horse;
import com.deepak.horsetrack.model.RaceStatus;
import com.deepak.horsetrack.repository.HorseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorseServiceImpl implements HorseService{

    @Autowired
    private HorseRepository horseRepository;
    @Override
    public void setWinnerHorse(int hourseNumber) {

        List<Horse> horses = horseRepository.findAll();

        horses.stream().forEach( h -> {
                if(h.getRaceStatus() == RaceStatus.WON)
                    h.setRaceStatus(RaceStatus.LOST);
                if (h.getHorseNumber() == hourseNumber)
                    h.setRaceStatus(RaceStatus.WON);

                    horseRepository.save(h);
        });
    }

    @Override
    public boolean isValidHorseNumber(int horseNumber) {
        return horseRepository.findByHorseNumberEquals(horseNumber) == null ? false : true;
    }

    @Override
    public String getHorseName(int hourseNumber) {
        return horseRepository.findByHorseNumberEquals(hourseNumber).getHorseName();
    }

    @Override
    public int getHorseOdds(int hourseNumber) {
        return horseRepository.findByHorseNumberEquals(hourseNumber).getOdds();
    }

    @Override
    public boolean isHorseWinner(int hourseNumber) {
       return horseRepository.findByHorseNumberEquals(hourseNumber).getRaceStatus() == RaceStatus.WON ? true : false;
    }
}
