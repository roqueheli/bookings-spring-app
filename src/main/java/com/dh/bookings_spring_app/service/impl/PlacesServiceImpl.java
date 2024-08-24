package com.dh.bookings_spring_app.service.impl;

import com.dh.bookings_spring_app.dto.GeocodingService;
import com.dh.bookings_spring_app.entities.Addresses;
import com.dh.bookings_spring_app.entities.Categories;
import com.dh.bookings_spring_app.entities.Images;
import com.dh.bookings_spring_app.entities.PlaceService;
import com.dh.bookings_spring_app.entities.Places;
import com.dh.bookings_spring_app.entities.PlacesRRSS;
import com.dh.bookings_spring_app.entities.RRSS;
import com.dh.bookings_spring_app.entities.Rooms;
import com.dh.bookings_spring_app.entities.Services;
import com.dh.bookings_spring_app.exception.ResourceNotFoundException;
import com.dh.bookings_spring_app.repository.AddressesRepository;
import com.dh.bookings_spring_app.repository.CategoriesRepository;
import com.dh.bookings_spring_app.repository.ImagesRepository;
import com.dh.bookings_spring_app.repository.PlaceRRSSRepository;
import com.dh.bookings_spring_app.repository.PlaceServiceRepository;
import com.dh.bookings_spring_app.repository.PlacesRepository;
import com.dh.bookings_spring_app.repository.RRSSRepository;
import com.dh.bookings_spring_app.repository.RoomsRepository;
import com.dh.bookings_spring_app.repository.ServicesRepository;
import com.dh.bookings_spring_app.service.IPlacesService;

import jakarta.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class PlacesServiceImpl implements IPlacesService {
    @Autowired
    private GeocodingService geocodingService;
    @Autowired
    private PlacesRepository placesRepository;
    @Autowired
    private AddressesRepository addressesRepository;
    @Autowired
    private ImagesRepository imagesRepository;
    @Autowired
    private PlaceServiceRepository placeServiceRepository;
    @Autowired
    private CategoriesRepository categoryRepository;
    @Autowired
    private RoomsRepository roomsRepository;
    @Autowired
    private ServicesRepository servicesRepository;
    @Autowired
    private RRSSRepository rrssRepository;
    @Autowired
    private PlaceRRSSRepository placeRRSSRepository;
    @Autowired
    private EntityManager entityManager;

    public PlacesServiceImpl(AddressesRepository addressesRepository, ImagesRepository imagesRepository,
            PlaceServiceRepository placeServiceRepository, RoomsRepository roomsRepository,
            ServicesRepository servicesRepository, GeocodingService geocodingService,
            RRSSRepository rrssRepository, PlaceRRSSRepository placeRRSSRepository) {
        this.addressesRepository = addressesRepository;
        this.imagesRepository = imagesRepository;
        this.placeServiceRepository = placeServiceRepository;
        this.roomsRepository = roomsRepository;
        this.servicesRepository = servicesRepository;
        this.geocodingService = geocodingService;
        this.rrssRepository = rrssRepository;
        this.placeRRSSRepository = placeRRSSRepository;
    }

    @Override
    public Places save(Places place) {
        if (placesRepository.findPlacesByName(place.getName().trim()).isPresent()) {
            throw new ResourceNotFoundException("Place already exists");
        }

        Addresses address = place.getAddress();

        if (address.getAddress_id() == null) {
            address = addressesRepository.save(address);
        } else {
            try {
                address = addressesRepository.findById(address.getAddress_id())
                        .orElseThrow(() -> new ResourceNotFoundException("Address not found"));
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }

        // Geocodificar la dirección para obtener la ubicación
        String addressLocation = place.getAddress().getStreet() + " " + place.getAddress().getStreetNum() + ", "
                + place.getAddress().getCity() + ", "
                + place.getAddress().getZip();
        String location = geocodingService.getLocationFromAddress(addressLocation);

        // Asignar la ubicación obtenida al place
        place.setLocation(location);
        place.setAddress(address);

        // Validar y asociar categoría
        Categories category = place.getCategory();
        if (category == null || category.getCategory_id() == null) {
            throw new ResourceNotFoundException("Category is required");
        }

        category = categoryRepository.findById(category.getCategory_id())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        place.setCategory(category);

        // Guardar primero el place
        Places savedPlace = new Places();
        savedPlace.setName(place.getName());
        savedPlace.setPhone(place.getPhone());
        savedPlace.setEmail(place.getEmail());
        savedPlace.setCalification(place.getCalification());
        savedPlace.setCategory(category);
        savedPlace.setAddress(place.getAddress());
        savedPlace.setLocation(location);
        placesRepository.save(savedPlace);

        // Guardar imágenes
        Set<Images> images = place.getImages();
        for (Images image : images) {
            image.setPlace(savedPlace);
        }
        imagesRepository.saveAll(images);

        Set<Rooms> rooms = place.getRooms();
        for (Rooms room : rooms) {
            room.setPlace(savedPlace);
        }
        roomsRepository.saveAll(rooms);

        // Guardar las relaciones con Service
        Set<PlaceService> placeServices = place.getPlaceServices();
        for (PlaceService placeService : placeServices) {
            Services existingService = servicesRepository.findById(placeService.getService().getService_id())
                    .orElseThrow(() -> new RuntimeException("Service not found"));
            placeService.setPlace(savedPlace);
            placeService.setService(existingService); // Asegurarse de que el servicio existe

            placeServiceRepository.save(placeService);
        }

        // Procesar las RRSS
        Set<PlacesRRSS> placeRRSSSet = new HashSet<>();
        for (PlacesRRSS placeRRSS : place.getPlacesRRSSs()) {
            RRSS rrss = rrssRepository.findById(placeRRSS.getRrss().getRrssId())
                    .orElseThrow(() -> new ResourceNotFoundException("RRSS not found"));

            placeRRSS.setPlace(savedPlace);
            placeRRSS.setRrss(rrss);
            placeRRSSSet.add(placeRRSS);
        }

        // Guardar las relaciones de RRSS con el lugar
        placeRRSSRepository.saveAll(placeRRSSSet);
        savedPlace.setPlacesRRSSs(placeRRSSSet);

        savedPlace.setImages(images);
        savedPlace.setRooms(rooms);
        savedPlace.setPlaceServices(placeServices);
        return savedPlace;
    }

    @Override
    public void update(Places updatedPlace) {
        // Buscar el lugar existente
        Places existingPlace = placesRepository.findById(updatedPlace.getPlace_id())
                .orElseThrow(() -> new ResourceNotFoundException("Place not found"));

        Addresses address = updatedPlace.getAddress();
        try {
            address = addressesRepository.findById(address.getAddress_id())
                    .orElseThrow(() -> new ResourceNotFoundException("Address not found"));
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }
        addressesRepository.save(updatedPlace.getAddress());
        existingPlace.setAddress(updatedPlace.getAddress());

        // Geocodificar la dirección para obtener la ubicación
        String addressLocation = updatedPlace.getAddress().getStreet() + " " + updatedPlace.getAddress().getStreetNum() + ", "
                + updatedPlace.getAddress().getCity() + ", "
                + updatedPlace.getAddress().getZip();
        String location = geocodingService.getLocationFromAddress(addressLocation);

        // Asignar la ubicación obtenida al place
        existingPlace.setLocation(location);

        // Validar y asociar categoría
        Categories category = updatedPlace.getCategory();
        if (category == null || category.getCategory_id() == null) {
            throw new ResourceNotFoundException("Category is required");
        }

        category = categoryRepository.findById(category.getCategory_id())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        existingPlace.setCategory(category);

        existingPlace.setName(updatedPlace.getName());
        existingPlace.setPhone(updatedPlace.getPhone());
        existingPlace.setEmail(updatedPlace.getEmail());
        existingPlace.setCalification(updatedPlace.getCalification());
        existingPlace.setCategory(category);
        placesRepository.save(existingPlace);

        // Guardar imágenes
        Set<Images> images = updatedPlace.getImages();
        imagesRepository.deleteAll(existingPlace.getImages()); // Eliminar imágenes actuales
        for (Images image : images) {
            image.setPlace(existingPlace);
        }
        imagesRepository.saveAll(images);

        Set<Rooms> rooms = updatedPlace.getRooms();
        roomsRepository.deleteAll(existingPlace.getRooms());
        for (Rooms room : rooms) {
            room.setPlace(existingPlace);
        }
        roomsRepository.saveAll(rooms);

        // Guardar las relaciones con Service
        Set<PlaceService> placeServices = updatedPlace.getPlaceServices();
        placeServiceRepository.deleteAll(existingPlace.getPlaceServices());
        for (PlaceService placeService : placeServices) {
            Services existingService = servicesRepository.findById(placeService.getService().getService_id())
                    .orElseThrow(() -> new RuntimeException("Service not found"));
            placeService.setPlace(existingPlace);
            placeService.setService(existingService); // Asegurarse de que el servicio existe
            placeServiceRepository.save(placeService);
        }

        // Procesar las RRSS
        Set<PlacesRRSS> placeRRSSSet = updatedPlace.getPlacesRRSSs();
        placeRRSSRepository.deleteAll(existingPlace.getPlacesRRSSs());
        for (PlacesRRSS placeRRSS : placeRRSSSet) {
            RRSS existingRrss = rrssRepository.findById(placeRRSS.getRrss().getRrssId())
                    .orElseThrow(() -> new ResourceNotFoundException("RRSS not found"));
            placeRRSS.setPlace(existingPlace);
            placeRRSS.setRrss(existingRrss);
            placeRRSSRepository.save(placeRRSS);
        }
    }

    @Override
    public Optional<Places> findById(Integer id) {
        return placesRepository.findById(id);
    }

    @Override
    public void delete(Integer id) throws ResourceNotFoundException {
        if (placesRepository.findById(id).isPresent()) {
            placesRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("place not found");
        }
    }

    @Override
    public List<Places> findAll() {
        return placesRepository.findAll();
    }

    public Optional<Places> findPlacesByName(String name) {
        return placesRepository.findPlacesByName(name);
    }

    @Override
    public List<Places> getRandomProducts() {
        return placesRepository.findRandomProducts();
    }

    public GeocodingService getGeocodingService() {
        return geocodingService;
    }

    public void setGeocodingService(GeocodingService geocodingService) {
        this.geocodingService = geocodingService;
    }

    public PlacesRepository getPlacesRepository() {
        return placesRepository;
    }

    public void setPlacesRepository(PlacesRepository placesRepository) {
        this.placesRepository = placesRepository;
    }

    public AddressesRepository getAddressesRepository() {
        return addressesRepository;
    }

    public void setAddressesRepository(AddressesRepository addressesRepository) {
        this.addressesRepository = addressesRepository;
    }

    public ImagesRepository getImagesRepository() {
        return imagesRepository;
    }

    public void setImagesRepository(ImagesRepository imagesRepository) {
        this.imagesRepository = imagesRepository;
    }

    public PlaceServiceRepository getPlaceServiceRepository() {
        return placeServiceRepository;
    }

    public void setPlaceServiceRepository(PlaceServiceRepository placeServiceRepository) {
        this.placeServiceRepository = placeServiceRepository;
    }

    public CategoriesRepository getCategoryRepository() {
        return categoryRepository;
    }

    public void setCategoryRepository(CategoriesRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public RoomsRepository getRoomsRepository() {
        return roomsRepository;
    }

    public void setRoomsRepository(RoomsRepository roomsRepository) {
        this.roomsRepository = roomsRepository;
    }

    public ServicesRepository getServicesRepository() {
        return servicesRepository;
    }

    public void setServicesRepository(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    public RRSSRepository getRrssRepository() {
        return rrssRepository;
    }

    public void setRrssRepository(RRSSRepository rrssRepository) {
        this.rrssRepository = rrssRepository;
    }

    public PlaceRRSSRepository getPlaceRRSSRepository() {
        return placeRRSSRepository;
    }

    public void setPlaceRRSSRepository(PlaceRRSSRepository placeRRSSRepository) {
        this.placeRRSSRepository = placeRRSSRepository;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
