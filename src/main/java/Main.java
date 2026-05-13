import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите породу на английском:");
        String name = scanner.nextLine();

        CloseableHttpClient client = HttpClients.createDefault();

        String url = "https://dog.ceo/api/breed/" + name + "/images/random";

        HttpGet request = new HttpGet(url);

        CloseableHttpResponse response = client.execute(request);

        ObjectMapper mapper = new ObjectMapper();

        DogAnswer dogAnswer = mapper.readValue(response.getEntity().getContent(), DogAnswer.class);
        String messageDogAnswer = dogAnswer.message;

        if ("success".equals(dogAnswer.status)) {
            request = new HttpGet(messageDogAnswer);
            CloseableHttpResponse image = client.execute(request);

            String[] imageUrl = messageDogAnswer.split("/");
            String nameFile = imageUrl[imageUrl.length - 1];
            System.out.println(nameFile);

            FileOutputStream fos = new FileOutputStream("images/" + nameFile);
            image.getEntity().writeTo(fos);
        } else {
            System.out.println("Такой пароды не существует!");
        }
    }
}
