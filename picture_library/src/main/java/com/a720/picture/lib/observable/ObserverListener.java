package com.a720.picture.lib.observable;

import com.a720.picture.lib.entity.LocalMedia;
import com.a720.picture.lib.entity.LocalMediaFolder;

import java.util.List;

/**
 * author：a720
 * project：PictureSelector
 * package：com.a720.picture.lib.observable
 * email：893855882@qq.com
 * data：17/1/16
 */
public interface ObserverListener {
    void observerUpFoldersData(List<LocalMediaFolder> folders);

    void observerUpSelectsData(List<LocalMedia> selectMedias);
}
