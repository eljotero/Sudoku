package org.example.view;

import javafx.scene.control.TextFormatter;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class InputFilter implements UnaryOperator<TextFormatter.Change> {
    @Override
    public TextFormatter.Change apply(TextFormatter.Change change) {
        String input = change.getControlNewText();
        if (input.matches("[1-9]")) {
            return change;
        } else if (change.isDeleted()) {
            change.setText("");
            return change;
        } else {
            return null;
        }
    }

    @Override
    public <V> Function<V, TextFormatter.Change> compose(Function<? super V, ? extends TextFormatter.Change> before) {
        return UnaryOperator.super.compose(before);
    }

    @Override
    public <V> Function<TextFormatter.Change, V> andThen(Function<? super TextFormatter.Change, ? extends V> after) {
        return UnaryOperator.super.andThen(after);
    }
}
