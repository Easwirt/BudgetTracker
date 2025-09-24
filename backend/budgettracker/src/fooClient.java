import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpClient()
public interface fooClient extends GenericClient {
    @GetExchange()
    String getString();
}