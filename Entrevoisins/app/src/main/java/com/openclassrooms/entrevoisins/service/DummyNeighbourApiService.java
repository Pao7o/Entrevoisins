package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     *
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public List<Neighbour> getFavListNeighbour() {
        List<Neighbour> favNeighbours = new ArrayList<>();
        for (Neighbour i: neighbours){
            if (i.getIsfav()==true){
                favNeighbours.add(i);
            }
        }
        return favNeighbours;
    }

    @Override
    public void addFav(Neighbour neighbour) {
        Neighbour n =neighbours.get(neighbours.indexOf(neighbour));
        n.setIsfav(true);
        neighbour.setIsfav(true);
    }

    @Override
    public void DeleteFav(Neighbour neighbour) {
        Neighbour n =neighbours.get(neighbours.indexOf(neighbour));
        n.setIsfav(false);
        neighbour.setIsfav(false);
    }


}

