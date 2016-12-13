package com.example.edejesus1097.innaflash;

import android.provider.BaseColumns;

/**
 * Created by edejesus1097 on 12/11/16.
 */

public class Subject extends Object{


        public Subject()
        {}


        public static abstract class Subject_info implements BaseColumns {
            public static final String TABLE_NAME = "Subjects";
            public static final String Subject = "subject";
            public static final String DATEABASE_NAME = "sub_info";
        }


}
