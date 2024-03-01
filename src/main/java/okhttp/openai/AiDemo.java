package okhttp.openai;

import java.io.IOException;

/**
 * @author：李晓楠 时间：2023/4/3 17:27
 */
public class AiDemo {
    public static void main(String[] args) {
        OpenAIClient aiClient = new OpenAIClient("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");
        try {
            String retun = aiClient.generateText("您好啊");
            System.out.println("回应的==="+retun);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
