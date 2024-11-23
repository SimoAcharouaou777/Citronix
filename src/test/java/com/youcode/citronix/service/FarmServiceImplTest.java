package com.youcode.citronix.service;

import com.youcode.citronix.dto.FarmDTO;
import com.youcode.citronix.entity.Farm;
import com.youcode.citronix.mapper.FarmMapper;
import com.youcode.citronix.repository.FarmRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FarmServiceImplTest {

    @Mock
    private FarmRepository farmRepository;

    @InjectMocks
    private FarmServiceImpl farmService;

    private FarmMapper farmMapper;

    private Farm farm;
    private FarmDTO farmDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        farmMapper = FarmMapper.INSTANCE;
        farm = new Farm();
        farm.setId(1L);
        farm.setName("Green Farm");
        farm.setLocation("Casablanca");
        farm.setSize(100.0);
        farm.setCreationDate(LocalDate.of(2020,1,1));
        farmDTO = farmMapper.farmToFarmDTO(farm);
    }

    @Test
    void createFarmSuccessfully() {
        when(farmRepository.save(any(Farm.class))).thenReturn(farm);
        FarmDTO createdFarm = farmService.createFarm(farmDTO);
        assertNotNull(createdFarm);
        assertEquals(farmDTO.getName(), createdFarm.getName());
        assertEquals(farmDTO.getLocation(), createdFarm.getLocation());
        verify(farmRepository, times(1)).save(any(Farm.class));
    }

    @Test
    void getAllFarms_shouldReturnListOfFarms() {
        when(farmRepository.findAll()).thenReturn(Arrays.asList(farm));
        List<FarmDTO> result = farmService.getAllFarms();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(farmDTO.getName(), result.get(0).getName());
        verify(farmRepository, times(1)).findAll();
    }

    @Test
    void getFarmById_shouldReturnFarm_whenItExists() {
        when(farmRepository.findById(1L)).thenReturn(Optional.of(farm));
        FarmDTO found = farmService.getFarmById(1L);
        assertNotNull(found);
        assertEquals(farmDTO.getName(), found.getName());
        verify(farmRepository, times(1)).findById(1L);
    }

    @Test
    void getFarmById_ShouldThrowException_whenFarmDoesNotExist(){
        when(farmRepository.findById(1L)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> farmService.getFarmById(1L));
        assertEquals("Farm not found", exception.getMessage());
        verify(farmRepository, times(1)).findById(1L);
    }

    @Test
    void updateFarm_ShouldReturnUpdatedFarm_WhenFarmExists() {
        when(farmRepository.findById(1L)).thenReturn(Optional.of(farm));
        when(farmRepository.save(any(Farm.class))).thenReturn(farm);
        farmDTO.setName("Updated Farm");
        FarmDTO updatedFarm = farmService.updateFarm(1L, farmDTO);
        assertNotNull(updatedFarm);
        assertEquals("Updated Farm", updatedFarm.getName());
        verify(farmRepository, times(1)).findById(1L);
        verify(farmRepository, times(1)).save(any(Farm.class));
    }

    @Test
    void updateFarm_ShouldThrownException_WhenFarmDoesNotExist(){
        when(farmRepository.findById(1L)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> farmService.updateFarm(1L, farmDTO));
        assertEquals("Farm not found", exception.getMessage());
        verify(farmRepository, times(1)).findById(1L);
    }

    @Test
    void deleteFarm_ShouldCallDeleteById_WhenFarmExists() {
        doNothing().when(farmRepository).deleteById(1L);
        farmService.deleteFarm(1L);
        verify(farmRepository, times(1)).deleteById(1L);
    }

    @Test
    void searchFarms_ShouldReturnMatchingFarm() {
        when(farmRepository.searchFarmsWithCriteria(anyString(), anyString(), anyDouble(), anyDouble(), any(LocalDate.class))).thenReturn(Arrays.asList(farm));
        List<FarmDTO> result = farmService.searchFarms("Green Farm", "California", 50.0, 150.0, LocalDate.of(2020,1,1));
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(farmDTO.getName(), result.get(0).getName());
        verify(farmRepository, times(1)).searchFarmsWithCriteria(anyString(), anyString(), anyDouble(), anyDouble(), any(LocalDate.class));
    }
}