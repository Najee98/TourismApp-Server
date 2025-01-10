package com.spu.TourismApp.Services;

import com.spu.TourismApp.Models.Tour;
import com.spu.TourismApp.Shared.Dto.Tour.CreateTourDto;
import com.spu.TourismApp.Shared.Dto.Tour.TourDto;

import java.util.List;

public interface TourService {
    List<TourDto> getAllTours();
    TourDto getTourById(Integer id);
    Tour createTour(CreateTourDto createTourDto);
    Tour updateTour(Integer id, CreateTourDto request);
    void bookUserForTour(Integer tourId, List<Integer> userIds);
    void deleteTour(Integer id);
}
