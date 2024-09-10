import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.ling.CoreAnnotations.*;
import edu.stanford.nlp.util.CoreMap;

import java.util.*;

public class SimpleChatbot {

    
    private static StanfordCoreNLP setupPipeline() {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,sentiment");
        return new StanfordCoreNLP(props);
    }

    
    private static String getResponse(String input, StanfordCoreNLP pipeline) {
        Annotation document = new Annotation(input);
        pipeline.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap sentence : sentences) {
            String text = sentence.get(CoreAnnotations.TextAnnotation.class);
            String sentiment = sentence.get(CoreAnnotations.SentimentClass.class);

            
            if (text.contains("hello") || text.contains("hi")) {
                return "Hello! How can I assist you today?";
            } else if (text.contains("your name")) {
                return "I am a chatbot created to assist you.";
            } else if (text.contains("weather")) {
                return "I can’t check the weather, but I can answer other questions.";
            } else if (text.contains("bye") || text.contains("goodbye")) {
                return "Goodbye! Have a great day!";
            } else {
                return "I’m not sure how to respond to that. Can you please rephrase?";
            }
        }
        return "I didn’t understand your request.";
    }

    public static void main(String[] args) {
        StanfordCoreNLP pipeline = SetupPipeline();
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("Chatbot is ready. Type 'exit' to end the conversation.");

        while (true) {
            System.out.print("You: ");
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Chatbot: Goodbye!");
                break;
            }

            String response = getResponse(input, pipeline);
            System.out.println("Chatbot: " + response);
        }

        scanner.close();
    }
}

