package com.example.crmbackend.Service.Strategy;

import java.io.InputStream;

public interface Strategy<T> {
    T savePhoto(Integer id, InputStream photo, String title) throws Exception;
}
