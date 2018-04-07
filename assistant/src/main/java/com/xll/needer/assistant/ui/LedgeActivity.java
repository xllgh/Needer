package com.xll.needer.assistant.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.xll.needer.assistant.R;
import com.xll.needer.assistant.Utils.TitleBar;
import com.xll.needer.assistant.databinding.ActivityLedgeBinding;
import com.xll.needer.assistant.eventhandler.LedgeHandler;
import com.xll.needer.assistant.bean.LedgeModel;

public class LedgeActivity  extends BaseActivity {
    private LedgeModel model;

    private boolean recordLedgeVisibility = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLedgeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_ledge);
        model = new LedgeModel();
        LedgeHandler handler = new LedgeHandler(this, model);
        binding.setLedgeHandler(handler);
        binding.setLedgeModel(model);
        setTitle(binding.getRoot(), model);
    }

    private void setTitle(View rootView, final LedgeModel model) {
        TitleBar bar = new TitleBar(rootView);
        bar.setTitle(R.string.ledge);
        bar.setIconLeftListener(new TitleBar.Action() {
            @Override
            public void call() {
                LedgeActivity.this.finish();
            }
        });
        bar.setIconRightListener(new TitleBar.Action() {
            @Override
            public void call() {
                model.setRecordLedgeVisibility(recordLedgeVisibility);
                recordLedgeVisibility = !recordLedgeVisibility;
            }
        });
    }
}
