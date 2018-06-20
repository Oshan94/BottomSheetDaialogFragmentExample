package com.example.oshan.fullscreenbottomsheetexample;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;


import com.example.oshanazbow.fullscreenbottomsheetexample.R;

import java.util.ArrayList;
import java.util.List;


public class FullBottomSheetDialogFragment extends BottomSheetDialogFragment  {

    private BottomSheetBehavior mBehavior;

    private ItemAdapter adapter;
    private List<Food> mFoodList;
    private List<Food> mSearchList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFoodList = createItems();
        mSearchList = createItems();
        adapter = new ItemAdapter(mSearchList);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        View view = View.inflate(getContext(), R.layout.bottom_sheet_layout, null);

        LinearLayout linearLayout = view.findViewById(R.id.root);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
        params.height = getScreenHeight();
        linearLayout.setLayoutParams(params);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        EditText editTextSearch = view.findViewById(R.id.editTextSearch);
        editTextSearch.addTextChangedListener(textWatcher);

        dialog.setContentView(view);
        mBehavior = BottomSheetBehavior.from((View) view.getParent());
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    public List<Food> createItems() {
        ArrayList<Food> items = new ArrayList<>();
        items.add(new Food("Burger", R.drawable.burger));
        items.add(new Food("Cokies", R.drawable.cokies));
        items.add(new Food("Fruit", R.drawable.fruit));
        items.add(new Food("Hotdogs", R.drawable.hotdogs));
        items.add(new Food("Pizza", R.drawable.pizza));
        items.add(new Food("Rice and Curry", R.drawable.rice_and_curry));
        items.add(new Food("Sri Lankan Milk Rice", R.drawable.sri_lankan_milk_rice));
        items.add(new Food("Strawberry", R.drawable.strawbery));
        items.add(new Food("Taco", R.drawable.tacos));
        return items;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }


    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void afterTextChanged(Editable s) {
            search(s.toString().toLowerCase());
        }

    };

    private void search(String searchTerm){
        mSearchList.clear();
        for (Food item : mFoodList){
            if (item.name.toLowerCase().contains(searchTerm)){
                mSearchList.add(item);
            }
        }

        adapter.notifyDataSetChanged();

    }


}
