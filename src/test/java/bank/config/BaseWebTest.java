package bank.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles({"accountConfig"})
@EnableWebMvc
public abstract class BaseWebTest<DTO> {
    @Autowired
    protected MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    protected abstract String feature();


    public void testList(final Integer size, final Integer page) throws Exception {
        mockMvc.perform(get(String.format("/api/%s?size=%d&page=%d", feature(), size, page)))
                .andExpect(status().isOk())
                .andReturn();
    }


    public void testGetById(final String id) throws Exception {
        mockMvc.perform(get(String.format("/api/%s/%s", feature(), id)))
                .andExpect(status().isOk())
                .andReturn();
    }

    public void testPut(final Object id, final DTO requestBody, final ResultMatcher status) throws Exception {
        mockMvc.perform(put(String.format("/api/%s/%s", feature(), id))
                        .content(objectMapper.writeValueAsString(requestBody))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status)
                .andReturn();
    }

    public void testPost(final DTO requestBody, final ResultMatcher status) throws Exception {
        mockMvc.perform(post(String.format("/api/%s", feature()))
                        .content(objectMapper.writeValueAsString(requestBody))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status)
                .andReturn();
    }

    public void testDelete(final String id) throws Exception {
        mockMvc.perform(delete(String.format("/api/%s/%s", feature(), id)))
                .andExpect(status().isOk())
                .andReturn();
    }

    private MockHttpServletRequestBuilder post(final String url) {
        return MockMvcRequestBuilders.post(url).secure(false);
    }

    private MockHttpServletRequestBuilder put(final String url) {
        return MockMvcRequestBuilders.put(url).secure(false);
    }

    private MockHttpServletRequestBuilder delete(final String url) {
        return MockMvcRequestBuilders.delete(url).secure(false);
    }

    private MockHttpServletRequestBuilder get(final String url) {
        return MockMvcRequestBuilders.get(url).secure(false);
    }
}