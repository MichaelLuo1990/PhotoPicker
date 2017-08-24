package com.michael.photopickerdemo;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.a720.picture.lib.PictureSelector;
import com.a720.picture.lib.compress.Luban;
import com.a720.picture.lib.config.PictureConfig;
import com.a720.picture.lib.config.PictureMimeType;
import com.a720.picture.lib.entity.LocalMedia;

import java.util.List;

import static android.R.attr.fragment;

/**
 * Created by michaelluo on 17/6/29.
 *
 * @desc 图片选择器监听
 */

public class PicturePickerListener implements GridImageAdapter.onAddPicClickListener {

    private Activity activity;//需引用fragment对象（PictureSelector.create方法）且回调才生效.forResult(PictureConfig.CHOOSE_REQUEST)
    private List<LocalMedia> selectList;

    public PicturePickerListener(Activity activity, List<LocalMedia> selectList) {
        this.activity = activity;
        this.selectList = selectList;
    }

    @Override
    public void onAddPicClick() {
        // 进入相册 以下是例子：不需要的api可以不写
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                .maxSelectNum(Constants.maxSelectNum)// 最大图片选择数量
                .minSelectNum(Constants.minSelectNum)// 最小选择数量
                .imageSpanCount(Constants.countForRow)// 每行显示个数
                .selectionMode(PictureConfig.MULTIPLE)// 多选-PictureConfig.MULTIPLE or 单选-PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片
                .previewVideo(false)// 是否可预览视频
                .enablePreviewAudio(false) // 是否可播放音频
                .compressGrade(Luban.THIRD_GEAR)// luban压缩档次，默认3档 Luban.FIRST_GEAR、Luban.CUSTOM_GEAR
                .isCamera(true)// 是否显示拍照按钮(预览列表首个)
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .enableCrop(false)// 是否裁剪
                .compress(true)// 是否压缩
                .compressMode(PictureConfig.SYSTEM_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .isGif(false)// 是否显示gif图片
                .openClickSound(false)// 是否开启点击声音
                .selectionMedia(selectList)// 是否传入已选图片
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }
}
