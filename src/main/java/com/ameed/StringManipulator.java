package com.ameed;

public interface StringManipulator {
    String manipulate(String text1, String text2);

    default StringManipulator andThen(StringManipulator next) {
        return new StringManipulatorWrapper(this, next);
    }

    class StringManipulatorWrapper implements StringManipulator {
        private final StringManipulator first;
        private final StringManipulator second;

        private StringManipulatorWrapper(StringManipulator first, StringManipulator second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public String manipulate(String text1, String text2) {
            String resultOf1 = first.manipulate(text1, text2);
            return second.manipulate(resultOf1, text2);
        }
    }
}
