package com.ameed;

import java.util.List;
import java.util.function.Consumer;

public class Application {

    public static void main(String[] args) {
        List<StringManipulator> manipulators = createManipulators();
        String message = "This is a string manipulator";
        Consumer<String> consumer = ((Consumer<String>)System.out::println)
                .andThen(s -> System.out.println(s.replace(" ", "*")));
        consumer.accept("A b");
        for (StringManipulator manipulator : manipulators) {
            String result = manipulator.manipulate(message, "---");
            System.out.println(result);
        }
    }

    private static List<StringManipulator> createManipulators() {
        StringManipulator addToMiddle = new StringManipulator() {
            @Override
            public String manipulate(String text1, String text2) {

                return text1.substring(0, text1.length() / 2)
                        + text2 + text1.substring(text1.length() / 2);
            }
        };

        StringManipulator spaceReplacer = new SpaceReplacerManipulator();

        return List.of(
                // 1. concat text1 and text2
                new StringManipulator() {
                    @Override
                    public String manipulate(String text1, String text2) {
                        return text1 + text2;
                    }
                },
                addToMiddle,                     // 2. add text2 to the middle of text1
                spaceReplacer,                   // 3. convert the SPACES in text1 to text2
                new StringManipulator() {
                    @Override
                    public String manipulate(String text1, String text2) {
                        return addToMiddle.manipulate(spaceReplacer.manipulate(text1, text2), text2);
                    }
                }
        );
    }

}
