package com.xll.needer.assistant.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xll.needer.assistant.R;
import com.xll.needer.assistant.Utils.DatabaseUtil;
import com.xll.needer.assistant.databinding.ActivityLedgeBinding;
import com.xll.needer.assistant.eventhandler.LedgeHandler;
import com.xll.needer.assistant.viewmodel.LedgeModel;

public class LedgeActivity extends AppCompatActivity {
    private LedgeModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLedgeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_ledge);
        model = new LedgeModel();
        LedgeHandler handler = new LedgeHandler(this, model);
        binding.setLedgeHandler(handler);
        binding.setLedgeModel(model);
        DatabaseUtil.getConsumption(-1, -1);
    }
}
