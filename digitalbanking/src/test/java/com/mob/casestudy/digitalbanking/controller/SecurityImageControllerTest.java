package com.mob.casestudy.digitalbanking.controller;

import com.mob.casestudy.digitalbanking.dto.SecurityImagesDto;
import com.mob.casestudy.digitalbanking.entity.SecurityImages;
import com.mob.casestudy.digitalbanking.exception.ImageNotFoundException;
import com.mob.casestudy.digitalbanking.repository.SecurityImageRepository;
import com.mob.casestudy.digitalbanking.service.SecurityImageServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SecurityImageControllerTest {

    @InjectMocks
    SecurityImageController securityImageController;
    @Mock
    SecurityImageServiceImpl securityImageServiceImpl;

    @Test
    void getList() throws Exception {
        SecurityImages securityImages = new SecurityImages();
        List<SecurityImages> list = new ArrayList<>();
        list.add(securityImages);
        List<SecurityImagesDto> expectedList = new ArrayList<>();
        expectedList.add(securityImages.toDto());
        Mockito.when(securityImageServiceImpl.findAll()).thenReturn(list);
        List<SecurityImagesDto> actualList = securityImageController.getList();
        org.assertj.core.api.Assertions.assertThat(expectedList).usingRecursiveComparison().isEqualTo(actualList);
    }

    @Test
    void getList_nullList_throwsException() throws Exception {
        List<SecurityImages> list = null;
        Mockito.when(securityImageServiceImpl.findAll()).thenReturn(list);
        Assertions.assertThrows(ImageNotFoundException.class,()->securityImageController.getList());
    }

    @Test
    void getList_emptyList_throwsException() throws Exception {
        List<SecurityImages> list = new ArrayList<>();
        Mockito.when(securityImageServiceImpl.findAll()).thenReturn(list);
        Assertions.assertThrows(ImageNotFoundException.class,()->securityImageController.getList());
    }
}