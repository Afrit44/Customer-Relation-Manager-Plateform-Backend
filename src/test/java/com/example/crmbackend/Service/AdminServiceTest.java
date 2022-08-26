package com.example.crmbackend.Service;

import com.example.crmbackend.DTO.AdminDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.example.crmbackend.DTO.AdminDTO.*;
import static com.example.crmbackend.Model.Gender.*;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    @Test
    public void shouldSaveAdminWithSuccess() throws Exception {

        AdminDTO expectedAdmin = AdminDTO.builder()
//                .adminEmail("Afrit@gmail.com")
                .departement("IT")
                .firstName("Karim")
                .lastName("Afrit")
                .adress("Bahdha el hamas")
                .gender(MALE)
                .password("123456789")
                //Please change the Email everytime you run the test otherwise it will raise exception email should be unique !!
                .email("Afrittt@gmail.com")
                .build();

        AdminDTO savedAdmin = adminService.save(expectedAdmin);
        assertNotNull(savedAdmin);
//        assertEquals(expectedAdmin.getAdminEmail(), savedAdmin.getAdminEmail());
        assertEquals(expectedAdmin.getEmail(), savedAdmin.getEmail());
        assertEquals(expectedAdmin.getDepartement(), savedAdmin.getDepartement());
        assertEquals(expectedAdmin.getFirstName(), savedAdmin.getFirstName());
        assertEquals(expectedAdmin.getLastName(), savedAdmin.getLastName());
        assertEquals(expectedAdmin.getGender(), savedAdmin.getGender());
        assertEquals(expectedAdmin.getPassword(), savedAdmin.getPassword());
        assertEquals(expectedAdmin.getAdress(), savedAdmin.getAdress());
        assertEquals(expectedAdmin.getClass(), savedAdmin.getClass());

    }


}