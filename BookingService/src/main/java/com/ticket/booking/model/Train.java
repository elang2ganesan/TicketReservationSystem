package com.ticket.booking.model;

import java.util.ArrayList;
import java.util.List;

public final class Train {
    private Integer trainNumber;
    private String source;
    private String destination;
    private List<Coach> coachList;

    private Train() {}

    private static Train train = null;

    public static Train getInstance(){

        if(train == null) {
            train = new Train();
            train.setTrainNumber(1234);
            train.setSource("");
            train.setDestination("");
            train.setCoachList(new ArrayList<>());


            Coach sectionA = new Coach();
            sectionA.setAvailableSeats(2);
            sectionA.setTotalBookedSeats(0);
            sectionA.setCoachName("sectionA");
            sectionA.setTotalNoOfSeats(2);

            train.getCoachList().add(sectionA);
            Coach sectionB = new Coach();
            sectionB.setAvailableSeats(1);
            sectionB.setTotalBookedSeats(0);
            sectionB.setCoachName("sectionB");
            sectionB.setTotalNoOfSeats(1);
            train.getCoachList().add(sectionB);
        }

        return train;
    }

    public Integer getTrainNumber() {
        return trainNumber;
    }

    private void setTrainNumber(Integer trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getSource() {
        return source;
    }

    private void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    private void setDestination(String destination) {
        this.destination = destination;
    }

    public List<Coach> getCoachList() {
        return coachList;
    }

    private void setCoachList(List<Coach> coachList) {
        this.coachList = coachList;
    }

}
