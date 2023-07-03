package com.cg;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KanbanBoardTests {

    @Autowired
    private ModelMapper modelMapper;

    @Test
    void contextLoads() {
        // Ensure that the application context loads successfully
    }

    @Test
    void testModelMapperBean() {
        // Verify that the modelMapper bean is autowired successfully
        Assertions.assertNotNull(modelMapper);
        Assertions.assertTrue(modelMapper instanceof ModelMapper);
    }

    @Test
    void testModelMapperConfigurations() {
        // Check if the modelMapper has the expected configurations or mappings
        // Perform assertions on specific mappings or configurations
    }

    // Add more test cases as needed to cover other functionalities of the application

}
