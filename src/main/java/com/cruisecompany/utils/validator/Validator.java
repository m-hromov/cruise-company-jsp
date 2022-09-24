package com.cruisecompany.utils.validator;

public interface Validator<T> {
    boolean validate(T obj);
}
