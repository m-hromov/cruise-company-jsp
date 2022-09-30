package com.cruisecompany.util.validator;

public interface Validator<T> {
    boolean validate(T obj);
}
