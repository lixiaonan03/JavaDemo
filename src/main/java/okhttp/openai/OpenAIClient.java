package okhttp.openai;

/**
 * @author：李晓楠 时间：2023/4/3 17:25
 */
import okhttp.OkHttpClientsUtils;
import okhttp3.*;

import java.io.IOException;

public class OpenAIClient {
    private final OkHttpClient httpClient = OkHttpClientsUtils.getClient();
    private final String apiKey;

    public OpenAIClient(String apiKey) {
        this.apiKey = apiKey;
    }

    public String generateText(String prompt) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");


        RequestBody body = RequestBody.create(mediaType, "{\"prompt\": \"" + prompt + "\",\"temperature\": 0.7,\"max_tokens\": 100}");


        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/engines/davinci/completions")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + apiKey)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                return responseBody.split("\"text\": \"")[1].split("\", \"")[0];
            } else {
                throw new IOException("Unexpected response code " + response.code() + " - " + response.body().string());
            }
        }
    }
}
