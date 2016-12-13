package com.example.edejesus1097.innaflash;

import android.provider.BaseColumns;

/**
 * Created by edejesus1097 on 12/7/16.
 */

public class FlashCard extends Object {

    public FlashCard()
    {}


    public static abstract class FlashInfo implements BaseColumns
    {
        public static final String CONCEPT = "concept";
        public static final String DEFINITION = "definition";
        public static final String DATEABASE_NAME = "flash_info";
        public static final String TABLE_NAME = "Flash_Table";
        public static final String CARD_SUB = "card_sub";
    }

}
