package com.michael.photopickerdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.a720.picture.lib.PictureSelector;
import com.a720.picture.lib.config.PictureConfig;
import com.a720.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

public class PhotoPickerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GridImageAdapter gridImageAdapter;
    private List<LocalMedia> selectList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPicPicker();
    }

    /**
     * 处理公共图片选择PicturePickerListener选择结果回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择
                    selectList = PictureSelector.obtainMultipleResult(data);
                    gridImageAdapter.setList(selectList);
                    gridImageAdapter.notifyDataSetChanged();
//                    Log.i(Constants.TAG, "onActivityResult:" + selectList.size());
                    break;
            }
        }
    }

    /**
     * 初始化图片选择器
     */
    private void initPicPicker() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        FullyGridLayoutManager manager = new FullyGridLayoutManager(PhotoPickerActivity.this, 4, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        PicturePickerListener picturePickerListener = new PicturePickerListener(this, selectList);
        gridImageAdapter = new GridImageAdapter(PhotoPickerActivity.this, picturePickerListener);
        gridImageAdapter.setList(selectList);
        gridImageAdapter.setSelectMax(Constants.maxSelectNum);
        recyclerView.setAdapter(gridImageAdapter);
        gridImageAdapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                PictureSelector.create(PhotoPickerActivity.this).externalPicturePreview(position, selectList);// 预览图片
            }
        });
    }
}
