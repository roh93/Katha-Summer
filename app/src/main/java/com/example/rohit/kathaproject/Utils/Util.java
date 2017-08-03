package com.example.rohit.kathaproject.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.View;

import com.example.rohit.kathaproject.R;
import com.example.rohit.kathaproject.constants.AppConsts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_100_days_wrk);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_boat_transportation);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_clg);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_dam);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_deforestation);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_hspitl);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_loan);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_pollution);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_poverty);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_water_bodies);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_wrkplce_security);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_electric);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_general_purpose_water);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_health);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_price_hike);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_terrorism);
        imageList.add(icon);
        return imageList;
    }


    public static List<Bitmap> getAllMapImages(Context context) {
        List<Bitmap> imageList = new ArrayList<>();
        Bitmap icon;
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_anganwadi);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_college);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_community_hall);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_electricity_street_light);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_electricity_transformer);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_graveyard);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_hospital);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_panchayat);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_playground);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_police_station);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_school);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_telephone_booth);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_telephone_tower);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_temple);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_water_source_other);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_water_source_pond);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_water_source_tank);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_water_source_tap);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_water_source_tubewell);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_water_source_well);
        imageList.add(icon);
        return imageList;
    }

    public static List<Bitmap> getBasicAmenitiesMapImages(Context context) {
        List<Bitmap> imageList = new ArrayList<>();
        Bitmap icon;
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_anganwadi);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_college);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_community_hall);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_hospital);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_panchayat);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_police_station);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_school);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_atm);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_bank);
        imageList.add(icon);
        return imageList;
    }

    public static List<Bitmap> getWaterSourcesMapImages(Context context) {
        List<Bitmap> imageList = new ArrayList<>();
        Bitmap icon;
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_water_source_tubewell);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_water_source_well);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_water_source_pond);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_water_source_tank);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_water_source_tap);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_water_source_other);
        imageList.add(icon);
        return imageList;
    }

    public static List<Bitmap> getTransportationMapImages(Context context) {
        List<Bitmap> imageList = new ArrayList<>();
        Bitmap icon;
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_railway_station);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_railway_line);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_bus_stop);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_ferry_ghat);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_motorable_road);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_non_motorable_road);
        imageList.add(icon);
        return imageList;
    }


    public static List<Bitmap> getDefecationMapImages(Context context) {
        List<Bitmap> imageList = new ArrayList<>();
        Bitmap icon;
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_toilet);
        imageList.add(icon);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_defecation);
        imageList.add(icon);
        return imageList;
    }


    public static Bitmap takeScreenshot(View rootView) {
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }

    public static void saveBitmap(Bitmap bitmap,String path) {
        File imagePath = new File(Environment.getExternalStorageDirectory() + path);
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getIssueListName(){
        List<String> issueList = new ArrayList<>();
        issueList.add(AppConsts.ic_addctn);
        issueList.add(AppConsts.ic_agriculture);
        issueList.add(AppConsts.ic_animal_husbandry);
        issueList.add(AppConsts.ic_crruption);
        issueList.add(AppConsts.ic_drinking_water);
        issueList.add(AppConsts.ic_drought);
        issueList.add(AppConsts.ic_elephant);
        issueList.add(AppConsts.ic_fisheries);
        issueList.add(AppConsts.ic_food);
        issueList.add(AppConsts.ic_froest);
        issueList.add(AppConsts.ic_frst_2);
        issueList.add(AppConsts.ic_handicrafts);
        issueList.add(AppConsts.ic_hygine);
        issueList.add(AppConsts.ic_income);
        issueList.add(AppConsts.ic_industry);
        issueList.add(AppConsts.ic_irrigation);
        issueList.add(AppConsts.ic_landownershipdoc);
        issueList.add(AppConsts.ic_law_n_ordr);
        issueList.add(AppConsts.ic_lnsslidess);
        issueList.add(AppConsts.ic_malnutrition);
        issueList.add(AppConsts.ic_mtorble_road);
        issueList.add(AppConsts.ic_natrl_calamities);
        issueList.add(AppConsts.ic_non_motorabl_road);
        issueList.add(AppConsts.ic_rural_house);
        issueList.add(AppConsts.ic_savings);
        issueList.add(AppConsts.ic_school);
        issueList.add(AppConsts.ic_toilet);
        issueList.add(AppConsts.ic_unemployment);
        issueList.add(AppConsts.ic_100_working_days);
        issueList.add(AppConsts.ic_boat_transportation);
        issueList.add(AppConsts.ic_clg);
        issueList.add(AppConsts.ic_dam);
        issueList.add(AppConsts.ic_deforestation);
        issueList.add(AppConsts.ic_hspitl);
        issueList.add(AppConsts.ic_loan);
        issueList.add(AppConsts.ic_pollution);
        issueList.add(AppConsts.ic_poverty);
        issueList.add(AppConsts.ic_water_bodies);
        issueList.add(AppConsts.ic_workplace_security);
        issueList.add(AppConsts.ic_electric);
        issueList.add(AppConsts.ic_general_purpose_water);
        issueList.add(AppConsts.ic_health);
        issueList.add(AppConsts.ic_price_hike);
        issueList.add(AppConsts.ic_terrorism);
        return issueList;
    }

    private void createDirectoryAndSaveFile(Bitmap imageToSave, String fileName) {

        File direct = new File(Environment.getExternalStorageDirectory() + "/Sanlap/MapOutput/");

      /*  if (!direct.exists()) {
            File wallpaperDirectory = new File(Environment.getExternalStorageDirectory() + "/Sanlap/MapInput/);
            wallpaperDirectory.mkdirs();
        }*/

        File file = new File(new File("/sdcard/DirName/"), fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            imageToSave.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void saveArrayList(ArrayList<String> arrayList, String path) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(arrayList);
            out.close();
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getMostCommonString(List<String> mostVoted){
        Map<String,Integer> counts = new HashMap<>();
        for(String s : mostVoted) {
            if(counts.containsKey(s)) {
                counts.put(s,counts.get(s)+1);
            } else {
                counts.put(s,1);
            }
        }
        Map.Entry<String,Integer> e = Collections.max(counts.entrySet(),new Comparator<Map.Entry<String,Integer>>() {

            @Override
            public int compare(Map.Entry<String,Integer> o1, Map.Entry<String,Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }

        });
        return e.getKey();
    }


}
