package com.a720.picture.lib.observable;


/**
 * author：a720
 * project：PictureSelector
 * package：com.a720.picture.lib.observable
 * email：893855882@qq.com
 * data：17/1/16
 */
public interface SubjectListener {
    void add(ObserverListener observerListener);

    void remove(ObserverListener observerListener);
}
