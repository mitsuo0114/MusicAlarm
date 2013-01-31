package jp.fedom.android.musicalarm.item;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

/**
 * dummy comment.
 * TODO:update comment
 * @author taka
 */
public class ConfigPreference {
    /** dummy comment. TODO:update comment */
    private final SharedPreferences preference;

    /** dummy comment. TODO:update comment */
    private static final String PREFERENCE_KEY = "musicalarm_config";

    /** dummy comment. TODO:update comment */
    private static final int ITEM_NUM = 8;

    /** dummy comment. TODO:update comment */
    private static final String TAG = "ConfigPreference";

    /**
     * dummy comment.
     * TODO:update comment
     * @param argPreference prefeence
     */
    public ConfigPreference(final SharedPreferences argPreference) {
        this.preference = argPreference;
    }

    /**
     * dummy comment.
     * TODO:update comment
     * @return list if under ITEM_NUM, padding inialized item.
     */
    public final List<ConfigItem> loadConfigItems() {
        ArrayList<ConfigItem> items;
        final String jsonData = preference.getString(PREFERENCE_KEY, "");

        if (jsonData == null || jsonData.isEmpty()) {
            items = new ArrayList<ConfigItem>();
        } else {
            try {
                items = (ArrayList<ConfigItem>) ConfigItem.JsonParser.getInstance().parse(jsonData);
            } catch (JSONException e) {
                items = new ArrayList<ConfigItem>();
            }
        }
        final int appendItemCount = ITEM_NUM - items.size();
        for (int i = 0; i < appendItemCount; i++) {
            items.add(new ConfigItem());
        }
        return items;
    }

    /**
     * dummy comment.
     * TODO:update comment
     * @param items items
     */
    public final void saveConfigItems(final List<ConfigItem> items) {
        if (items != null) {
            Editor editor = preference.edit();
            try {
                editor.putString(PREFERENCE_KEY, ConfigItem.JsonGenerator.getInstance().genJson(items));
                editor.apply();
            } catch (JSONException e) {
                // do nothing
                Log.w(TAG, "SAVE_PREFERMCE_ERROR");
            }
        }
    }

}