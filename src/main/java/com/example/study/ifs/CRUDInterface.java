package com.example.study.ifs;

import com.example.study.model.network.Header;

public interface CRUDInterface {
    Header create();

    Header read(Long id);

    Header update();

    Header delete(Long id);
}
