import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.exception.ServiceException;
import com.mjc.school.service.impl.NewsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class NewsServiceImplTest {
    NewsModel news1 = new NewsModel(1L, "Blablabla", "Conten content fawfwaffwwf", LocalDateTime.now(), LocalDateTime.now(), 2L);
    NewsModel news2 = new NewsModel(2L, "Blablabla dwad", "Contenrt content fawawddawad dawdawdfwaffwwf", LocalDateTime.now(), LocalDateTime.now(), 3L);
    NewsDto newsDto = new NewsDto("Blablaadwff", "awdawdwad afwawfwagfwa whgrhrshsrhsrh serhshs ", 3L);

    private NewsServiceImpl newsService;
    @Mock
    private NewsRepository<NewsModel> newsRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        newsService = new NewsServiceImpl();
    }

    @Test
    void create_ValidNewsDto_ReturnsCreatedNewsDto() {

        NewsModel newsModel = new NewsModel();
        newsModel.setId(1L);
        newsModel.setTitle("Blablaadwff");
        newsModel.setContent("awdawdwad afwawfwagfwa whgrhrshsrhsrh serhshs ");
        newsModel.setAuthorId(3L);

        when(newsRepository.create(any())).thenReturn(newsModel);

        NewsDto createdNewsDto = newsService.create(newsDto);

        assertNotNull(createdNewsDto);
    }

    @Test
    void create_InvalidNewsDto_ThrowsServiceException() {

        NewsDto newsDto = new NewsDto("Dw", "A", 5L);
        when(newsRepository.create(any())).thenReturn(null);

        assertThrows(ServiceException.class, () -> newsService.create(newsDto));
    }

    @Test
    void readAll_ReturnsListOfNewsDto() {
        List<NewsModel> newsModels = Arrays.asList(news1, news2);
        when(newsRepository.readAll()).thenReturn(newsModels);

        List<NewsDto> newsDtos = newsService.readAll();

        assertNotNull(newsDtos);
    }

    @Test
    void readById_ExistingId_ReturnsNewsDto() {
        Long id = 10L;
        when(newsRepository.readById(id)).thenReturn(news1);

        NewsDto newsDto = newsService.readById(id);

        assertNotNull(newsDto);
    }

    @Test
    void readById_NonExistingId_ThrowsServiceException() {
        Long id = 33L;
        when(newsRepository.readById(id)).thenReturn(null);

        assertThrows(ServiceException.class, () -> newsService.readById(id));
    }

    @Test
    void update_ValidNewsDto_ReturnsUpdatedNewsDto() {
        NewsDto newsDtoToUdpate = new NewsDto(5L, "AWdawdwadawd", "Cawfawfawfawfawf", 4L);
        NewsModel newsToUpdate = new NewsModel(5L, "Blablabla dwad", "Contenrt content fawawddawad dawdawdfwaffwwf", LocalDateTime.now(), LocalDateTime.now(), 3L);

        when(newsRepository.update(any())).thenReturn(newsToUpdate);

        NewsDto updatedNewsDto = newsService.update(newsDtoToUdpate);

        assertNotNull(updatedNewsDto);
        assertNotEquals(newsToUpdate.getLastUpdateTime(), updatedNewsDto.getLastUpdateTime());
    }

    @Test
    void deleteById_ExistingId_ReturnsTrue() {
        Long id = 16L;
        when(newsRepository.deleteById(id)).thenReturn(true);

        Boolean result = newsService.deleteById(id);

        assertTrue(result);
    }

    @Test
    void deleteById_NonExistingId_ReturnsFalse() {
        Long id = 66L;
        when(newsRepository.deleteById(id)).thenReturn(false);

        assertThrows(ServiceException.class, () -> newsService.deleteById(id));
    }
}
