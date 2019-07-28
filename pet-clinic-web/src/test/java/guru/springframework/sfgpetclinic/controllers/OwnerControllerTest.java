package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Extend class with JUnit5 MockitoExtensions that will initialize mocks
@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    // Mock concrete ownerService
    @Mock
    OwnerService ownerService;

    // set up controller with the mock injected to it
    @InjectMocks
    OwnerController ownerController;

    Set<Owner> owners;

    // Mock controller to test with
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();

        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());

        // standalone setup is very light setup (does not boot up server),
        // for each test method it initialize a mock environment for controller
        mockMvc = MockMvcBuilders
                .standaloneSetup(ownerController)
                .build();


    }

    @Test
    void listOwners() throws Exception {

        when(ownerService.findAll()).thenReturn(owners);

        // import static for get() org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
        // import static for status() org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
        mockMvc.perform(get("/owners"))
                //.andExpect(status().is(200)); // or when the insert is done:
                .andExpect(status().isOk()) // response status 2xx
                // test return proper view from controller
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void listOwnersByIndex() throws Exception {

        when(ownerService.findAll()).thenReturn(owners);

        // import static for get() org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
        // import static for status() org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
        // use different path that listOwners() and test is:
        mockMvc.perform(get("/owners/index"))
                //.andExpect(status().is(200)); // or when the insert is done:
                .andExpect(status().isOk()) // response status 2xx
                // test return proper view from controller
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void findOwners() throws Exception {

        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("notImplemented"));
        // verify interaction with mock ownerService, should not interact with ownerService
        verifyZeroInteractions(ownerService);
    }
}