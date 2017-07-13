package com.example.rohit.kathaproject.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.rohit.kathaproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rohit on 10-07-2017.
 */

public class Util {

    public static List<Bitmap> getBitmapList(Context context){
        List<Bitmap> imageList = new ArrayList<>();
        Bitmap icon;
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_addctn);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_agriculture);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_animal_husbandry);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_crruption);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_drinking_water);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_drought);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_elephant);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_fisheries);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_food);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_froest);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_frst_2);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_handicrafts);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_hygine);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_income);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_industry);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_irrigation);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_landownershipdoc);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_law_n_ordr);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_lnsslidess);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_malnutrition);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_mtorble_road);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_natrl_calamities);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_non_motorabl_road);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_rural_house);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_savings);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_school);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_toilet);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_unemployment);
        imageList.add(icon);
        return imageList;
    }

}
