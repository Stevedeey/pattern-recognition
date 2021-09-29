package ch.welld.patternrecognition.Controllers;

import ch.welld.patternrecognition.Models.Point;
import ch.welld.patternrecognition.Models.Response;
import ch.welld.patternrecognition.Services.SpaceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SpaceController.class)
class SpaceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpaceService mockSpaceService;

    @Test
    void testAddPoint() throws Exception {

        final Response response = new Response("Point has been added to space", new Point(0.0, 0.0));
        when(mockSpaceService.addPoint(new Point(0.0, 0.0))).thenReturn(response);

        final MockHttpServletResponse mockHttpServletResponse = mockMvc.perform(post("/point")
                        .content("{ \"x\": 0.0, \"y\": 0.0 }").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(mockHttpServletResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(mockHttpServletResponse.getContentAsString()).isEqualTo(
                "{\"message\":\"Point has been added to space\",\"point\":{\"x\":0.0,\"y\":0.0}}");

    }


    @Test
    void testDeleteSpace() throws Exception {

        final MockHttpServletResponse response = mockMvc.perform(delete("/space")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.ACCEPTED.value());
        assertThat(response.getContentAsString()).isEqualTo("All points on space has been cleared");
        verify(mockSpaceService).clearAll();
    }

    @Test
    void testGetAllPoints() throws Exception {

        when(mockSpaceService.getAllPoints()).thenReturn(Set.of(new Point(6.0, 3.0)));

        final MockHttpServletResponse response = mockMvc.perform(get("/space")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[{\"x\":6.0,\"y\":3.0}]");
    }

    @Test
    void testGetAllPoints_SpaceServiceReturnsNoItems() throws Exception {
        when(mockSpaceService.getAllPoints()).thenReturn(Collections.emptySet());

        final MockHttpServletResponse response = mockMvc.perform(get("/space")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }


    @Test
    void testGetAllPointsFromEachLineByNumberOfPoints_SpaceServiceReturnsNoItems() throws Exception {

        when(mockSpaceService.getAllPointsFromLinesByNumberOfPoints(3)).thenReturn(Collections.emptyList());

        final MockHttpServletResponse response = mockMvc.perform(get("/lines/{n}", 3)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }


    @Test
    void testGetAllLines_SpaceServiceReturnsNoItems() throws Exception {

        when(mockSpaceService.getAllPointsFromLinesByNumberOfPoints(0)).thenReturn(Collections.emptyList());

        final MockHttpServletResponse response = mockMvc.perform(get("/lines")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }
}