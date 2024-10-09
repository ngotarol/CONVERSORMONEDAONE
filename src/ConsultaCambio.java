import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaCambio {

    public Moneda buscaMoneda(String baseCode){
        String apiKey = "77a13061fbbe7904fa9c43d5";
        String apiURL = "https://v6.exchangerate-api.com/v6/"+apiKey+"/latest/";
        URI direction =  URI.create(apiURL+baseCode);

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(direction).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            return new Gson().fromJson(json,Moneda.class);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
