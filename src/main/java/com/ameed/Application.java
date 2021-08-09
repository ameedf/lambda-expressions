package com.ameed;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        List<StringManipulator> manipulators = createManipulators();
        String message = "This is a string manipulator";
        for (StringManipulator manipulator : manipulators) {
            String result = manipulator.manipulate(message, "---");
            System.out.println(result);
        }
    }

    private static List<StringManipulator> createManipulators() {
        StringManipulator addToMiddle = (text1, text2) -> text1.substring(0, text1.length() / 2)
                + text2 + text1.substring(text1.length() / 2);

        StringManipulator spaceReplacer = new SpaceReplacerManipulator();

        return List.of(
                String::concat,                     // 1. concat text1 and text2
                addToMiddle,                        // 2. add text2 to the middle of text1
                spaceReplacer,                      // 3. convert the SPACES in text1 to text2
                spaceReplacer.andThen(addToMiddle)  // 4. "3" and then "2"
        );
    }

}
